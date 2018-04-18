package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
                assert response_string != null;
                final JsonArray responseJson = parser.parse(response_string).getAsJsonArray();
                final LinearLayout myToolbar = findViewById(R.id.my_toolbar);
                final SharedPreferences dateaboutuser = getSharedPreferences("Save Data", MODE_PRIVATE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                        showDocument.setBackground(getResources().getDrawable(R.drawable.roundbtn));
                        showDocument.setTextColor(getResources().getColor(R.color.white));
                        showDocument.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onClickShowDoc();
                            }
                        });
                        myToolbar.addView(showDocument);


                    }

                });
            }

        }, myId);
    }
    private void onClickShowDoc() {
        LinearLayout myToolbar = findViewById(R.id.my_toolbar);
        Map<String, String> items  = getAllChildElements(myToolbar);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("doc_id", 1);
        for (Map.Entry<String, String> entry : items.entrySet())
            jsonObject.addProperty(entry.getKey(), entry.getValue());

        MainActivity.server.sendPrefile(new Callback() {
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

//                        String response_string = null;
//                        try {
//                            response_string = body.string();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        InputStream is = (InputStream)body.byteStream();

                        Helper.getInstance().setInputStreamer(is);
                        Intent intent = new Intent(DocumentEditActivity.this, DocumentShowActivity.class);
                        //intent.putExtras(bundle);
                        startActivity(intent);

                    }

                });
            }

        }, jsonObject);
    }
    public static final Map<String, String> getAllChildElements(ViewGroup layoutCont) {
        if (layoutCont == null) return new HashMap<String, String>();
        Map<String, String> items = new HashMap<String, String>();
        final int mCount = layoutCont.getChildCount();

        // Loop through all of the children.
        for (int i = 1; i < mCount;  i+=2) {
            final View mChild = layoutCont.getChildAt(i);

            if (mChild instanceof ViewGroup) {
                // Recursively attempt another ViewGroup.
                Map<String, String> items2 =  getAllChildElements((ViewGroup) mChild);
                 for(Map.Entry<String, String> e : items2.entrySet())
                {
                    items.put(e.getKey(), e.getValue());
                }
            } else {
                TextView r = (TextView)mChild;
                final TextView text = (TextView)layoutCont.getChildAt(i-1);
                items.put(text.getText().toString(), r.getText().toString());
                // Set the font if it is a TextView.

            }
        }
        return items;
    }
}
class Helper {

    private static Helper mHelper;
    private InputStream mInputStream;

    private Helper(){

    }


    public static Helper getInstance() {
        if (mHelper != null)
            return mHelper;

        return new Helper();
    }


    public void setInputStreamer(InputStream is){
        mInputStream = is;
    }

    public InputStream getInputStreamer(){
        return mInputStream;
    }
}

