/*
 * Made with all the love in the world
 * by scireum in Remshalden, Germany
 *
 * Copyright by scireum GmbH
 * http://www.scireum.de - info@scireum.de
 */

package com.mpobjects.bdparsii;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.junit.Test;

import com.mpobjects.bdparsii.eval.Expression;
import com.mpobjects.bdparsii.eval.Function;
import com.mpobjects.bdparsii.eval.Parser;
import com.mpobjects.bdparsii.eval.Scope;
import com.mpobjects.bdparsii.eval.Variable;
import com.mpobjects.bdparsii.tokenizer.ParseException;

/**
 * Tests the {@link Parser} class.
 *
 * @author Andreas Haufler (aha@scireum.de)
 * @since 2013/09
 */
public class ParserTest {
    @Test
    public void simple() throws ParseException {
        assertEquals(BigDecimal.valueOf(-109), Parser.parse("1 - (10 - -100)").evaluate());
        assertEquals(BigDecimal.valueOf(0.01d), Parser.parse("1 / 10 * 10 / 100").evaluate());
        assertEquals(BigDecimal.valueOf(-89), Parser.parse("1 + 10 - 100").evaluate());
        assertEquals(BigDecimal.valueOf(91), Parser.parse("1 - 10 - -100").evaluate());
        assertEquals(BigDecimal.valueOf(91), Parser.parse("1 - 10  + 100").evaluate());
        assertEquals(BigDecimal.valueOf(-109), Parser.parse("1 - (10 + 100)").evaluate());
        assertEquals(BigDecimal.valueOf(-89), Parser.parse("1 + (10 - 100)").evaluate());
        assertEquals(BigDecimal.valueOf(100), Parser.parse("1 / 1 * 100").evaluate());
        assertEquals(BigDecimal.valueOf(0.01d), Parser.parse("1 / (1 * 100)").evaluate());
        assertEquals(BigDecimal.valueOf(0.01d), Parser.parse("1 * 1 / 100").evaluate());
        assertEquals(BigDecimal.valueOf(7), Parser.parse("3+4").evaluate());
        assertEquals(BigDecimal.valueOf(7), Parser.parse("3      +    4").evaluate());
        assertEquals(BigDecimal.valueOf(-1), Parser.parse("3+ -4").evaluate());
        assertEquals(BigDecimal.valueOf(-1), Parser.parse("3+(-4)").evaluate());

        assertEquals(BigDecimal.valueOf(1), Parser.parse("5%4").evaluate());
        assertEquals(BigDecimal.valueOf(4), Parser.parse("2^2").evaluate());
        assertEquals(BigDecimal.valueOf(4), Parser.parse("2**2").evaluate());
    }

    @Test
    public void simpleLogical() throws ParseException {
        assertEquals(BigDecimal.valueOf(1), Parser.parse("2=2").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("1=2").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("1!=2").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("2!=2").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("2>1").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("1>2").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("2>=1").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("1>=2").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("1<2").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("2<1").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("1<=2").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("2<=1").evaluate());

        assertEquals(BigDecimal.valueOf(1), Parser.parse("1&&1").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("1&&0").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("0&&1").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("1||0").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("0||0").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("0||1").evaluate());
    }

    @Test
    public void number() throws ParseException {
        assertEquals(BigDecimal.valueOf(4003.333333d), Parser.parse("3.333_333+4_000").evaluate());
    }

    @Test
    public void precedence() throws ParseException {
        // term vs. product
        assertEquals(BigDecimal.valueOf(19), Parser.parse("3+4*4").evaluate());
        // product vs. power
        assertEquals(BigDecimal.valueOf(20.25d), Parser.parse("3^4/4").evaluate());
        // relation vs. product
        assertEquals(BigDecimal.valueOf(1), Parser.parse("3 < 4*4").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("3 > 4*4").evaluate());
        // brackets
        assertEquals(BigDecimal.valueOf(28), Parser.parse("(3 + 4) * 4").evaluate());
    }

    @Test
    public void signed() throws ParseException {
        assertEquals(BigDecimal.valueOf(-2.02), Parser.parse("-2.02").evaluate());
        assertEquals(BigDecimal.valueOf(2.02), Parser.parse("+2.02").evaluate());
        assertEquals(BigDecimal.valueOf(1.01), Parser.parse("+2.02 + -1.01").evaluate());
        assertEquals(BigDecimal.valueOf(-4.03), Parser.parse("-2.02 - +2.01").evaluate());
        assertEquals(BigDecimal.valueOf(3.03), Parser.parse("+2.02 + +1.01").evaluate());
    }

