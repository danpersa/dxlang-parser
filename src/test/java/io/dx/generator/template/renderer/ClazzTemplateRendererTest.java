package io.dx.generator.template.renderer;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.junit.Rule;
import org.junit.Test;

import com.github.jknack.handlebars.Handlebars;

import com.google.common.collect.ImmutableList;
import com.google.common.io.CharStreams;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.junit4.GuiceBerryRule;

import com.google.inject.multibindings.MapBinder;

import io.dx.generator.domain.AccessType;
import io.dx.generator.domain.Assignment;
import io.dx.generator.domain.Block;
import io.dx.generator.domain.CallParam;
import io.dx.generator.domain.Clazz;
import io.dx.generator.domain.Method;
import io.dx.generator.domain.MethodCall;
import io.dx.generator.domain.Param;
import io.dx.generator.domain.VariableDeclaration;
import io.dx.generator.template.HandlebarsProvider;
import io.dx.generator.template.TemplateRenderer;

public class ClazzTemplateRendererTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(Config.class);

    @Inject
    private ClazzTemplateRenderer clazzTemplateRenderer;

    @Test
    public void testPopulateTemplate() throws Exception {
        final Clazz stringClazz = Clazz.builder("String").build();
        final Clazz integerClazz = Clazz.builder("Integer").build();

        final Param param1 = Param.of("str", stringClazz);
        final Param param2 = Param.of("number", integerClazz);

        final MethodCall methodCall =
            MethodCall.builder("print")                 //
                      .addParam(CallParam.of("first"))  //
                      .addParam(CallParam.of("second")) //
                      .build();

        final Block block =
            Block.builder()                                                //
                 .addStatement(VariableDeclaration.of("str", stringClazz)) //
                 .addStatement(Assignment.of("str", methodCall))           //
                 .build();

        final Method method =
            Method.builder("methodName", "boolean")             //
                  .withStaticMethod(true)                       //
                  .withAccessType(AccessType.PRIVATE)           //
                  .withParams(ImmutableList.of(param1, param2)) //
                  .withBlock(block)                             //
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

            MapBinder<Class, StatementTemplateRenderer> statementClassToTemplateRendererBinder = MapBinder.newMapBinder(
                    binder(), Class.class, StatementTemplateRenderer.class);

            statementClassToTemplateRendererBinder.addBinding(VariableDeclaration.class)         //
                                                  .to(VariableDeclarationTemplateRenderer.class) //
                                                  .in(Singleton.class);

            statementClassToTemplateRendererBinder.addBinding(MethodCall.class)         //
                                                  .to(MethodCallTemplateRenderer.class) //
                                                  .in(Singleton.class);

            statementClassToTemplateRendererBinder.addBinding(Assignment.class)         //
                                                  .to(AssignmentTemplateRenderer.class) //
                                                  .in(Singleton.class);
        }
    }
}
