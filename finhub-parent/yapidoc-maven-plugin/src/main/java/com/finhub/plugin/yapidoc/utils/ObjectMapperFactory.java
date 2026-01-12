package com.finhub.plugin.yapidoc.utils;

import com.finhub.plugin.yapidoc.ExtensionsMixin;
import com.finhub.plugin.yapidoc.deserializer.ComponentsMixin;
import com.finhub.plugin.yapidoc.deserializer.DeserializationModule;
import com.finhub.plugin.yapidoc.deserializer.OpenAPIMixin;
import com.finhub.plugin.yapidoc.deserializer.OperationMixin;
import com.finhub.plugin.yapidoc.deserializer.SchemaSerializer;
import com.finhub.plugin.yapidoc.models.Components;
import com.finhub.plugin.yapidoc.models.ExternalDocumentation;
import com.finhub.plugin.yapidoc.models.OpenAPI;
import com.finhub.plugin.yapidoc.models.Operation;
import com.finhub.plugin.yapidoc.models.PathItem;
import com.finhub.plugin.yapidoc.models.Paths;
import com.finhub.plugin.yapidoc.models.callbacks.Callback;
import com.finhub.plugin.yapidoc.models.examples.Example;
import com.finhub.plugin.yapidoc.models.headers.Header;
import com.finhub.plugin.yapidoc.models.info.Contact;
import com.finhub.plugin.yapidoc.models.info.Info;
import com.finhub.plugin.yapidoc.models.info.License;
import com.finhub.plugin.yapidoc.models.links.Link;
import com.finhub.plugin.yapidoc.models.links.LinkParameter;
import com.finhub.plugin.yapidoc.models.media.Encoding;
import com.finhub.plugin.yapidoc.models.media.EncodingProperty;
import com.finhub.plugin.yapidoc.models.media.MediaType;
import com.finhub.plugin.yapidoc.models.media.Schema;
import com.finhub.plugin.yapidoc.models.media.XML;
import com.finhub.plugin.yapidoc.models.parameters.Parameter;
import com.finhub.plugin.yapidoc.models.parameters.RequestBody;
import com.finhub.plugin.yapidoc.models.responses.ApiResponse;
import com.finhub.plugin.yapidoc.models.responses.ApiResponses;
import com.finhub.plugin.yapidoc.models.security.OAuthFlow;
import com.finhub.plugin.yapidoc.models.security.OAuthFlows;
import com.finhub.plugin.yapidoc.models.security.Scopes;
import com.finhub.plugin.yapidoc.models.security.SecurityScheme;
import com.finhub.plugin.yapidoc.models.servers.Server;
import com.finhub.plugin.yapidoc.models.servers.ServerVariable;
import com.finhub.plugin.yapidoc.models.servers.ServerVariables;
import com.finhub.plugin.yapidoc.models.tags.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectMapperFactory {

    protected static ObjectMapper createJson() {
        return create(null);
    }

    protected static ObjectMapper createYaml() {
        YAMLFactory factory = new YAMLFactory();
        factory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        factory.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        factory.enable(YAMLGenerator.Feature.SPLIT_LINES);
        factory.enable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS);

        return create(factory);
    }

    private static ObjectMapper create(JsonFactory jsonFactory) {
        ObjectMapper mapper = jsonFactory == null ? new ObjectMapper() : new ObjectMapper(jsonFactory);

        // handle ref schema serialization skipping all other props
        mapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription desc,
                        JsonSerializer<?> serializer) {
                        if (Schema.class.isAssignableFrom(desc.getBeanClass())) {
                            return new SchemaSerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });

        Module deserializerModule = new DeserializationModule();
        mapper.registerModule(deserializerModule);

        Map<Class<?>, Class<?>> sourceMixins = new LinkedHashMap<>();

        sourceMixins.put(ApiResponses.class, ExtensionsMixin.class);
        sourceMixins.put(ApiResponse.class, ExtensionsMixin.class);
        sourceMixins.put(Callback.class, ExtensionsMixin.class);
        sourceMixins.put(Components.class, ComponentsMixin.class);
        sourceMixins.put(Contact.class, ExtensionsMixin.class);
        sourceMixins.put(Encoding.class, ExtensionsMixin.class);
        sourceMixins.put(EncodingProperty.class, ExtensionsMixin.class);
        sourceMixins.put(Example.class, ExtensionsMixin.class);
        sourceMixins.put(ExternalDocumentation.class, ExtensionsMixin.class);
        sourceMixins.put(Header.class, ExtensionsMixin.class);
        sourceMixins.put(Info.class, ExtensionsMixin.class);
        sourceMixins.put(License.class, ExtensionsMixin.class);
        sourceMixins.put(Link.class, ExtensionsMixin.class);
        sourceMixins.put(LinkParameter.class, ExtensionsMixin.class);
        sourceMixins.put(MediaType.class, ExtensionsMixin.class);
        sourceMixins.put(OAuthFlow.class, ExtensionsMixin.class);
        sourceMixins.put(OAuthFlows.class, ExtensionsMixin.class);
        sourceMixins.put(OpenAPI.class, OpenAPIMixin.class);
        sourceMixins.put(Operation.class, OperationMixin.class);
        sourceMixins.put(Parameter.class, ExtensionsMixin.class);
        sourceMixins.put(PathItem.class, ExtensionsMixin.class);
        sourceMixins.put(Paths.class, ExtensionsMixin.class);
        sourceMixins.put(RequestBody.class, ExtensionsMixin.class);
        sourceMixins.put(Scopes.class, ExtensionsMixin.class);
        sourceMixins.put(SecurityScheme.class, ExtensionsMixin.class);
        sourceMixins.put(Server.class, ExtensionsMixin.class);
        sourceMixins.put(ServerVariable.class, ExtensionsMixin.class);
        sourceMixins.put(ServerVariables.class, ExtensionsMixin.class);
        sourceMixins.put(Tag.class, ExtensionsMixin.class);
        sourceMixins.put(XML.class, ExtensionsMixin.class);
        sourceMixins.put(Schema.class, ExtensionsMixin.class);

        mapper.setMixIns(sourceMixins);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }
}
