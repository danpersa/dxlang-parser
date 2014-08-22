package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

/**
 * @author  dpersa
 */
public class Clazz {

    private final String name;
    private final boolean finalClass;
    private final AccessType accessType;
    private final List<Method> methods;

    public Clazz(final String name, final boolean finalClass, @Nonnull final AccessType accessType,
            @Nonnull final List<Method> methods) {
        this.name = checkNotNull(name, "name should not be null");
        this.finalClass = finalClass;
        this.accessType = checkNotNull(accessType, "accessType should not be null");
        this.methods = checkNotNull(methods, "methods should not be null");
    }

    public boolean isFinalClass() {
        return finalClass;
    }

    @Nonnull
    public AccessType getAccessType() {
        return accessType;
    }

    @Nonnull
    public List<Method> getMethods() {
        return methods;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public static Builder builder(@Nonnull final String name) {
        return new Builder(name);
    }

    public static class Builder {
        private String name;
        private boolean finalClass = false;
        private AccessType accessType = AccessType.PUBLIC;
        private List<Method> methods = ImmutableList.of();

        private Builder(@Nonnull final String name) {
            this.name = checkNotNull(name, "name should not be null");
        }

        @Nonnull
        public Builder withName(final String name) {
            this.name = checkNotNull(name, "name should not be null");
            return this;
        }

        @Nonnull
        public Builder withFinalClass(final boolean finalClass) {
            this.finalClass = checkNotNull(finalClass, "finalClass should not be null");
            return this;
        }

        @Nonnull
        public Builder withAccessType(@Nonnull final AccessType accessType) {
            this.accessType = checkNotNull(accessType, "accessType should not be null");
            return this;
        }

        @Nonnull
        public Builder withMethods(@Nonnull final List<Method> methods) {
            this.methods = checkNotNull(methods, "methods should not be null");
            return this;
        }

        @Nonnull
        public Clazz build() {
            return new Clazz(name, finalClass, accessType, ImmutableList.copyOf(methods));
        }
    }
}
