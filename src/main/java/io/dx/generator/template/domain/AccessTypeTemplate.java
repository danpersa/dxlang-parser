package io.dx.generator.template.domain;

import javax.annotation.Nonnull;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.AccessType;

/**
 * @author  danix
 */
public interface AccessTypeTemplate extends TypeSafeTemplate<AccessType> {

    @Nonnull
    AccessTypeTemplate setAccessType(@Nonnull String accessType);

}
