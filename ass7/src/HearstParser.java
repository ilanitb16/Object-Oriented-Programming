import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The HearstParser class parses a given line and extracts noun phrases (NPs)
 * along with their hypernym and hyponyms.
 */
public class HearstParser {
    /**
     * The given line.
     *
     */
    private final String line;

    /**
     * Constructs a HearstParser object with the given line.
     *
     * @param line the line to be parsed
     */
    public HearstParser(String line) {
        this.line = line;
    }

    /**
     * Extracts and returns a list of noun phrases (NPs) from the line.
     *
     * @return a list of noun phrases
     */
    public List<String> getNounPhrases() {
        Pattern npPattern = Pattern.compile(RegexPattern.NP);
        Matcher matcher = npPattern.matcher(this.line);
        List<String> npList = new ArrayList<>();

        while (matcher.find()) {
            String np = this.line.substring(matcher.start(), matcher.end());
            np = np.replace("<np>", "");
            np = np.replace("</np>", "");
            npList.add(np);
        }

        return npList;
    }

    /**
     * Extracts and returns the hypernym from the line.
     *
     * @return the hypernym
     */
    public String getHypernym() {
        List<String> npList = getNounPhrases();

        if (this.line.contains("which is")) {
            return npList.get(npList.size() - 1);
        }

        return npList.get(0);
    }

    /**
     * Extracts and returns the list of hyponyms from the line.
     *
     * @return the list of hyponyms
     */
    public List<String> getHyponyms() {
        List<String> npList = getNounPhrases();
        String hypernym = getHypernym();
        npList.remove(hypernym);
        return npList;
    }
}
