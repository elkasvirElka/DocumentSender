package com.example.a25fli.documentsender;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Server {
    private String ip;
    private int port;
    private OkHttpClient client;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;

        client = new OkHttpClient();
    }

    private HttpUrl.Builder getHttpBuilder() {
        return new HttpUrl.Builder().scheme("http").host(ip).port(port);
    }

    private void makeGetRequest(HttpUrl url, Callback callback) {
        client.newCall(new Request.Builder().url(url).build()).enqueue(callback);
    }

    private void makePostRequest(HttpUrl url, RequestBody body, Callback callback) {
        client.newCall(new Request.Builder().url(url).post(body).build()).enqueue(callback);
    }

    public void getDocList(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("doc_list")
                .build(), callback);
    }
    public void getDocEditFields(Callback callback, String userId) {
        if(userId == null || userId == ""){
            userId = "0";
        }
        makeGetRequest(getHttpBuilder().addPathSegment("doc_fields")
                .addQueryParameter("id", String.valueOf(userId)).build(), callback);
    }
    public void getDocRequestList(Callback callback, String userId) {
        if(userId == null || userId == ""){
            userId = "0";
        }
        makeGetRequest(getHttpBuilder().addPathSegment("doc_request")
                .addQueryParameter("id", String.valueOf(userId)).build(), callback);
    }
    public void sendPrefile(Callback callback, Map<String, String> item) {
        RequestBody body = RequestBody.create(JSON, item.toString());
        makePostRequest(getHttpBuilder().addPathSegment("prefile").build(),
                body, callback);
    }
}
