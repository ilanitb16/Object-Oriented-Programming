/**
 * Represents a regex pattern for matching "such NP as" phrases.
 * Extends the BasicRegex class.
 */
public class SuchNpAs extends BasicRegex {
    /**
     * Constructs a SuchNpAs object with the provided decorator.
     *
     * @param decorator The decorator for the relation.
     */
    public SuchNpAs(Relation decorator) {
        super(decorator, "such <np>[^<]+<\\/np> as <np>[^<]+<\\/np>(( |, | , | ,)<np>[^<]+<\\/np>)*"
                + "(( |, | , | ,)(and |or |)<np>[^<]+<\\/np>)?");
    }
}
