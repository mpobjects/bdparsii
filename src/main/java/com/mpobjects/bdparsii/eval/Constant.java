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
 * Represents a constant numeric expression.
 */
public final class Constant implements Expression {

    private BigDecimal value;

    public Constant(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate(MathContext mathContext) {
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
