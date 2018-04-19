package com.example.a25fli.documentsender;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SettingsActivity extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static SettingsActivity newInstance() {
        SettingsActivity fragment = new SettingsActivity();
        return fragment;
    }
    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        String[] settingsitems = {"Смена фотографии", "О программе"};
        ListView settingslist = view.findViewById(R.id.settingsList);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                settingsitems
        );
        settingslist.setAdapter(listViewAdapter);

        settingslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                        case 0:
                            intent = new Intent(getActivity(), SettingsEditPhotoActivity.class);
                            break;
                    case 1:
                        intent = new Intent(getActivity(), SettingsAboutAppActivity.class);
                        break;
                    }
                startActivity(intent);
            }
        });
        return view;
    }


}

/*
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[] {"Цвет","Размер шрифта","О программе"});
        //Заполнение списка настроек
        ListView SettingsList = findViewById(R.id.SettingsList);
        SettingsList.setAdapter(adapter);
        SettingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                if(strText.equalsIgnoreCase("Цвет")) {
                    // Запускаем активность, связанную с определенной настройкой
                    //startActivity(new Intent(this, Some_Activity.class));
                    Toast.makeText(SettingsActivity.this, "Тут должны быть настройки", Toast.LENGTH_SHORT).show();
                }
                else if(strText.equalsIgnoreCase("Размер шрифта")) {
                    Toast.makeText(SettingsActivity.this, "Тут тоже должны быть настройки", Toast.LENGTH_SHORT).show();
                }
                else if(strText.equalsIgnoreCase("О программе")) {
                    Toast.makeText(SettingsActivity.this, "Программа DocumentSender", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
*/