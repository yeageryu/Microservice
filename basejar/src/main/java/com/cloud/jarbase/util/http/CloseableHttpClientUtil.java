package com.cloud.jarbase.util.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class CloseableHttpClientUtil {

    private CloseableHttpClientUtil() {
    }

    private static class CloseableHttpClientUtilHolder {
        private static final CloseableHttpClientUtil INSTANCE = new CloseableHttpClientUtil();
    }

    public static final CloseableHttpClientUtil getInstance() {
        return CloseableHttpClientUtilHolder.INSTANCE;
    }

    private static CloseableHttpClient client;

    private static RequestConfig config;

    private static RequestConfig config2;

    static {
        config = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build();
        config2 = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
        client = HttpClients.createDefault();
    }

    private static ResponseHandler<String> responseHandler = new BasicResponseHandler();

    public String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        String response = null;
        try {
            response = client.execute(httpGet, responseHandler);
        } catch (IOException e) {
            log.error("HttpClient execute error", e);
        }
        return response;
    }

    /**
     * XE专用，超时时间为2s
     * @param url
     * @param userName
     * @param password
     * @return
     */
    public CloseableHttpResponse doGet(String url, String userName, String password) {
        UsernamePasswordCredentials cre = new UsernamePasswordCredentials(userName,password);
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(AuthScope.ANY, cre);

        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(provider);

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config2);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet, context);
        } catch (IOException e) {
            log.error("HttpClient execute error", e);
        }
        return response;
    }

    public CloseableHttpResponse doGet(String url, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        headers.forEach((k, v) -> httpGet.setHeader(k, v));

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            log.error("HttpClient execute error", e);
        }
        return response;
    }
}
