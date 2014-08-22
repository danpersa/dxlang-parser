package io.dx.generator.template.renderer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.annotation.Nonnull;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.TypeSafeTemplate;

import com.google.common.base.Throwables;

import io.dx.generator.template.TemplateCreator;

/**
 * @author  danix
 */
public abstract class AbstractTemplateRenderer<D, T extends TypeSafeTemplate<D>> implements Renderer<D, T> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodTemplateRenderer.class);

    @Inject
    private TemplateCreator templateCreator;

    @Override
    @Nonnull
    public StringBuilder render(@Nonnull final D domain) {
        checkNotNull(domain, "domain should not be null");

        final Template template = templateCreator.create(getTemplateName());

        final T typeSafeTemplate = createTemplate(template);

        populateTemplate(domain, typeSafeTemplate);

        return render(domain, typeSafeTemplate);
    }

    protected StringBuilder render(@Nonnull final D domain, @Nonnull final T template) {
        try {
            return new StringBuilder(template.apply(domain));
        } catch (IOException e) {
            LOG.error("Could not render template", e);
            Throwables.propagate(e);
        }

        throw new IllegalStateException("Could not render template");
    }

    protected abstract void populateTemplate(@Nonnull final D domain, @Nonnull final T template);

    @Nonnull
    protected abstract String getTemplateName();

    @Nonnull
    protected abstract T createTemplate(@Nonnull Template template);
}
