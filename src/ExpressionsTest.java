// 316443902 nir koren

import java.util.Map;
import java.util.TreeMap;

/**
 * This is a test class for the expression methods.
 */
public class ExpressionsTest {
    /**
     * @param args from the cmd
     * @throws Exception if at least one var is not assigned, and the expression is evaluated.
     */
    public static void main(String[] args) throws Exception {
        Expression expression = new Xnor(new Var("x"), new Var("y"));
        Expression expression2 = new Not(
                new Xor(
                        new And(
                                new Val(true),
                                new Or(
                                        new Var("x"),
                                        new Var("y")
                                )
                        ),
                        new Var("w")
                )
        );
        expression = expression.assign("x", expression2);
        System.out.println(expression);
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("y", true);
        assignment.put("x", false);
        assignment.put("w", true);
        Expression nandified = expression.nandify();
        Expression norified = expression.norify();
        System.out.println(expression.evaluate(assignment));
        System.out.println(nandified);
        System.out.println(norified);
        System.out.println(expression.simplify());
    }
}
