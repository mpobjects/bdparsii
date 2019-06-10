package com.mpobjects.bdparsii.eval;

import java.math.MathContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScopeTest {

    private Scope scope;

    @Before
    public void setUp() throws Exception {
        scope = new Scope();
    }

    @Test
    public void testScope() {
        Assert.assertNotNull(scope.getVariable("pi").getValue());
        Assert.assertNotNull(scope.getVariable("euler").getValue());
    }

    @Test
    public void testWithStrictLookup() {
        Assert.assertSame(scope, scope.withStrictLookup(false));
        Assert.assertFalse(scope.getLocalNames().contains("foo"));
        Assert.assertNotNull(scope.getVariable("foo"));
        Assert.assertTrue(scope.getLocalNames().contains("foo"));

        Assert.assertSame(scope, scope.withStrictLookup(true));
        Assert.assertFalse(scope.getLocalNames().contains("bar"));
        try {
            scope.getVariable("bar");
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // nop
        }
        Assert.assertFalse(scope.getLocalNames().contains("bar"));
    }

    @Test
    public void testWithParent() {
        Scope parentScope = new Scope();
        Variable variable = parentScope.create("foo");
        Assert.assertSame(scope, scope.withParent(parentScope));
        Assert.assertSame(variable, scope.getVariable("foo"));
        Assert.assertFalse(scope.getLocalNames().contains("foo"));
    }

    @Test
    public void testFind() {
        Assert.assertNull(scope.find("foo"));
        scope.create("foo");
        Assert.assertNotNull(scope.find("foo"));

        Assert.assertNull(scope.find("bar"));
        Scope parentScope = new Scope();
        parentScope.create("bar");
        scope.withParent(parentScope);
        Assert.assertNotNull(scope.find("bar"));
    }

    @Test
    public void testCreate() {
        Variable variable = scope.create("foo");
        Assert.assertNotNull(variable);
        Assert.assertEquals("foo", variable.getName());
        // Returns the same instance
        Assert.assertSame(variable, scope.create("foo"));
    }

    @Test
    public void testRemove() {
        Assert.assertNull(scope.find("foo"));
        Variable variable = scope.create("foo");
        Assert.assertNotNull(scope.find("foo"));
        Assert.assertSame(variable, scope.remove("foo"));
        Assert.assertNull(scope.find("foo"));

        Assert.assertNull(scope.remove("foo"));
    }

    @Test
    public void testGetLocalNames() {
        Assert.assertTrue(scope.getLocalNames().contains("pi"));
        Assert.assertFalse(scope.getLocalNames().contains("foo"));
        scope.create("foo");
        Assert.assertTrue(scope.getLocalNames().contains("foo"));

        Scope parentScope = new Scope();
        parentScope.create("bar");
        scope.withParent(parentScope);
        Assert.assertFalse(scope.getLocalNames().contains("bar"));
    }

    @Test
    public void testGetNames() {
        Assert.assertTrue(scope.getNames().contains("pi"));

        Scope parentScope = new Scope();
        parentScope.create("bar");
        scope.withParent(parentScope);
        Assert.assertTrue(scope.getNames().contains("bar"));
    }

    @Test
    public void testGetLocalVariables() {
        Assert.assertNotNull(scope.getLocalVariables());

        Variable variable = scope.create("foo");
        Assert.assertTrue(scope.getLocalVariables().contains(variable));

        Scope parentScope = new Scope();
        variable = parentScope.create("bar");
        scope.withParent(parentScope);
        Assert.assertFalse(scope.getLocalVariables().contains(variable));
    }

    @Test
    public void testGetVariables() {
        Assert.assertNotNull(scope.getVariables());

        Variable variable = scope.create("foo");
        Assert.assertTrue(scope.getVariables().contains(variable));

        Scope parentScope = new Scope();
        variable = parentScope.create("bar");
        scope.withParent(parentScope);
        Assert.assertTrue(scope.getVariables().contains(variable));
    }

    @Test
    public void testSetMathContext() {
        Assert.assertNotNull(scope.getMathContext());
        scope.setMathContext(MathContext.DECIMAL128);
        Assert.assertEquals(MathContext.DECIMAL128, scope.getMathContext());

        try {
            scope.setMathContext(null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // nop
        }
    }

    @Test
    public void testWithMathContext() {
        Assert.assertEquals(scope, scope.withMathContext(MathContext.DECIMAL128));
        Assert.assertEquals(MathContext.DECIMAL128, scope.getMathContext());
    }

}
