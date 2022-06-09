package ass7;

import java.util.HashMap;
import java.util.List;

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
    public void addHypernym(List<List<String>> relations) {
        for (List<String> relation : relations) {
            String first = relation.get(0);
            if (!hypernym.containsKey(first)) {
                hypernym.put(first, new HashMap<String, Integer>());
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
        }
    }
    /**
     * get the hypernym hashmap.
     * @return the hypernym hashmap
     */
    public HashMap<String, HashMap<String, Integer>> getHashMap() {
        return hypernym;
    }
}
