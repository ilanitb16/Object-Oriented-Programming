
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
    public void printHypernymsOfThisHyponym(String hyponym) {

        // Gets a map of the whole relations in the corpus.
        CreateHypernymDatabase database = new CreateHypernymDatabase(this.corpusFolder.getPath());
        TreeMap<String, TreeMap<String, Integer>> map = database.createMapOfRelations();
        TreeMap<String, Integer> hypernyms = new TreeMap<>();

        /*
         * A loop that find the whole hypernyms of this hyponym,
         * and insert them to a map with the number of times they appeared.
         */
        for (String key : map.keySet()) {
            if (map.get(key).containsKey(hyponym)) {
                hypernyms.put(key, map.get(key).get(hyponym));
            }
        }

        /*
         * If there isn't an hypernyms for this lemma, it prints there isn't and returns.
         */
        if (hypernyms.size() == 0) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        } else {

            /*
             * A loop that print those hypernyms order by their integer value (decent).
             */
            TreeMap<String, Integer> sortedMap = IntegerMapValueComparator.sortMapByValues(hypernyms);
            for (Map.Entry<String, Integer> hypo : sortedMap.entrySet()) {
                System.out.println(hypo.getKey() + ": (" + hypo.getValue() + ")");
            }
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
        hypernymDiscoverer.printHypernymsOfThisHyponym(args[HYPONYM_ARGUMENT_INDEX]);
    }
}
