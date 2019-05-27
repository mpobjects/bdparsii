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
 * Provides mostly functions defined by {@link Math}
 */
public class Functions {

    /**
     * BigDecimal constant for PI
     */
    public static final BigDecimal PI = BigDecimal.valueOf(Math.PI);

    /**
     * BigDecimal constant for 180
     */
    public static final BigDecimal ONEHUNDREDEIGHTY = BigDecimal.valueOf(180);

    /**
     * Provides access to {@link Math#sin(double)}
     */
    public static final Function SIN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.sin(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#sinh(double)}
     */
    public static final Function SINH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.sinh(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#cos(double)}
     */
    public static final Function COS = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.cos(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#cosh(double)}
     */
    public static final Function COSH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.cosh(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#tan(double)}
     */
    public static final Function TAN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.tan(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#tanh(double)}
     */
    public static final Function TANH = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.tanh(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#abs(double)}
     */
    public static final Function ABS = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.abs(mathContext);
        }
    };

    /**
     * Provides access to {@link Math#asin(double)}
     */
    public static final Function ASIN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.asin(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#acos(double)}
     */
    public static final Function ACOS = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.acos(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#atan(double)}
     */
    public static final Function ATAN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.atan(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#atan2(double, double)}
     */
    public static final Function ATAN2 = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return BigDecimalMath.atan2(a, b, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#round(double)}
     */
    public static final Function ROUND = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.setScale(0, RoundingMode.HALF_EVEN);
        }
    };

    /**
     * Provides access to {@link Math#floor(double)}
     */
    public static final Function FLOOR = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.setScale(0, RoundingMode.FLOOR);
        }
    };

    /**
     * Provides access to {@link Math#ceil(double)}
     */
    public static final Function CEIL = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.setScale(0, RoundingMode.CEILING);
        }
    };

    /**
     * Provides access to {@link Math#pow(double, double)}
     */
    public static final Function POW = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return BigDecimalMath.pow(a, b, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#sqrt(double)}
     */
    public static final Function SQRT = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.sqrt(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#exp(double)}
     */
    public static final Function EXP = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.exp(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#log(double)}
     */
    public static final Function LN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.log(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#log10(double)}
     */
    public static final Function LOG = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimalMath.log10(a, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#min(double, double)}
     */
    public static final Function MIN = new BinaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, BigDecimal b, MathContext mathContext) {
            return a.min(b);
        }
    };

    /**
     * Provides access to {@link Math#max(double, double)}
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
     * Provides access to {@link Math#signum(double)}
     */
    public static final Function SIGN = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return BigDecimal.valueOf(a.signum());
        }
    };

    /**
     * Provides access to {@link Math#toDegrees(double)}
     */
    public static final Function DEG = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.multiply(ONEHUNDREDEIGHTY, mathContext).divide(PI, mathContext);
        }
    };

    /**
     * Provides access to {@link Math#toRadians(double)}
     */
    public static final Function RAD = new UnaryFunction() {
        @Override
        protected BigDecimal eval(BigDecimal a, MathContext mathContext) {
            return a.divide(ONEHUNDREDEIGHTY, mathContext).multiply(PI, mathContext);
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
