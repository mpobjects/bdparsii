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
 * Safe-guard for big-math operations which have no support for unlimited precision calculations.
 */
public final class MathContextGuard {
    private static MathContext safeMathContext = MathContext.DECIMAL128;

    /**
     * Get a safe to use MathContext for certain big-math operations which do not support unlimited precision.
     *
     * @param mathContext MathContext to validate
     * @return Safe to use MathContext
     */
    public static MathContext getSafeContext(MathContext mathContext) {
        if (mathContext == null || mathContext.getPrecision() == 0) {
            return safeMathContext;
        } else {
            return mathContext;
        }
    }

    /**
     * Change the safe MathContext
     *
     * @param mathContext The new safe MathContext
     */
    public static void setSafeContext(MathContext mathContext) {
        if (mathContext == null) {
            throw new IllegalArgumentException("Safe MathContext cannot be null.");
        }
        safeMathContext = mathContext;
    }

    private MathContextGuard() {
        // nop
    }
}
