import java.util.Comparator;
import java.util.TreeMap;

/**
 * Implementation of the Relation interface that represents a basic relation
 * between hypernyms and hyponyms.
 */
public class BasicRelation implements Relation {

    private static final int ADD_ONE_RELATION = 1;

    private TreeMap<String, TreeMap<String, Integer>> map;

    /**
     * Constructs a BasicRelation object with the specified comparator for
     * sorting the relations.
     *
     * @param relationsComparator The comparator used to sort the relations.
     */
    public BasicRelation(Comparator<String> relationsComparator) {
        this.map = new TreeMap<>(relationsComparator);
    }

    @Override
    public TreeMap<String, TreeMap<String, Integer>> getRelations() {
        return this.map;
    }

    @Override
    public void addNewRelation(String hypernym, String hyponym) {
        this.map.put(hypernym, new TreeMap<>());
        this.map.get(hypernym).put(hyponym, ADD_ONE_RELATION);
    }

    @Override
    public Boolean containsHypernym(String hypernym) {
        return this.map.containsKey(hypernym);
    }

    @Override
    public Boolean containsRelation(String hypernym, String hyponym) {
        return this.map.get(hypernym).containsKey(hyponym);
    }

    @Override
    public void addContainsHypernym(String hypernym, String hyponym) {
        this.map.get(hypernym).put(hyponym, ADD_ONE_RELATION);
    }

    @Override
    public void addContainsRelation(String hypernym, String hyponym) {
        this.map.get(hypernym).put(hyponym, this.map.get(hypernym).get(hyponym) + ADD_ONE_RELATION);
    }

    @Override
    public void addAllRelations(String text) {

    }
}
