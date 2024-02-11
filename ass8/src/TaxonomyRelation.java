
/*
 * Osher Elhadad
 *
 * This file defines a Relation interface.
 */

import java.util.TreeMap;

/**
 * Relation between hypernyms and hyponyms.
 */
public interface TaxonomyRelation {

    /**
     * Returns the relations stored in the taxonomy.
     *
     * @return A TreeMap representing the relations, where each hypernym maps to a TreeMap of hyponyms and their counts.
     */
    TreeMap<String, TreeMap<String, Integer>> getRelations();

    /**
     * Adds a new relation to the taxonomy.
     *
     * @param hypernym The hypernym of the relation.
     * @param hyponym  The hyponym of the relation.
     */
    void addNewRelation(String hypernym, String hyponym);

    /**
     * Checks if the taxonomy contains the specified hypernym.
     *
     * @param hypernym The hypernym to check.
     * @return true if the hypernym exists in the taxonomy, false otherwise.
     */
    Boolean containsHypernym(String hypernym);

    /**
     * Checks if the taxonomy contains the specified relation.
     *
     * @param hypernym The hypernym of the relation.
     * @param hyponym  The hyponym of the relation.
     * @return true if the relation exists in the taxonomy, false otherwise.
     */
    Boolean containsRelation(String hypernym, String hyponym);

    /**
     * Adds a hypernym-hyponym pair to the existing hypernym in the taxonomy.
     *
     * @param hypernym The hypernym to add the relation to.
     * @param hyponym  The hyponym to add to the hypernym.
     */
    void addContainsHypernym(String hypernym, String hyponym);

    /**
     * Adds a new relation between the specified hypernym and hyponym in the taxonomy.
     *
     * @param hypernym The hypernym of the relation.
     * @param hyponym  The hyponym of the relation.
     */
    void addContainsRelation(String hypernym, String hyponym);

    /**
     * Adds relations to the taxonomy based on the provided text.
     *
     * @param text The text containing relations to be added to the taxonomy.
     */
    void addAllRelations(String text);
}
