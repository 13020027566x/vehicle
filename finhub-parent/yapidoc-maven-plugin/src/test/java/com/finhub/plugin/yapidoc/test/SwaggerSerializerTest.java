package com.finhub.plugin.yapidoc.test;

import com.finhub.plugin.yapidoc.ModelConverters;
import com.finhub.plugin.yapidoc.models.OpenAPI;
import com.finhub.plugin.yapidoc.models.Operation;
import com.finhub.plugin.yapidoc.models.PathItem;
import com.finhub.plugin.yapidoc.models.Paths;
import com.finhub.plugin.yapidoc.models.info.Contact;
import com.finhub.plugin.yapidoc.models.info.Info;
import com.finhub.plugin.yapidoc.models.links.Link;
import com.finhub.plugin.yapidoc.models.media.Content;
import com.finhub.plugin.yapidoc.models.media.IntegerSchema;
import com.finhub.plugin.yapidoc.models.media.MediaType;
import com.finhub.plugin.yapidoc.models.media.Schema;
import com.finhub.plugin.yapidoc.models.media.StringSchema;
import com.finhub.plugin.yapidoc.models.parameters.Parameter;
import com.finhub.plugin.yapidoc.models.parameters.RequestBody;
import com.finhub.plugin.yapidoc.models.responses.ApiResponse;
import com.finhub.plugin.yapidoc.models.responses.ApiResponses;
import com.finhub.plugin.yapidoc.models.servers.Server;
import com.finhub.plugin.yapidoc.test.res.Person;
import com.finhub.plugin.yapidoc.test.swagger.SerializationMatchers;
import com.finhub.plugin.yapidoc.utils.Json;
import com.thoughtworks.qdox.model.JavaClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @date 2019-06-06 22:28
 */
public class SwaggerSerializerTest {

    @Test
    public void convertSpec() throws IOException {
        JavaClass error = TestBase.builder.getClassByName(Error.class.getName());
        JavaClass persion = TestBase.builder.getClassByName(Person.class.getName());
        final Schema errorModel = ModelConverters.getInstance().read(error).get("Error");
        final Schema personModel = ModelConverters.getInstance().read(persion).get("Person");
        final Info info = new Info().version("1.0.0").title("Swagger Petstore");
        final Contact contact = new Contact().name("Swagger API Team").email("foo@bar.baz").url("http://swagger.io");
        info.setContact(contact);

        final Map<String, Object> map = new HashMap<>();
        map.put("name", "value");
        info.addExtension("x-test2", map);
        info.addExtension("x-test", "value");

        final OpenAPI swagger = new OpenAPI().info(info).addServersItem(new Server().url("http://petstore.swagger.io"))
            .schema("Person", personModel).schema("Error", errorModel);
        final Operation get = new Operation().summary("finds pets in the system").description("a longer description")
            .addTagsItem("Pet Operations").operationId("get pet by id").deprecated(true);

        get.addParametersItem(new Parameter().in("query").name("tags").description("tags to filter by").required(false)
            .schema(new StringSchema()));

        get.addParametersItem(new Parameter().in("path").name("petId").description("pet to fetch")
            .schema(new IntegerSchema().format("int64")));

        final ApiResponse response = new ApiResponse().description("pets returned").content(new Content()
            .addMediaType("application/json", new MediaType().schema(new Schema().$ref("Person")).example("fun")));

        final ApiResponse errorResponse = new ApiResponse().description("error response")
            .link("myLink",
                new Link().description("a link").operationId("theLinkedOperationId").parameters("userId",
                    "gah"))
            .content(new Content().addMediaType("application/json",
                new MediaType().schema(new Schema().$ref("Error"))));

        get.responses(new ApiResponses().addApiResponse("200", response).addApiResponse("default", errorResponse));
        final Operation post = new Operation().summary("adds a new pet").description("you can add a new pet this way")
            .addTagsItem("Pet Operations").operationId("add pet")
            .responses(new ApiResponses().addApiResponse("default", errorResponse))
            .requestBody(new RequestBody().description("the pet to add").content(
                new Content().addMediaType("*/*", new MediaType().schema(new Schema().$ref("Person")))));

        swagger.paths(new Paths().addPathItem("/pets", new PathItem().get(get).post(post)));
        final String swaggerJson = Json.mapper().writeValueAsString(swagger);
        Json.prettyPrint(swagger);
        final OpenAPI rebuilt = Json.mapper().readValue(swaggerJson, OpenAPI.class);
        SerializationMatchers.assertEqualsToJson(rebuilt, swaggerJson);
    }
}
