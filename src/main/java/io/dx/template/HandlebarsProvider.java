package io.dx.template;

import javax.inject.Provider;
import javax.inject.Singleton;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import com.google.inject.Provides;

/**
 * @author  dpersa
 */
public class HandlebarsProvider implements Provider<Handlebars> {

    @Override
    public Handlebars get() {
        final TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".hbs");
        return new Handlebars(loader);
    }
}
