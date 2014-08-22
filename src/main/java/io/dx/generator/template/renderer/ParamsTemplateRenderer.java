package io.dx.generator.template.renderer;

import java.util.List;

import javax.annotation.Nonnull;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.Param;

import io.dx.generator.template.domain.ParamsTemplate;

/**
 * @author  danix
 */
@Singleton
public class ParamsTemplateRenderer extends AbstractTemplateRenderer<List<Param>, ParamsTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(ParamsTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "params";

    @Override
    protected void populateTemplate(@Nonnull final List<Param> domain, @Nonnull final ParamsTemplate template) { }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected ParamsTemplate createTemplate(@Nonnull final Template template) {
        return template.as(ParamsTemplate.class);
    }
}
