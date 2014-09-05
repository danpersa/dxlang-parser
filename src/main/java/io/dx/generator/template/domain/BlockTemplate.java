package io.dx.generator.template.domain;

import com.github.jknack.handlebars.TypeSafeTemplate;

import io.dx.generator.domain.Block;

import javax.annotation.Nonnull;

/**
 * @author  dpersa
 */
public interface BlockTemplate extends TypeSafeTemplate<Block> {

    @Nonnull
    BlockTemplate setRenderedStatements(@Nonnull String renderedStatements);
}
