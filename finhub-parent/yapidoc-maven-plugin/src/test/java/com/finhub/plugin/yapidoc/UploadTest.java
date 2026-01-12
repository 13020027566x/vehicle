package com.finhub.plugin.yapidoc;

import com.finhub.plugin.yapidoc.models.OpenAPI;
import com.finhub.plugin.yapidoc.utils.Json;
import com.finhub.plugin.yapidoc.yapi.upload.UploadToYapi;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

public class UploadTest {
    @Test
    public void testUpload() throws Exception {
        String filePath = "/Users/TaoBangren/fenbeipay/fenbei-fx-card/fenbei-fx-card-web/target/classes/yapidoc.json";
        String yapiProjectToken = "c3f0e5c37d57d51ea3aaca469872607c395c44f6b04f9d0c7a53f56b82c169d6";
        String yapiUrl = "https://yapi.fenbeijinfu.com/";
        String json = FileUtils.readFileToString(new File(filePath), "utf-8");
        OpenAPI openAPI = Json.mapper().readValue(json, OpenAPI.class);
        UploadToYapi uploadToYapi = new UploadToYapi(yapiProjectToken, yapiUrl);
        uploadToYapi.upload(openAPI, true);
    }
}
