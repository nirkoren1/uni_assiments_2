// 316443902 nir koren

public class And extends BinaryExpression{
    public And(Expression ex1, Expression ex2) {
        super(ex1, ex2, "&");
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getEx1().evaluate() && getEx2().evaluate();
    }
}
