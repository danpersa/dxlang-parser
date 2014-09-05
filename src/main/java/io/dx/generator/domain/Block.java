package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * @author  dpersa
 */
public class Block {

    private static final Block EMPTY_BLOCK = new EmtpyBlock();

    private final List<Statement> statements;

    private Block(@Nonnull final List<Statement> statements) {
        this.statements = checkNotNull(statements, "statements should not be null");
    }

    @Nonnull
    public static Block emptyBlock() {
        return EMPTY_BLOCK;
    }

    private static class EmtpyBlock extends Block {
        private EmtpyBlock() {
            super(ImmutableList.<Statement>of());
        }
    }

    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    @Nonnull
    public List<Statement> getStatements() {
        return statements;
    }

    public static class Builder {

        private List<Statement> statements = Lists.newArrayList();

        private Builder() { }

        @Nonnull
        public Builder withStatements(@Nonnull final List<Statement> statements) {
            this.statements = checkNotNull(statements, "statements should not be null");
            return this;
        }

        @Nonnull
        public Builder addStatement(@Nonnull final Statement statement) {
            this.statements.add(checkNotNull(statement, "statement should not be null"));
            return this;
        }

        public Block build() {
            return new Block(ImmutableList.copyOf(statements));
        }
    }
}
