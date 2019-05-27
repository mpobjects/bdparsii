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

/**
 * Represents a reference to a variable.
 */
public final class VariableReference implements Expression {

    private Variable var;

    /**
     * Creates a new reference to the given variable.
     *
     * @param var the variable to access when this expression is evaluated
     */
    public VariableReference(Variable var) {
        this.var = var;
    }

    @Override
    public BigDecimal evaluate(MathContext mathContext) {
        return var.getValue();
    }

    @Override
    public String toString() {
        return var.getName();
    }

    @Override
    public boolean isConstant() {
        return var.isConstant();
    }

    @Override
    public Expression simplify(MathContext mathContext) {
        if (isConstant()) {
            return new Constant(evaluate(mathContext));
        } else {
            return this;
        }
    }
}
