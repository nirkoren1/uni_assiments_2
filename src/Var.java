// 316443902 nir koren

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Var implements Expression {
    private String stringRep;
    public Var(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return this.stringRep;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.stringRep)) {
            return expression;
        }
        return this;
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new RuntimeException("Variable " + this.stringRep + " not assigned");
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        throw new RuntimeException("Variable " + this.stringRep + " not assigned");
    }

    @Override
    public List<String> getVariables() {
        List<String> l = new ArrayList<>();
        l.add(this.stringRep);
        return l;
    }
    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
