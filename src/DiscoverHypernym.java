// Nir koren 316443902
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class Construct a database of hypernym relations.
 * and can find the hypernyms of a word.
 */
public class DiscoverHypernym {
    private static List<String> prefixes = new ArrayList<>();
    private static TreeMap<String, Integer> hypernyms = new TreeMap<>();
    private static String postfix = "((<np>[\\w ]+</np>)( , <np>[\\w ]+</np>)*(( , )? ?(or|and) ?<np>[\\w ]+</np>)?)+";

    /**
     * turnToList.
     * @param line the line
     * @return the list
     */
    public static List<String> turnToList(String line) {
        List<String> out = new ArrayList<>();
        String[] list = line.split("</np>");
        for (String s : list) {
            Pattern pattern = Pattern.compile("<np>[\\w ]+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                out.add(s.substring(matcher.start() + 4, matcher.end()) + "");
            }
        }
        return out;
    }
    /**
     * main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
        String inputPath = args[0];
        StringBuilder lemmaB = new StringBuilder();
        lemmaB.append(args[1]);
        for (int i = 2; i < args.length; i++) {
            lemmaB.append(" ");
            lemmaB.append(args[i]);
        }
        String lemma = lemmaB.toString();
        postfix = postfix.replace("[\\w ]+", "([\\w ]+|" + lemma + ")");
        prefixes.add("<np>[\\w ]+</np> such as ");
        prefixes.add("such <np>[\\w ]+</np> as ");
        prefixes.add("<np>[\\w ]+</np>( ,)? including ");
        prefixes.add("<np>[\\w ]+</np>( ,)? especially ");
        prefixes.add("<np>[\\w ]+</np>( ,)? which is ((an example|a kind|a class) of )?");
        HashMapCreator hmc = new HashMapCreator();
        File file = new File(inputPath);
        for (String fileNames : file.list()) {
            String fileName = inputPath + "/" + fileNames;
            StringBuilder theWholeFile = new StringBuilder();
            Reader reader = new Reader();
            reader.openFile(fileName);
            String line = reader.readNextLine();
            while (line != null) {
                theWholeFile.append(line);
                line = reader.readNextLine();
            }
            reader.closeFile();
            String text = theWholeFile.toString();
            for (String prefix :prefixes) {
                Pattern pattern = Pattern.compile(prefix + postfix);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    List<String> allRelations = turnToList(text.substring(matcher.start(), matcher.end()));
                    if (hypernyms.containsKey(allRelations.get(0))) {
                        hypernyms.put(allRelations.get(0), hypernyms.get(allRelations.get(0)) + 1);
                    } else {
                        hypernyms.put(allRelations.get(0), 1);
                    }
                }
            }
        }
        for (String key: hypernyms.keySet()) {
            System.out.println(key + ": (" + hypernyms.get(key) + ")");
        }
    }
}
