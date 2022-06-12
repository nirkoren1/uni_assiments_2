package ass7;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class represents a regex pattern.
 */
public class RegexPattern {
    private String patternString;
    /**
     * constructor.
     * @param prefix the prefix
     */
    public RegexPattern(String prefix) {
        this.patternString = prefix + "((<np>[\\w ]+</np>)( , <np>[\\w ]+</np>)*(( , )? ?(or|and) ?<np>[\\w ]+</np>)?)+";
    }

    /**
     * turn the line to a list of strings.
     * @param line the line
     * @return the list
     */
    public List<String> turnToList(String line) {
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
     * get all the relations.
     * @param text the text
     * @return the relations
     */
    public List<List<String>> getAllRelations(String text) {
        List<List<String>> out = new java.util.ArrayList<>();
        Pattern pattern = Pattern.compile(this.patternString);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            out.add(turnToList(text.substring(matcher.start(), matcher.end()) + " "));
        }
        return out;
    }
}
