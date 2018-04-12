package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
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

import static android.content.Context.MODE_PRIVATE;

public class HistoryActivity extends Fragment {

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
        return inflater.inflate(R.layout.history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button registration = getView().findViewById(R.id.registration);

        SharedPreferences dateaboutuser = this.getActivity().getSharedPreferences("Save Data", MODE_PRIVATE);
        String saveddate = dateaboutuser.getString("name", "");
        if(saveddate.length() > 0){
            registration.setVisibility(View.GONE);
        }else{
            registration.setVisibility(View.VISIBLE);
        }

        MainActivity.server.getDocRequestList(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //не помню до конца
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response)
                    throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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

                        final GridView grid = getView().findViewById(R.id.DocStatus);
                        for (JsonElement element : responseJson) {
                            JsonObject object = element.getAsJsonObject();
//
//                            final TextView textView = new TextView(getContext());
//                            final TextView textView2 = new TextView(getContext());
//                            textView.setText(object.get("name").getAsString());
//                            textView.setText(object.get("status").getAsString());
////                            textView.setId(object.get("id") == null ? object.get("id").getAsShort() : 1);
//                            textView.setTextColor(Color.BLUE);
//
//                            textView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    Intent intent = new Intent(getActivity(), DocumentEditActivity.class);
//                                    intent.setData(Uri.parse("id:"+ textView.getId()));
//                                    startActivity(intent);
//                                }
//                            });

                         //   SimpleAdapter adapter = new SimpleA dapter(
                           // object
                           //(textView);
                          //  gridLayout.addView(textView2);
                        }
                        //TODO
                        //grid.setAdapter();
                    }

                });
            }

        }, "0");
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
