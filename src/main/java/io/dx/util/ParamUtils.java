package io.dx.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.dx.domain.Param;
import org.dx.parser.DxParser;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

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
            @Nonnull final List<String> paramTypes) {
        checkNotNull(paramNames, "paramNames should not be null");
        checkNotNull(paramTypes, "paramTypes should not be null");
        checkArgument(paramNames.size() == paramTypes.size(), "both lists should have the same size");

        final Iterator<String> paramNamesIterator = paramNames.iterator();
        final Iterator<String> paramTypeIterator = paramTypes.iterator();
        final ImmutableList.Builder<Param> paramListBuilder = ImmutableList.<Param>builder();
        while (paramNamesIterator.hasNext()) {
            final String paramName = paramNamesIterator.next();
            final String paramType = paramTypeIterator.next();
            final Param param = Param.of(paramName, paramType);
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
    public static List<String> paramTypeContextToStrings(@Nonnull final List<DxParser.ParamTypeContext> paramTypes) {
        checkNotNull(paramTypes, "paramTypes should not be null");
        return FluentIterable.from(paramTypes)                              //
                             .transform(PARAM_TYPE_CONTEXT_STRING_FUNCTION) //
                             .toList();
    }

}
