package com.finhub.plugin.yapidoc.deserializer;

import com.finhub.plugin.yapidoc.EncodingPropertyStyleEnumDeserializer;
import com.finhub.plugin.yapidoc.EncodingStyleEnumDeserializer;
import com.finhub.plugin.yapidoc.HeaderStyleEnumDeserializer;
import com.finhub.plugin.yapidoc.models.Paths;
import com.finhub.plugin.yapidoc.models.callbacks.Callback;
import com.finhub.plugin.yapidoc.models.headers.Header;
import com.finhub.plugin.yapidoc.models.media.Encoding;
import com.finhub.plugin.yapidoc.models.media.EncodingProperty;
import com.finhub.plugin.yapidoc.models.media.Schema;
import com.finhub.plugin.yapidoc.models.parameters.Parameter;
import com.finhub.plugin.yapidoc.models.responses.ApiResponses;
import com.finhub.plugin.yapidoc.models.security.SecurityScheme;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class DeserializationModule extends SimpleModule {

    public DeserializationModule() {

        this.addDeserializer(Schema.class, new ModelDeserializer());
        this.addDeserializer(Parameter.class, new ParameterDeserializer());
        this.addDeserializer(Header.StyleEnum.class, new HeaderStyleEnumDeserializer());
        this.addDeserializer(Encoding.StyleEnum.class, new EncodingStyleEnumDeserializer());
        this.addDeserializer(EncodingProperty.StyleEnum.class, new EncodingPropertyStyleEnumDeserializer());

        this.addDeserializer(SecurityScheme.class, new SecuritySchemeDeserializer());

        this.addDeserializer(ApiResponses.class, new ApiResponsesDeserializer());
        this.addDeserializer(Paths.class, new PathsDeserializer());
        this.addDeserializer(Callback.class, new CallbackDeserializer());
    }
}
