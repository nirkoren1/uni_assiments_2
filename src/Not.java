// 316443902 nir koren

/**
 * Class of the Not logic expression.
 */
public class Not extends UnaryExpression {
    /**
     * assign the letter ~ to the sign.
     * @param ex1 first expression
     */
    public Not(Expression ex1) {
        super(ex1, "~");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !getEx1().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getEx1().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        super.nandify();
        return new Nand(getEx1Nand(), getEx1Nand());
    }

    @Override
    public Expression norify() {
        super.norify();
        return new Nor(getEx1Nor(), getEx1Nor());
    }
}
