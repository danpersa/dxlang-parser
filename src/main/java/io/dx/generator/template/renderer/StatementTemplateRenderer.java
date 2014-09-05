package io.dx.generator.template.renderer;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.Statement;

/**
 * @author  dpersa
 */
public interface StatementTemplateRenderer<D extends Statement, T extends TypeSafeTemplate<D>> extends Renderer<D, T> {

    Class<D> supports();
}
