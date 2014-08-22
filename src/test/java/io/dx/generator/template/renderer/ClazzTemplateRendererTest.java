package io.dx.generator.template.renderer;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.io.CharStreams;
import io.dx.generator.template.HandlebarsProvider;
import io.dx.generator.template.TemplateRenderer;
import org.junit.Rule;
import org.junit.Test;

import com.github.jknack.handlebars.Handlebars;

import com.google.common.collect.ImmutableList;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.junit4.GuiceBerryRule;

import io.dx.generator.domain.AccessType;
import io.dx.generator.domain.Clazz;
import io.dx.generator.domain.Method;
import io.dx.generator.domain.Param;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

public class ClazzTemplateRendererTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(Config.class);

    @Inject
    private ClazzTemplateRenderer clazzTemplateRenderer;

    @Test
    public void testPopulateTemplate() throws Exception {

        final Param param1 = Param.of("str", "String");
        final Param param2 = Param.of("number", "Integer");

        final Method method =
            Method.builder("methodName", "boolean")             //
                  .withStaticMethod(true)                       //
                  .withAccessType(AccessType.PRIVATE)           //
                  .withParams(ImmutableList.of(param1, param2)) //
                  .build();

        final Clazz clazz =
            Clazz.builder("Hello")                      //
                 .withAccessType(AccessType.PUBLIC)     //
                 .withFinalClass(true)                  //
                 .withMethods(ImmutableList.of(method)) //
                 .build();

        final String code = clazzTemplateRenderer.render(clazz).toString();

        final String file = getClassPathFileAsString("io/dx/generator/example/Hello.java");

        assertThat(code, is(file));
    }

    @Nonnull
    private String getClassPathFileAsString(@Nonnull final String filePath) throws IOException {
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        return CharStreams.toString(inputStreamReader);
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
