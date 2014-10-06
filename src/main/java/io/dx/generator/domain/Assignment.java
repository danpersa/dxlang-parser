package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public class Assignment implements Statement {

    private final String variableName;

    private final Statement statement;

    public static Assignment of(@Nonnull final String variableName, @Nonnull final Statement statement) {
        return new Assignment(variableName, statement);
    }

    private Assignment(@Nonnull final String variableName, @Nonnull final Statement statement) {
        this.variableName = checkNotNull(variableName, "variableName should not be null");
        this.statement = checkNotNull(statement, "statement should not be null");
    }

    @Nonnull
    public String getVariableName() {
        return variableName;
    }

    @Nonnull
    public Statement getStatement() {
        return statement;
    }
}
