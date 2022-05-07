// 316443902 nir koren
public class Main {
    public static void main(String[] args) throws Exception {
        Expression expression = new And(new Var("x"), new Var("y"));
        Expression expression2 = new And(new Var("z"), new Var("w"));
        expression = expression.assign("x", expression2);
        expression = expression.assign("x", expression2);
//        expression = expression.assign("y", new Val(true));
//        expression = expression.assign("z", new Val(true));
//        expression = expression.assign("w", new Val(true));
        System.out.println(expression.getVariables());
    }
}
