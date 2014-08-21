package io.dx.parser.visitor;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import org.dx.parser.DxLexer;
import org.dx.parser.DxParser;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DxPrintVisitorTest {

    @Test
    public void testVisitFunctionCall() throws Exception {
        Logger logger = LoggerFactory.getLogger(DxPrintVisitorTest.class);
        logger.info("Hello World");

        DxLexer lexer = new DxLexer(new ANTLRInputStream(getClass().getResourceAsStream("/standard_module.dx")));

        DxParser parser = new DxParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.prog();

        DxPrintVisitor visitor = new DxPrintVisitor();
        visitor.visit(tree);
    }

}
