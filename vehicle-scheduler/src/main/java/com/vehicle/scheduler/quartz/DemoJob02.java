package com.vehicle.scheduler.quartz;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Quartz Job 定时任务测试
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@DisallowConcurrentExecution
public class DemoJob02 extends QuartzJobBean {

    private static final AtomicInteger COUNTS = new AtomicInteger();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        testEnhanceOkHttpClient();
//        testEnhanceHutoolHttpRequest();
//        testEnhanceCloseableHttpClient();
//        testEnhanceCloseableHttpAsyncClient();

        log.info("[QuartzJobBean.executeInternal] {} 定时第 ({}) 次执行成功", "DemoJob02", COUNTS.incrementAndGet());
    }

    private void testEnhanceOkHttpClient() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url("http://localhost:58080/login")
                .build();
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testEnhanceHutoolHttpRequest() {
        HttpUtil.createGet("http://localhost:58080/login").execute();
    }

    private void testEnhanceCloseableHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            httpClient.execute(new HttpGet("http://localhost:58080/login"));
        } catch (IOException e) {
            log.warn("Test enhance CloseableHttpClient fail.", e);
        }
    }

    private void testEnhanceCloseableHttpAsyncClient() {
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();

        httpClient.execute(new HttpGet("http://localhost:58080/login"), new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {

            }

            @Override
            public void failed(Exception ex) {

            }

            @Override
            public void cancelled() {

            }
        });
    }
}
