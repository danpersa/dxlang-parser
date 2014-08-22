package io.dx.generator.template.domain;

import javax.annotation.Nonnull;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.Clazz;

/**
 * @author  danix
 */
public interface ClazzTemplate extends TypeSafeTemplate<Clazz> {

    @Nonnull
    ClazzTemplate setName(@Nonnull String name);

    @Nonnull
    ClazzTemplate setFinalClass(boolean finalClass);

    @Nonnull
    ClazzTemplate setRenderedAccessType(@Nonnull String accessType);

    @Nonnull
    ClazzTemplate setRenderedMethods(@Nonnull String methods);
}
