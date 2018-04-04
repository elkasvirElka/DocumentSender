package com.example.a25fli.documentsender;


import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;


public class DocumentActivity extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static DocumentActivity newInstance() {
        DocumentActivity fragment = new DocumentActivity();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.document, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button edit_document = getView().findViewById(R.id.edit_document);

        edit_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DocumentEditActivity.class);
                startActivity(intent);
            }
        });
        final TextView status = getView().findViewById(R.id.status);
        final GridLayout gridLayout = getView().findViewById(R.id.grid);
    

        MainActivity.server.getDocList(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Ошибка подключения!");
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response)
                    throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        status.setText("Успешное подключение!");
                if (response.code() != 200)
                    return;

                JsonParser parser = new JsonParser();
                ResponseBody body = response.body();

                if (body == null)
                    return;

                        String response_string = null;
                        try {
                            response_string = body.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JsonArray responseJson = parser.parse(response_string).getAsJsonArray();

                for (JsonElement element : responseJson) {
                    JsonObject object = element.getAsJsonObject();

                    TextView textView = new TextView(getContext());
                    textView.setText(object.get("name").getAsString());
                    textView.setTextColor(Color.WHITE);

                    gridLayout.addView(textView);
                }

                    }

                });
            }

        });
    }
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