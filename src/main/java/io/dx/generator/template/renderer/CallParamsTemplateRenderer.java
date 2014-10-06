package io.dx.generator.template.renderer;

import java.util.List;

import javax.annotation.Nonnull;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.CallParam;
import io.dx.generator.template.domain.CallParamsTemplate;

/**
 * @author  danix
 */
@Singleton
public class CallParamsTemplateRenderer extends AbstractTemplateRenderer<List<CallParam>, CallParamsTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(CallParamsTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "callParams";

    @Override
    protected void populateTemplate(@Nonnull final List<CallParam> domain, @Nonnull final CallParamsTemplate template) { }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected CallParamsTemplate createTemplate(@Nonnull final Template template) {
        return template.as(CallParamsTemplate.class);
    }
}
