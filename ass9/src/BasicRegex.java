import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * A regex implementation of the TaxonomyRelation interface.
 */

public class BasicRegex implements TaxonomyRelation {

    private TaxonomyRelation decorator;
    private String reg;

    private static final int PREFIX_SIZE_NP = 4;

    private static final int SUFFIX_SIZE_NP = 5;
    /**
     * Constructs a BasicRegex object with the specified decorator and regex pattern.
     *
     * @param decorator The decorator TaxonomyRelation.
     * @param reg       The regex pattern to match relations.
     */
    public BasicRegex(TaxonomyRelation decorator, String reg) {
        this.decorator = decorator;
        this.reg = reg;
    }
    /**
     * Gets the regex pattern used by this BasicRegex object.
     *
     * @return The regex pattern.
     */
    public String getReg() {
        return this.reg;
    }

    @Override
    public TreeMap<String, TreeMap<String, Integer>> getRelations() {
        return this.decorator.getRelations();
    }

    @Override
    public void addNewRelation(String hypernym, String hyponym) {
        this.decorator.addNewRelation(hypernym, hyponym);
    }

    @Override
    public Boolean containsHypernym(String hypernym) {
        return this.decorator.containsHypernym(hypernym);
    }

    @Override
    public Boolean containsRelation(String hypernym, String hyponym) {
        return this.decorator.containsRelation(hypernym, hyponym);
    }

    @Override
    public void addContainsHypernym(String hypernym, String hyponym) {
        this.decorator.addContainsHypernym(hypernym, hyponym);
    }

    @Override
    public void addContainsRelation(String hypernym, String hyponym) {
        this.decorator.addContainsRelation(hypernym, hyponym);
    }

    @Override
    public void addAllRelations(String text) {
        Pattern patt = Pattern.compile(this.reg);
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
