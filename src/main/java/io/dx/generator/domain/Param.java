package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.google.common.base.Objects;

/**
 * @author  dpersa
 */
public class Param {

    private final String paramName;
    private final Clazz clazz;

    @Nonnull
    public static Param of(@Nonnull final String paramName, @Nonnull final Clazz clazz) {
        return new Param(paramName, clazz);
    }

    private Param(final String paramName, final Clazz clazz) {
        this.paramName = checkNotNull(paramName, "paramName should not be null");
        this.clazz = checkNotNull(clazz, "clazz should not be null");
    }

    @Nonnull
    public String getParamName() {
        return paramName;
    }

    @Nonnull
    public Clazz getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)        //
                      .add("paramName", paramName) //
                      .add("clazz", clazz) //
                      .toString();
    }
}
