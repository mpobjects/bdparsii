/*
 * Made with all the love in the world
 * by scireum in Remshalden, Germany
 *
 * Copyright by scireum GmbH
 * http://www.scireum.de - info@scireum.de
 */

package com.mpobjects.bdparsii.eval;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the invocation of a function.
 */
public final class FunctionCall extends AbstractExpression {

    private List<Expression> parameters = new ArrayList<>();
    private Function function;

    /**
     * Create a function call expression.
     *
     * @param mathContext the math context
     */
    public FunctionCall(MathContext mathContext) {
        setMathContext(mathContext);
    }

    @Override
    public BigDecimal evaluate(MathContext mathContext) {
        return function.eval(parameters, mathContext);
    }

    @Override
    public Expression simplify(MathContext mathContext) {
        if (!function.isNaturalFunction()) {
            return this;
        }
        for (Expression expr : parameters) {
            if (!expr.isConstant()) {
                return this;
            }
        }
        return new Constant(evaluate(mathContext));
    }

    /**
     * Sets the function to evaluate.
     *
     * @param function the function to evaluate
     */
    public void setFunction(Function function) {
        this.function = function;
    }

    /**
     * Adds an expression as parameter.
     *
     * @param expression the parameter to add
     */
    public void addParameter(Expression expression) {
        parameters.add(expression);
    }

    /**
     * Returns all parameters added so far.
     *
     * @return a list of parameters added to this call
     */
    public List<Expression> getParameters() {
        return parameters;
    }

}
