package com.mpobjects.bdparsii.tokenizer;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {

    @Test
    public void testLineComment() throws Exception {
        Tokenizer tokenizer = new Tokenizer(new StringReader("foo // xzcvxzcv\nbar"));
        Assert.assertEquals("foo", tokenizer.current().getContents());
        tokenizer.consume();
        Assert.assertEquals("bar", tokenizer.current().getContents());
    }

    @Test
    public void testStrings() throws Exception {
        Tokenizer tokenizer = new Tokenizer(new StringReader("\"foo\"  'bar'"));
        Assert.assertEquals("foo", tokenizer.current().getContents());
        tokenizer.consume();
        Assert.assertEquals("bar", tokenizer.current().getContents());

        tokenizer = new Tokenizer(new StringReader("\"start_\\\"\\n\\r\\\\_end\""));
        Assert.assertEquals("start_\"\n\r\\_end", tokenizer.current().getContents());

        // No escaping here
        tokenizer = new Tokenizer(new StringReader("'foo\\nbar'"));
        Assert.assertEquals("foo\\nbar", tokenizer.current().getContents());
    }

    @Test
    public void testKeyword() throws Exception {
        Tokenizer tokenizer = new Tokenizer(new StringReader("foo bar_quux"));
        tokenizer.addKeyword("foo");
        Assert.assertTrue(tokenizer.current().isKeyword("foo"));
        Assert.assertTrue(tokenizer.next().isIdentifier());
    }

    @Test
    public void testSpecialId() throws Exception {
        Tokenizer tokenizer = new Tokenizer(new StringReader("$foo bar_quux"));
        tokenizer.addSpecialIdStarter('$');
        Assert.assertTrue(tokenizer.current().isSpecialIdentifier("$"));
        Assert.assertTrue(tokenizer.current().isSpecialIdentifierWithContent("$", "foo"));
        Assert.assertTrue(tokenizer.current().wasTriggeredBy("$"));
        tokenizer.consume();
        Assert.assertTrue(tokenizer.current().isIdentifier(""));
    }
}
