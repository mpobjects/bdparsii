package com.mpobjects.bdparsii.eval;

import java.math.MathContext;

import org.junit.Assert;
import org.junit.Test;

import com.mpobjects.bdparsii.eval.MathContextGuard;

public class MathContextGuardTest {

    @Test
    public void testGetSafeContext() {
        Assert.assertNotNull(MathContextGuard.getSafeContext(null));
        Assert.assertNotNull(MathContextGuard.getSafeContext(MathContext.UNLIMITED));
        Assert.assertSame(MathContext.DECIMAL128, MathContextGuard.getSafeContext(MathContext.DECIMAL128));
    }

    @Test
    public void testSetSafeContext() {
        try {
            MathContextGuard.setSafeContext(null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            // nop
        }

        MathContextGuard.setSafeContext(MathContext.DECIMAL32);
        Assert.assertSame(MathContext.DECIMAL32, MathContextGuard.getSafeContext(null));
    }

}
