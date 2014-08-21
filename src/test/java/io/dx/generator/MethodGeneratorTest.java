package io.dx.generator;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.junit4.GuiceBerryRule;

import io.dx.domain.AccessType;
import io.dx.domain.Method;

public class MethodGeneratorTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(Config.class);

    @Inject
    private MethodGenerator methodGenerator;

    @Test
    public void testGenerate() throws Exception {
        Method method =
            Method.builder("methodName", "boolean")   //
                  .withStaticMethod(true)             //
                  .withAccessType(AccessType.PRIVATE) //
                  .build();

        final StringBuilder code = methodGenerator.generate(method);
        assertThat(code.toString(), is("code"));

    }

    public static class Config extends GuiceBerryModule {

        @Override
        protected void configure() {
            super.configure();
            bind(MethodGenerator.class).asEagerSingleton();
        }
    }
}
