package io.dx.generator;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import io.dx.domain.Block;

/**
 * @author  dpersa
 */
@Singleton
public class BlockGenerator implements Generator<Block> {

    @Nonnull
    @Override
    public StringBuilder generate(@Nonnull final Block block) {
        checkNotNull(block, "block should not be null");
        return new StringBuilder("// this is a block");
    }
}
