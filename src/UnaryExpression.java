// 316443902 nir koren
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Abstract class representing the unary expressions.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression ex1;
    private String sign;
    private Expression ex1Nand;
    private Expression ex1Nor;
    /**
     * assign the letter given to the sign.
     * @param sign the sign of the logic.
     * @param ex1 the expression given
     */
    public UnaryExpression(Expression ex1, String sign) {
        this.ex1 = ex1;
        this.sign = sign;
    }
    @Override
    public String toString() {
        return this.sign + "(" + this.ex1.toString() + ")";
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        List<String> vars = this.getVariables();
        for (String var: vars) {
            this.ex1 = this.ex1.assign(var, new Val(assignment.get(var)));
        }
        return this.evaluate();
    }
    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.addAll(this.ex1.getVariables());
        return vars;
    }

    /**
     * @return the inner expression.
     */
    public Expression getEx1() {
        return ex1;
    }

    @Override
    public Expression simplify() {
        this.ex1 = ex1.simplify();
        return this;
    }

    @Override
    public Expression nandify() {
        this.ex1Nand = this.ex1.nandify();
        return null;
    }

    @Override
    public Expression norify() {
        this.ex1Nor = this.ex1.norify();
        return null;
    }

    /**
     * @return the nandified inner expression.
     */
    public Expression getEx1Nand() {
        return ex1Nand;
    }

    /**
     * @return the norified inner expression.
     */
    public Expression getEx1Nor() {
        return ex1Nor;
    }
}
