package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;

public class HistoryActivity extends Fragment {
    static final int GALLERY_REQUEST = 1;

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
        SharedPreferences dateaboutuser = this.getActivity().getSharedPreferences("Save Data", MODE_PRIVATE);

        TextView user_photo = getView().findViewById(R.id.download);
        user_photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });


        Button registration = getView().findViewById(R.id.registration);
        TextView stud_ID = getView().findViewById(R.id.stud_ID);
        stud_ID.setText(dateaboutuser.getString("numberofID",""));
        TextView stud_name = getView().findViewById(R.id.stud_name);
        stud_name.setText(dateaboutuser.getString("name",""));
        TextView stud_surname = getView().findViewById(R.id.stud_surname);
        stud_surname.setText(dateaboutuser.getString("surname",""));
        TextView stud_patronymic = getView().findViewById(R.id.stud_patronymic);
        stud_patronymic.setText(dateaboutuser.getString("patronymic", ""));
        TextView stud_fac = getView().findViewById(R.id.stud_fac);
        stud_fac.setText(dateaboutuser.getString("institute",""));
        TextView stud_form = getView().findViewById(R.id.stud_form);
        stud_form.setText(dateaboutuser.getString("formofeducation",""));
        String saveddate = dateaboutuser.getString("name", "");
        if(saveddate.length() > 0){
            registration.setVisibility(View.GONE);
        }else{
            registration.setVisibility(View.VISIBLE);
        }

        MainActivity.server.getDocRequestList(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Activity activity = getActivity();
                if (activity == null)
                    return;// ToDo
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast error = Toast.makeText(getActivity(),"Ошибка подключения", Toast.LENGTH_LONG);
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
                        final ListView grid = getView().findViewById(R.id.DocStatus);
                        ArrayList<myTableClass> data = new ArrayList<myTableClass>();
                        data.add(new myTableClass("№","Документ", "Статус"));
                        for (JsonElement element : responseJson) {
                            JsonObject object = element.getAsJsonObject();

                            data.add(new myTableClass(object.get("id").getAsString(),object.get("doc_id").getAsString(),
                                    object.get("state_id").getAsString()));
                        }
                        grid.setAdapter(new MyAdapter(getActivity(),data));
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
