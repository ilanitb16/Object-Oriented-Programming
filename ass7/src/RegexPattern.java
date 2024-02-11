/**
 * This class' purpose contains regular expression patterns for
 * hearst patterns.
 */

public class RegexPattern {

    /**
     * The pattern to match noun phrases enclosed in <np /> tags.
     */
    static final String NP = "<np>([^<]+)<\\/np>";

    /**
     * The pattern to match phrases with "such as" construction.
     */
    static final String SUCH_AS_PATTERN  =
            "such NP as NP( , NP)*( (, )?(and|or) NP)?";
    /**
     * The pattern to match phrases with "including" construction.
     */
    static final String INCLUDING_PATTERN  =
            "NP (, )?including NP( (, )?NP)*( (, )?(and|or) NP)?";

    /**
     * The pattern to match phrases with "especially" construction.
     */
    static final String ESPECIALLY_PATTERN =
            "NP (, )?especially NP( (, )?NP)*( (, )?(and|or) NP)?";

    /**
     * The pattern to match phrases with "such as" construction
     * (alternative pattern).
     */
    static final String SUCH_AS_PATTERN_2 =
            "NP (, )?such as NP( (, )?NP)*( (, )?(and|or) NP)?";
    /**
     * The pattern to match phrases with "which is" construction.
     */
    static final String  WHICH_IS_PATTERN =
            "NP (, )?which is ((an example|a kind|a class) of )?NP";

    /**
     * Returns a regex pattern based on the provided line.
     *
     * @param line The line of text to analyze.
     * @return The regex pattern corresponding to the line, or an empty string if no match is found.
     */
    static String getPatternByName(String line) {
        if (line.contains("including")) {
            return RegexPattern.INCLUDING_PATTERN;
        }

        if (line.contains("especially")) {
            return RegexPattern.ESPECIALLY_PATTERN;
        }

        if (line.contains("which")) {
            return RegexPattern.WHICH_IS_PATTERN;
        }
        if (line.contains("such as")) {
            return RegexPattern.SUCH_AS_PATTERN_2;
        }
        if (line.contains("such")) {
            return RegexPattern.SUCH_AS_PATTERN;
        }

        return "";
    }
}