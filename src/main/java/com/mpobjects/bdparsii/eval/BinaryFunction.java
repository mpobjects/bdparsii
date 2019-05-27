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
import java.util.List;

/**
 * Represents a binary function.
 * <p>
 * A binary function has two arguments which are always evaluated in order to compute the final result.
 */
public abstract class BinaryFunction implements Function {

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    @Override
    public BigDecimal eval(List<Expression> args, MathContext mathContext) {
        BigDecimal a = args.get(0).evaluate(mathContext);
        if (a == null) {
            throw new ArithmeticException("Parameter 1 evaluated to null");
        }
        BigDecimal b = args.get(1).evaluate(mathContext);
        if (b == null) {
            throw new ArithmeticException("Parameter 2 evaluated to null");
        }
        return eval(a, b, mathContext);
    }

    /**
     * Performs the computation of the binary function
     *
     * @param a the first argument of the function
     * @param b the second argument of the function
     * @param mathContext the math context
     * @return the result of calling the function with a and b
     */
    protected abstract BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext);

    @Override
    public boolean isNaturalFunction() {
        return true;
    }
}
