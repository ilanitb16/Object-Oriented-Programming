/**
 * Represents a regex pattern for matching "such as" phrases.
 * Extends the BasicRegex class.
 */
public class SuchAs extends BasicRegex {
    /**
     * Constructs a SuchAs object with the provided decorator.
     *
     * @param decorator The decorator for the relation.
     */
    public SuchAs(TaxonomyRelation decorator) {
        super(decorator, "<np>[^<]+<\\/np>( |, | , | ,)such as <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
