package io.dx.generator.template.domain;

import javax.annotation.Nonnull;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.Assignment;

/**
 * @author  dpersa
 */
public interface AssignmentTemplate extends TypeSafeTemplate<Assignment> {

    @Nonnull
    AssignmentTemplate setRenderedStatement(@Nonnull String renderedStatement);
}
