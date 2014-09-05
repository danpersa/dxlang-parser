package io.dx.generator.template.renderer;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.VariableDeclaration;
import io.dx.generator.template.domain.VariableDeclarationTemplate;

/**
 * @author  dpersa
 */
public class VariableDeclarationTemplateRenderer
    extends AbstractTemplateRenderer<VariableDeclaration, VariableDeclarationTemplate>
    implements StatementTemplateRenderer<VariableDeclaration, VariableDeclarationTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(ParamsTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "variableDeclaration";

    @Override
    protected void populateTemplate(@Nonnull final VariableDeclaration domain,
            @Nonnull final VariableDeclarationTemplate template) { }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected VariableDeclarationTemplate createTemplate(@Nonnull final Template template) {
        return template.as(VariableDeclarationTemplate.class);
    }

    @Override
    public Class<VariableDeclaration> supports() {
        return VariableDeclaration.class;
    }
}
