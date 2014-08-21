package io.dx.template.renderer;

import com.github.jknack.handlebars.Template;
import io.dx.domain.Clazz;
import io.dx.domain.Method;
import io.dx.template.domain.ClazzTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author danix
 */
@Singleton
public class ClazzTemplateRenderer extends AbstractTemplateRenderer<Clazz, ClazzTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(ClazzTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "clazz";

    @Inject
    private MethodTemplateRenderer methodTemplateRenderer;

    @Inject
    private AccessTypeTemplateRenderer accessTypeTemplateRenderer;

    @Override
    protected void populateTemplate(@Nonnull final Clazz domain, @Nonnull final ClazzTemplate template) {
        populateMethods(domain, template);
        populateAccessType(domain, template);
    }

    private void populateAccessType(@Nonnull Clazz domain, @Nonnull ClazzTemplate template) {
        StringBuilder accessTypeSb = new StringBuilder(accessTypeTemplateRenderer.render(domain.getAccessType()));
        template.setRenderedAccessType(accessTypeSb.toString());
    }

    private void populateMethods(@Nonnull final Clazz domain, @Nonnull final ClazzTemplate template) {
        LOG.debug("start populate methods");
        final StringBuilder methodsSb = new StringBuilder();

        for (final Method method : domain.getMethods()) {
            methodsSb.append(methodTemplateRenderer.render(method));
        }

        template.setRenderedMethods(methodsSb.toString());
    }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected ClazzTemplate createTemplate(@Nonnull Template template) {
        return template.as(ClazzTemplate.class);
    }
}
