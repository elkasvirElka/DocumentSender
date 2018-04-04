package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class DocumentEditActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_edit);
        Button edit_document = findViewById(R.id.show_document);

        edit_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DocumentEditActivity.this, DocumentShowActivity.class);
                startActivity(intent);
            }
        });
        String myId = getIntent().getStringExtra("userId");

        MainActivity.server.getDocEditFields(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // status.setText("Ошибка подключения!");
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response)
                    throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      // status.setText("Успешное подключение!");
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
//
//                            final TextView textView = new TextView(getContext());
//                            textView.setText(object.get("name").getAsString());
//                            textView.setId(object.get("id") == null ? object.get("id").getAsShort() : 1);
//                            textView.setTextColor(Color.WHITE);
//
//                            textView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    Intent intent = new Intent(getActivity(), DocumentEditActivity.class);
//                                    intent.setData(Uri.parse("id:"+ textView.getId()));
//                                    startActivity(intent);
//                                }
//                            });
//                            gridLayout.addView(textView);
                        }

                    }

                });
            }

        }, myId);
    }
}
