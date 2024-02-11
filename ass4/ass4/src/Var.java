// 322453200 Ilanit Berditchevski.
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * Class representing Var.
 *
 */
public class Var implements Expression {
    private String variable;

    /**
     * Constructor for Var.
     * @param variable value of expression
     */
    public Var(String variable) {
        this.variable = variable;
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (!assignment.containsKey(variable)) {
            throw new Exception("Variable " + variable + " is not assigned a value.");
        }
        return assignment.get(variable);
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        throw new Exception("Cannot evaluate variable without assignment.");
    }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return Collections.singletonList(variable);
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
        return variable;
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
        if (variable.equals(var)) {
            return expression;
        }
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
        if (variable.equals(var)) {
            return new Num(1); // (x)' = 1
        }
        return new Num(0); // (y) dx = 0
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Determines if two expressions are equal or not.
     * @param expression the variable-value
     * @return true or false
     */
    @Override
    public boolean equals(Expression expression) {
        if (expression.toString().equals(variable)) {
            return true;
        }
        return false;
    }
}
