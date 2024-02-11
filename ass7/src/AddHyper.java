import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The AddHyper class processes a given line and adds hypernym-hyponym
 * relationships to a base map.
 *
 */
public class AddHyper {
    /**
     * The given line.
     *
     */
    private final String line;

    /**
     * The hypernym map of the data.
     *
     */
    private final Map<String, Map<String, Integer>> hypernymMap;

    /**
     * Constructs an AddHyper object with the given line and base map.
     *
     * @param l        the line to be processed
     * @param hypernymMap the base map to store hypernym-hyponym relationships
     */
    public AddHyper(String l, Map<String, Map<String, Integer>> hypernymMap) {
        this.line = l;
        this.hypernymMap = hypernymMap;
    }

    /**
     * Processes the line using the provided regex pattern.
     *
     * @param regexPattern the regex pattern to be applied
     */
    public void processLineWithRegex(String regexPattern) {
        String text = this.line;

        Pattern pattern = Pattern.compile(regexPattern.replace("NP",
                RegexPattern.NP));
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String extractedLine = text.substring(matcher.start(),
                    matcher.end());
            processExtractedLine(extractedLine);
        }
    }

    /**
     * Processes the extracted line to extract hypernym-hyponym relationships
     * and add them to the base map.
     *
     * @param extractedLine the extracted line containing hypernym-hyponym
     *                     relationship.
     */
    public void processExtractedLine(String extractedLine) {
        HearstParser parser = new HearstParser(extractedLine);
        List<String> hyponyms = parser.getHyponyms();
        String hypernym = parser.getHypernym();

        for (String hyponym : hyponyms) {
            addToBaseMap(hypernym, hyponym);
        }
    }

    /**
     * Adds a hypernym-hyponym relationship to the base map.
     *
     * @param hypernym the hypernym
     * @param hyponym  the hyponym
     */
    public void addToBaseMap(String hypernym, String hyponym) {
        if (hypernymMap.containsKey(hypernym)) {
            Map<String, Integer> hyponymMap = hypernymMap.get(hypernym);
            if (hyponymMap.containsKey(hyponym)) {
                hyponymMap.put(hyponym, hyponymMap.get(hyponym) + 1);
            } else {
                hyponymMap.put(hyponym, 1);
            }
        } else {
            TreeMap<String, Integer> newHyponymMap =
                    new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            newHyponymMap.put(hyponym, 1);
            hypernymMap.put(hypernym, newHyponymMap);
        }
    }
}
