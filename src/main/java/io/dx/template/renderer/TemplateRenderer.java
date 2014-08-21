package io.dx.template.renderer;

import com.github.jknack.handlebars.TypeSafeTemplate;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface TemplateRenderer<D, T extends TypeSafeTemplate<D>> {

    @Nonnull
    StringBuilder render(@Nonnull D domain);
}
