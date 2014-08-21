package io.dx.generator;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.jknack.handlebars.Handlebars;
import io.dx.template.HandlebarsProvider;
import io.dx.template.TemplateRenderer;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.junit4.GuiceBerryRule;

import io.dx.domain.AccessType;
import io.dx.domain.Clazz;
import io.dx.domain.Method;

public class ClazzGeneratorTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(Config.class);

    @Inject
    private ClazzGenerator clazzGenerator;

    @Test
    public void testGenerate() throws Exception {

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

        final StringBuilder code = clazzGenerator.generate(clazz);
        assertThat(code.toString(), is("code"));

    }

    public static class Config extends GuiceBerryModule {

        @Override
        protected void configure() {
            super.configure();
            bind(ClazzGenerator.class).in(Singleton.class);
            bind(TemplateRenderer.class).in(Singleton.class);
            bind(Handlebars.class).toProvider(HandlebarsProvider.class).in(Singleton.class);
        }
    }
}
