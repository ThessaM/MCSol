package com.example.a2501974391_uts_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.a2501974391_uts_mcs.Database.GlobalData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout mainFrame;
//    Integer curUserId;

    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        curUserId = GlobalData.getData().currentUserId;

        getSupportActionBar().setLogo(AppCompatResources.getDrawable(this, R.drawable.mcsol_logo));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        bottomNavigationView = findViewById(R.id.home_bottom_navigation);
        mainFrame = findViewById(R.id.home_mainFrame);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.home_mainFrame, new Home_Home()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    fragment = new Home_Home();
                    break;
                case R.id.menu_schedule:
                    fragment = new Home_Schedule();
                    break;
                case R.id.menu_history:
                    fragment = new Home_History();
                    break;
            }
            fragmentManager.beginTransaction().replace(R.id.home_mainFrame, fragment).commit();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem menuLogout = findViewById(R.id.menu_logout);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_logout){
            finish();
        }

//        switch (item.getItemId()){
//            case R.id.menu_logout:
//                finish();
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}