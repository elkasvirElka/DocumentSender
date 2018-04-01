package com.example.a25fli.documentsender;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Server {
    private String ip;
    private int port;
    private OkHttpClient client;

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
        makeGetRequest(getHttpBuilder().addPathSegment("doc_list").build(), callback);
    }
}
