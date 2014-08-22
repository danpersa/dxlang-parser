package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.google.common.base.Objects;

/**
 * @author  dpersa
 */
public class Param {

    private final String paramName;
    private final String paramType;

    @Nonnull
    public static Param of(@Nonnull final String paramName, @Nonnull final String paramType) {
        return new Param(paramName, paramType);
    }

    private Param(final String paramName, final String paramType) {
        this.paramName = checkNotNull(paramName, "paramName should not be null");
        this.paramType = checkNotNull(paramType, "paramType should not be null");
    }

    @Nonnull
    public String getParamName() {
        return paramName;
    }

    @Nonnull
    public String getParamType() {
        return paramType;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)        //
                      .add("paramName", paramName) //
                      .add("paramType", paramType) //
                      .toString();
    }
}
