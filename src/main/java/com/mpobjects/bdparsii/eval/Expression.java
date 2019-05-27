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
 * Represents the result of a parsed expression.
 * <p>
 * Can be evaluated to return a BigDecimal value.
 */
public interface Expression {

    /**
     * Evaluates the expression to a BigDecimal number.
     *
     * @return the double value as a result of evaluating this expression.
     */
    default BigDecimal evaluate() {
        return evaluate(getMathContext());
    }

    /**
     * Evaluates the expression to a BigDecimal number.
     *
     * @param mathContext Use this math context during calculation instead of the one provided during parsing. Does not
     *            affect already simplified subexpressions.
     * @return the double value as a result of evaluating this expression.
     */
    BigDecimal evaluate(MathContext mathContext);

    /**
     * Returns a simplified version of this expression.
     *
     * @param mathContext Match context used during simplification.
     * @return a simplified version of this expression or <tt>this</tt> if the expression cannot be simplified
     */
    default Expression simplify(MathContext mathContext) {
        return this;
    }

    /**
     * Determines the this expression is constant
     *
     * @return <tt>true</tt> if the result of evaluate will never change and does not depend on external state like
     *         variables
     */
    default boolean isConstant() {
        return false;
    }

    /**
     * Can return the default math context to use during evaluation.
     *
     * @return math context
     */
    default MathContext getMathContext() {
        return null;
    }
}
