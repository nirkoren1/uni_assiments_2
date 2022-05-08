import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression {
    private Expression ex1;
    private String sign;
    public UnaryExpression(Expression ex1, String sign) {
        this.ex1 = ex1;
        this.sign = sign;
    }

    public abstract Boolean evaluate() throws Exception;

    public String toString() {
        return this.sign + "(" + this.ex1.toString() + ")";
    }

    public abstract Expression assign(String var, Expression expression);

    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        List<String> vars = this.getVariables();
        for (String var: vars) {
            this.ex1 = this.ex1.assign(var, new Val(assignment.get(var)));
        }
        return this.evaluate();
    }

    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.addAll(this.ex1.getVariables());
        return vars;
    }

    public Expression getEx1() {
        return ex1;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
