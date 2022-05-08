// 316443902 nir koren

/**
 * Class represents of the Nor logic expression.
 */
public class Nor extends BinaryExpression {
    /**
     * assign the letter V to the sign.
     * @param ex1 first expression
     * @param ex2 second expression
     */
    public Nor(Expression ex1, Expression ex2) {
        super(ex1, ex2, "V");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(getEx1().evaluate() || getEx2().evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(getEx1().assign(var, expression), getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        super.nandify();
        return new Nand(new Or(getEx1Nand(), getEx2Nand()).nandify(), new Or(getEx1Nand(), getEx2Nand()).nandify());
    }

    @Override
    public Expression norify() {
        super.norify();
        return this;
    }

    @Override
    public Expression simplify() {
        super.simplify();
        if (getEx2().toString().equals(getEx1().toString())) {
            return new Not(getEx1());
        }
        try {
            if (getEx2().evaluate()) {
                return new Val(false);
            } else {
                return new Not(getEx1());
            }
        } catch (Exception e) {
            try {
                if (getEx1().evaluate()) {
                    return new Val(false);
                } else {
                    return new Not(getEx2());
                }
            } catch (Exception e2) {
                return this;
            }
        }
    }
}
