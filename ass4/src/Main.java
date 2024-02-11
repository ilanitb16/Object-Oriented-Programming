import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
//        Expression e = new Sin(
//                new Pow(
//                        new Mul(
//                                new Plus(
//                                        new Mul(new Num(2), new Var("x")),
//                                        new Var("y")),
//                                new Num(4)),
//                        new Var("x")));
//
//        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
//        String s = e2.toString();
//        System.out.println(s);
//
//        List<String> vars = e2.getVariables();
//        for (String v : vars) {
//            System.out.println(v);
//        }
//
//        Expression e3 = e2.assign("y", e2);
//        System.out.println(e3);
//        // (x + ((x + y)^2))^2
//
//        e3 = e3.assign("x", new Num(1));
//        System.out.println(e3);
//        // (1 + ((1 + y)^2))^2
//
//        Map<String, Double> assignment = new TreeMap<String, Double>();
//        assignment.put("x", 2.0);
//        assignment.put("y", 4.0);
//
//        try {
//            double value = e2.evaluate(assignment);
//            System.out.println("The result is: " + (int)value);
//        } catch (Exception x) {
//            System.out.println("Failed evaluating the expression: "
//                    + x.getMessage());
//        }
//
//        System.out.println();
//
//        Expression e4 = new Pow(new Var("x"), new Num(4));
//        Expression de = e4.differentiate("x");
//        System.out.println(de); // we expect to see 4*(x^3)
//        // but seeing: ((x ^ 4.0) * ((1.0 * (4.0 / x)) + (0.0 * log(e, x))))
//        // is also fine, as it is equivalent (we will improve it in the next part).
//
//        System.out.println();

//        Expression e5 = new Pow(new Plus(new Var("x"), new Var("y")),
//                new Num(2));
//        System.out.println(e5.differentiate("x"));
//        // the result is:
//        // (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
//
//        System.out.println(e5.differentiate("x").simplify());
//        // the result is:
//        // (((x + y) ^ 2.0) * (2.0 / (x + y)))

        System.out.println();
        Expression e6 = new Pow(new Var("e"), new Var("x"));
        System.out.println(e6.differentiate("x"));
        // ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e6.differentiate("x").simplify());
        // (e ^ x)
    }
}