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
 * Represents the result of a parsed expression.
 * <p>
 * Can be evaluated to return a BigDecimal value.
 */
public abstract class Expression {

    /**
     * Evaluates the expression to a BigDecimal number.
     *
     * @return the double value as a result of evaluating this expression.
     */
    public abstract BigDecimal evaluate();

    /**
     * Returns a simplified version of this expression.
     *
     * @return a simplified version of this expression or <tt>this</tt> if the expression cannot be simplified
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Determines the this expression is constant
     *
     * @return <tt>true</tt> if the result of evaluate will never change and does not depend on external state like
     *         variables
     */
    public boolean isConstant() {
        return false;
    }
}
