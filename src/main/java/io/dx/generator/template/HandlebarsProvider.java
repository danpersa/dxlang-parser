package io.dx.generator.template;

import java.util.Iterator;
import java.util.List;

import javax.inject.Provider;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.dx.generator.domain.Param;

/**
 * @author  dpersa
 */
public class HandlebarsProvider implements Provider<Handlebars> {

    @Override
    public Handlebars get() {
        final TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".hbs");

        final Handlebars handlebars = new Handlebars(loader);
        handlebars.registerHelper("param-list", new Helper<List<Param>>() {
                public CharSequence apply(final List<Param> list, final Options options) {
                    StringBuilder result = new StringBuilder();
                    Iterator<Param> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        final Param param = iterator.next();
                        result.append(param.getParamType()).append(" ").append(param.getParamName());
                        if (iterator.hasNext()) {
                            result.append(", ");
                        }
                    }

                    return new Handlebars.SafeString(result);
                }
            });
        return handlebars;
    }
}
