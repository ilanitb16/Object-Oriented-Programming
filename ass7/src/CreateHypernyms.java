import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedSet;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Collections;

/**
 * The CreateHypernyms class manages the creation of hypernym-hyponym
 * relationships and related operations.
 */
public class CreateHypernyms {
    /**
     * The character code for the new line character.
     */
    private static final int NEW_LINE_CHAR = 10;
    /**
     * Value for size.
     */
    private static final int SIZE = 3;
    private Map<String, Map<String, Integer>> hypernymMap;

    /**
     * Constructs a CreateHypernyms object with an empty base map.
     */
    public CreateHypernyms() {
        this.hypernymMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * The main method that reads files, creates hypernym-hyponym relationships,
     * and writes the output to a file.
     *
     * @param args the command-line arguments containing input and output
     *            file paths.
     * @throws IOException if an I/O error occurs while reading or writing files
     */
    public static void main(final String[] args) throws IOException {
        CreateHypernyms hypernymDatabase = new CreateHypernyms();
        String inputFolderPath = args[0];
        String outputFolderPath = args[1];
        File inputFolder = new File(inputFolderPath);

//        File inputFolder = new File(args[0]);


        File[] files = inputFolder.listFiles();
        if (files == null) {
            return;
        }

        for (final File fileEntry : files) {
            FileReader fileReader = new FileReader(fileEntry.getAbsolutePath(),
                    hypernymDatabase.hypernymMap);
            fileReader.readerBuffer();
        }

        hypernymDatabase.writeToOutputFile(outputFolderPath);
    }

    /**
     * Converts the hyponyms of a hypernym into a string representation.
     *
     * @param hypernym the hypernym for which to retrieve the hyponyms
     * @return a string representation of the hyponyms of the given hypernym
     */
    public String getHyponymString(String hypernym) {
        List<String> hyponymsList = new ArrayList<>();
        for (Map.Entry<String, Integer> hyponymEntry : sortByValueDescending(this.hypernymMap.get(hypernym))) {
            hyponymsList.add(hyponymEntry.getKey() + " (" + hyponymEntry.getValue().toString() + ")");
        }
        return String.join(", ", hyponymsList);
    }

    /**
     * Sorts a map's entries by value in descending order.
     *
     * @param map the map to be sorted
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map, which must be comparable
     * @return a sorted set of map entries in descending order of values
     */
    private <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> sortByValueDescending(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries =
                new TreeSet<>(Collections.reverseOrder(Map.Entry.comparingByValue()));
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    /**
     * Writes the hypernym-hyponym relationships to an output file.
     *
     * @param outputFolderPath the output folder path
     * @throws IOException if an I/O error occurs while writing the file
     */
    private void writeToOutputFile(String outputFolderPath) throws IOException {
        FileOutputStream outputFile = null;
        try {
            outputFile = new FileOutputStream(outputFolderPath + "/"
                    + "Ilanit" + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String hypernym : this.hypernymMap.keySet()) {
            if (this.hypernymMap.get(hypernym).size() < SIZE) {
                continue;
            }
            String line = hypernym + ": " + getHyponymString(hypernym);
            assert outputFile != null;
            outputFile.write(line.getBytes());
            outputFile.write(NEW_LINE_CHAR);
        }

        try {
            outputFile.close();
        } catch (IOException e) {
            System.out.println();
        }
    }

    /**
     * Finds the hypernyms that contain a specific lemma and displays them in
     * descending order of occurrence count.
     *
     * @param lemma the lemma to search for
     */
    public void findHypernymsByLemma(String lemma) {
        Map<String, Integer> lemmaMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (Map.Entry<String, Map<String,
                Integer>> hypernymEntry : hypernymMap.entrySet()) {
            String hypernym = hypernymEntry.getKey();
            Map<String, Integer> hyponyms = hypernymEntry.getValue();

            if (hyponyms.containsKey(lemma)) {
                int lemmaCounter = hyponyms.get(lemma);
                lemmaMap.put(hypernym, lemmaCounter);
            }
        }

        lemmaMap.entrySet().stream().sorted(Map
                        .Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry
                        .getKey() + ": " + "(" + entry.getValue() + ")"));
    }

    /**
     * Retrieves the base hypernym map.
     *
     * @return the base hypernym map
     */
    public Map<String, Map<String, Integer>> getHypernymMap() {
        return hypernymMap;
    }
}
