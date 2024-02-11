// 322453200 Ilanit Berditchevski.
import java.util.Map;
/**
 * Class representing Plus operation.
 */
public class Plus extends BinaryExpression {
    /**
     * Constructor for Plus.
     * @param e1 value of expression
     * @param e2 value of expression
     */
    public Plus(Expression e1, Expression e2) {
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
        return leftValue + rightValue;
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        double leftValue = e1.evaluate();
        double rightValue = e2.evaluate();
        return leftValue + rightValue;
    }

    /**
     * Returns the operator.
     * @return the operator
     */
    public String getOperator() {
        return "+";
    }

    /**
     * Applies operator.
     * @param x first expression
     * @param y second expression
     * @return  result
     */
    public double applyOperator(double x, double y) {
        return x + y;
    }

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    public Expression createExpression(Expression e1, Expression e2) {
        return new Plus(e1, e2);
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        return new Plus(e1.differentiate(var), e2.differentiate(var));
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        // Simplify the left and right expressions
        Expression leftExpr = e1.simplify();
        Expression rightExpr = e2.simplify();

        // Simplify the expression if possible
        if (leftExpr instanceof Num && rightExpr instanceof Num) {
            double leftValue = ((Num) leftExpr).getNum();
            double rightValue = ((Num) rightExpr).getNum();
            double resultValue = leftValue + rightValue;

            return new Num(resultValue);
        } else if (leftExpr instanceof Num && ((Num) leftExpr).getNum() == 0) {
            return rightExpr;
        } else if (rightExpr instanceof Num && ((Num) rightExpr).getNum() == 0) {
            return leftExpr;
        }

        return new Plus(leftExpr, rightExpr);
    }

    /**
     * Determines if two expressions are equal or not.
     * @param expression the variable-value
     * @return true or false
     */
    @Override
    public boolean equals(Expression expression) {
        String expStr = expression.toString();

        return toString() == expStr || new Plus(e2, e1).toString() == expStr;
    }
}
