package com.example.aungsithumoe.calculatorforwood;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.myatminsoe.mdetect.MDetect;
import me.myatminsoe.mdetect.Rabbit;

public class MainActivity extends AppCompatActivity {
    dbhelper dbh;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fr = new Fragment1();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fr = new Fragment1();
                    break;
                case R.id.navigation_dashboard:
                    fr = new Fragment2();
                    break;
            }

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentreplace, fr);
            fragmentTransaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(MDetect.INSTANCE.getText(Rabbit.zg2uni("သစ္တန္တြက္စက္")));
        dbh=new dbhelper(getApplicationContext());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_home).setTitle( MDetect.INSTANCE.getText(Rabbit.zg2uni(getResources().getString(R.string.title_home))));
        navigation.getMenu().findItem(R.id.navigation_dashboard).setTitle( MDetect.INSTANCE.getText(Rabbit.zg2uni(getResources().getString(R.string.title_dashboard))));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.clear).setTitle(MDetect.INSTANCE.getText(Rabbit.zg2uni("ဖ်က္မည္")));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// TODO Auto-generated method stub
        if(item.getItemId()==R.id.clear) {
            dbh.clear();
            finish();
            Intent i=new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}