package io.dx.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

/**
 * @author  dpersa
 */
public class Method {

    private final AccessType accessType;
    private final boolean staticMethod;
    private final String name;
    private final String returnType;
    private final List<Param> params;

    private Method(@Nonnull final AccessType accessType, @Nonnull final String name, @Nonnull final String returnType,
            @Nonnull final List<Param> params, final boolean staticMethod) {
        this.accessType = checkNotNull(accessType, "accessType should not be null");
        this.name = checkNotNull(name, "name should not be null");
        this.returnType = checkNotNull(returnType, "returnType should not be null");
        this.params = checkNotNull(params, "params should not be null");
        this.staticMethod = staticMethod;
    }

    @Nonnull
    public AccessType getAccessType() {
        return accessType;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getReturnType() {
        return returnType;
    }

    @Nonnull
    public List<Param> getParams() {
        return params;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    @Nonnull
    public static Builder builder(@Nonnull final String name, @Nonnull final String returnType) {
        return new Builder(name, returnType);
    }

    public static class Builder {
        private AccessType accessType = AccessType.PUBLIC;
        private List<Param> params = ImmutableList.of();
        private boolean staticMethod = false;
        private String name;
        private String returnType;

        private Builder(@Nonnull final String name, @Nonnull final String returnType) {
            withName(name);
            withReturnType(returnType);
        }

        @Nonnull
        public Builder withAccessType(@Nonnull final AccessType accessType) {
            this.accessType = checkNotNull(accessType, "accessType should not be null");
            return this;
        }

        @Nonnull
        public Builder withName(@Nonnull final String name) {
            this.name = checkNotNull(name, "name should not be null");
            return this;
        }

        @Nonnull
        public Builder withReturnType(@Nonnull final String returnType) {
            this.returnType = checkNotNull(returnType, "returnType should not be null");
            return this;
        }

        @Nonnull
        public Builder withParams(@Nonnull final List<Param> params) {
            this.params = checkNotNull(params, "params should not be null");
            return this;
        }

        @Nonnull
        public Builder withStaticMethod(@Nonnull final boolean staticMethod) {
            this.staticMethod = staticMethod;
            return this;
        }

        @Nonnull
        public Method build() {
            return new Method(accessType, name, returnType, ImmutableList.copyOf(params), staticMethod);
        }
    }
}
