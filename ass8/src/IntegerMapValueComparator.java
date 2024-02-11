
import java.util.Comparator;
import java.util.TreeMap;

/**
 * A comparator for sorting a TreeMap based on the values (Integer) in a descending order.
 */
public class IntegerMapValueComparator implements Comparator<String> {

    private final TreeMap<String, Integer> map;

    /**
     * Constructs an IntegerMapValueComparator with the specified TreeMap.
     *
     * @param map The TreeMap to be used for value comparison.
     */
    public IntegerMapValueComparator(TreeMap<String, Integer> map) {
        this.map = map;
    }

    /**
     * Compares the values of two keys in the TreeMap.
     *
     * @param key1 The first key to compare.
     * @param key2 The second key to compare.
     * @return 0 if the values are equal, -1 if the value of key1 is greater, 1 otherwise.
     */
    @Override
    public int compare(String key1, String key2) {
        Integer value1 = map.get(key1);
        Integer value2 = map.get(key2);

        if (key1.equals(key2) && value1.equals(value2)) {
            return 0;
        }

        return Integer.compare(value2, value1);
    }

    /**
     * Sorts a TreeMap of TreeMaps based on the values (Integer) in descending order.
     *
     * @param map The TreeMap of TreeMaps to be sorted.
     * @return A new TreeMap with sorted TreeMaps based on values in descending order.
     */
    public static TreeMap<String, TreeMap<String, Integer>> sortMapsOfMapByValues(
            TreeMap<String, TreeMap<String, Integer>> map) {
        TreeMap<String, TreeMap<String, Integer>> newMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String outerKey : map.keySet()) {
            TreeMap<String, Integer> sortedInnerMap = sortMapByValues(map.get(outerKey));
            newMap.put(outerKey, sortedInnerMap);
        }
        return newMap;
    }

    /**
     * Sorts a TreeMap based on the values (Integer) in descending order.
     *
     * @param map The TreeMap to be sorted.
     * @return A new TreeMap with sorted entries based on values in descending order.
     */
    public static TreeMap<String, Integer> sortMapByValues(TreeMap<String, Integer> map) {
        IntegerMapValueComparator comparator = new IntegerMapValueComparator(map);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(map);
        return sortedMap;
    }
}
