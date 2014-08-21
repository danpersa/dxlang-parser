package io.dx.template.renderer;

import com.github.jknack.handlebars.Template;
import io.dx.domain.Method;
import io.dx.template.domain.MethodTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

/**
 * @author danix
 */
@Singleton
public class MethodTemplateRenderer extends AbstractTemplateRenderer<Method, MethodTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "method";

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    protected void populateTemplate(Method method, MethodTemplate methodTemplate) {

    }

    @Nonnull
    @Override
    protected MethodTemplate createTemplate(@Nonnull final Template template) {
        return template.as(MethodTemplate.class);
    }

}
