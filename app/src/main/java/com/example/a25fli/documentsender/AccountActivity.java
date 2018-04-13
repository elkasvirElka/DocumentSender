package com.example.a25fli.documentsender;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class AccountActivity extends Fragment {
    SharedPreferences dateaboutuser;
    EditText name, surname, patronymic, dateofbirth, institute, numberofgroup,
            numberofID, placeofbirth, seriesandnumber, placeofissue, dateofissue,
            codeofissue, placeofregistration;
    RadioButton man, woman;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static AccountActivity newInstance() {
        AccountActivity fragment = new AccountActivity();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.registration, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        name = getView().findViewById(R.id.name);
        surname = getView().findViewById(R.id.surname);
        patronymic = getView().findViewById(R.id.patronymic);
        dateofbirth = getView().findViewById(R.id.dateofbirth);
        institute = getView().findViewById(R.id.institute);
        numberofgroup = getView().findViewById(R.id.numberofgroup);
        numberofID = getView().findViewById(R.id.numberofID);
        placeofbirth = getView().findViewById(R.id.placeofbirth);
        seriesandnumber = getView().findViewById(R.id.seriesandnumber);
        placeofissue = getView().findViewById(R.id.placeofissue);
        dateofissue = getView().findViewById(R.id.dateofissue);
        codeofissue = getView().findViewById(R.id.codeofissue);
        placeofregistration = getView().findViewById(R.id.placeofregistration);
        Button savedatebutton = getView().findViewById(R.id.savedate);
        savedatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedate();
            }
        });
        loaddate();
    }

    private void savedate(){
        dateaboutuser = getActivity().getSharedPreferences("Save Data", MODE_PRIVATE);
        SharedPreferences.Editor edit = dateaboutuser.edit();
        edit.putString("name", name.getText().toString());
        edit.putString("surname", surname.getText().toString());
        edit.putString("patronymic", patronymic.getText().toString());
        edit.putString("dateofbirth", dateofbirth.getText().toString());
        edit.putString("institute", institute.getText().toString());
        edit.putString("numberofgroup", numberofgroup.getText().toString());
        edit.putString("numberofID",numberofID.getText().toString());
        edit.putString("placeofbirth",placeofbirth.getText().toString());
        edit.putString("seriesandnumber", seriesandnumber.getText().toString());
        edit.putString("placeofissue", placeofissue.getText().toString());
        edit.putString("dateofissue", dateofissue.getText().toString());
        edit.putString("codeofissue",codeofissue.getText().toString());
        edit.putString("placeofregistration", placeofregistration.getText().toString());

        edit.commit();
        Toast msg = Toast.makeText(getActivity(),"Успешно", Toast.LENGTH_LONG);
        msg.show();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, AccountActivity.newInstance());
        transaction.commit();
    }
    private void loaddate(){
        dateaboutuser = getActivity().getSharedPreferences("Save Data", MODE_PRIVATE);

        String saveddate = dateaboutuser.getString("name", "");
        name.setText(saveddate);
        surname.setText(dateaboutuser.getString("surname", ""));
        patronymic.setText(dateaboutuser.getString("patronymic",""));
        dateofbirth.setText(dateaboutuser.getString("dateofbirth", ""));
        institute.setText(dateaboutuser.getString("institute", ""));
        numberofgroup.setText(dateaboutuser.getString("numberofgroup", ""));
        numberofID.setText(dateaboutuser.getString("numberofID", ""));
        placeofbirth.setText(dateaboutuser.getString("placeofbirth", ""));
        seriesandnumber.setText(dateaboutuser.getString("seriesandnumber", ""));
        placeofissue.setText(dateaboutuser.getString("placeofissue", ""));
        dateofissue.setText(dateaboutuser.getString("dateofissue", ""));
        codeofissue.setText(dateaboutuser.getString("codeofissue",""));
        placeofregistration.setText(dateaboutuser.getString("placeofregistration", ""));

    }
    }
