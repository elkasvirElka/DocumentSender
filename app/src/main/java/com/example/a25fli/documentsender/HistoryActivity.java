package com.example.a25fli.documentsender;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class HistoryActivity extends Activity {
    OkHttpClient client;
    class Preference {
        String key;
        String typeString;
        public View view;

        int intValue;
        float floatValue;
        boolean booleanValue;
        String stringValue;

        Preference(String key, int value) {
            this.key = key;
            typeString = "int";
            intValue = value;
        }

        Preference(String key, float value) {
            this.key = key;
            typeString = "float";
            floatValue = value;
        }

        Preference(String key, boolean value) {
            this.key = key;
            typeString = "boolean";
            booleanValue = value;
        }

        Preference(String key, String value) {
            this.key = key;
            typeString = "string";
            stringValue = value;
        }
    }
    private Pump pump;
    List<Preference> preferences = new ArrayList<>();


    HistoryActivity(Pump pump) { this.pump = pump; }

    void update() {
        pump.getPreferences(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if (e != null){
                    int a = 1;
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.code() != 200)
                   return; // ToDo
            }
        });
//        pump.getPreferences(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.code() != 200)
//                    return; // ToDo
//
//                JsonParser parser = new JsonParser();
//                ResponseBody body = response.body();
//
//                if (body == null)
//                    return;
//
//                preferences.clear();
//
//                String response_string = body.string();
//                JsonObject responseJson = parser.parse(response_string).getAsJsonObject();
//
//                for (String key : responseJson.keySet()) {
//                    JsonElement element = responseJson.get(key);
//
//                    try {
//                        preferences.add(new Preference(key,
//                                Integer.parseInt(element.getAsString())));
//                        continue;
//                    }
//                    catch (NumberFormatException ignored) { }
//
//                    try {
//                        preferences.add(new Preference(key,
//                                Float.parseFloat(element.getAsString())));
//                        continue;
//                    }
//                    catch (NumberFormatException ignored) { }
//
//                    if (element.getAsString().equals("false") ||
//                            element.getAsString().equals("true")) {
//                        preferences.add(new Preference(key,
//                                Boolean.parseBoolean(element.getAsString())));
//                        continue;
//                    }
//
//                    preferences.add(new Preference(key, element.getAsString()));
//                }
//            }
//        });
    }

    void update(Runnable runnable) {
        this.update();
        AsyncTask.execute(runnable);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        update();
        //setContentView(R.layout.history);

//        Button button2 = findViewById(R.id.button2);
//        final TextView textView = findViewById(R.id.history);
//
//        client = new OkHttpClient();
//
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                final Request request = new Request.Builder()
////                        .url(new HttpUrl.Builder().scheme("http").host("127.0.0.1").port(8080).build())
////                        .build();
////                client.newCall(request).enqueue(new Callback() {
////                    @Override
////                    public void onFailure(Request request, IOException e) {
////                        textView.setText("упс");
////                    }
////
////                    @Override
////                    public void onResponse(Response response) throws IOException {
////                        if (response.code() == 200) {
////                            textView.setText(response.body().string());
////                        }
////                    }
////                });
//            }
//        });
    }
}
