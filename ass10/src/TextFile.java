
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
/**
 * Utility class for reading from and writing to text files.
 */
public class TextFile {

    private String filePath;
    private static final int MIN_HYPONYMS_TO_WRITE = 3;

    /**
     * Constructs a TextFile object with the specified file path.
     *
     * @param filePath The path of the text file.
     */
    public TextFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the content of the text file and returns it as a single-line string.
     *
     * @return The content of the text file as a single-line string.
     */
    public String getOneLineString() {
        try {
            String text = new String(Files.readAllBytes(Paths.get(this.filePath)));
            text = text.replace("\n", "");
            return text;
        } catch (IOException e) {
            System.out.println("Reading Failed!");
            return null;
        }
    }

    /**
     * Writes the relations stored in the map to the text file.
     *
     * @param map The map containing the relations to be written.
     */
    public void writeRelationsToFile(TreeMap<String, TreeMap<String, Integer>> map) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (String str : map.keySet()) {
                if (map.get(str).size() >= MIN_HYPONYMS_TO_WRITE) {
                    writeRelation(writer, str, map.get(str));
                }
            }
        } catch (IOException e) {
            System.out.println("Writing Failed!");
        }
    }

    /**
     * Writes a single relation (hypernym and its hyponyms) to the text file.
     *
     * @param writer    The FileWriter object used for writing to the file.
     * @param hypernym  The hypernym of the relation.
     * @param hyponyms  The TreeMap containing the hyponyms and their counts.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void writeRelation(FileWriter writer, String hypernym,
                               TreeMap<String, Integer> hyponyms) throws
            IOException {
        writer.write(hypernym + ": ");
        int count = 0;

        for (Map.Entry<String, Integer> hyponym : hyponyms.entrySet()) {
            if (count > 0) {
                writer.write(", ");
            }
            writer.write(hyponym.getKey() + " (" + hyponym.getValue() + ")");
            count++;
        }

        writer.write("\n");
    }
}
