import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Read a file.
 */
public class FileReader {
    /**
     * The base map to add  the file contents to.
     */
    private final Map<String, Map<String, Integer>> baseMap;
    /**
     * he path of the file we want to read.
     */
    private final String filePath;

    /**
     * constructor.
     * @param filePath - the absolut file path.
     * @param baseMap - the base treemap.
     */
    public FileReader(String filePath, Map<String, Map<String, Integer>> baseMap) {
        this.filePath = filePath;
        this.baseMap = baseMap;
    }

    /**
     * read from the file line by line.
     * @throws IOException throw expedition.
     */
    public void readerBuffer() throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(this.filePath)));

            String line;

            while ((line = reader.readLine()) != null) {
                AddHyper toDataBase = new AddHyper(line, this.baseMap);
                line = line.toLowerCase();
                String stringRegex = RegexPattern.getPatternByName(line);

                if (stringRegex != "") {
                    toDataBase.processLineWithRegex(stringRegex);
                }
            }

        } catch (IOException e) {
            System.out.println("Reading FAILED!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("File closing FAILED!");
                }
            }
        }
    }
}