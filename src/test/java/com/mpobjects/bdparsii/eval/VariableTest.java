package com.mpobjects.bdparsii.eval;

import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VariableTest {

    private Variable variable;

    @Before
    public void setUp() throws Exception {
        variable = new Variable("foo");
    }

    @Test
    public void testSetValueBigDecimal() {
        variable.setValue(BigDecimal.TEN);
        Assert.assertEquals(BigDecimal.TEN, variable.getValue());

        try {
            variable.setValue(null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // nop
        }

        variable.makeConstant(BigDecimal.ONE);
        try {
            variable.setValue(BigDecimal.ZERO);
            Assert.fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            // nop
        }
    }

    @Test
    public void testSetValueDouble() {
        variable.setValue(123d);
        Assert.assertEquals(123d, variable.getValue().doubleValue(), 0);
    }

    @Test
    public void testSetValueLong() {
        variable.setValue(123);
        Assert.assertEquals(123, variable.getValue().longValue());
    }

    @Test
    public void testMakeConstantBigDecimal() {
        Assert.assertFalse(variable.isConstant());
        variable.makeConstant(BigDecimal.ONE);
        Assert.assertTrue(variable.isConstant());

        try {
            variable.makeConstant(BigDecimal.ZERO);
            Assert.fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            // nop
        }
    }

    @Test
    public void testMakeConstantSupplierOfBigDecimal() {
        Assert.assertFalse(variable.isConstant());
        variable.makeConstant(() -> BigDecimal.ONE);
        Assert.assertTrue(variable.isConstant());

        try {
            variable.makeConstant(() -> BigDecimal.TEN);
            Assert.fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            // nop
        }
    }

    @Test
    public void testGetValue() {
        Assert.assertEquals(BigDecimal.ZERO, variable.getValue());
        variable.setValue(BigDecimal.ONE);
        Assert.assertEquals(BigDecimal.ONE, variable.getValue());
        variable.makeConstant(() -> BigDecimal.TEN);
        Assert.assertEquals(BigDecimal.TEN, variable.getValue());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("foo", variable.getName());
    }

    @Test
    public void testWithValueBigDecimal() {
        Assert.assertSame(variable, variable.withValue(BigDecimal.ONE));
        Assert.assertEquals(BigDecimal.ONE, variable.getValue());
    }

    @Test
    public void testWithValueDouble() {
        Assert.assertSame(variable, variable.withValue(123d));
        Assert.assertEquals(123d, variable.getValue().doubleValue(), 0);
    }

    @Test
    public void testWithValueLong() {
        Assert.assertSame(variable, variable.withValue(123));
        Assert.assertEquals(123, variable.getValue().longValue());
    }

}
