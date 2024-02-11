

import java.util.Comparator;
import java.util.TreeMap;

/**
 * A comparator for sorting a TreeMap based on the values (Integer) in a descending order.
 */
public class IntegerMapValueComparator implements Comparator<String> {
    /**
     * Constructs an IntegerMapValueComparator with the specified TreeMap.
     *
     * @param map The TreeMap to be used for value comparison.
     */
    private TreeMap<String, Integer> map;
    /**
     * Constructs an IntegerMapValueComparator with the specified TreeMap.
     *
     * @param map The TreeMap to be used for value comparison.
     */
    public IntegerMapValueComparator(TreeMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(String s1, String s2) {
        if (s1.equals(s2) && this.map.get(s1).equals(this.map.get(s2))) {
            return 0;
        }
        if (this.map.get(s1) >= this.map.get(s2)) {
            return -1;
        } else {
            return 1;
        }
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
        for (String key : map.keySet()) {
            newMap.put(key, sortMapByValues(map.get(key)));
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
        TreeMap<String, Integer> map1 = new TreeMap<>(comparator);
        map1.putAll(map);
        return map1;
    }
}
