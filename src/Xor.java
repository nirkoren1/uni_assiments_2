// 316443902 nir koren
public class Xor extends BinaryExpression {
    public Xor(Expression ex1, Expression ex2) {
        super(ex1, ex2, "^");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getEx1().evaluate() ^ getEx2().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(getEx1().assign(var, expression), getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        super.nandify();
        Nand nand = new Nand(getEx1Nand(), getEx2Nand());
        return new Nand(new Nand(getEx1Nand(), nand), new Nand(getEx2Nand(), nand));
    }

    @Override
    public Expression norify() {
        super.norify();
        return new Nor(new And(getEx1Nor(), getEx2Nor()).norify(), new Nor(getEx1Nor(), getEx2Nor()));
    }

    @Override
    public Expression simplify() {
        super.simplify();
        if (getEx2().toString().equals(getEx1().toString())) {
            return new Val(false);
        }
        try {
            if (getEx2().evaluate()) {
                return new Not(getEx1());
            } else {
                return getEx1();
            }
        } catch (Exception e) {
            try {
                if (getEx1().evaluate()) {
                    return new Not(getEx2());
                } else {
                    return getEx2();
                }
            } catch (Exception e2) {
                return this;
            }
        }
    }
}
