// 322453200 Ilanit Berditchevski.
import java.util.Map;
import java.util.TreeMap;
/**
 * Class representing Test.
 *
 */
public class ExpressionsTest {
    /**
     * Main class.
     * @param args arguments
     *
     */
    public static void main(String[] args) {

        Expression linear =  new Mult(new Num(2), new Var("x"));
        Expression sin = new Sin(new Mult(new Num(4), new Var("y")));
        Expression power = new Pow(new Var("e"), new Var("x"));

        Expression expression = new Plus(new Plus(linear, sin), power);

        linear.toString();
        sin.toString();
        power.toString();

        System.out.println(expression.toString());

        // Evaluate the expression with (x=2, y=0.25, e=2.71)
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);

        try {
            double value = expression.evaluate(assignment);
            System.out.println(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Expression differentiated = expression.differentiate("x");

        System.out.println(differentiated.simplify().toString());

        try {
            // Evaluate the differentiated expression with (x=2, y=0.25, e=2.71)
            double diffValue = differentiated.evaluate(assignment);
            System.out.println(diffValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Simplify expression
        Expression simplified = differentiated.simplify();

        System.out.println(simplified.toString());


//        Expression: ((2 * x) + sin((4 * y))) + exp(x)
//        Expression value: 12.46382907818432
//        Differentiated expression (according to x): ((2 * 1) + cos((4 * y))) + exp(x)
//        Differentiated expression value (according to x): 7.3890560989306495
//        Simplified differentiated expression: (2 + cos((4 * y))) + exp(x)
    }
}
