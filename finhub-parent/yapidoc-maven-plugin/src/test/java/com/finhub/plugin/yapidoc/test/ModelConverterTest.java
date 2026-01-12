package com.finhub.plugin.yapidoc.test;

import com.finhub.plugin.yapidoc.ModelConverters;
import com.finhub.plugin.yapidoc.converter.AnnotatedType;
import com.finhub.plugin.yapidoc.models.media.Schema;
import com.finhub.plugin.yapidoc.test.res.Person;
import com.finhub.plugin.yapidoc.test.res.Pet;
import com.finhub.plugin.yapidoc.test.swagger.ResourceUtils;
import com.finhub.plugin.yapidoc.test.swagger.SerializationMatchers;
import com.thoughtworks.qdox.model.JavaClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @version V1.0
 * @date 2019-06-05 22:22
 */
public class ModelConverterTest {
    @Test
    public void readModel() throws IOException {
        JavaClass classByName = TestBase.builder.getClassByName(Pet.class.getName());
        assertEqualsToJson(readAll(classByName), "Pet.json");
    }

    public void convertModel() throws IOException {
        JavaClass classByName = TestBase.builder.getClassByName(Person.class.getName());

        assertEqualsToJson(read(classByName), "Person.json");
    }

    private Map<String, Schema> read(JavaClass type) {
        return ModelConverters.getInstance().read(type);
    }

    private Map<String, Schema> readAll(JavaClass type) {
        return ModelConverters.getInstance().readAll(new AnnotatedType(type));
    }

    private void assertEqualsToJson(Object objectToSerialize, String fileName) throws IOException {
        final String json = ResourceUtils.loadClassResource(getClass(), fileName);
        SerializationMatchers.assertEqualsToJson(objectToSerialize, json);
    }
}
