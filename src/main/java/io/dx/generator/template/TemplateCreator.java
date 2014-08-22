package io.dx.generator.template;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.annotation.Nonnull;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import com.google.common.base.Throwables;

import com.google.inject.Inject;

/**
 * @author  danix
 */
@Singleton
public class TemplateCreator {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateCreator.class);

    @Inject
    private Handlebars handlebars;

    @Nonnull
    public Template create(@Nonnull final String templateName) {
        checkNotNull(templateName, "templateName should not be null");

        final Template template;
        try {
            template = handlebars.compile(templateName);
            return template;
        } catch (IOException e) {
            LOG.error("Template compile failed!", e);
            Throwables.propagate(e);
        }

        throw new IllegalStateException("Could not create template!!");
    }

}
