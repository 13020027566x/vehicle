package com.finhub.plugin.yapidoc.test;

import com.finhub.plugin.yapidoc.models.OpenAPI;
import com.finhub.plugin.yapidoc.test.swagger.SerializationMatchers;
import com.finhub.plugin.yapidoc.utils.Yaml;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class AbstractAnnotationTest {

    public void compareAsYaml(final String actualYaml, final String expectedYaml) throws IOException {
        SerializationMatchers.assertEqualsToYaml(Yaml.mapper().readValue(actualYaml, OpenAPI.class), expectedYaml);
    }

    public void compareAsJson(final String actualJson, final String expectedJson) throws IOException {
        SerializationMatchers.assertEqualsToJson(Yaml.mapper().readValue(actualJson, OpenAPI.class), expectedJson);
    }

    protected String getOpenAPIAsString(final String file) throws IOException {
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream(file);
            return IOUtils.toString(in, StandardCharsets.UTF_8);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
