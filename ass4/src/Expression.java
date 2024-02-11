// 322453200 Ilanit Berditchevski.
import java.util.List;
import java.util.Map;

/**
 * Interface representing Expression.
 *
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var the variable-value to replace
     * @param expression the provided expression
     * @return  a new expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    Expression simplify();

    /**
     * Determines if two expressions are equal or not.
     * @param expression the variable-value
     * @return true or false
     */
    boolean equals(Expression expression);
}
