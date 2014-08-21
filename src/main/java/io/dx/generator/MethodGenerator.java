package io.dx.generator;

import com.google.common.base.Preconditions;
import io.dx.domain.Method;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.*;

/**
 * @author dpersa
 */
@Singleton
public class MethodGenerator implements Generator<Method> {
    
    @Inject
    private AccessTypeGenerator accessTypeGenerator;
    
    @Inject
    private BlockGenerator blockGenerator;
    
    @Nonnull
    @Override
    public StringBuilder generate(@Nonnull Method method) {
        checkNotNull(method, "method should not be null");
        final StringBuilder methodBuilder = accessTypeGenerator.generate(method.getAccessType()).append(" ");
        if (method.isStaticMethod()) {
            methodBuilder.append("static ");
        }
        methodBuilder.append(method.getName());
        methodBuilder.append("(");
        
        // render parameters
        
        methodBuilder.append(") {");
        
        //blockGenerator.generate(method.getBlock());
        
        methodBuilder.append("}");
        return methodBuilder;
    }
}
