package io.dx.generator.template.renderer;

import javax.annotation.Nonnull;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.AccessType;

import io.dx.generator.template.domain.AccessTypeTemplate;

/**
 * @author  danix
 */
@Singleton
public class AccessTypeTemplateRenderer extends AbstractTemplateRenderer<AccessType, AccessTypeTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(AccessTypeTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "accessType";

    @Override
    protected void populateTemplate(@Nonnull final AccessType domain, @Nonnull final AccessTypeTemplate template) {
        if (domain == AccessType.DEFAULT) {
            template.setAccessType("");
            return;
        }

        template.setAccessType(domain.name().toLowerCase());
    }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected AccessTypeTemplate createTemplate(@Nonnull final Template template) {
        return template.as(AccessTypeTemplate.class);
    }
}
