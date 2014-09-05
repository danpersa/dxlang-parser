package io.dx.parser.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dx.parser.DxParser;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import io.dx.generator.domain.Clazz;
import io.dx.generator.domain.Param;

/**
 * @author  dpersa
 */
public final class ParamUtils {

    private static final Function<DxParser.ParamNameContext, String> PARAM_NAME_CONTEXT_STRING_FUNCTION =
        new Function<DxParser.ParamNameContext, String>() {
            @Nullable
            @Override
            public String apply(@Nullable final DxParser.ParamNameContext paramNameContext) {
                return paramNameContext.getText();
            }
        };

    private static final Function<DxParser.ParamTypeContext, String> PARAM_TYPE_CONTEXT_STRING_FUNCTION =
        new Function<DxParser.ParamTypeContext, String>() {
            @Nullable
            @Override
            public String apply(@Nullable final DxParser.ParamTypeContext paramTypeContext) {
                return paramTypeContext.getText();
            }
        };

    @Nonnull
    public static List<Param> createParamList(@Nonnull final List<String> paramNames,
            @Nonnull final List<Clazz> clazzes) {
        checkNotNull(paramNames, "paramNames should not be null");
        checkNotNull(clazzes, "clazzes should not be null");
        checkArgument(paramNames.size() == clazzes.size(), "both lists should have the same size");

        final Iterator<String> paramNamesIterator = paramNames.iterator();
        final Iterator<Clazz> clazzesIterator = clazzes.iterator();
        final ImmutableList.Builder<Param> paramListBuilder = ImmutableList.<Param>builder();
        while (paramNamesIterator.hasNext()) {
            final String paramName = paramNamesIterator.next();
            final Clazz clazz = clazzesIterator.next();
            final Param param = Param.of(paramName, clazz);
            paramListBuilder.add(param);
        }

        return paramListBuilder.build();
    }

    @Nonnull
    public static List<String> paramNameContextToStrings(@Nonnull final List<DxParser.ParamNameContext> paramNames) {
        checkNotNull(paramNames, "paramNames should not be null");
        return FluentIterable.from(paramNames)                              //
                             .transform(PARAM_NAME_CONTEXT_STRING_FUNCTION) //
                             .toList();
    }

    @Nonnull
    public static List<Clazz> paramTypeContextToStrings(@Nonnull final List<DxParser.ParamTypeContext> paramTypes,
            @Nonnull final Map<String, Clazz> nameToClazz) {
        checkNotNull(paramTypes, "paramTypes should not be null");
        return FluentIterable.from(paramTypes)                                    //
                             .transform(new ParamTypeContextToClazz(nameToClazz)) //
                             .toList();
    }

    private static class ParamTypeContextToClazz implements Function<DxParser.ParamTypeContext, Clazz> {

        private final Map<String, Clazz> nameToClazz;

        private ParamTypeContextToClazz(@Nonnull final Map<String, Clazz> nameToClazz) {
            this.nameToClazz = checkNotNull(nameToClazz, "nameToClazz should not be null");
        }

        @Nullable
        @Override
        public Clazz apply(@Nullable final DxParser.ParamTypeContext input) {
            final String clazzName = input.getText();
            if (!nameToClazz.containsKey(clazzName)) {
                throw new RuntimeException("Class " + clazzName + " not defined");
            }

            return nameToClazz.get(clazzName);
        }
    }

}
