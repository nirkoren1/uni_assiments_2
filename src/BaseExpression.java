//316443902 nir koren

/**
 * This class represents a basic expression.
 */
public abstract class BaseExpression implements Expression {
    @Override
    public abstract Boolean evaluate() throws Exception;
    @Override
    public abstract Expression assign(String var, Expression expression);
}
