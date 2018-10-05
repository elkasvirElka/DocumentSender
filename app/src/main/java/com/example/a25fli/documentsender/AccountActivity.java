package com.example.a25fli.documentsender;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class AccountActivity extends Fragment {
    SharedPreferences dateaboutuser;
    EditText name, surname, patronymic, dateofbirth, institute, numberofgroup,
            numberofID, placeofbirth, seriesandnumber, placeofissue, dateofissue,
            codeofissue, placeofregistration, dateofstart, dateoffinish, formofeducation;

    public class TextWatcherP implements TextWatcher {
        public EditText editText;

        public TextWatcherP(EditText et) {
            super();
            editText = et;
        }

        public void afterTextChanged(Editable s) {
            if (editText.getText().length() == 2 || editText.getText().length() == 5) {
                editText.setText(editText.getText().toString() + '.');
                editText.setSelection(editText.getText().length());
            }
            if (editText.getText().length() == 11) {
                editText.setText(editText.getText().subSequence(0, editText.getText().length() - 1));
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

    }

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
        TextWatcherP inputTextWatcher = new TextWatcherP(dateofbirth);
        dateofbirth.addTextChangedListener(inputTextWatcher);
        institute = getView().findViewById(R.id.institute);
        numberofgroup = getView().findViewById(R.id.numberofgroup);
        numberofID = getView().findViewById(R.id.numberofID);
        formofeducation = getView().findViewById(R.id.formofeducation);
        dateofstart = getView().findViewById(R.id.dateofstart);
        TextWatcherP input1 = new TextWatcherP(dateofstart);
        dateofstart.addTextChangedListener(input1);
        dateoffinish = getView().findViewById(R.id.dateoffinish);
        TextWatcherP input2 = new TextWatcherP(dateoffinish);
        dateoffinish.addTextChangedListener(input2);
        placeofbirth = getView().findViewById(R.id.placeofbirth);
        seriesandnumber = getView().findViewById(R.id.seriesandnumber);
        placeofissue = getView().findViewById(R.id.placeofissue);
        dateofissue = getView().findViewById(R.id.dateofissue);
        TextWatcherP input3 = new TextWatcherP(dateofissue);
        dateofissue.addTextChangedListener(input3);
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

    private void savedate() {
        dateaboutuser = getActivity().getSharedPreferences("Save Data", MODE_PRIVATE);
        SharedPreferences.Editor edit = dateaboutuser.edit();
        edit.putString("name", name.getText().toString());
        edit.putString("surname", surname.getText().toString());
        edit.putString("patronymic", patronymic.getText().toString());
        edit.putString("dateofbirth", dateofbirth.getText().toString());
        edit.putString("institute", institute.getText().toString());
        edit.putString("numberofgroup", numberofgroup.getText().toString());
        edit.putString("numberofID", numberofID.getText().toString());
        edit.putString("formofeducation", formofeducation.getText().toString());
        edit.putString("dateofstart", dateofstart.getText().toString());
        edit.putString("dateoffinish", dateoffinish.getText().toString());
        edit.putString("placeofbirth", placeofbirth.getText().toString());
        edit.putString("seriesandnumber", seriesandnumber.getText().toString());
        edit.putString("placeofissue", placeofissue.getText().toString());
        edit.putString("dateofissue", dateofissue.getText().toString());
        edit.putString("codeofissue", codeofissue.getText().toString());
        edit.putString("placeofregistration", placeofregistration.getText().toString());


        edit.commit();
        Toast msg = Toast.makeText(getActivity(), "Успешно", Toast.LENGTH_LONG);
        msg.show();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, AccountActivity.newInstance());
        transaction.commit();
    }

    private void loaddate() {
        dateaboutuser = getActivity().getSharedPreferences("Save Data", MODE_PRIVATE);

        name.setText(dateaboutuser.getString("name", ""));
        surname.setText(dateaboutuser.getString("surname", ""));
        patronymic.setText(dateaboutuser.getString("patronymic", ""));
        dateofbirth.setText(dateaboutuser.getString("dateofbirth", ""));
        institute.setText(dateaboutuser.getString("institute", ""));
        numberofgroup.setText(dateaboutuser.getString("numberofgroup", ""));
        numberofID.setText(dateaboutuser.getString("numberofID", ""));
        formofeducation.setText(dateaboutuser.getString("formofeducation", ""));
        dateofstart.setText(dateaboutuser.getString("dateofstart", ""));
        dateoffinish.setText(dateaboutuser.getString("dateoffinish", ""));
        placeofbirth.setText(dateaboutuser.getString("placeofbirth", ""));
        seriesandnumber.setText(dateaboutuser.getString("seriesandnumber", ""));
        placeofissue.setText(dateaboutuser.getString("placeofissue", ""));
        dateofissue.setText(dateaboutuser.getString("dateofissue", ""));
        codeofissue.setText(dateaboutuser.getString("codeofissue", ""));
        placeofregistration.setText(dateaboutuser.getString("placeofregistration", ""));


    }
}
