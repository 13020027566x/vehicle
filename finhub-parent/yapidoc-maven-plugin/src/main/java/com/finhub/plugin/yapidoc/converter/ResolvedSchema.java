package com.finhub.plugin.yapidoc.converter;

import com.finhub.plugin.yapidoc.models.media.Schema;

import java.util.Map;

public class ResolvedSchema {
    public Schema schema;
    public Map<String, Schema> referencedSchemas;
}
