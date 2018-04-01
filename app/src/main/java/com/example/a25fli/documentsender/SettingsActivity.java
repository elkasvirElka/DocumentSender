package com.example.a25fli.documentsender;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;


public class SettingsActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation4);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
}
