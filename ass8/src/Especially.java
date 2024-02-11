/**
 * Especially relation pattern for extracting relationships from text.
 */
public class Especially extends BasicRegex {
    /**
     * Constructor.
     *  @param decorator The TaxonomyRelation decorator used for relations.
     */
    public Especially(TaxonomyRelation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)especially <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
