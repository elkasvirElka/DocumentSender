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
    codeofissue, placeofregistration, dateofstart, dateoffinish;
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
        dateofstart = findViewById(R.id.dateofstart);
        dateoffinish = findViewById(R.id.dateoffinish);
        placeofbirth = findViewById(R.id.placeofbirth);
        seriesandnumber = findViewById(R.id.seriesandnumber);
        placeofissue = findViewById(R.id.placeofissue);
        dateofissue = findViewById(R.id.dateofissue);
        codeofissue = findViewById(R.id.codeofissue);
        placeofregistration = findViewById(R.id.placeofregistration);
        Button savedatebutton = findViewById(R.id.savedate);

        savedatebutton.setOnClickListener((View.OnClickListener) this);

        man = findViewById(R.id.sex_man);
        woman = findViewById(R.id.sex_woman);
        loaddate();
    }

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
        edit.putString("dateofstart", dateofstart.getText().toString());
        edit.putString("dateoffinish", dateoffinish.getText().toString());
        edit.putString("placeofbirth",placeofbirth.getText().toString());
        edit.putString("seriesandnumber", seriesandnumber.getText().toString());
        edit.putString("placeofissue", placeofissue.getText().toString());
        edit.putString("dateofissue", dateofissue.getText().toString());
        edit.putString("codeofissue",codeofissue.getText().toString());
        edit.putString("placeofregistration", placeofregistration.getText().toString());
        if(man.isChecked()) {
            edit.putString("sex", "М");

        }
        if(woman.isChecked()){
            edit.putString("sex", "Ж");
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
        dateofstart.setText(dateaboutuser.getString("dateofstart",""));
        dateoffinish.setText(dateaboutuser.getString("dateoffinish",""));
        placeofbirth.setText(dateaboutuser.getString("placeofbirth", ""));
        seriesandnumber.setText(dateaboutuser.getString("seriesandnumber", ""));
        placeofissue.setText(dateaboutuser.getString("placeofissue", ""));
        dateofissue.setText(dateaboutuser.getString("dateofissue", ""));
        codeofissue.setText(dateaboutuser.getString("codeofissue",""));
        placeofregistration.setText(dateaboutuser.getString("placeofregistration", ""));
        if(dateaboutuser.getString("sex", "")=="М"){
            man.setChecked(true);
        }
        if(dateaboutuser.getString("sex","")=="Ж"){
            woman.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        savedate();

    }
}
