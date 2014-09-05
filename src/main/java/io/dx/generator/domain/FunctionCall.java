package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

/**
 * @author  dpersa
 */
public class FunctionCall {

    private final String objectName;
    private final String methodName;
    private final List<CallParam> callParams;

    private FunctionCall(@Nonnull final String objectName, @Nonnull final String methodName,
            @Nonnull final List<CallParam> callParams) {
        this.objectName = checkNotNull(objectName, "objectName should not be null");
        this.methodName = checkNotNull(methodName, "methodName should not be null");
        this.callParams = checkNotNull(callParams, "callParams should not be null");
    }

    @Nonnull
    public String getObjectName() {
        return objectName;
    }

    @Nonnull
    public String getMethodName() {
        return methodName;
    }

    @Nonnull
    public List<CallParam> getCallParams() {
        return callParams;
    }

    @Nonnull
    public static Builder builder(@Nonnull final String methodName) {
        return new Builder(methodName);
    }

    public static class Builder {

        private String methodName;
        private String objectName = "this";
        private List<CallParam> params = ImmutableList.of();

        private Builder(@Nonnull final String methodName) {
            withMethodName(methodName);
        }

        @Nonnull
        public Builder withMethodName(@Nonnull final String methodName) {
            this.methodName = checkNotNull(methodName, "methodName should not be null");
            return this;
        }

        @Nonnull
        public Builder withObjectName(@Nonnull final String objectName) {
            this.objectName = checkNotNull(objectName, "objectName should not be null");
            return this;
        }

        @Nonnull
        public Builder withParams(@Nonnull final List<CallParam> params) {
            this.params = checkNotNull(params, "callParams should not be null");
            return this;
        }

        public FunctionCall build() {
            return new FunctionCall(objectName, methodName, ImmutableList.copyOf(params));
        }
    }
}
