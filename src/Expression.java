// 316443902 nir koren
import java.util.List;
import java.util.Map;

/**
 * Interface for all the expressions.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment Map object of the string to the boolean value
     * @return the boolean value of the expression according to the assignment given
     * @throws Exception if at least one of the vars is not assigned
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     * @return the boolean value of the expression
     * @throws Exception if at least one of the vars is not assigned
     */
    Boolean evaluate() throws Exception;

    /**
     * @return List of the variables in the expression
     */
    List<String> getVariables();

    /**
     * @return String representation of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var String of the var to assign
     * @param expression expression assigned to the var
     * @return the new expression assigned to the var
     */
    Expression assign(String var, Expression expression);

    // Returns the expression tree resulting from converting all the operations to the logical Nand operation.

    /**
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * @return Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}
