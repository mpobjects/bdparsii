/*
 * Made with all the love in the world
 * by scireum in Remshalden, Germany
 *
 * Copyright by scireum GmbH
 * http://www.scireum.de - info@scireum.de
 */

package com.mpobjects.bdparsii.eval;

import java.math.BigDecimal;

/**
 * Represents a constant numeric expression.
 */
public class Constant extends Expression {
    private static final long serialVersionUID = 7461494011371773146L;

    private BigDecimal value;

    /**
     * Used as dummy expression by the parser if an error occurs while parsing.
     */
    public static final Constant EMPTY = new Constant(null);

    public Constant(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
