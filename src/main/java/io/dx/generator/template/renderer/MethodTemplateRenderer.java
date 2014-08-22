package io.dx.generator.template.renderer;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.Method;

import io.dx.generator.template.domain.MethodTemplate;

/**
 * @author  danix
 */
@Singleton
public class MethodTemplateRenderer extends AbstractTemplateRenderer<Method, MethodTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "method";

    @Inject
    private ParamsTemplateRenderer paramsTemplateRenderer;

    @Inject
    private BlockTemplateRenderer blockTemplateRenderer;

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    protected void populateTemplate(@Nonnull final Method method, @Nonnull final MethodTemplate methodTemplate) {
        populateParams(method, methodTemplate);
        populateBlock(method, methodTemplate);
    }

    private void populateBlock(final Method method, final MethodTemplate methodTemplate) {
        final StringBuilder renderedBlock = blockTemplateRenderer.render(method.getBlock());
        methodTemplate.setRenderedBlock(renderedBlock);
    }

    private void populateParams(@Nonnull final Method method, @Nonnull final MethodTemplate methodTemplate) {
        final StringBuilder renderedParams = paramsTemplateRenderer.render(method.getParams());
        methodTemplate.setRenderedParams(renderedParams.toString());
    }

    @Nonnull
    @Override
    protected MethodTemplate createTemplate(@Nonnull final Template template) {
        return template.as(MethodTemplate.class);
    }

}
