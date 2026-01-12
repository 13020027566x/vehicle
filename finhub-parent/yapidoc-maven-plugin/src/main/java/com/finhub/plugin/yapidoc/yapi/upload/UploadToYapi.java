package com.finhub.plugin.yapidoc.yapi.upload;

import com.finhub.plugin.yapidoc.yapi.constant.YapiConstant;
import com.finhub.plugin.yapidoc.yapi.utils.HttpClientUtil;
import com.finhub.plugin.yapidoc.utils.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 上传到yapi
 *
 * @author fenglibin
 * @version V1.0
 */
public class UploadToYapi {
    public static Map<String, Map<String, Integer>> catMap = new HashMap<>();
    public final ObjectMapper mapper = Json.mapper();
    private final String projectToken;
    private final String yapiUrl;

    public UploadToYapi(String projectToken, String yapiUrl) {
        this.projectToken = projectToken;
        this.yapiUrl = yapiUrl;
    }

    /**
     * 上传OpenAPi
     *
     * @param openAPI
     * @throws Exception
     */
    public String upload(Object openAPI, boolean merge) throws Exception {
        Map<String, Object> send = new HashMap<>(4);
        send.put("type", "swagger");
        send.put("token", projectToken);
        send.put("merge", merge);
        send.put("json", mapper.writeValueAsString(openAPI));
        CloseableHttpResponse response = null;
        try {
            // Send Request
            response = HttpClientUtil.getHttpclient()
                .execute(getHttpPost(yapiUrl + YapiConstant.IMPUT, mapper.writeValueAsString(send)));
            // Receive response
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 获得httpPost
     *
     * @return
     */
    private HttpPost getHttpPost(String url, String body) {
        HttpPost httpPost = null;
        httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        HttpEntity reqEntity = new StringEntity(body == null ? "" : body, "UTF-8");
        httpPost.setEntity(reqEntity);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000)
            .setSocketTimeout(120000).build();
        httpPost.setConfig(requestConfig);
        return httpPost;
    }

}
