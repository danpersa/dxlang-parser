package org.dx.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.dx.parser.DxLexer;
import org.dx.parser.DxParser;


import org.junit.Test;

import java.io.IOException;

/**
 * @author  dpersa
 */
public class DxTest {

    @Test
    public void test() throws IOException {

        // create a CharStream that reads from standard input
        //ANTLRInputStream input = new ANTLRInputStream(System.in); // create a lexer that feeds off of input CharStream

        DxLexer lexer = new DxLexer(new ANTLRInputStream(getClass().getResourceAsStream("/standard_module.dx")));

        DxParser parser = new DxParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.prog();

        System.out.println(tree.toStringTree(parser));            // print LISP-style tree
    }

}
