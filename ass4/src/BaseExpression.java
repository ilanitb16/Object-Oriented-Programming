// 322453200 Ilanit Berditchevski.
import java.util.List;
import java.util.Map;

/**
 * Class representing BaseExpression.
 *
 */
public class BaseExpression implements Expression {
    protected Expression e1;

    /**
     * Constructor for BaseExpression.
     * @param expression  the given expression
     */
    public BaseExpression(Expression expression) {
        this.e1 = expression;
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return e1.evaluate(assignment);
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        return e1.evaluate();
    }
    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return e1.getVariables();
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
        return e1.toString();
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
        return this;
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        return e1.differentiate(var);
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        return e1.simplify();
    }

    /**
     * Determines if two expressions are equal or not.
     * @param expression the variable-value
     * @return true or false
     */
    public boolean equals(Expression expression) {
        return this.e1.toString() == expression.toString();
    }
}
