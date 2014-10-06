package io.dx.generator.template.renderer;

import java.util.Map;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.dx.generator.domain.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Template;

import io.dx.generator.domain.Assignment;
import io.dx.generator.template.domain.AssignmentTemplate;

/**
 * @author  dpersa
 */
@Singleton
public class AssignmentTemplateRenderer extends AbstractTemplateRenderer<Assignment, AssignmentTemplate>
    implements StatementTemplateRenderer<Assignment, AssignmentTemplate> {

    private static final Logger LOG = LoggerFactory.getLogger(ParamsTemplateRenderer.class);

    private static final String TEMPLATE_NAME = "assignment";

    private Map<Class, StatementTemplateRenderer> domainClassToStatementRenderer;

    @Override
    protected void populateTemplate(@Nonnull final Assignment domain, @Nonnull final AssignmentTemplate template) {
        populateStatement(domain, template);
    }

    private void populateStatement(@Nonnull final Assignment domain, @Nonnull final AssignmentTemplate template) {
        final StringBuilder statementsSb = new StringBuilder();
        final Statement statement = domain.getStatement();
        final StatementTemplateRenderer renderer = domainClassToStatementRenderer.get(statement.getClass());
        
        statementsSb.append(renderer.render(statement));

        template.setRenderedStatement(statementsSb.toString());
    }

    @Nonnull
    @Override
    protected String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Nonnull
    @Override
    protected AssignmentTemplate createTemplate(@Nonnull final Template template) {
        return template.as(AssignmentTemplate.class);
    }

    @Override
    public Class<Assignment> supports() {
        return Assignment.class;
    }

    @Inject
    private void setDomainClassToStatementRenderer(
            final Map<Class, StatementTemplateRenderer> statementClassToTemplateRender) {
        domainClassToStatementRenderer = statementClassToTemplateRender;
    }
}
