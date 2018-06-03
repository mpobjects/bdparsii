/*
 * Made with all the love in the world
 * by scireum in Remshalden, Germany
 *
 * Copyright by scireum GmbH
 * http://www.scireum.de - info@scireum.de
 */

package com.mpobjects.bdparsii.eval;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents an unary function.
 * <p>
 * An unary function has one arguments which is always evaluated in order to compute the final result.
 */
public abstract class UnaryFunction implements Function {

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    public BigDecimal eval(List<Expression> args) {
        BigDecimal a = args.get(0).evaluate();
        if (a == null) {
            throw new ArithmeticException("Parameter evaluated to null");
        }
        return eval(a);
    }

    /**
     * Performs the computation of the unary function
     *
     * @param a the argument of the function
     * @return the result of calling the function with a as argument
     */
    protected abstract BigDecimal eval(BigDecimal a);

    @Override
    public boolean isNaturalFunction() {
        return true;
    }
}
