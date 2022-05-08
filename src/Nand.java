// 316443902 nir koren
public class Nand extends BinaryExpression {
    public Nand(Expression ex1, Expression ex2) {
        super(ex1, ex2, "A");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return !(getEx1().evaluate() && getEx2().evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(getEx1().assign(var, expression), getEx2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return new Nor(new And(getEx1(), getEx2()).norify(), new And(getEx1(), getEx2()).norify());
    }

    @Override
    public Expression simplify() {
        super.simplify();
        if (getEx2().toString().equals(getEx1().toString())) {
            return new Not(getEx1());
        }
        try {
            if (getEx2().evaluate()) {
                return new Not(getEx1());
            } else {
                return new Val(true);
            }
        } catch (Exception e) {
            try {
                if (getEx1().evaluate()) {
                    return new Not(getEx2());
                } else {
                    return new Val(true);
                }
            } catch (Exception e2) {
                return this;
            }
        }
    }
}
