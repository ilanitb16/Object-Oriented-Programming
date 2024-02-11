
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BasicRegex is a concrete implementation of the Relation interface that
 * applies a regular expression pattern to extract hypernym-hyponym relations
 * from a given text.
 */
public class BasicRegex implements Relation {

    private Relation decorator;
    private String regexPattern;
    private static final int PREFIX_SIZE_NP = 4;
    private static final int SUFFIX_SIZE_NP = 5;

    /**
     * Constructs a BasicRegex object with the specified decorator and regular expression pattern.
     *
     * @param decorator      The decorated Relation object.
     * @param regexPattern   The regular expression pattern used to extract relations.
     */
    public BasicRegex(Relation decorator, String regexPattern) {
        this.decorator = decorator;
        this.regexPattern = regexPattern;
    }

    /**
     * Retrieves the regular expression pattern.
     *
     * @return The regular expression pattern.
     */
    public String getRegexPattern() {
        return regexPattern;
    }

    @Override
    public TreeMap<String, TreeMap<String, Integer>> getRelations() {
        return decorator.getRelations();
    }
    @Override
    public void addNewRelation(String hypernym, String hyponym) {
        decorator.addNewRelation(hypernym, hyponym);
    }
    @Override
    public Boolean containsHypernym(String hypernym) {
        return decorator.containsHypernym(hypernym);
    }
    @Override
    public Boolean containsRelation(String hypernym, String hyponym) {
        return decorator.containsRelation(hypernym, hyponym);
    }
    @Override
    public void addContainsHypernym(String hypernym, String hyponym) {
        decorator.addContainsHypernym(hypernym, hyponym);
    }
    @Override
    public void addContainsRelation(String hypernym, String hyponym) {
        decorator.addContainsRelation(hypernym, hyponym);
    }
    @Override
    public void addAllRelations(String text) {
        Pattern patt = Pattern.compile(this.regexPattern);
        Matcher matcher = patt.matcher(text);

        while (matcher.find()) {
            String np = "<np>[^<]+<\\/np>";
            String sub = text.substring(matcher.start(), matcher.end());
            Pattern patt2 = Pattern.compile(np);
            Matcher matcher2 = patt2.matcher(sub);
            String hypernym = "";

            if (matcher2.find()) {
                hypernym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);
            }

            while (matcher2.find()) {
                String hyponym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);

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

        this.decorator.addAllRelations(text);
    }
}