    @Test
    public void blockComment() throws ParseException {
        assertEquals(BigDecimal.valueOf(29), Parser.parse("27+ /*xxx*/ 2").evaluate());
        assertEquals(BigDecimal.valueOf(29), Parser.parse("27+/*xxx*/ 2").evaluate());
        assertEquals(BigDecimal.valueOf(29), Parser.parse("27/*xxx*/+2").evaluate());
    }

    @Test
    public void startingWithDecimalPoint() throws ParseException {
        assertEquals(BigDecimal.valueOf(.2), Parser.parse(".2").evaluate());
        assertEquals(BigDecimal.valueOf(.2), Parser.parse("+.2").evaluate());
        assertEquals(BigDecimal.valueOf(.4), Parser.parse(".2+.2").evaluate());
        assertEquals(BigDecimal.valueOf(.4), Parser.parse(".6+-.2").evaluate());
    }

    @Test
    public void signedParentheses() throws ParseException {
        assertEquals(BigDecimal.valueOf(0.2), Parser.parse("-(-0.2)").evaluate());
        assertEquals(BigDecimal.valueOf(1.2), Parser.parse("1-(-0.2)").evaluate());
        assertEquals(BigDecimal.valueOf(0.8), Parser.parse("1+(-0.2)").evaluate());
        assertEquals(BigDecimal.valueOf(2.2), Parser.parse("+(2.2)").evaluate());
    }

    @Test
    public void trailingDecimalPoint() throws ParseException {
        assertEquals(BigDecimal.valueOf(2), Parser.parse("2.").evaluate());
    }

    @Test
    public void scale() throws ParseException {
        assertEquals(BigDecimal.valueOf(1), Parser.parse("1").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(1), Parser.parse("1.0").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(2), Parser.parse("1.00").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(10), Parser.parse("1.0000000000").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(10), Parser.parse("0.5000000000 + 0.5").evaluate());
    }

    @Test
    public void signedValueAfterOperand() throws ParseException {
        assertEquals(BigDecimal.valueOf(-1.2), Parser.parse("1+-2.2").evaluate());
        assertEquals(BigDecimal.valueOf(3.2), Parser.parse("1++2.2").evaluate());
        assertEquals(BigDecimal.valueOf(-6.6), Parser.parse("6*-1.1").evaluate());
        assertEquals(BigDecimal.valueOf(6.6), Parser.parse("6*+1.1").evaluate());
    }

    @Test
    public void variables() throws ParseException {
        Scope scope = new Scope();

        Variable a = scope.create("a");
        Variable b = scope.create("b");
        Expression expr = Parser.parse("3*a + 4 * b", scope);
        assertEquals(BigDecimal.valueOf(0), expr.evaluate());
        a.setValue(2);
        assertEquals(BigDecimal.valueOf(6), expr.evaluate());
        b.setValue(3);
        assertEquals(BigDecimal.valueOf(18), expr.evaluate());
        assertEquals(BigDecimal.valueOf(18), expr.evaluate());

        expr = Parser.parse("(1 + a) + (2 + 1)", scope);
        assertEquals(BigDecimal.valueOf(6), expr.evaluate());
        expr = Parser.parse("(1 * a) * (2 * 1)", scope);
        assertEquals(BigDecimal.valueOf(4), expr.evaluate());
        expr = Parser.parse("(1 * a) * (1 * b)", scope);
        assertEquals(BigDecimal.valueOf(6), expr.evaluate());
        expr = Parser.parse("(a * 1) * (b * 1)", scope);
        assertEquals(BigDecimal.valueOf(6), expr.evaluate());
    }

    @Test
    public void functions() throws ParseException {
        assertEquals(BigDecimal.valueOf(0).setScale(10),
                     Parser.parse("1 + scale(sin(-pi), 10) + scale(cos(pi), 10)").evaluate());
        assertEquals(new BigDecimal("4.720383415753841"), Parser.parse("tan(sqrt(euler ^ (pi * 3)))").evaluate());
        assertEquals(BigDecimal.valueOf(3), Parser.parse("| 3 - 6 |").evaluate());
        assertEquals(BigDecimal.valueOf(3), Parser.parse("if(3 > 2 && 2 < 3, 2+1, 1+1)").evaluate());
        assertEquals(BigDecimal.valueOf(2), Parser.parse("if(3 < 2 || 2 > 3, 2+1, 1+1)").evaluate());
        assertEquals(BigDecimal.valueOf(2), Parser.parse("min(3,2)").evaluate());

        // Test a var arg method...
        Parser.registerFunction("avg", new Function() {
            @Override
            public int getNumberOfArguments() {
                return -1;
            }

            @Override
            public BigDecimal eval(List<Expression> args, MathContext mathContext) {
                BigDecimal avg = BigDecimal.ZERO;
                if (args.isEmpty()) {
                    return avg;
                }
                for (Expression e : args) {
                    avg = avg.add(e.evaluate(mathContext));
                }
                return avg.divide(BigDecimal.valueOf(args.size()));
            }

            @Override
            public boolean isNaturalFunction() {
                return true;
            }
        });
        assertEquals(BigDecimal.valueOf(3.25d), Parser.parse("avg(3,2,1,7)").evaluate());
    }

