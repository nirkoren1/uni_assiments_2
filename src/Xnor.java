public class Xnor extends BinaryExpression {
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
        return new Nand(new Or(getEx1(), getEx2()).nandify(), new Nand(getEx1(), getEx2()));
    }

    @Override
    public Expression norify() {
        Nor nor = new Nor(getEx1(), getEx2());
        return new Nor(new Nor(getEx1(), nor), new Nor(getEx2(), nor));
    }
}
