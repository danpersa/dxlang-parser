package io.dx.template;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;

import com.google.common.base.Throwables;

/**
 * @author  dpersa
 */
@Singleton
public class TemplateRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateRenderer.class);

    @Inject
    private Handlebars handlebars;

    @Nonnull
    public String render(@Nonnull final String templateName, @Nonnull final Object model) {
        checkNotNull(templateName, "templateName should not be null");
        checkNotNull(model, "model should not be null");

        final Context context =
            Context.newBuilder(model)                        //
                   .resolver(JavaBeanValueResolver.INSTANCE) //
                   .build();

        final Template template;
        try {
            template = handlebars.compile(templateName);
            return template.apply(context);
        } catch (IOException e) {
            LOG.error("Template compile failed!", e);
            Throwables.propagate(e);
        }

        throw new IllegalStateException("should not reach this!");
    }
}
