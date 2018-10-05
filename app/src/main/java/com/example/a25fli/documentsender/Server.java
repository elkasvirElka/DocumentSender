package com.example.a25fli.documentsender;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
//это не Сервер!!!
//класс работы с запросами на сервер.
public class Server {
    private String ip;
    private int port;
    private OkHttpClient client;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * определение ip сервера, к которому обращаемся и порта
     */
    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;

        client = new OkHttpClient();
    }
    /**
     * строим строку
     * */
    private HttpUrl.Builder getHttpBuilder() {
        return new HttpUrl.Builder().scheme("http").host(ip).port(port);
    }

    private void makeGetRequest(HttpUrl url, Callback callback) {
        client.newCall(new Request.Builder().url(url).build()).enqueue(callback);
    }

    private void makePostRequest(HttpUrl url, RequestBody body, Callback callback) {
        client.newCall(new Request.Builder().url(url).post(body).build()).enqueue(callback);
    }
    /**
     * Обращение к серверному методу с названием doc_list
     * */
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
    //получение картинки для предпросмотра дока
    public void sendPrefile(Callback callback, JsonObject jsonObject) {
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(JSON, gson.toJson(jsonObject));
        makePostRequest(getHttpBuilder().addPathSegment("prefile").build(),
                body, callback);
    }

}
