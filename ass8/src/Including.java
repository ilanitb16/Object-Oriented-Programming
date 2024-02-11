
/**
 * Including relation pattern for extracting relationships from text.
 */
public class Including extends BasicRegex {
    /**
     * Constructor.
     *  @param decorator The TaxonomyRelation decorator used for relations.
     */
    public Including(TaxonomyRelation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)including <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
