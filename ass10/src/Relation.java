import java.util.TreeMap;

/**
 * Relation between hypernyms and hyponyms.
 */
public interface Relation {
    /**
     * Retrieves all relations.
     *
     * @return A TreeMap containing the relations, where each hypernym maps to
     * a TreeMap of hyponyms with their frequencies.
     */
    TreeMap<String, TreeMap<String, Integer>> getRelations();

    /**
     * Adds a new relation between a hypernym and hyponym.
     *
     * @param hypernym The hypernym of the relation.
     * @param hyponym  The hyponym of the relation.
     */
    void addNewRelation(String hypernym, String hyponym);

    /**
     /**
     * Checks if the relation contains a specific hypernym.
     *
     * @param hypernym The hypernym to check.
     * @return true if the hypernym exists in the relation, false otherwise.
     */
    Boolean containsHypernym(String hypernym);

    /**
     * Checks if the relation contains a specific hypernym-hyponym relation.
     *
     * @param hypernym The hypernym to check.
     * @param hyponym  The hyponym to check.
     * @return true if the relation exists, false otherwise.
     */
    Boolean containsRelation(String hypernym, String hyponym);

    /**
     * Adds a hypernym and hyponym relation, incrementing the frequency if the
     * relation already exists.
     *
     * @param hypernym The hypernym of the relation.
     * @param hyponym  The hyponym of the relation.
     */
    void addContainsHypernym(String hypernym, String hyponym);

    /**
     * Adds a relation between a hypernym and hyponym, incrementing the
     * frequency if the relation already exists,
     * or adds a new relation if it doesn't.
     *
     * @param hypernym The hypernym of the relation.
     * @param hyponym  The hyponym of the relation.
     */
    void addContainsRelation(String hypernym, String hyponym);

    /**
     * Adds multiple relations from the provided text.
     * The text should contain hypernym-hyponym pairs separated by a specific delimiter.
     *
     * @param text The text containing hypernym-hyponym pairs separated by a delimiter.
     */
    void addAllRelations(String text);
}
