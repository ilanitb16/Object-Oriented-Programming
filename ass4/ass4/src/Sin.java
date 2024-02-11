// 322453200 Ilanit Berditchevski.
import java.util.List;
import java.util.Map;

/**
 * Class representing Sin.
 *
 */
public class Sin extends UnaryExpression {
    protected Expression expression;

    /**
     * Constructor for Sin.
     * @param expression expression
     */
    public Sin(Expression expression) {
        super(expression);
        this.expression = expression;
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(expression.evaluate(assignment)));
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(expression.evaluate()));
    }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return expression.getVariables();
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
        return "sin(" + expression.toString() + ")";
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
        return new Sin(this.expression.assign(var, expression));
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        Expression inner = expression;

        // Apply chain rule: differentiate the inner expression and multiply by the derivative of sin/cos
        Expression dInner = inner.differentiate(var);
        Expression derivative = new Cos(inner);

        return new Mult(dInner, derivative);
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        Expression simplifiedInnerExp = expression.simplify();

        // Check if the inner expression is a constant or a variable
        if (simplifiedInnerExp instanceof Num || simplifiedInnerExp instanceof Var) {
            try {
                double value = Math.sin(simplifiedInnerExp.evaluate());
                return new Num(value);
            } catch (Exception e) {
                // Exception while evaluating inner expression
                return new Sin(simplifiedInnerExp);
            }
        }

        // Check if inner expression is negative
        if (simplifiedInnerExp instanceof Neg) {
            // Simplify: sin(-x) = -sin(x)
            Expression inner =
                    ((Neg) simplifiedInnerExp).e1.simplify();
            return new Neg(new Sin(inner));
        }

        // The inner expression is not a constant/variable
         return new Sin(simplifiedInnerExp);
    }
}
