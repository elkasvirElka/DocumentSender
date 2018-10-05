package com.example.a25fli.documentsender;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
//Первый класс в который зайдет программа
public class MainActivity extends AppCompatActivity {

  //  public static Server server = new Server("193.105.65.66", 1025);
    public static Server server = new Server("193.105.65.66", 1025);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);
        //добавляем items на нижний тулбар + определение цвета
        {
            AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.title_home,
                    R.drawable.ic_home_black_24dp, R.color.colorPrimaryDark);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.title_data,
                    R.drawable.ic_dashboard_black_24dp, R.color.colorPrimaryDark);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.account,
                    R.drawable.icons_admin, R.color.colorPrimaryDark);
            AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.navigation_settings,
                    R.drawable.icons_settings, R.color.colorPrimaryDark);

            bottomNavigation.addItem(item1);
            bottomNavigation.addItem(item2);
            bottomNavigation.addItem(item3);
            bottomNavigation.addItem(item4);

            bottomNavigation.setDefaultBackgroundColor(getResources()
                    .getColor(R.color.white));

            bottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
            bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        }
        //добавляем подписку на события кликания по кнопкам тулбара
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Fragment selectedFragment = null;
                switch (position) {
                    case 0:
                        //открытие первой вкладки с историей запросов
                        selectedFragment = HistoryActivity.newInstance();
                        break;
                    case 1:
                        //открытие щаблонов всех возможных документов
                        selectedFragment = DocumentActivity.newInstance();
                        break;
                    case 2:
                        //открытие аккаунта студента(поля ФИО,др и прочее для автоматической подстановки в док.)
                        selectedFragment = AccountActivity.newInstance();
                        break;
                    case 3:
                        //открытие настроек самого приложения
                        selectedFragment = SettingsActivity.newInstance();
                        break;
                }

                if (selectedFragment == null) {
                    return false;
                }
                //обновляем страничку
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HistoryActivity.newInstance());
        transaction.commit();
    }
}
