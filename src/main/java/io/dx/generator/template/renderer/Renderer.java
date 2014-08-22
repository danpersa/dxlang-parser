package io.dx.generator.template.renderer;

import javax.annotation.Nonnull;

import com.github.jknack.handlebars.TypeSafeTemplate;

/**
 * @author  danix
 */
public interface Renderer<D, T extends TypeSafeTemplate<D>> {

    @Nonnull
    StringBuilder render(@Nonnull final D domain);
}
