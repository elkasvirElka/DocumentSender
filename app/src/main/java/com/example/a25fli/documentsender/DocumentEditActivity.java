package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class DocumentEditActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_edit);
        String myId = "0";
         Bundle extras = getIntent().getExtras();
        if(extras != null) {
            myId = extras.get("docId").toString();
        }

        MainActivity.server.getDocEditFields(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast error = Toast.makeText(DocumentEditActivity.this, "Ошибка подключения", Toast.LENGTH_LONG);
                        error.show();
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
                        LinearLayout myToolbar = findViewById(R.id.my_toolbar);
                        SharedPreferences dateaboutuser = getSharedPreferences("Save Data", MODE_PRIVATE);

                        for (JsonElement element : responseJson) {
                            JsonObject object = element.getAsJsonObject();
//
                            String value = dateaboutuser.getString(object.get("field_name").getAsString(), "");
                            final TextView textView = new TextView(DocumentEditActivity.this);
                            textView.setText(object.get("name").getAsString());
                            myToolbar.addView(textView);
                            final EditText editText = new EditText(DocumentEditActivity.this);
                            editText.setText(value);
                            myToolbar.addView(editText);
                        }
                        final Button showDocument = new Button(DocumentEditActivity.this);
                        showDocument.setText("Предпросмотр документ");
                        showDocument.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(DocumentEditActivity.this, DocumentShowActivity.class);
                                startActivity(intent);
                            }
                        });
                        myToolbar.addView(showDocument);


                    }

                });
            }

        }, myId);
    }
}
