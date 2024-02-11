// 322453200 Ilanit Berditchevski.
/**
 * Class representing Multiplication.
 *
 */
public class Mult extends BinaryExpression {

    /**
     * Constructor for Multiplication.
     * @param e1 value of expression
     * @param e2 value of expression
     */
    public Mult(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Returns the operator.
     * @return the operator
     */
    public String getOperator() {
        return "*";
    }

    /**
     * Applies operator.
     * @param x first expression
     * @param y second expression
     * @return  result
     */
    public double applyOperator(double x, double y) {
        return x * y;
    }

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    public Expression createExpression(Expression e1, Expression e2) {
        return new Mult(e1, e2);
    }

    /**
     * Returns the expression resulting from differentiating
     * the current expression relative to variable `var`.
     * current expression).
     * @param var the variable-value
     * @return  a new expression
     */
    public Expression differentiate(String var) {
        return new Plus(new Mult(e1.differentiate(var), e2), new Mult(e1,
                e2.differentiate(var)));
    }

    /**
     * Returned a simplified version of the current expression.
     * current expression).
     * @return simplified expression
     */
    public Expression simplify() {
        Expression leftExpr = e1;
        Expression rightExpr = e2;

        // Simplify the left and right expressions
        leftExpr = leftExpr.simplify();
        rightExpr = rightExpr.simplify();

        // Simplify the expression if possible
        if (leftExpr instanceof Num && rightExpr instanceof Num) {
            double leftValue = ((Num) leftExpr).getNum();
            double rightValue = ((Num) rightExpr).getNum();
            double resultValue = leftValue * rightValue;
            return new Num(resultValue);
        } else if (leftExpr instanceof Num && ((Num) leftExpr).getNum() == 0
                || rightExpr instanceof Num && ((Num) rightExpr).getNum() == 0) {
            return new Num(0);
        } else if (leftExpr instanceof Num && ((Num) leftExpr).getNum() == 1) {
            return rightExpr;
        } else if (rightExpr instanceof Num && ((Num) rightExpr).getNum() == 1) {
            return leftExpr;
        }
        return new Mult(leftExpr, rightExpr);
    }

    /**
     * Determines if two expressions are equal or not.
     * @param expression the variable-value
     * @return true or false
     */
    @Override
    public boolean equals(Expression expression) {
        String expStr = expression.toString();

        return toString() == expStr || new Mult(e2, e1).toString() == expStr;
    }
}
