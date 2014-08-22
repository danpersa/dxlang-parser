package io.dx.generator.template;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.dx.generator.template.HandlebarsProvider;
import io.dx.generator.template.TemplateRenderer;
import org.junit.Rule;
import org.junit.Test;

import com.github.jknack.handlebars.Handlebars;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.junit4.GuiceBerryRule;

public class TemplateRendererTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(Config.class);

    @Inject
    private TemplateRenderer templateRenderer;

    @Test
    public void testRender() throws Exception {

        final String result = templateRenderer.render("hello", "Dan");
        assertThat(result, is("Hello Dan!"));
    }

    public static class Config extends GuiceBerryModule {

        @Override
        protected void configure() {
            super.configure();
            bind(TemplateRenderer.class).in(Singleton.class);
            bind(Handlebars.class).toProvider(HandlebarsProvider.class).in(Singleton.class);
        }
    }
}
