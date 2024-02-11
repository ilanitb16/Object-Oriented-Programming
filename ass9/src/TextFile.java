
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
/**
 * A utility class for reading and writing text files.
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
     * Reads the contents of the text file and returns it as a single line string.
     *
     * @return The contents of the text file as a single line string, or null if reading fails.
     */
    public String getOneLineString() {
        try {

            String text = new String(Files.readAllBytes(Paths.get(this.filePath)));
            text = text.replace("\n", "");
            return text;
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
            return null;
        }
    }
    /**
     * Writes a single relation (hypernym and its associated hyponyms) to the file.
     *
     * @param map the map
     */
    public void writeRelationsToFile(TreeMap<String, TreeMap<String, Integer>> map) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(this.filePath);

            for (String str : map.keySet()) {

                if (map.get(str).size() >= MIN_HYPONYMS_TO_WRITE) {
                    myWriter.write(str + ": ");
                    int count = 0;

                    for (Map.Entry<String, Integer> hyponym : map.get(str).entrySet()) {
                        if (count > 0) {
                            myWriter.write(", ");
                        }
                        myWriter.write(hyponym.getKey() + " (" + hyponym.getValue() + ")");
                        count++;
                    }
                    myWriter.write("\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Writing fail");
        } finally {
            if (myWriter != null) {
                try {
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println(" Closing fail!");
                }
            }
        }
    }
}
