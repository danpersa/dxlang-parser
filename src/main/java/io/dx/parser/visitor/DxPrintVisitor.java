package io.dx.parser.visitor;

import io.dx.domain.Param;
import io.dx.util.ParamUtils;
import org.dx.parser.DxBaseVisitor;
import org.dx.parser.DxParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author  dpersa
 */
public class DxPrintVisitor extends DxBaseVisitor<Void> {

    private static final Logger LOG = LoggerFactory.getLogger(DxPrintVisitor.class);

    @Override
    public Void visitArg(final DxParser.ArgContext ctx) {
        return null;
    }

    @Override
    public Void visitFunctionCall(final DxParser.FunctionCallContext ctx) {
        LOG.info("visit function call");
        return super.visitFunctionCall(ctx);
    }

    @Override
    public Void visitFunctionDef(final DxParser.FunctionDefContext ctx) {
        LOG.info("visit function def with name: {} and return type: {}", ctx.functionName().getText(), ctx.returnType().getText());
        final List<Param> params = ParamUtils.createParamList(ParamUtils.paramNameContextToStrings(ctx.paramName()),
                ParamUtils.paramTypeContextToStrings(ctx.paramType()));
        
        for (Param param : params) {
            LOG.info("    -> {}", param);
        }

        return super.visitFunctionDef(ctx);
    }

    @Override
    public Void visitParamName(final DxParser.ParamNameContext ctx) {
        LOG.info("visit param name");
        return super.visitParamName(ctx);
    }

    @Override
    public Void visitParamType(final DxParser.ParamTypeContext ctx) {
        LOG.info("visit param type");
        return super.visitParamType(ctx);
    }

    @Override
    public Void visitProg(final DxParser.ProgContext ctx) {
        LOG.info("visit prog");
        return super.visitProg(ctx);
    }

    @Override
    public Void visitMethodCall(final DxParser.MethodCallContext ctx) {
        LOG.info("visit method call");
        return super.visitMethodCall(ctx);
    }

    @Override
    public Void visitBlock(final DxParser.BlockContext ctx) {
        LOG.info("visit block");
        return super.visitBlock(ctx);
    }

    @Override
    public Void visitObject(final DxParser.ObjectContext ctx) {
        LOG.info("visit object");
        return super.visitObject(ctx);
    }

    @Override
    public Void visitFunctionName(final DxParser.FunctionNameContext ctx) {
        LOG.info("visit function name");
        return super.visitFunctionName(ctx);
    }

    @Override
    public Void visitPrint(final DxParser.PrintContext ctx) {
        LOG.info("visit print");
        return super.visitPrint(ctx);
    }

    @Override
    public Void visitReturnType(final DxParser.ReturnTypeContext ctx) {
        LOG.info("visit return type");
        return super.visitReturnType(ctx);
    }
}
