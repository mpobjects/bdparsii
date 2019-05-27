package com.mpobjects.bdparsii;

import org.junit.Assert;
import org.junit.Test;

import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.tokenizer.ParseException;

/**
 * Issue #19 from parsii: https://github.com/scireum/parsii/issues/19
 */
public class ExponentTest {

    @Test
    public void testIssue19() throws ParseException {
        Assert.assertEquals(-33, Parser.parse("3-(6^2)").evaluate().intValueExact());
        //Assert.assertEquals(-33, Parser.parse("3-6^2").evaluate().intValueExact());

        Scope scope = new Scope();
        scope.create("a").setValue(3);
        scope.create("b").setValue(6);
        Expression expr = Parser.parse("a-b^2", scope);
        Assert.assertEquals(-33, expr.evaluate().intValueExact());
    }

}
