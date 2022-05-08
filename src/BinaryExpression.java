import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BinaryExpression extends BaseExpression {
    private Expression ex1;
    private Expression ex2;
    private String sign;
    private Expression ex1Nand;
    private Expression ex2Nand;
    private Expression ex1Nor;
    private Expression ex2Nor;

    public BinaryExpression(Expression ex1, Expression ex2, String sign) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.sign = sign;
    }

    public String toString() {
        return "(" + this.ex1.toString() + " " + this.sign + " " + this.ex2.toString() + ")";
    }

    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        List<String> vars = this.getVariables();
        for (String var: vars) {
            this.ex1 = this.ex1.assign(var, new Val(assignment.get(var)));
            this.ex2 = this.ex2.assign(var, new Val(assignment.get(var)));
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

    public Expression simplify() {
        this.ex1 = this.ex1.simplify();
        this.ex2 = this.ex2.simplify();
        return null;
    }

    @Override
    public Expression nandify() {
        System.out.println(this.ex1);
        this.ex1Nand = this.ex1.nandify();
        this.ex2Nand = this.ex2.nandify();
        return null;
    }

    @Override
    public Expression norify() {
        this.ex1Nor = this.ex1.norify();
        this.ex2Nor = this.ex2.norify();
        return null;
    }

    public Expression getEx1Nand() {
        return ex1Nand;
    }

    public Expression getEx2Nand() {
        return ex2Nand;
    }

    public Expression getEx1Nor() {
        return ex1Nor;
    }

    public Expression getEx2Nor() {
        return ex2Nor;
    }
}
