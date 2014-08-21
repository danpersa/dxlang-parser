package io.dx.template.renderer;

import com.github.jknack.handlebars.Handlebars;
import com.google.common.collect.ImmutableList;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.junit4.GuiceBerryRule;
import io.dx.domain.AccessType;
import io.dx.domain.Clazz;
import io.dx.domain.Method;
import io.dx.generator.ClazzGenerator;
import io.dx.template.HandlebarsProvider;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClazzTemplateRendererTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(Config.class);

    @Inject
    private ClazzTemplateRenderer clazzTemplateRenderer;

    @Test
    public void testPopulateTemplate() throws Exception {

        final Method method =
                Method.builder("methodName", "boolean")   //
                        .withStaticMethod(true)             //
                        .withAccessType(AccessType.PRIVATE) //
                        .build();

        final Clazz clazz =
                Clazz.builder("Hello")                      //
                        .withAccessType(AccessType.PUBLIC)     //
                        .withFinalClass(true)                  //
                        .withMethods(ImmutableList.of(method)) //
                        .build();

        final StringBuilder code = clazzTemplateRenderer.render(clazz);
        assertThat(code.toString(), is("code"));

    }

    public static class Config extends GuiceBerryModule {

        @Override
        protected void configure() {
            super.configure();
            bind(ClazzGenerator.class).in(Singleton.class);
            bind(io.dx.template.TemplateRenderer.class).in(Singleton.class);
            bind(Handlebars.class).toProvider(HandlebarsProvider.class).in(Singleton.class);
        }
    }
}