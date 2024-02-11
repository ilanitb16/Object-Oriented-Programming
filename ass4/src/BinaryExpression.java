// 322453200 Ilanit Berditchevski.
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class representing BinaryExpression.
 *
 */

public abstract class BinaryExpression extends BaseExpression {
    protected Expression e2;

    /**
     * Constructor for BinaryExpression.
     * @param e1 value of expression
     * @param e2 value of expression
     */
    public BinaryExpression(Expression e1, Expression e2) {
        super(e1);
        this.e2 = e2;
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double leftVal = e1.evaluate(assignment);
        double rightVal = e2.evaluate(assignment);
        return applyOperator(leftVal, rightVal);
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() throws Exception {
        double leftVal = e1.evaluate();
        double rightVal = e2.evaluate();
        return applyOperator(leftVal, rightVal);
    }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.addAll(e1.getVariables());
        variables.addAll(e2.getVariables());
        return variables;
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
//        if (e1 instanceof Num && e2 instanceof Num) {
//           return "("+ this.applyOperator(((Num) e1).getNum(),
//                ((Num) e2).getNum())+ ")";
//        }
        return "(" + e1.toString() + " " + getOperator() + " " + e2.toString() + ")";
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
        return createExpression(e1.assign(var, expression),
                e2.assign(var, expression));
    }

    /**
     * Applies operator.
     * @param  e1 first expression
     * @param  e2 second expression
     * @return  result
     */
    protected abstract double applyOperator(double e1, double e2);

    /**
     * Returns the operator.
     * @return the operator
     */
    protected abstract String getOperator();

    /**
     * Returns a new expression.
     * @param e1 first expression
     * @param e2 second expression
     * @return  a new expression
     */
    protected abstract Expression createExpression(Expression e1,
                                                   Expression e2);

}

