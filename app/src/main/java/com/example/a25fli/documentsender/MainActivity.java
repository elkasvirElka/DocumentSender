package com.example.a25fli.documentsender;

import android.app.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
       // BottomNavigationView navigation_items = (BottomNavigationView) findViewById(R.id.navigation_items);
        //navigation_items.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = HistoryActivity.newInstance();
                                break;
                            case R.id.navigation_documents:
                                selectedFragment = DocumentActivity.newInstance();
                                break;
//                case R.id.action_item3:
//                    selectedFragment = ItemThreeFragment.newInstance();
//                    break;
                        }

                        if (selectedFragment == null) {
                            return false;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HistoryActivity.newInstance());
        transaction.commit();

        Button registration = findViewById(R.id.registration);

        SharedPreferences dateaboutuser = getSharedPreferences("Save Data", MODE_PRIVATE);
        String saveddate = dateaboutuser.getString("name", "");
        if(saveddate.length() > 0){
            registration.setVisibility(View.GONE);
        }else{
            registration.setVisibility(View.VISIBLE);
        }
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

}
