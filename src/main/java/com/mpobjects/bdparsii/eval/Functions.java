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
import java.math.RoundingMode;
import java.util.List;

import ch.obermuhlner.math.big.BigDecimalMath;

/**
 * Contains a set of predefined standard functions.
 * <p>
 * Provides mostly functions defined by {@link BigDecimalMath}
 */
public class Functions {

    /**
     * BigDecimal constant for 180
     */
    public static final BigDecimal ONEHUNDREDEIGHTY = BigDecimal.valueOf(180);

    /**
     * Provides access to {@link BigDecimalMath#sin(BigDecimal, MathContext)}
     */
    public static final Function SIN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.sin(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#sinh(BigDecimal, MathContext)}
     */
    public static final Function SINH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.sinh(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#cos(BigDecimal, MathContext)}
     */
    public static final Function COS = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.cos(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#cosh(BigDecimal, MathContext)}
     */
    public static final Function COSH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.cosh(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#cot(BigDecimal, MathContext)}
     */
    public static final Function COT = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.cot(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#coth(BigDecimal, MathContext)}
     */
    public static final Function COTH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.coth(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#tan(BigDecimal, MathContext)}
     */
    public static final Function TAN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.tan(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#tanh(BigDecimal, MathContext)}
     */
    public static final Function TANH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.tanh(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimal#abs(MathContext)}
     */
    public static final Function ABS = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.abs(mathContext);
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#asin(BigDecimal, MathContext)}
     */
    public static final Function ASIN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.asin(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#asinh(BigDecimal, MathContext)}
     */
    public static final Function ASINH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.asinh(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#acos(BigDecimal, MathContext)}
     */
    public static final Function ACOS = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.acos(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#acosh(BigDecimal, MathContext)}
     */
    public static final Function ACOSH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.acosh(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#acot(BigDecimal, MathContext)}
     */
    public static final Function ACOT = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.acot(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#acoth(BigDecimal, MathContext)}
     */
    public static final Function ACOTH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.acoth(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#atan(BigDecimal, MathContext)}
     */
    public static final Function ATAN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.atan(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#atanh(BigDecimal, MathContext)}
     */
    public static final Function ATANH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.atanh(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#atan2(BigDecimal, BigDecimal, MathContext)}
     */
    public static final Function ATAN2 = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return BigDecimalMath.atan2(a, b, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Rounds half up
     */
    public static final Function ROUND = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.setScale(0, RoundingMode.HALF_UP);
        }
    };

    /**
     * Floors the value
     */
    public static final Function FLOOR = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.setScale(0, RoundingMode.FLOOR);
        }
    };

    /**
     * Get the ceiling value
     */
    public static final Function CEIL = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.setScale(0, RoundingMode.CEILING);
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#pow(BigDecimal, BigDecimal, MathContext)}
     */
    public static final Function POW = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return BinaryOperation.pow(a, b, mathContext);
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#sqrt(BigDecimal, MathContext)}
     */
    public static final Function SQRT = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.sqrt(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#exp(BigDecimal, MathContext)}
     */
    public static final Function EXP = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.exp(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#log(BigDecimal, MathContext)}
     */
    public static final Function LN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.log(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#log10(BigDecimal, MathContext)}
     */
    public static final Function LOG = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.log10(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#log2(BigDecimal, MathContext)}
     */
    public static final Function LOG2 = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.log2(a, MathContextGuard.getSafeContext(mathContext));
        }
    };

    /**
     * Provides access to {@link BigDecimal#min(BigDecimal)}
     */
    public static final Function MIN = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return a.min(b);
        }
    };

    /**
     * Provides access to {@link BigDecimal#max(BigDecimal)}
     */
    public static final Function MAX = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return a.max(b);
        }
    };

    /**
     * Provides access to {@link Math#random()} which will be multiplied by the given argument.
     */
    public static final Function RND = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.multiply(BigDecimal.valueOf(Math.random()), mathContext);
        }
    };

    /**
     * Provides access to {@link BigDecimal#signum()}
     */
    public static final Function SIGN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimal.valueOf(a.signum());
        }
    };

    /**
     * Converts radients to degrees.
     */
    public static final Function DEG = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.multiply(ONEHUNDREDEIGHTY, mathContext)
                    .divide(BigDecimalMath.pi(MathContextGuard.getSafeContext(mathContext)), mathContext);
        }
    };

    /**
     * Converts degrees to radients.
     */
    public static final Function RAD = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.divide(ONEHUNDREDEIGHTY, mathContext)
                    .multiply(BigDecimalMath.pi(MathContextGuard.getSafeContext(mathContext)), mathContext);
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#exponent(BigDecimal)}
     */
    public static final Function EXPONENT = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimal.valueOf(BigDecimalMath.exponent(a));
        }
    };

    /**
     * Provides access to {@link BigDecimalMath#mantissa(BigDecimal)}
     */
    public static final Function MANTISSA = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.mantissa(a);
        }
    };

    /**
     * Provides an if-like function
     * <p>
     * It expects three arguments: A condition, an expression being evaluated if the condition is non zero and an
     * expression which is being evaluated if the condition is zero.
     */
    public static final Function IF = new IfFunction();

    /**
     * Provides access to the scale of the current value.
     * <p>
     * Called with a single argument it returns the scale of the provided value. Called with two arguments it returns a
     * value with the scale set to the second argument.
     */
    public static final Function SCALE = new ScaleFunction();

    private Functions() {
    }

    private static class IfFunction implements Function {
        @Override
        public int getNumberOfArguments() {
            return 3;
        }

        @Override
        public BigDecimal eval(List<Expression> args, MathContext mathContext) {
            BigDecimal check = args.get(0).evaluate(mathContext);
            if (check == null) {
                throw new ArithmeticException("Parameter 1 evaluated to null");
            }
            if (check.compareTo(BigDecimal.ZERO) != 0) {
                return args.get(1).evaluate(mathContext);
            } else {
                return args.get(2).evaluate(mathContext);
            }
        }

        @Override
        public boolean isNaturalFunction() {
            return false;
        }
    }

    private static class ScaleFunction implements Function {

        @Override
        public int getNumberOfArguments() {
            return -1;
        }

        @Override
        public BigDecimal eval(List<Expression> args, MathContext mathContext) {
            if (args.size() == 1) {
                BigDecimal val = args.get(0).evaluate(mathContext);
                if (val == null) {
                    throw new ArithmeticException("Parameter 1 evaluated to null");
                }
                return BigDecimal.valueOf(val.scale());
            } else if (args.size() == 2) {
                BigDecimal val = args.get(0).evaluate(mathContext);
                if (val == null) {
                    throw new ArithmeticException("Parameter 1 evaluated to null");
                }
                BigDecimal scale = args.get(1).evaluate(mathContext);
                if (scale == null) {
                    throw new ArithmeticException("Parameter 2 evaluated to null");
                }
                return val.setScale(scale.intValueExact(), mathContext.getRoundingMode());
            } else {
                throw new ArithmeticException("Unsupported number of arguments: " + args.size());
            }
        }

        @Override
        public boolean isNaturalFunction() {
            return true;
        }
    }

}
