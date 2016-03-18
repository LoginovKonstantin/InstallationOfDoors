package com.home.installationofdoors;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
                View view = LayoutInflater.from(ShowDBActivity.this).inflate(R.layout.user_input, null);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ShowDBActivity.this);
                alertBuilder.setView(view);

                /*определение всех компонентов управления в диалоге*/
                final EditText nameProfile = (EditText)findViewById(R.id.nameProfile);
                final EditText widthProfile = (EditText)findViewById(R.id.widthProfile);
                final EditText xvaluex = (EditText)findViewById(R.id.xvaluex);
                final EditText valueRollers = (EditText)findViewById(R.id.valueRollers);
                final EditText valueTolerance = (EditText)findViewById(R.id.valueTolerance);
                final EditText jumperMagnitude = (EditText)findViewById(R.id.jumperMagnitude);
                alertBuilder.setCancelable(true).setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(nameProfile.getText().toString().equals("")||widthProfile.getText().toString().equals("")||
                                xvaluex.getText().toString().equals("")||valueRollers.getText().toString().equals("")||
                                valueTolerance.getText().toString().equals("")||jumperMagnitude.getText().toString().equals("")){
                            Toast.makeText(ShowDBActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT);
                        }else{

                        }
                    }
                });
                Dialog dialog = alertBuilder.create();
                dialog.show();
                break;

            case R.id.buttonDelProfile:
                Log.d("myLog", "Удаление нового профиля");
                break;
        }
    }
}
