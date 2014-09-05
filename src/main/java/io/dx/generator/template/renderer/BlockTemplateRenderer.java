package io.dx.generator.template.renderer;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.multibindings.MapBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import com.google.common.collect.ImmutableMap;

import io.dx.generator.domain.Block;
import io.dx.generator.domain.Statement;
import io.dx.generator.template.domain.BlockTemplate;

/**
 * @author  danix
 */
@Singleton
public class BlockTemplateRenderer extends AbstractTemplateRenderer<Block, BlockTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(BlockTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "block";

    private Map<Class, StatementTemplateRenderer> domainClassToStatementRenderer;

    @Override
    protected void populateTemplate(@Nonnull final Block domain, @Nonnull final BlockTemplate template) { 
        populateStatements(domain, template);
    }

    private void populateStatements(@Nonnull final Block domain, @Nonnull final BlockTemplate template) {
        final StringBuilder statementsSb = new StringBuilder();

        for (final Statement statement : domain.getStatements()) {
            final StatementTemplateRenderer renderer = domainClassToStatementRenderer.get(statement.getClass());
            statementsSb.append(renderer.render(statement));
        }

        template.setRenderedStatements(statementsSb.toString());
    }

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
    
    @Inject
    private void setDomainClassToStatementRenderer(Map<Class, StatementTemplateRenderer> statementClassToTemplateRender) {
        domainClassToStatementRenderer = statementClassToTemplateRender;
    }

//    @Inject
//    private void setStatementTemplateRenderers(@Nonnull final Set<StatementTemplateRenderer> renderers) {
//        final ImmutableMap.Builder<Class<? extends Statement>, StatementTemplateRenderer> builder = ImmutableMap.builder();
//        for (final StatementTemplateRenderer renderer : renderers) {
//            builder.put(renderer.supports(), renderer);
//        }
//
//        domainClassToStatementRenderer = builder.build();
//    }
}
