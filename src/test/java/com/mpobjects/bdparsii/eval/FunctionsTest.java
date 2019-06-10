package com.mpobjects.bdparsii.eval;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Just evaluate the functions. The results are not that important, we assume Big Math is properly tested
 */
public class FunctionsTest {

    private List<Expression> args(BigDecimal... aValues) {
        List<Expression> res = new ArrayList<>();
        for (BigDecimal arg : aValues) {
            res.add(new Constant(arg));
        }
        return res;
    }

    @Test
    public void testSin() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.SIN.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testSinh() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.SINH.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testCos() {
        Assert.assertEquals(BigDecimal.ONE, Functions.COS.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testCosh() {
        Assert.assertEquals(BigDecimal.ONE, Functions.COSH.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testCot() {
        Assert.assertEquals(new BigDecimal("0.6420926"),
                            Functions.COT.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testCoth() {
        Assert.assertEquals(new BigDecimal("1.313035"),
                            Functions.COTH.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testTan() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.TAN.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testTanh() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.TANH.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testAbs() {
        Assert.assertEquals(BigDecimal.ONE, Functions.ABS.eval(args(BigDecimal.ONE.negate()), MathContext.DECIMAL32));
    }

    @Test
    public void testAsin() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.ASIN.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testAsinh() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.ASINH.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testAcos() {
        Assert.assertEquals(BigDecimal.ZERO,
                            Functions.ACOS.eval(args(BigDecimal.ONE), MathContext.DECIMAL32).setScale(0));
    }

    @Test
    public void testAcosh() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.ACOSH.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testAcot() {
        Assert.assertEquals(new BigDecimal("0.7853982"),
                            Functions.ACOT.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testAcoth() {
        Assert.assertEquals(new BigDecimal("0.1003353"),
                            Functions.ACOTH.eval(args(BigDecimal.TEN), MathContext.DECIMAL32));
    }

    @Test
    public void testAtan() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.ATAN.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testAtan2() {
        Assert.assertEquals(new BigDecimal("0.7853982"),
                            Functions.ATAN2.eval(args(BigDecimal.ONE, BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testAtanh() {
        Assert.assertEquals(new BigDecimal("0.0"), Functions.ATANH.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testRound() {
        Assert.assertEquals(BigDecimal.ONE, Functions.ROUND.eval(args(new BigDecimal("0.5")), MathContext.DECIMAL32));
    }

    @Test
    public void testFloor() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.FLOOR.eval(args(new BigDecimal("0.9")), MathContext.DECIMAL32));
    }

    @Test
    public void testCeil() {
        Assert.assertEquals(BigDecimal.ONE, Functions.CEIL.eval(args(new BigDecimal("0.1")), MathContext.DECIMAL32));
    }

    @Test
    public void testPow() {
        Assert.assertEquals(BigDecimal.TEN,
                            Functions.POW.eval(args(BigDecimal.TEN, BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testSqrt() {
        Assert.assertEquals(new BigDecimal("2.0"),
                            Functions.SQRT.eval(args(BigDecimal.valueOf(4)), MathContext.DECIMAL32));
    }

    @Test
    public void testExp() {
        Assert.assertEquals(new BigDecimal("2.718282"),
                            Functions.EXP.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testLn() {
        Assert.assertEquals(BigDecimal.ZERO, Functions.LN.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testLog() {
        Assert.assertEquals(BigDecimal.ZERO,
                            Functions.LOG.eval(args(BigDecimal.ONE), MathContext.DECIMAL32).setScale(0));
    }

    @Test
    public void testLog2() {
        Assert.assertEquals(BigDecimal.ZERO,
                            Functions.LOG2.eval(args(BigDecimal.ONE), MathContext.DECIMAL32).setScale(0));
    }

    @Test
    public void testMin() {
        Assert.assertEquals(BigDecimal.ZERO,
                            Functions.MIN.eval(args(BigDecimal.ONE, BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testMax() {
        Assert.assertEquals(BigDecimal.ONE,
                            Functions.MAX.eval(args(BigDecimal.ONE, BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testRnd() {
        // Random result, cannot test
        Assert.assertNotNull(Functions.RND.eval(args(BigDecimal.ONE), MathContext.DECIMAL32));
    }

    @Test
    public void testSign() {
        Assert.assertEquals(BigDecimal.ONE, Functions.SIGN.eval(args(BigDecimal.TEN), MathContext.DECIMAL32));
        Assert.assertEquals(BigDecimal.ONE.negate(),
                            Functions.SIGN.eval(args(BigDecimal.TEN.negate()), MathContext.DECIMAL32));
        Assert.assertEquals(BigDecimal.ZERO, Functions.SIGN.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
    }

    @Test
    public void testDeg() {
        Assert.assertEquals(new BigDecimal("180.0000"),
                            Functions.DEG.eval(args(new BigDecimal("3.141593")), MathContext.DECIMAL32));
    }

    @Test
    public void testRad() {
        Assert.assertEquals(new BigDecimal("3.141593"),
                            Functions.RAD.eval(args(new BigDecimal("180")), MathContext.DECIMAL32));
    }

    @Test
    public void testExponent() {
        Assert.assertEquals(BigDecimal.valueOf(2),
                            Functions.EXPONENT.eval(args(BigDecimal.valueOf(200)), MathContext.DECIMAL32));
    }

    @Test
    public void testMantissa() {
        Assert.assertEquals(BigDecimal.valueOf(2),
                            Functions.MANTISSA.eval(args(BigDecimal.valueOf(200)), MathContext.DECIMAL32).setScale(0));
    }

    @Test
    public void testIf() {
        Assert.assertEquals(BigDecimal.TEN,
                            Functions.IF.eval(args(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN),
                                              MathContext.DECIMAL32));
        Assert.assertEquals(BigDecimal.ONE,
                            Functions.IF.eval(args(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.TEN),
                                              MathContext.DECIMAL32));

        try {
            Functions.IF.eval(args(null, BigDecimal.ONE, BigDecimal.TEN), MathContext.DECIMAL32);
            Assert.fail("ArithmeticException expected");
        } catch (ArithmeticException e) {
            // nop
        }
    }

    @Test
    public void testScale() {
        // read
        Assert.assertEquals(BigDecimal.ZERO, Functions.SCALE.eval(args(BigDecimal.ZERO), MathContext.DECIMAL32));
        Assert.assertEquals(BigDecimal.TEN,
                            Functions.SCALE.eval(args(BigDecimal.ZERO.setScale(10)), MathContext.DECIMAL32));

        try {
            Assert.assertEquals(BigDecimal.TEN, Functions.SCALE.eval(args((BigDecimal) null), MathContext.DECIMAL32));
            Assert.fail("ArithmeticException expected");
        } catch (ArithmeticException e) {
            // nop
        }

        // set scale
        Assert.assertEquals(new BigDecimal("1.0000000000"),
                            Functions.SCALE.eval(args(BigDecimal.ONE, BigDecimal.TEN), MathContext.DECIMAL32));

        try {
            Functions.SCALE.eval(args((BigDecimal) null, BigDecimal.ONE), MathContext.DECIMAL32);
            Assert.fail("ArithmeticException expected");
        } catch (ArithmeticException e) {
            // nop
        }
        try {
            Functions.SCALE.eval(args(BigDecimal.ONE, (BigDecimal) null), MathContext.DECIMAL32);
            Assert.fail("ArithmeticException expected");
        } catch (ArithmeticException e) {
            // nop
        }

        try {
            // wrong number of arguments
            Assert.assertEquals(BigDecimal.TEN,
                                Functions.SCALE.eval(args(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                                                     MathContext.DECIMAL32));
            Assert.fail("ArithmeticException expected");
        } catch (ArithmeticException e) {
            // nop
        }
    }
}
