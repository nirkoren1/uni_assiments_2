// 316443902 nir koren

public class And extends BinaryExpression {
    public And(Expression ex1, Expression ex2) {
        super(ex1, ex2, "&");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getEx1().evaluate() && getEx2().evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(getEx1().assign(var, expression), getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getEx1(), getEx2()), new Nand(getEx1(), getEx2()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getEx1(), getEx1()), new Nor(getEx2(), getEx2()));
    }

    @Override
    public Expression simplify() {
        super.simplify();
        if (getEx2().toString().equals(getEx1().toString())) {
            return getEx1();
        }
        try {
            if (getEx2().evaluate()) {
                return getEx1();
            } else {
                return new Val(false);
            }
        } catch (Exception e) {
            try {
                if (getEx1().evaluate()) {
                    return getEx2();
                } else {
                    return new Val(false);
                }
            } catch (Exception e2) {
                return this;
            }
        }
    }
}
