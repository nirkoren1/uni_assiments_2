import java.util.Map;
import java.util.TreeMap;

// 316443902 nir koren
public class Main {
    public static void main(String[] args) throws Exception {
//        Expression expression = new And(new Var("x"), new Var("y"));
//        Expression expression2 = new Not(
//                new Xor(
//                        new And(
//                                new Val(true),
//                                new Or(
//                                        new Var("x"),
//                                        new Var("y")
//                                )
//                        ),
//                        new Var("x")
//                )
//        );
//        Map<String, Boolean> assignment = new TreeMap<>();
//        expression = expression.assign("x", expression2);
//        assignment.put("y", true);
//        assignment.put("x", true);
//        assignment.put("w", true);
//        expression = expression.assign("x", expression2);
//        expression = expression.assign("y", new Val(true));
//        expression = expression.assign("z", new Val(true));
//        expression = expression.assign("w", new Val(true));
//        System.out.println(expression.toString());
//        System.out.println(expression.getVariables());
//        System.out.println(expression.evaluate(assignment));
        Expression e = new And(new Var("x"), new Var("y"));
        Expression e2 = new And(new Var("w"), new Var("z"));
        e = e.assign("x", e2);
        System.out.println(e);
        e = e.assign("w", new Val(true));
        System.out.println(e);
        System.out.println(e.simplify());
    }
}
