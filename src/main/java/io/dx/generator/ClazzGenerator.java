package io.dx.generator;

import javax.annotation.Nonnull;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.common.base.Preconditions;
import io.dx.domain.Clazz;
import io.dx.domain.Method;
import io.dx.template.TemplateRenderer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author  dpersa
 */
@Singleton
public class ClazzGenerator implements Generator<Clazz> {

    @Inject
    private AccessTypeGenerator accessTypeGenerator;

    @Inject
    private MethodGenerator methodGenerator;

    @Inject
    private BlockGenerator blockGenerator;
    
    @Inject
    private TemplateRenderer templateRenderer;

    @Nonnull
    @Override
    public StringBuilder generate(@Nonnull final Clazz clazz) {
        checkNotNull(clazz, "clazz should not be null");
        
        return new StringBuilder(templateRenderer.render("clazz", clazz));
        
        
// StringBuilder sb = new StringBuilder();
//
// sb.append(accessTypeGenerator.generate(clazz.getAccessType()) + " ");
// if (clazz.isFinalClass()) {
// sb.append("final ");
// }
//
// sb.append("class " + clazz.getName());
// sb.append("{");
//
// for (Method method : clazz.getMethods()) {
// sb.append(methodGenerator.generate(method));
// }
//
// sb.append("}");
// return sb;
    }
}
