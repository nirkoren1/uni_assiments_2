// 316443902 nir koren

public class And extends BinaryExpression{
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
}
