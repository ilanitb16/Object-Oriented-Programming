// 322453200 Ilanit Berditchevski.
import java.util.List;
import java.util.Map;

/**
 * Class representing a negative number.
 *
 */
public class Neg extends UnaryExpression {
    /**
     * Constructor for Neg.
     * @param expression expression
     */
    public Neg(Expression expression) {
        super(expression);
    }
    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return e1.getVariables();
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * e1.evaluate(assignment);
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        return -1 * e1.evaluate();
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
        return "(-" + e1.toString() + ")";
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var the variable-value to replace
     * @param expression the provided expression
     * @return  a new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Neg(this.e1.assign(var, expression));
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        Expression simplifiedInnerExpression = e1.simplify();

        try {
            // Check if the inner expression is a constant or a variable
            if (simplifiedInnerExpression instanceof Num || simplifiedInnerExpression instanceof Var) {
                double value = simplifiedInnerExpression.evaluate();
                return new Num(-value);  // Negate the value
            }

            // Check if the inner expression is a Neg expression
            if (simplifiedInnerExpression instanceof Neg) {
                // Simplify by removing the double negation
                return ((Neg) simplifiedInnerExpression).e1.simplify();
            }
        } catch (Exception e) {
            // Exception occurred while evaluating the inner expression
            // Simply return the original Neg expression
            return new Neg(simplifiedInnerExpression);
        }

        // If none of the simplification rules apply, return the original Neg expression
        return new Neg(simplifiedInnerExpression);
    }
}
