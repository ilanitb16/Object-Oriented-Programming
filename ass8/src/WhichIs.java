
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *  WhichIs relation pattern for extracting relationships from text.
 *
 */
public class WhichIs extends BasicRegex {
    private static final int PREFIX_SIZE_NP = 4;
    private static final int SUFFIX_SIZE_NP = 5;
    /**
     * Constructs a WhichIs object with the given decorator and regex pattern.
     *
     * @param decorator The TaxonomyRelation decorator to be used.
     */
    public WhichIs(TaxonomyRelation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)which is "
                + "((an example|a kind|a class) of )?<np>[^<]+<\\/np>");
    }
    @Override
    public void addAllRelations(String text) {
        Pattern patt = Pattern.compile(super.getReg());
        Matcher matcher = patt.matcher(text);

        while (matcher.find()) {
            String np = "<np>[^<]+<\\/np>";
            String sub = text.substring(matcher.start(), matcher.end());
            Pattern patt2 = Pattern.compile(np);
            Matcher matcher2 = patt2.matcher(sub);
            String hypernym = "", hyponym = "";

            if (matcher2.find()) {
                hyponym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);
            }

            if (matcher2.find()) {
                hypernym = sub.substring(matcher2.start() + PREFIX_SIZE_NP, matcher2.end() - SUFFIX_SIZE_NP);
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
