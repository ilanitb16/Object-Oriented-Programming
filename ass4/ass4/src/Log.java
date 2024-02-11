// 322453200 Ilanit Berditchevski.
import java.util.Map;

/**
 * Class representing Log.
 *
 */
public class Log extends BinaryExpression {

    /**
     * Constructor for Log.
     * @param e1 value of expression
     * @param e2 value of expression
     */
    public Log(Expression e1, Expression e2) {
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
        double argumentValue = e2.evaluate(assignment);
        return Math.log(argumentValue) / Math.log(baseValue);
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        double baseValue = e1.evaluate();
        double argumentValue = e2.evaluate();
        return Math.log(argumentValue) / Math.log(baseValue);
    }

    /**
     * Applies operator.
     * @param x first expression
     * @param y second expression
     * @return  result
     */
    protected double applyOperator(double x, double y) {
        return Math.log(x) / Math.log(y);
    }

    /**
     * Returns the operator.
     * @return the operator
     */
    protected String getOperator() {
        return "log";
    }

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    protected Expression createExpression(Expression e1, Expression e2) {
        return new Log(e1, e2);
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
        Expression argument = e2;
        Num e = new Num(Math.E);
        Expression numerator = argument.differentiate(var);
        Expression denominator = new Mult(argument, new Log(e, base));
        return new Div(numerator, denominator);
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
        return "log(" + e1.toString() + ", " + e2.toString() + ")";
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        // Simplify the base and argument expressions
        Expression base = e1.simplify();
        Expression argument = e2.simplify();

        // check if expressions are equal
        if (equals(argument)) {
            return new Num(1);
        }

        // Simplify the expression if possible
        if (base instanceof Num && argument instanceof Num) {
            double baseValue = ((Num) base).getNum();
            double argumentValue = ((Num) argument).getNum();
            double resultValue = Math.log(argumentValue) / Math.log(baseValue);
            return new Num(resultValue);
        }

        return new Log(base, argument);
    }
}
