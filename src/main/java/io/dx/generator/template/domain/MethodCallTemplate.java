package io.dx.generator.template.domain;

import javax.annotation.Nonnull;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.MethodCall;

/**
 * @author  dpersa
 */
public interface MethodCallTemplate extends TypeSafeTemplate<MethodCall> {

    @Nonnull
    MethodCallTemplate setRenderedCallParams(@Nonnull String callParams);
}
