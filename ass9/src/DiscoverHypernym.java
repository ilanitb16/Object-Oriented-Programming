
/*
 * Ilanit Berditchevski 322453200
 *
 *  DiscoverHypernym class.
 */

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class for discovering hypernyms and printing them for a given hyponym.
 *
 * @version 29 June 2023
 * @author Ilanit Berditchevski
 */
public class DiscoverHypernym {

    /**
     * Represents the folder containing the corpus files.
     */
    private File corpusFolder;
    /**
     * The expected number of command-line arguments.
     */
    private static final int EXPECTED_ARGUMENTS_COUNT = 2;
    /**
     * The index of the command-line argument for the corpus folder path.
     */
    private static final int FOLDER_PATH_ARGUMENT_INDEX = 0;
    /**
     * The index of the command-line argument for the hyponym.
     */
    private static final int HYPONYM_ARGUMENT_INDEX = 1;
    /**
     * Constructs a HypernymDiscoverer object with the provided corpus folder path.
     *
     * @param corpusFolderPath The path to the corpus folder.
     */

    public DiscoverHypernym(String corpusFolderPath) {
        this.corpusFolder = new File(corpusFolderPath);
    }
    /**
     * Prints the hypernyms for the given hyponym.
     *
     * @param hyponym The hyponym for which to find hypernyms.
     */
    public void printHypernyms(String hyponym) {
        // Create the hypernym database
        CreateHypernymDatabase databaseCreator = new CreateHypernymDatabase(this.corpusFolder.getPath());
        TreeMap<String, TreeMap<String, Integer>> relationsMap = databaseCreator.createMapOfRelations();

        // Find hypernyms for the given hyponym
        TreeMap<String, Integer> hypernyms = findHypernyms(relationsMap, hyponym);

        // Prints results
        if (hypernyms.isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
        } else {
            printSortedHypernyms(hypernyms);
        }
    }

    /**
     * Finds the hypernyms for the given hyponym in the relations map.
     *
     * @param relationsMap The map of relations.
     * @param hyponym      The hyponym to find hypernyms for.
     * @return A TreeMap containing the hypernyms and their frequencies.
     */
    private TreeMap<String, Integer> findHypernyms(TreeMap<String,
            TreeMap<String, Integer>> relationsMap, String hyponym) {
        TreeMap<String, Integer> hypernyms = new TreeMap<>();
        for (Map.Entry<String, TreeMap<String, Integer>> entry : relationsMap.entrySet()) {
            TreeMap<String, Integer> relations = entry.getValue();
            if (relations.containsKey(hyponym)) {
                hypernyms.put(entry.getKey(), relations.get(hyponym));
            }
        }
        return hypernyms;
    }

    /**
     * Prints the hypernyms in sorted order.
     *
     * @param hypernyms The TreeMap containing the hypernyms and their frequencies.
     */
    private void printSortedHypernyms(TreeMap<String, Integer> hypernyms) {
        TreeMap<String, Integer> sortedMap = new TreeMap<>(new IntegerMapValueComparator(hypernyms));
        sortedMap.putAll(hypernyms);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": (" + entry.getValue() + ")");
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Check if the number of arguments is correct
        if (args.length != EXPECTED_ARGUMENTS_COUNT) {
            throw new RuntimeException("Expected 2 arguments.");
        }

        // Create a HypernymDiscoverer object and print hypernyms
        DiscoverHypernym hypernymDiscoverer = new DiscoverHypernym(args[FOLDER_PATH_ARGUMENT_INDEX]);
        hypernymDiscoverer.printHypernyms(args[HYPONYM_ARGUMENT_INDEX]);
    }
}
