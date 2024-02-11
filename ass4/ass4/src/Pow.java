// 322453200 Ilanit Berditchevski.
import java.util.Map;

/**
 * Class representing Pow.
 *
 */
public class Pow extends BinaryExpression {

    /**
     * Constructor for Pow.
     * @param e1 value of expression
     * @param e2 value of expression
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double baseValue = e1.evaluate(assignment);
        double exponentValue = e2.evaluate(assignment);
        return Math.pow(baseValue, exponentValue);
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        double baseValue = e1.evaluate();
        double exponentValue = e2.evaluate();
        return Math.pow(baseValue, exponentValue);
    }

    /**
     * Returns the operator.
     * @return the operator
     */
    public String getOperator() {
        return "^";
    }

    /**
     * Applies operator.
     * @param x first expression
     * @param y second expression
     * @return  result
     */
    public double applyOperator(double x, double y) {
        return Math.pow(x, y);
    }

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    public Expression createExpression(Expression e1, Expression e2) {
        return new Pow(e1, e2);
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        Expression base = e1;
        Expression exponent = e2;

        Expression dBase = base.differentiate(var);
        Expression dExponent = exponent.differentiate(var);

        // (g*f')/f
        Expression p1 = new Mult(dBase, new Div(exponent, base));

        // g' * ln(f)
        Expression p2 = new Mult(dExponent, new Log(new Var("e"), base));

        Expression p3 = new Pow(base, exponent);

        Expression ex1 = new Plus(p1, p2);

        Expression result = new Mult(p3, ex1);

        return result;
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
        return  "(" + e1.toString() + "^" + e2.toString() + ")";
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        Expression base = e1;
        Expression exponent = e2;

        // Simplify the base and exponent expressions
        base = base.simplify();
        exponent = exponent.simplify();

        // Simplify the expression if possible
        if (base instanceof Num && exponent instanceof Num) {
            double baseValue = ((Num) base).getNum();
            double exponentValue = ((Num) exponent).getNum();
            double resultValue = Math.pow(baseValue, exponentValue);
            return new Num(resultValue);
        } else if (base instanceof Num && ((Num) base).getNum() == 1) {
            return new Num(1);
        } else if (exponent instanceof Num && ((Num) exponent).getNum() == 0) {
            return new Num(1);
        }

        return new Pow(base, exponent);
    }
}
