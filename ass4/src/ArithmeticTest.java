// 322453200 Ilanit Berditchevski.

/**
 * Class representing ArithmeticTest.
 *
 */
public class ArithmeticTest {
    public static void main(String[] args) {
        Expression e = new Sin(new Pow(new Mult(new Plus(
                new Mult(new Num(2.0), new Var("x")),
                new Var("y")), new Num(4.0)),
                new Var("x"))); //sin(((2x + y) * 4)^x)

        Expression e2 = new Pow(new Plus(new Var("x"),
                new Var("y")), new Num(2.0)); //(x + y)^2

        Expression e4 = new Pow(new Minus(new Mult(new Num(2.0),
                new Num(8.0)), new Num(6.0)), new Num(2.0)); //((2*8)-6)^2

        Expression log = new Log(new Num(2.0), new Num(8.0));

        Expression e6 = new Pow(new Var("e"), new Var("x"));

        Expression e7 = new Log(new Var("e"), new Var("e"));

        differentiate();
    }
    public static void differentiate () {
        //some are log differentiated differently
        Expression e = new Pow(new Var("x"), new Num(4.0));
        System.out.println(e.differentiate("x").simplify().toString());
        System.out.println(
                "((x^4.0) * ((4.0 * (1.0 / (x * log(e, e))))"
                        + " + (0.0 * log(e, x)" + ")))");
        System.out.println();

        e = new Mult(new Var("x"), new Log(new Var("e"), new Var("x")));
        System.out.println(e.differentiate("x").simplify().toString());
        System.out.println(
                "((x * (1.0 / (x * log(e, e)))) + (1.0 * log(e, x)))");
        System.out.println();

        Expression e1 = new Sin(new Var("x"));
        System.out.println(e1.differentiate("x").toString());
        System.out.println("(cos(x) * 1.0)");
        System.out.println();

        e1 = new Cos(new Var("x"));
        System.out.println(e1.differentiate("x").toString());
        System.out.println("(-(sin(x) * 1.0))");
        e1 = new Neg(new Var("x"));
//        Assertions.assertEquals(e1.differentiate("x").toString(),
//                "(-1.0)");
//        Assertions.assertEquals(e1.differentiate("y").toString(),
//                "(-0.0)");
//        Expression e3 = new Pow(new Plus(new Var("x"), new Var("y")),
//                new Num(2.0));
//        System.out.println(e3.differentiate("x"));
//        Expression e4 = e3.differentiate("x");
        // the result is:
        // (((x + y) ^ 2.0)(((1.0 + 0.0)(2.0 / (x + y))) + (0.0 * log(e, (x + y)))))


        /// ((cos(x)) + log(e,x)) + (1/x + sin (x^2)))

        Expression exp = new Plus(new Plus(new Cos(new Var("x")), new Log(e,
                new Var("x")), new Plus(new Div(new Num(1),new Var("x")),
                new Sin(new Pow(new Var("x"), new Num(2)));
    }

}
