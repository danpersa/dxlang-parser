package io.dx.template.domain;

import com.github.jknack.handlebars.TypeSafeTemplate;
import io.dx.domain.AccessType;
import io.dx.domain.Method;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface AccessTypeTemplate extends TypeSafeTemplate<AccessType> {

    @Nonnull
    AccessTypeTemplate setAccessType(@Nonnull String accessType);

}
