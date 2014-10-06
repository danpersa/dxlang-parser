package io.dx.generator.template.renderer;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.MethodCall;
import io.dx.generator.template.domain.MethodCallTemplate;

/**
 * @author  danix
 */
@Singleton
public class MethodCallTemplateRenderer extends AbstractTemplateRenderer<MethodCall, MethodCallTemplate>
    implements StatementTemplateRenderer<MethodCall, MethodCallTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodCallTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "methodCall";

    @Inject
    private CallParamsTemplateRenderer callParamsTemplateRenderer;

    @Override
    protected void populateTemplate(@Nonnull final MethodCall domain, @Nonnull final MethodCallTemplate template) {
        populateCallParams(domain, template);
    }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    private void populateCallParams(@Nonnull final MethodCall methodCall,
            @Nonnull final MethodCallTemplate methodCallTemplate) {
        final StringBuilder renderedParams = callParamsTemplateRenderer.render(methodCall.getCallParams());
        methodCallTemplate.setRenderedCallParams(renderedParams.toString());
    }

    @Nonnull
    @Override
    protected MethodCallTemplate createTemplate(@Nonnull final Template template) {
        return template.as(MethodCallTemplate.class);
    }

    @Override
    public Class<MethodCall> supports() {
        return MethodCall.class;
    }
}
