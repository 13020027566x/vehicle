package com.finhub.plugin.yapidoc.converter;

import com.finhub.plugin.yapidoc.models.media.Schema;

import java.util.Iterator;

/**
 * 模型解析
 */
public interface ModelConverter {

    /**
     * 解析
     *
     * @param type
     * @param context
     * @param chain   the chain of model converters to try if this implementation
     *                cannot process
     * @return null if this ModelConverter cannot convert the given Type
     */
    Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain);
}
