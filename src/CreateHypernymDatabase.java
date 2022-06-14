// Nir koren 316443902
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * this class Construct a database of hypernym relations.
 */
public class CreateHypernymDatabase {
    private static List<RegexPattern> patterns = new ArrayList<>();
    /**
     * main method.
     * @param args the arguments
     */
    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1] + "\\hypernym_db.txt";
        patterns.add(new RegexPattern("<np>[\\w ]+?</np> such as "));
        patterns.add(new RegexPattern("such <np>[\\w ]+?</np> as "));
        patterns.add(new RegexPattern("<np>[\\w ]+?</np>( ,)? including "));
        patterns.add(new RegexPattern("<np>[\\w ]+?</np>( ,)? especially "));
        patterns.add(new RegexPattern(
                "<np>[\\w ]+?</np>( ,)? which is ((an example|a kind|a class) of )?<np>[\\w ]+?</np>", true));
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
            for (RegexPattern pattern :patterns) {
                hmc.addHypernyms(pattern.getAllRelations(theWholeFile.toString()));
            }
        }
        // create the output file
        FileOutputStream out = null;
        HashMap<String, HashMap<String, Integer>> hm = hmc.getHashMap();
        try {
            out = new FileOutputStream(outputPath);
            PrintStream s = new PrintStream(out);
            System.setOut(s);
            for (String key: hm.keySet()) {
                if (hm.get(key).size() < 3) {
                    continue;
                }
                System.out.print(key + ": ");
                int i = 0;
                for (String s1 : hm.get(key).keySet()) {
                    if (i == 0) {
                        System.out.print(s1 + " (" + hm.get(key).get(s1) + ")");
                    } else {
                        System.out.print(", " + s1 + " (" + hm.get(key).get(s1) + ")");
                    }
                    i += 1;
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
