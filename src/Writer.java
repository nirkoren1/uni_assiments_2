// Nir koren 316443902
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * this class can write to a file.
 */
public class Writer {
    private DataOutputStream writer;
    /**
     * constructor.
     */
    public Writer() {

    }
    /**
     * open the file.
     * @param fileName the file name
     */
    public void openFile(String fileName) {
        try {
            this.writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    /**
     * close the file.
     */
    public void closeFile() {
        try {
            this.writer.close();
        } catch (java.io.IOException e) {
            System.out.println("File not found");
        }
    }
    /**
     * write to the file.
     * @param line the line
     */
    public void writeLine(String line) {
        try {
            this.writer.writeUTF(line);
        } catch (java.io.IOException e) {
            System.out.println("File not found");
        }
    }
}
