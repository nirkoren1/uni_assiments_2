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
        Nand nand = new Nand(getEx1(), getEx2());
        return new Nand(new Nand(getEx1(), nand), new Nand(getEx2(), nand));
    }

    @Override
    public Expression norify() {
        return new Nor(new And(getEx1(), getEx2()).norify(), new Nor(getEx1(), getEx2()));
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
