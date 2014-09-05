package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public class MethodCall implements Statement {

    private final String objectName;
    private final FunctionCall functionCall;

    public static MethodCall of(@Nonnull final String objectName, @Nonnull final FunctionCall functionCall) {
        return new MethodCall(objectName, functionCall);
    }

    private MethodCall(@Nonnull final String objectName, @Nonnull final FunctionCall functionCall) {
        this.objectName = checkNotNull(objectName, "objectName should not be null");
        this.functionCall = checkNotNull(functionCall, "functionCall should not be null");
    }

    @Nonnull
    public String getObjectName() {
        return objectName;
    }

    @Nonnull
    public FunctionCall getFunctionCall() {
        return functionCall;
    }
}
