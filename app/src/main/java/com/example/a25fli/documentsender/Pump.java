package com.example.a25fli.documentsender;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


class Pump {

    private String ip;
    private int port;
    private OkHttpClient client;

    Pump(String ip, int port) {
        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        this.ip = ip;
        this.port = port;

        //syncExecutor.start();
    }

    @NonNull
    private HttpUrl.Builder getHttpBuilder() {
        return new HttpUrl.Builder().scheme("http").host(ip).port(port);
    }

    private void makeGetRequest(HttpUrl url, Callback callback) {
        client.newCall(new Request.Builder().url(url).build()).enqueue(callback);
    }

    private void makePostRequest(HttpUrl url, RequestBody body, Callback callback) {
        client.newCall(new Request.Builder().url(url).post(body).build()).enqueue(callback);
    }

    void resumePump(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("resume_pump").build(),
                callback);
    }

    void suspendPump(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("suspend_pump").build(),
                callback);
    }

    void takeOffSignal(Callback callback) {
        makePostRequest(getHttpBuilder().addPathSegment("press_keys").build(),
                RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        "{\"keys\": [\"DOWN\",\"ESC\", \"ACT\"]}"),
                callback);
    }

    void getGlucose(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("glucose").build(),
                callback);
    }

    void getBasalRates(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("basal_rate").build(),
                callback);
    }

    void getBolus(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("bolus").build(),
                callback);
    }

    void enterBolus(float units, Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("bolus")
                        .addQueryParameter("units", String.valueOf(units)).build(),
                callback);
    }

    void setTempBasal(String type, float rate, int duration, Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("set_temp_basal")
                        .addQueryParameter("temp", type)
                        .addQueryParameter("rate", String.valueOf(rate))
                        .addQueryParameter("duration", String.valueOf(duration)).build(),
                callback);
    }

    void orefSettings(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                .addPathSegment("settings").build(), callback);
    }

    void orefStatus(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                .addPathSegment("status").build(), callback);
    }

    void getPreferences(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("doc_list").build()
               // .addPathSegment("preferences")
                , callback);
    }

    void setPreferences(Callback callback, HistoryActivity preferences) {
        //Gson gson = new Gson();
        JsonObject content = new JsonObject();

        for (HistoryActivity.Preference preference : preferences.preferences) {
            switch (preference.typeString) {
                case "int":
                    content.addProperty(preference.key, preference.intValue);
                    break;
                case "float":
                    content.addProperty(preference.key, preference.floatValue);
                    break;
                case "boolean":
                    content.addProperty(preference.key, preference.booleanValue);
                    break;
                case "string":
                    content.addProperty(preference.key, preference.stringValue);
                    break;
            }
        }

        makePostRequest(getHttpBuilder().addPathSegment("oref")
                        .addPathSegment("preferences").build(),
                RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        content.toString()),
                callback);
    }

    void orefEdisonBattery(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                        .addPathSegment("edison_battery").build(),
                callback);
    }

    void orefBattery(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                        .addPathSegment("pump_battery").build(),
                callback);
    }

    void orefReservoir(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                        .addPathSegment("reservoir").build(),
                callback);
    }

    void orefIOB(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                .addPathSegment("iob").build(), callback);
    }

    void orefLastGlucose(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                .addPathSegment("last_glucose").build(), callback);
    }

    void orefEnable(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("enable").build(),
                callback);
    }

    void orefDisable(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("disable").build(),
                callback);
    }

    void orefTempBasal(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref")
                .addPathSegment("temp_basal").build(), callback);
    }

    void getSerial(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("get_serial").build(),
                callback);
    }

    void orefEnabled(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("enabled").build(),
                callback);
    }

    void orefClock(Callback callback) {
        makeGetRequest(getHttpBuilder().addPathSegment("oref").addPathSegment("clock").build(),
                callback);
    }

    void reportToTelegram(String text) {
        String TOKEN = "582776727:AAHt0uN2uIUBQCC-nVDfDElen2wFCI4E2Cw";
        String ADMIN_CHAT_ID = "50976308";
        HttpUrl url = new HttpUrl.Builder().scheme("https").host("api.telegram.org")
                .addPathSegment("bot" + TOKEN).addPathSegment("sendMessage")
                .addQueryParameter("chat_id", ADMIN_CHAT_ID)
                .addQueryParameter("text", text).build();
        makeGetRequest(url, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response)
                    throws IOException {
                System.out.print("test");
            }
        });
    }
}

