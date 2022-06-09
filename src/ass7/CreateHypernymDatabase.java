package ass7;

//import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

/**
 * this class Construct a database of hypernym relations.
 */
public class CreateHypernymDatabase {
    /**
     * get the hypernym of a word.
     * @param line the line
     * @return the hypernym of a word
     */
    public static String getTheFirstWord(String line) {
        Pattern p = Pattern.compile("<np>\\w+");
        Matcher m = p.matcher(line);
        if (m.find()) {
            return m.group().toLowerCase().substring(4);
        }
        return null;
    }
    /**
     * main method.
     * @param args the arguments
     */
    public static void main(String[] args) {
//        File file = new File("src/ass7/data/corpus");
//        for(String fileNames : file.list()) System.out.println(fileNames);
        StringBuilder theWholeFile = new StringBuilder();
        Reader reader = new Reader();
        reader.openFile("src/ass7/data/corpus/mbta.com_mtu.pages_0.possf2");
        String line = reader.readNextLine();
        while (line != null) {
            theWholeFile.append(line);
            line = reader.readNextLine();
        }
        reader.closeFile();
        NpSuchAsNp npSuchAsNp = new NpSuchAsNp();
        System.out.println(npSuchAsNp.getAllRelations(theWholeFile.toString()).size());

    }
}
