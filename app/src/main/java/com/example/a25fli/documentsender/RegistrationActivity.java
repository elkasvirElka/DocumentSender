package com.example.a25fli.documentsender;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegistrationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);


        final EditText name = findViewById(R.id.name);
        final EditText surname = findViewById(R.id.surname);
        final EditText patronymic = findViewById(R.id.patronymic);
    }
}
