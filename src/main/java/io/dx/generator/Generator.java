package io.dx.generator;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public interface Generator<T> {

    @Nonnull
    StringBuilder generate(@Nonnull T domain);
}
