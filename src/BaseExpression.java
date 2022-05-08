//316443902 nir koren
public abstract class BaseExpression implements Expression {
    public abstract Boolean evaluate() throws Exception;
    public abstract Expression assign(String var, Expression expression);
}
