import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BinaryExpression extends BaseExpression {
    private Expression ex1;
    private Expression ex2;
    private String sign;
    public BinaryExpression(Expression ex1, Expression ex2, String sign) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.sign = sign;
    }

    public abstract Boolean evaluate() throws Exception;

    public String toString() {
        return "(" + this.ex1.toString() + " " + this.sign + " " + this.ex2.toString() + ")";
    }

    public Expression assign(String var, Expression expression) {
        return new And(this.ex1.assign(var, expression), this.ex2.assign(var, expression));
    }

    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        List<String> vars = this.getVariables();
        for (String var: vars) {
            this.ex1.assign(var, new Val(assignment.get(var)));
            this.ex2.assign(var, new Val(assignment.get(var)));
        }
        return this.evaluate();
    }

    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.addAll(this.ex1.getVariables());
        vars.addAll(this.ex2.getVariables());
        return vars;
    }

    public Expression getEx1() {
        return ex1;
    }

    public Expression getEx2() {
        return ex2;
    }
}
