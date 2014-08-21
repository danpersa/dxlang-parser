package io.dx.template.domain;

import com.github.jknack.handlebars.TypeSafeTemplate;
import io.dx.domain.Clazz;

import javax.annotation.Nonnull;

/**
 * @author danix
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
