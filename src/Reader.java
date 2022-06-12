// Nir koren 316443902
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * this class can read a file and return the next line in the file.
 */
public class Reader {
    private DataInputStream reader;
    private String line;

    /**
     * constructor.
     */
    public Reader() {
    }
    /**
     * open the file.
     * @param fileName the file name
     */
    public void openFile(String fileName) {
        try {
            this.reader = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
        }
    }
    /**
     * close the file.
     */
    public void closeFile() {
        try {
            this.reader.close();
        } catch (java.io.IOException e) {
            System.out.println("File not found");
        }
    }
    /**
     * read the next line.
     * @return the next line
     */
    public String readNextLine() {
        try {
            this.line = this.reader.readLine();
        } catch (java.io.IOException e) {
            System.out.println("File not found");
        }
        return this.line;
    }
}
