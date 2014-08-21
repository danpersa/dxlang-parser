package io.dx.generator;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.google.inject.Singleton;

import io.dx.domain.AccessType;

/**
 * @author  dpersa
 */
@Singleton
public class AccessTypeGenerator implements Generator<AccessType> {

    @Nonnull
    @Override
    public StringBuilder generate(@Nonnull final AccessType accessType) {
        checkNotNull(accessType, "accessType should not be null");
        if (accessType == AccessType.DEFAULT) {
            return new StringBuilder();
        }

        return new StringBuilder(accessType.toString().toLowerCase());
    }
}
