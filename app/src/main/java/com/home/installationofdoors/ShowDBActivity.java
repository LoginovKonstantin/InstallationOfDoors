package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 4 on 17.03.2016.
 */
public class ShowDBActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddProfile, buttonDelProfile;
    private ArrayAdapter<String> dbList;
    private ListView listView;
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

        /*определены кнопки + обработчики события*/
        buttonAddProfile = (Button)findViewById(R.id.buttonAddProfile);
        buttonAddProfile.setOnClickListener(this);
        buttonDelProfile = (Button)findViewById(R.id.buttonDelProfile);
        buttonDelProfile.setOnClickListener(this);

        /*определение ListView для показа базы данных*/
        listView = (ListView)findViewById(R.id.listView);

        dbList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.selectAll(db, database));
        listView.setAdapter(dbList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAddProfile:
                Log.d("myLog", "Добавление нового профиля");
                break;
            case R.id.buttonDelProfile:
                Log.d("myLog", "Удаление нового профиля");
                break;
        }
    }
}
