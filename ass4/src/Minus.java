// 322453200 Ilanit Berditchevski.
import java.util.Map;
/**
 * Class representing Minus operation.
 */
public class Minus extends BinaryExpression {

    /**
     * Constructor for Minus.
     * @param e1 value of expression
     * @param e2 value of expression
     */
    public Minus(Expression e1, Expression e2) {
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
        double leftValue = e1.evaluate(assignment);
        double rightValue = e2.evaluate(assignment);
        return leftValue - rightValue;
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        double leftValue = e1.evaluate();
        double rightValue = e2.evaluate();
        return leftValue - rightValue;
    }

    /**
     * Returns the operator.
     * @return the operator
     */
    public String getOperator() {
        return "-";
    }

    /**
     * Applies operator.
     * @param x first expression
     * @param y second expression
     * @return  result
     */
    public double applyOperator(double x, double y) {
        return x - y;
    }

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    public Expression createExpression(Expression e1, Expression e2) {
        return new Minus(e1, e2);
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        return new Minus(e1.differentiate(var), e2.differentiate(var));
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        // Simplify left and right expressions
        Expression leftExpr = e1.simplify();
        Expression rightExpr = e2.simplify();

        // check if expressions are equal
        if (leftExpr.equals(rightExpr)) {
            return new Num(0);
        }

        // Simplify expression
        if (leftExpr instanceof Num && rightExpr instanceof Num) {
            double leftValue = ((Num) leftExpr).getNum();
            double rightValue = ((Num) rightExpr).getNum();
            double resultValue = leftValue - rightValue;
            return new Num(resultValue);
        } else if (rightExpr instanceof Num && ((Num) rightExpr).getNum() == 0) {
            return leftExpr;
        } else if (leftExpr instanceof Num && ((Num) leftExpr).getNum() == 0) {
            return new Mult(new Num(-1), rightExpr);
        }

        return new Minus(leftExpr, rightExpr);
    }
}
