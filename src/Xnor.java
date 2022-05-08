// 316443902 nir koren

/**
 * Class for the Xnor logic expression.
 */
public class Xnor extends BinaryExpression {
    /**
     * assign the letter # to the sign memory.
     * @param ex1 first expression
     * @param ex2 second expression
     */
    public Xnor(Expression ex1, Expression ex2) {
        super(ex1, ex2, "#");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getEx1().evaluate() == getEx2().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(getEx1().assign(var, expression), getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        super.nandify();
        return new Nand(new Or(getEx1Nand(), getEx2Nand()).nandify(), new Nand(getEx1Nand(), getEx2Nand()));
    }

    @Override
    public Expression norify() {
        super.norify();
        Nor nor = new Nor(getEx1Nor(), getEx2Nor());
        return new Nor(new Nor(getEx1Nor(), nor), new Nor(getEx2Nor(), nor));
    }

    @Override
    public Expression simplify() {
        super.simplify();
        if (getEx2().toString().equals(getEx1().toString())) {
            return new Val(true);
        }
        return this;
    }
}
