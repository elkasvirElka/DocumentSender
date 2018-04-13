package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class RegistrationActivity extends Activity implements View.OnClickListener{
    SharedPreferences dateaboutuser;
    EditText name, surname, patronymic, dateofbirth, institute, numberofgroup,
    numberofID, placeofbirth, seriesandnumber, placeofissue, dateofissue,
    codeofissue, placeofregistration;
    RadioButton man, woman;

    public RegistrationActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);



        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        patronymic = findViewById(R.id.patronymic);
        dateofbirth = findViewById(R.id.dateofbirth);
        institute = findViewById(R.id.institute);
        numberofgroup = findViewById(R.id.numberofgroup);
        numberofID = findViewById(R.id.numberofID);
        placeofbirth = findViewById(R.id.placeofbirth);
        seriesandnumber = findViewById(R.id.seriesandnumber);
        placeofissue = findViewById(R.id.placeofissue);
        dateofissue = findViewById(R.id.dateofissue);
        codeofissue = findViewById(R.id.codeofissue);
        placeofregistration = findViewById(R.id.placeofregistration);
        Button savedatebutton = findViewById(R.id.savedate);
        loaddate();
        savedatebutton.setOnClickListener((View.OnClickListener) this);

        man = findViewById(R.id.sex_man);
        woman = findViewById(R.id.sex_woman);
        man.setOnClickListener(sex);
        woman.setOnClickListener(sex);
    }
    View.OnClickListener sex = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.sex_man: woman.setChecked(false);
                break;
                case R.id.sex_woman: man.setChecked(false);
                break;
                default: break;
            }
        }
    };

    private void savedate(){
        dateaboutuser = getSharedPreferences("Save Data", MODE_PRIVATE);
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
        if(man.isChecked()) {
            edit.putBoolean("sex", true);

        }
        if(woman.isChecked()){
            edit.putBoolean("sex", false);
        }
            edit.commit();
    }
    private void loaddate(){
        dateaboutuser = getSharedPreferences("Save Data", MODE_PRIVATE);

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

    @Override
    public void onClick(View view) {
        savedate();
    }
}
