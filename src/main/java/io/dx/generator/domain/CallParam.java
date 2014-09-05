package io.dx.generator.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public class CallParam {

    private final String paramName;

    public static CallParam of(@Nonnull final String paramName) {
        return new CallParam(paramName);
    }

    private CallParam(@Nonnull final String paramName) {
        this.paramName = checkNotNull(paramName, "paramName should not be null");
    }

    @Nonnull
    public String getParamName() {
        return paramName;
    }
}
