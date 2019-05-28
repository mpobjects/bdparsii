package com.mpobjects.bdparsii;

import java.math.MathContext;

import org.junit.Test;

import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.eval.Variable;

/**
 * Test the expression from the performance test, just to be sure it works.
 */
public class PerfTestExprTest {

    @Test
    public void testExpression() throws Exception {
        Scope scope = new Scope();
        scope.setMathContext(MathContext.UNLIMITED);
        Variable var = scope.create("x");
        Expression expr = Parser.parse("2 + (7 - 5) * 3.14159 * x^(12-10) + sin(-3.141)", scope);
        for (int x = 0; x < 1_000_000; ++x) {
            var.setValue(x);
            expr.evaluate();
        }
    }
}
