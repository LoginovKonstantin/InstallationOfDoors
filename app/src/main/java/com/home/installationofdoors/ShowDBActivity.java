package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 4 on 17.03.2016.
 */
public class ShowDBActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdb);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db = new DatabaseHelper(this);

        /*Вывод в лог бд*/
        ArrayList<String> a = db.selectAll(db, database);
        Log.d("myLog", "Просмотр бд");
        for(String s : a){
            Log.d("myLog", s);
        }


    }
}
