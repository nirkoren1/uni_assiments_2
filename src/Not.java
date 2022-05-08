// 316443902 nir koren
public class Not extends UnaryExpression {
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
        return new Nand(getEx1(), getEx1());
    }

    @Override
    public Expression norify() {
        return new Nor(getEx1(), getEx1());
    }
}
