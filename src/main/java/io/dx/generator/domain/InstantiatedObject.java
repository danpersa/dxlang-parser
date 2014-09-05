package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public class InstantiatedObject {

    private final Clazz clazz;

    public static InstantiatedObject of(@Nonnull final Clazz clazz) {
        return new InstantiatedObject(clazz);
    }

    private InstantiatedObject(@Nonnull final Clazz clazz) {
        this.clazz = checkNotNull(clazz, "clazz should not be null");
    }

    @Nonnull
    public Clazz getClazz() {
        return clazz;
    }
}
