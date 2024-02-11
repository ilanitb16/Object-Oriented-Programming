
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A regex implementation for capturing relations using the "which is" pattern.
 */
public class WhichIs extends BasicRegex {

    private static final int PREFIX_SIZE_NP = 4;
    private static final int SUFFIX_SIZE_NP = 5;

    /**
     * Constructs a `WhichIs` object with the specified decorator.
     *
     * @param decorator The decorator relation.
     */
    public WhichIs(Relation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)which is ((an example|a kind|a class) of )?<np>[^<]+<\\/np>");
    }

    /**
     * Adds relations to the decorator based on the "which is" pattern in
     * the provided text.
     *
     * @param text The text to search for relations.
     */
    @Override
    public void addAllRelations(String text) {
        Pattern pattern = Pattern.compile(super.getRegexPattern());
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String np = "<np>[^<]+<\\/np>";
            String sub = text.substring(matcher.start(), matcher.end());
            Pattern npPattern = Pattern.compile(np);
            Matcher npMatcher = npPattern.matcher(sub);
            String hypernym = "";
            String hyponym = "";

            if (npMatcher.find()) {
                hyponym = sub.substring(npMatcher.start() + PREFIX_SIZE_NP, npMatcher.end() - SUFFIX_SIZE_NP);
            }

            if (npMatcher.find()) {
                hypernym = sub.substring(npMatcher.start() + PREFIX_SIZE_NP, npMatcher.end() - SUFFIX_SIZE_NP);
            }

            if (this.containsHypernym(hypernym)) {
                if (this.containsRelation(hypernym, hyponym)) {
                    this.addContainsRelation(hypernym, hyponym);
                } else {
                    this.addContainsHypernym(hypernym, hyponym);
                }
            } else {
                this.addNewRelation(hypernym, hyponym);
            }
        }
    }
}
