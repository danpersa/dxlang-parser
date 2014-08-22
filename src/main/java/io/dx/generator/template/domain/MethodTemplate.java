package io.dx.generator.template.domain;

import javax.annotation.Nonnull;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.Method;

/**
 * @author  danix
 */
public interface MethodTemplate extends TypeSafeTemplate<Method> {

    @Nonnull
    MethodTemplate setRenderedParams(@Nonnull final String renderedParams);

    @Nonnull
    MethodTemplate setRenderedBlock(@Nonnull final StringBuilder renderedBlock);
}
