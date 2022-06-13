// Nir koren 316443902
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * this class Construct a database of hypernym relations.
 * and can find the hypernyms of a word.
 */
public class DiscoverHypernym3 {
    private static List<RegexPattern> patterns = new ArrayList<>();

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
        patterns.add(new RegexPattern("<np>[\\w ]+</np> such as "));
        patterns.add(new RegexPattern("such <np>[\\w ]+</np> as "));
        patterns.add(new RegexPattern("<np>[\\w ]+</np>( ,)? including "));
        patterns.add(new RegexPattern("<np>[\\w ]+</np>( ,)? especially "));
        patterns.add(new RegexPattern("<np>[\\w ]+</np>( ,)? which is ((an example|a kind|a class) of )?"));
        HashMapCreator hmc = new HashMapCreator();
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
            for (RegexPattern pattern :patterns) {
                hmc.addHypernyms(pattern.getAllRelations(theWholeFile.toString()));
            }
        }
        HashMap<String, HashMap<String, Integer>> hm = hmc.getHashMap();
        for (String key: hm.get(lemma).keySet()) {
            System.out.println(key + ": (" + hm.get(lemma).get(key) + ")");
        }
    }
}
