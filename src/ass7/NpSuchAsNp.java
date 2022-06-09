package ass7;

/**
 * this class create the NpSuchAsNp pattern.
 */
public class NpSuchAsNp extends RegexPattern {
    /**
     * constructor.
     */
    public NpSuchAsNp() {
        super("<np>[\\w ]+</np> such as ");
    }
}
