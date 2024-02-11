// 322453200 Ilanit Berditchevski.

/**
 * Class representing Div.
 *
 */
public class Div extends BinaryExpression {

    /**
     * Constructor for Div.
     * @param e1 first expression
     * @param e2 second expression
     */
    public Div(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Returns the operator.
     * @return the operator
     */
    public String getOperator() {
        return "/";
    }

    /**
     * Applies operator.
     * @param x first expression
     * @param y second expression
     * @return  result
     */
    public double applyOperator(double x, double y) {
        return x / y;
    }

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    public Expression createExpression(Expression e1, Expression e2) {
        return new Div(e1, e2);
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        Expression numerator = new Minus(new Mult(e1.differentiate(var), e2),
                new Mult(e1, e2.differentiate(var)));
        Expression denominator = new Pow(e2, new Num(2));
        return new Div(numerator, denominator);
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

        // check if expressions are equal
        if (leftExpr.equals(rightExpr)) {
            return new Num(1);
        }

        // Simplify the expression if possible
        if (leftExpr instanceof Num && rightExpr instanceof Num) {
            double leftValue = ((Num) leftExpr).getNum();
            double rightValue = ((Num) rightExpr).getNum();
            double resultValue = leftValue / rightValue;
            return new Num(resultValue);
        } else if (leftExpr instanceof Num && ((Num) leftExpr).getNum() == 0) {
            return new Num(0);
//        } else if (rightExpr instanceof Num && ((Num) rightExpr).getNum() == 0) {
//            throw new Exception("Division by zero");
        } else if (rightExpr instanceof Num && ((Num) rightExpr).getNum() == 1) {
            return leftExpr;
        }

        return new Div(leftExpr, rightExpr);
    }
}
