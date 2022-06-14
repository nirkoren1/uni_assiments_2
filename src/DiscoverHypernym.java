// Nir koren 316443902
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    private static String postfix =
            "((<np>[\\w ]+?</np>)( , <np>[\\w ]+?</np>)*(( , )? ?(or|and) ?<np>[\\w ]+?</np>)?)+";
    /**
     * compare the number of appearances of two words than the two strings.
     * @param hm the hashmap
     * @param s1 the first string
     * @param s2 the second string
     * @return comparison result
     */
    public static int compareNumOfAppearancesThanLexicografic(TreeMap<String, Integer> hm, String s1, String s2) {
        int num1 = hm.get(s1);
        int num2 = hm.get(s2);
        if (num1 > num2) {
            return 1;
        } else if (num1 < num2) {
            return -1;
        } else {
            return compareLexicographically(s2.toLowerCase(), s1.toLowerCase());
        }
    }
    /**
     * compare the lexicographically of two strings.
     * @param s1 the first string
     * @param s2 the second string
     * @return comparison result
     */
    public static int compareLexicographically(String s1, String s2) {
        if (s1.compareTo(s2) > 0) {
            return 1;
        } else if (s1.compareTo(s2) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
    private static HashMap<String, Integer> valueSort(TreeMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort((o1, o2) -> compareNumOfAppearancesThanLexicografic(hm, o2.getKey(), o1.getKey()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    /**
     * turnToList.
     * @param line the line
     * @param specialCase the special case
     * @return the list
     */
    public static List<String> turnToList(String line, boolean specialCase) {
        List<String> out = new ArrayList<>();
        String[] list = line.split("</np>");
        for (String s : list) {
            Pattern pattern = Pattern.compile("<np>[\\w ]+");
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                out.add(s.substring(matcher.start() + 4, matcher.end()) + "");
            }
        }
        if (specialCase) {
            //swap the first and the last element
            String temp = out.get(0);
            out.set(0, out.get(out.size() - 1));
            out.set(out.size() - 1, temp);
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
        postfix = postfix.replace("[\\w ]+?", "([\\w ]+?|" + lemma + ")");
        prefixes.add("<np>[\\w ]+?</np> such as ");
        prefixes.add("such <np>[\\w ]+?</np> as ");
        prefixes.add("<np>[\\w ]+?</np>( ,)? including ");
        prefixes.add("<np>[\\w ]+?</np>( ,)? especially ");
        prefixes.add("<np>[\\w ]+?</np>( ,)? which is ((an example|a kind|a class) of )?<np>[\\w ]+?</np>");
        File file = new File(inputPath);
        for (String fileNames : file.list()) {
            System.out.println(fileNames);
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
                Pattern pattern;
                if (prefixes.indexOf(prefix) == 4) {
                    pattern = Pattern.compile(prefix);
                } else {
                    pattern = Pattern.compile(prefix + postfix);
                }
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    List<String> allRelations = turnToList(text.substring(matcher.start(), matcher.end()),
                            prefixes.indexOf(prefix) == 4);
                    if (hypernyms.containsKey(allRelations.get(0))) {
                        hypernyms.put(allRelations.get(0), hypernyms.get(allRelations.get(0)) + 1);
                    } else {
                        hypernyms.put(allRelations.get(0), 1);
                    }
                }
            }
        }
        if (hypernyms.size() == 0) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        }
        HashMap<String, Integer> hypernymsSorted = valueSort(hypernyms);
        for (String key: hypernymsSorted.keySet()) {
            System.out.println(key + ": (" + hypernymsSorted.get(key) + ")");
        }
    }
}
