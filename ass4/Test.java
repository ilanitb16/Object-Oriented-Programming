public class Test {
    public static void main(string [] args){
        Expression e = new Sin(
                new Pow(
                        new Mul(
                                new Plus(
                                        new Mul(new Num(2), new Var("x")),
                                        new Var("y")),
                                new Num(4)),
                        new Var("x")));

    }
}
