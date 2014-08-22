package io.dx.generator.template.renderer;

import javax.annotation.Nonnull;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.Block;

import io.dx.generator.template.domain.BlockTemplate;

/**
 * @author  danix
 */
@Singleton
public class BlockTemplateRenderer extends AbstractTemplateRenderer<Block, BlockTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(BlockTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "block";

    @Override
    protected void populateTemplate(@Nonnull final Block domain, @Nonnull final BlockTemplate template) { }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected BlockTemplate createTemplate(@Nonnull final Template template) {
        return template.as(BlockTemplate.class);
    }
}
