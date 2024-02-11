// 322453200 Ilanit Berditchevski.
import java.util.List;
import java.util.Map;

/**
 * Class representing Cos.
 *
 */
public class Cos extends UnaryExpression {
    /**
     * Constructor for Sin.
     * @param expression value of expression
     */
    public Cos(Expression expression) {
        super(expression);
    }
    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(e1.evaluate(assignment)));
    }

    /**
     * EA convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(e1.evaluate()));
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
        return "cos(" + e1.toString() + ")";
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
        return new Cos(this.e1.assign(var, expression));
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        Expression inner = e1;

        // Apply chain rule: differentiate the inner expression and multiply by the derivative of sin/cos
        Expression dInner = inner.differentiate(var);
        Expression derivative = new Neg(new Sin(inner));

        return new Mult(dInner, derivative);
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        Expression simplifiedInnerExp = e1.simplify();

        // Check if the inner expression is a constant/variable
        if (simplifiedInnerExp instanceof Num || simplifiedInnerExp instanceof Var) {
            try {
                double value = Math.sin(simplifiedInnerExp.evaluate());
                return new Num(value);
            } catch (Exception e) {
                // Exception when evaluating inner expression
                return new Sin(simplifiedInnerExp);
            }
        }

        // Check if inner expression is negative
        if (simplifiedInnerExp instanceof Neg) {
            // Simplify: cos(-x) = cos(x)
            return ((Neg) simplifiedInnerExp).e1.simplify();
        }

        // If none of the simplification rules succeed, return the original Cos
        return new Cos(simplifiedInnerExp);
    }
}
