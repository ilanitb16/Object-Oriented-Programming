// 322453200 Ilanit Berditchevski.
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Class representing a number.
 *
 */
public class Num implements Expression {
    private double num = 0;

    /**
     * Constructor for Num.
     * @param num  value of number.
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * Evaluate the expression using the variable values provided in the
     * assignment, and return the result.  If the expression contains a
     * variable which is not in the assignment, an exception is thrown.
     * @param assignment the variable-value assignment
     * @return result
     */
    public double evaluate(Map<String, Double> assignment) {
        return num;
    }

    /**
     * Evaluate the expression but uses an empty assignment.
     * @return result
     */
    public double evaluate() {
        return num;
    }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * Returns a nice string representation of the expression.
     * @return string representation of the expression.
     */
    public String toString() {
       return Double.toString(num);
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
        return new Num(0); // Derivative of a constant number is 0
    }

    /**
     * Returned the number value.
     * @return number value.
     */
    public double getNum() {
        return this.num;
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
        if (expression instanceof Num && ((Num) expression).getNum() == num) {
            return true;
        }
        return false;

    }
}
