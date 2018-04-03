package com.example.a25fli.documentsender;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HistoryActivity extends Fragment {
    private Server server = new Server("192.168.8.103", 8080);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public static HistoryActivity newInstance() {
        HistoryActivity fragment = new HistoryActivity();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.history);
//
//        final TextView status = findViewById(R.id.status);
//        final GridLayout gridLayout = findViewById(R.id.grid);
//
//        server.getDocList(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        status.setText("Ошибка подключения!");
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response)
//                    throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        status.setText("Успешное подключение!");
//                    }
//                });
//
//                if (response.code() != 200)
//                    return;
//
//                JsonParser parser = new JsonParser();
//                ResponseBody body = response.body();
//
//                if (body == null)
//                    return;
//
//                String response_string = body.string();
//                JsonArray responseJson = parser.parse(response_string).getAsJsonArray();
//
//                for (JsonElement element : responseJson) {
//                    JsonObject object = element.getAsJsonObject();
//
//                    TextView textView = new TextView(getApplicationContext());
//                    textView.setText(object.get("name").getAsString());
//                    textView.setTextColor(Color.BLACK);
//
//                    gridLayout.addView(textView);
//                }
//            }
//        });
//    }
}
