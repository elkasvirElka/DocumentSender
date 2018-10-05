package com.example.a25fli.documentsender;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
//страничка настройки-> о программе
public class SettingsAboutAppActivity extends Activity {
    SharedPreferences be_photo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about_program);
    }
}