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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 4 on 17.03.2016.
 */
public class ShowDBActivity extends AppCompatActivity{

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

        /*определение ListView для показа базы данных*/
        listView = (ListView)findViewById(R.id.listView);
        registerForContextMenu(listView);
        dbList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, db.selectAll(db, database));
        listView.setAdapter(dbList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "Добавить профиль");
        menu.add(0, 1, 0, "Удалить профиль");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        /*Добавление*/
        if(item.getItemId() == 0){
            createInputDialog();

        }
        /*Удаление*/
        if (item.getItemId() == 1) {
            // получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            String elem = dbList.getItem(Integer.parseInt(String.valueOf(acmi.id)));
            String temp = "";

            for(int i = 3; i < elem.length(); i++){
                if(elem.charAt(i) == '\n'){
                    break;
                }else{
                    temp += elem.charAt(i);
                }

            }

            Log.d("myLog", temp);
            // удаляем Map из коллекции, используя позицию пункта в списке
//            data.remove(acmi.position);
//            // уведомляем, что данные изменились
//            sAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void createInputDialog(){
        Dialog dialog = new Dialog(ShowDBActivity.this);
        dialog.setTitle("Новый профиль");
        dialog.setContentView(R.layout.activity_input_new_profile);
        dialog.show();

        /*инициализация всех полей и кнокпи из диалога*/
        final EditText name_profile = (EditText)dialog.findViewById(R.id.editText);
        final EditText width_profile = (EditText)dialog.findViewById(R.id.editText2);
        final EditText xvaluex = (EditText)dialog.findViewById(R.id.editText3);
        final EditText value_rollers = (EditText)dialog.findViewById(R.id.editText4);
        final EditText value_tolerance = (EditText)dialog.findViewById(R.id.editText5);
        final EditText jumper_magnitude = (EditText)dialog.findViewById(R.id.editText6);
        final Button buttonOk = (Button)dialog.findViewById(R.id.buttonAddProfile);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_profile.getText().toString().equalsIgnoreCase("")||width_profile.getText().toString().equalsIgnoreCase("")||
                        xvaluex.getText().toString().equalsIgnoreCase("")||value_rollers.getText().toString().equalsIgnoreCase("")||
                        value_tolerance.getText().toString().equalsIgnoreCase("")||jumper_magnitude.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Добавляем", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
