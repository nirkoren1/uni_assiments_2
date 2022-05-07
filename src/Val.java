// 316443902 nir koren

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Val implements Expression {
    private Boolean valBool;
    public Val(Boolean bool) {
        this.valBool = bool;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.valBool;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return null;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public String toString() {
        if (valBool) {
            return "T";
        } else {
            return "F";
        }
    }
}
