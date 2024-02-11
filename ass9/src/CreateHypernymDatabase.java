import java.io.File;
import java.util.TreeMap;
/**
 * A class for creating a hypernym database from a folder containing text files.
 */
public class CreateHypernymDatabase {
    private File folder;
    private static final int NUMBER_OF_ARGUMENTS = 2;
    private static final int FIRST_ARGUMENT = 0;
    private static final int SECOND_ARGUMENT = 1;
    /**
     * Constructs a CreateHypernymDatabase object with the specified folder path.
     *
     * @param folderPath The path to the folder containing text files.
     */
    public CreateHypernymDatabase(String folderPath) {
        this.folder = new File(folderPath);
    }
    /**
     * Creates a map of hypernym relations from the text files in the folder.
     *
     * @return A TreeMap containing the hypernym relations.
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
     * Creates a hypernym database by writing the relations to a text file.
     *
     * @param outputPath The path to the output file.
     */
    public void createDatabase(String outputPath) {

        TreeMap<String, TreeMap<String, Integer>> sortedMap = IntegerMapValueComparator.
                sortMapsOfMapByValues(this.createMapOfRelations());
        TextFile outputFile = new TextFile(outputPath);
        outputFile.writeRelationsToFile(sortedMap);
    }

    /**
     * The Main of the program.
     *
     * @param args Command-line arguments. Expects two arguments: the input folder path and the output file path.
     * @throws RuntimeException if the number of arguments is not 2.
     */
    public static void main(String[] args) {

        if (args.length != NUMBER_OF_ARGUMENTS) {
            throw new RuntimeException("no 2 arguments.");
        }
        CreateHypernymDatabase database = new CreateHypernymDatabase(args[FIRST_ARGUMENT]);
        database.createDatabase(args[SECOND_ARGUMENT]);
    }
}
