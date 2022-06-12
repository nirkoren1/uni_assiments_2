package ass7;

//import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 * this class Construct a database of hypernym relations.
 */
public class CreateHypernymDatabase {
    private static List<RegexPattern> patterns = new ArrayList<>();
    /**
     * main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
        patterns.add(new RegexPattern("<np>[\\w ]+</np> such as "));
        patterns.add(new RegexPattern("such <np>[\\w ]+</np> as "));
        patterns.add(new RegexPattern("<np>[\\w ]+</np>( ,)? including "));
        patterns.add(new RegexPattern("<np>[\\w ]+</np>( ,)? especially "));
        patterns.add(new RegexPattern("<np>[\\w ]+</np>( ,)? which is ((an example|a kind|a class) of )?"));
        HashMapCreator hmc = new HashMapCreator();
//        File file = new File("src/ass7/data/corpus");
//        for(String fileNames : file.list()) System.out.println(fileNames);
        StringBuilder theWholeFile = new StringBuilder();
        Reader reader = new Reader();
        reader.openFile("src/ass7/corpus/mbta.com_mtu.pages_0.possf2");
        String line = reader.readNextLine();
        while (line != null) {
            theWholeFile.append(line);
            line = reader.readNextLine();
        }
        reader.closeFile();
        for (RegexPattern pattern :patterns) {
            hmc.addHypernyms(pattern.getAllRelations(theWholeFile.toString()));
        }
//        for (String key: hmc.getHashMap().keySet()) {
//            System.out.println(key + ": " + hmc.getHashMap().get(key));
//        }
        System.out.println("the size of the hashmap is: " + hmc.getHashMap().size());
    }
}
