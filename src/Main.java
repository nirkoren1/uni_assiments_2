import java.util.Map;
import java.util.TreeMap;

// 316443902 nir koren
public class Main {
    public static void main(String[] args) throws Exception {
        Expression expression = new And(new Var("x"), new Var("y"));
        Expression expression2 = new And(new Var("z"), new Var("w"));
        Map<String, Boolean> assignment = new TreeMap<>();
        expression = expression.assign("x", expression2);
        assignment.put("y", true);
        assignment.put("z", false);
        assignment.put("w", true);
//        expression = expression.assign("x", expression2);
//        expression = expression.assign("y", new Val(true));
//        expression = expression.assign("z", new Val(true));
//        expression = expression.assign("w", new Val(true));
        System.out.println(expression.toString());
        System.out.println(expression.getVariables());
        System.out.println(expression.evaluate(assignment));
    }
}
