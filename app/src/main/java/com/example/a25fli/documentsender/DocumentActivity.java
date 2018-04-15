package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
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

        final GridLayout gridLayout = getView().findViewById(R.id.grid);


        MainActivity.server.getDocList(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Activity activity = getActivity();
                if (activity == null)
                    return; // ToDo
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast error = Toast.makeText(getActivity(), "Ошибка подключения", Toast.LENGTH_LONG);
                        error.show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response)
                    throws IOException {
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

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GridLayout.LayoutParams param = new GridLayout.LayoutParams();

                        for (JsonElement element : responseJson) {
                            JsonObject object = element.getAsJsonObject();

                            final TextView textView = new TextView(getContext());
                            textView.setText(object.get("name").getAsString());
                            textView.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);

                            int a = object.get("id") == null ? object.get("id").getAsShort() : 0;
                            textView.setId(a);


                            gridLayout.addView(textView);
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getActivity(), DocumentEditActivity.class);
                                    intent.putExtra("userId:", view.getId());
                                    startActivity(intent);
                                }
                            });
                        }

                    }

                });
            }

        });
    }
}