    @Test
    public void scopes() throws ParseException {
        Scope root = new Scope();
        Variable a = root.getVariable("a").withValue(1);
        Scope subScope1 = new Scope().withParent(root);
        Scope subScope2 = new Scope().withParent(root);
        Variable b1 = subScope1.getVariable("b").withValue(2);
        Variable b2 = subScope2.getVariable("b").withValue(3);
        Variable c = root.getVariable("c").withValue(4);
        Variable c1 = subScope1.getVariable("c").withValue(5);
        assertEquals(c, c1);
        Variable d = root.getVariable("d").withValue(9);
        Variable d1 = subScope1.create("d").withValue(7);
        assertNotEquals(d, d1);
        Expression expr1 = Parser.parse("a + b + c + d", subScope1);
        Expression expr2 = Parser.parse("a + b + c + d", subScope2);
        assertEquals(BigDecimal.valueOf(15), expr1.evaluate());
        assertEquals(BigDecimal.valueOf(18), expr2.evaluate());
        a.setValue(10);
        b1.setValue(20);
        b2.setValue(30);
        c.setValue(40);
        c1.setValue(50);
        assertEquals(BigDecimal.valueOf(87), expr1.evaluate());
        assertEquals(BigDecimal.valueOf(99), expr2.evaluate());
    }

    @Test
    public void errors() throws ParseException {
        // We expect the parser to continue after an recoverable error!
        try {
            Parser.parse("test(1 2)+sin(1,2)*34-34.45.45+");
            assertTrue(false);
        } catch (ParseException e) {
            assertEquals(5, e.getErrors().size());
        }

        // We expect the parser to report an invalid quantifier.
        try {
            Parser.parse("1x");
            assertTrue(false);
        } catch (ParseException e) {
            assertEquals(1, e.getErrors().size());
        }

        // We expect the parser to report an unfinished expression
        try {
            Parser.parse("1(");
            assertTrue(false);
        } catch (ParseException e) {
            assertEquals(1, e.getErrors().size());
        }
    }

    @Test
    public void relationalOperators() throws ParseException {
        // Test for Issue with >= and <= operators (#4)
        assertEquals(BigDecimal.valueOf(1), Parser.parse("5 <= 5").evaluate());
        assertEquals(BigDecimal.valueOf(1), Parser.parse("5 >= 5").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("5 < 5").evaluate());
        assertEquals(BigDecimal.valueOf(0), Parser.parse("5 > 5").evaluate());
    }

    @Test
    public void quantifiers() throws ParseException {
        assertEquals(BigDecimal.valueOf(1000), Parser.parse("1K").evaluate());
        assertEquals(BigDecimal.valueOf(1000).setScale(3), Parser.parse("1M * 1m").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(9), Parser.parse("1n * 1G").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(3), Parser.parse("(1M / 1k) * 1m").evaluate());
        assertEquals(BigDecimal.valueOf(1).setScale(7), Parser.parse("1u * 10 k * 1000  m * 0.1 k").evaluate());
    }

    @Test
    public void getVariables() throws ParseException {
        Scope s = new Scope();
        Parser.parse("a*b+c", s);
        assertTrue(s.getNames().contains("a"));
        assertTrue(s.getNames().contains("b"));
        assertTrue(s.getNames().contains("c"));
        assertFalse(s.getNames().contains("x"));

        // pi and euler are always defined...
        assertEquals(5, s.getVariables().size());
    }

    @Test
    public void errorOnUnknownVariable() throws ParseException {
        Scope s = new Scope();
        try {
            s.create("a");
            s.create("b");
            Parser.parse("a*b+c", s);
        } catch (ParseException e) {
            assertEquals(1, e.getErrors().size());
        }

        s.create("c");
        Parser.parse("a*b+c", s);
    }

    @Test
    public void removeVariable() throws ParseException {
        Scope s = new Scope();
        s.create("X");
        assertTrue(s.find("X") != null);
        assertTrue(s.remove("X") != null);
        assertTrue(s.find("X") == null);
    }

    @Test
    public void removeVariableFromSubscope() throws ParseException {
        Scope s = new Scope();
        Scope child = new Scope().withParent(s);
        s.create("X");
        assertTrue(child.find("X") != null);
        assertTrue(child.remove("X") == null);
        assertTrue(child.find("X") != null);
    }
}
