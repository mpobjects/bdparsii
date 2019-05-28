/*
 * Made with all the love in the world
 * by scireum in Remshalden, Germany
 *
 * Copyright by scireum GmbH
 * http://www.scireum.de - info@scireum.de
 */

package com.mpobjects.bdparsii.eval;

import java.math.MathContext;

/**
 * Expression which holds a {@link MathContext} field.
 */
public abstract class AbstractExpression implements Expression {

    private final MathContext mathContext;

    /**
     * Create a new abstract expression
     *
     * @param mathContext the math context. Cannot be null.
     */
    protected AbstractExpression(MathContext mathContext) {
        if (mathContext == null) {
            throw new IllegalArgumentException("MathContext cannot be null");
        }
        this.mathContext = mathContext;
    }

    @Override
    public final MathContext getMathContext() {
        return mathContext;
    }

}
