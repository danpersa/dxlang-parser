package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public class VariableDeclaration implements Statement {

    private final String variableName;

    private final Clazz clazz;

    public static VariableDeclaration of(@Nonnull final String variableName, @Nonnull final Clazz clazz) {
        return new VariableDeclaration(variableName, clazz);
    }

    private VariableDeclaration(@Nonnull final String variableName, @Nonnull final Clazz clazz) {
        this.variableName = checkNotNull(variableName, "variableName should not be null");
        this.clazz = checkNotNull(clazz, "clazz should not be null");
    }

    @Nonnull
    public String getVariableName() {
        return variableName;
    }

    @Nonnull
    public Clazz getClazz() {
        return clazz;
    }
}
