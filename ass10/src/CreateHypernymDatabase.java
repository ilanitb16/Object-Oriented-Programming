
import java.io.File;
import java.util.TreeMap;

/**
 * Creates a hypernym database based on text files in a given folder.
 */
public class CreateHypernymDatabase {

    private File folder;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    private static final int FIRST = 0;

    private static final int SECOND = 1;

    /**
     * Constructs a CreateHypernymDatabase object with the specified folder path.
     *
     * @param folderPath The path to the folder containing the text files.
     */
    public CreateHypernymDatabase(String folderPath) {
        this.folder = new File(folderPath);
    }

    /**
     * Creates a map of hypernym relations from the text files in the folder.
     *
     * @return A TreeMap representing the hypernym relations.
     */
    public TreeMap<String, TreeMap<String, Integer>> createMapOfRelations() {

        SuchAs regex = new SuchAs(new SuchNpAs(new Especially(new Including(
                new WhichIs(new BasicRelation(String.CASE_INSENSITIVE_ORDER))))));

        for (File file : this.folder.listFiles()) {
            TextFile textFile = new TextFile(file.getPath());
            String text = textFile.getOneLineString();
            regex.addAllRelations(text);
        }
        return regex.getRelations();
    }

    /**
     * Creates a database file with hypernym relations.
     *
     * @param outputPath The path to the output file.
     */
    public void createDatabase(String outputPath) {
        TreeMap<String, TreeMap<String, Integer>> sortedMap =
                sortRelationsByValues(createMapOfRelations());
        TextFile outputFile = new TextFile(outputPath);
        outputFile.writeRelationsToFile(sortedMap);
    }

    /**
     * Sorts the relations map by descending integer values.
     *
     * @param relations The original relations map.
     * @return A new TreeMap with sorted relations based on values in descending order.
     */
    private TreeMap<String, TreeMap<String, Integer>> sortRelationsByValues(
            TreeMap<String, TreeMap<String, Integer>> relations) {
        return IntegerMapValueComparator.sortMapsOfMapByValues(relations);
    }

    /**
     * Main method to run the CreateHypernymDatabase application.
     *
     * @param args The command-line arguments. The first argument should be the
     *            folder path, and the second argument should be the output
     *             file path.
     */
    public static void main(String[] args) {
        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("no 2 arguments.");
        }
        CreateHypernymDatabase database = new CreateHypernymDatabase(args[FIRST]);
        database.createDatabase(args[SECOND]);
    }
}
