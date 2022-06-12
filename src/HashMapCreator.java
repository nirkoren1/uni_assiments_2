// Nir koren 316443902
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * this class create the HashMap.
 */
public class HashMapCreator {
    private HashMap<String, HashMap<String, Integer>> hypernym = new HashMap<>();
    /**
     * constructor.
     */
    public HashMapCreator() {

    }
    /**
     * create the hypernym hashmap.
     * @param relations the list of relations
     */
    public void addHypernyms(List<List<String>> relations) {
        for (List<String> relation : relations) {
            String first = relation.get(0);
            if (!hypernym.containsKey(first)) {
                hypernym.put(first, new HashMap<>());
                for (int i = 1; i < relation.size(); i++) {
                    hypernym.get(first).put(relation.get(i), 1);
                }
            } else {
                for (int i = 1; i < relation.size(); i++) {
                    if (hypernym.get(first).containsKey(relation.get(i))) {
                        hypernym.get(first).put(relation.get(i), hypernym.get(first).get(relation.get(i)) + 1);
                    } else {
                        hypernym.get(first).put(relation.get(i), 1);
                    }
                }
            }
            this.hypernym.put(first, sortNumOfAppearances(hypernym.get(first)));
        }
    }

    /**
     * compare the number of appearances of two words than the two strings.
     * @param hm the hashmap
     * @param s1 the first string
     * @param s2 the second string
     * @return comparison result
     */
    public int compareNumOfAppearancesThanLexicografic(HashMap<String, Integer> hm, String s1, String s2) {
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
     * compare lexicographically of two strings.
     * @param s1 the first string
     * @param s2 the second string
     * @return int representing the result
     */
    public int compareLexicographically(String s1, String s2) {
        if (s1.compareTo(s2) > 0) {
            return 1;
        } else if (s1.compareTo(s2) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * sort the hashmap by the number of appearances.
     * @param hm the hashmap
     * @return the sorted hashmap
     */
    private HashMap<String, Integer> sortNumOfAppearances(HashMap<String, Integer> hm) {
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
     * sort the hypernym hashmap.
     * @param hm the hashmap
     * @return the sorted hashmap
     */
    public HashMap<String, HashMap<String, Integer>> sortAlphabetically(HashMap<String, HashMap<String, Integer>> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, HashMap<String, Integer>>> list = new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort((o1, o2) -> compareLexicographically(o1.getKey().toLowerCase(), o2.getKey().toLowerCase()));

        // put data from sorted list to hashmap
        HashMap<String, HashMap<String, Integer>> temp = new LinkedHashMap<>();
        for (Map.Entry<String, HashMap<String, Integer>> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    /**
     * get the hypernym hashmap.
     * @return the hypernym hashmap
     */
    public HashMap<String, HashMap<String, Integer>> getHashMap() {
        return sortAlphabetically(hypernym);
    }
}
