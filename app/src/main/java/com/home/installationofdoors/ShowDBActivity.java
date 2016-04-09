package com.home.installationofdoors;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
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
public class ShowDBActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayAdapter<String> dbList;
    private ListView listView;
    private DatabaseHelper db;
    private SQLiteDatabase database;
    private Button btnAddProfile;
    private ArrayList<Profile> profilesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdb);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db = new DatabaseHelper(this);

        /*Определение кнопки добавления*/
        btnAddProfile = (Button)findViewById(R.id.buttonAddProfile);
        btnAddProfile.setOnClickListener(this);

        /*запись в массив*/
        profilesList = db.selectAll(db, database);
        ArrayList<String> nameProfileList = new ArrayList<String>();
        for(Profile s : profilesList){
            nameProfileList.add(s.toString());
        }

        /*определение ListView для показа базы данных*/
        listView = (ListView)findViewById(R.id.listView);
        registerForContextMenu(listView);
        dbList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameProfileList);
        listView.setAdapter(dbList);
    }

    @Override
    public void onClick(View v) {
        createInputDialog();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Удалить профиль");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        /*Удаление*/
        if (item.getItemId() == 1) {
            // получаем информацию о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            database = db.getWritableDatabase();
            long n = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).id;//Номер в списке
            Log.d("myLog", String.valueOf(n));
            String s = dbList.getItem((int) n);
            String numberInBd = "";
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == ')'){
                    break;
                }else{
                    numberInBd += s.charAt(i);
                }
            }
            Log.d("myLog", numberInBd + "");

            database.delete(DatabaseHelper.TABLE_PROFILE, "_id=" + numberInBd, null);
            dbList.remove(dbList.getItem((int) n));
            // уведомляем, что данные изменились
            dbList.notifyDataSetChanged();
            /*Изменяем данный в выпадающем списке, который в MainActivity*/
            MainActivity.adapter.remove(MainActivity.adapter.getItem((int)n));
            MainActivity.adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Профиль удален", Toast.LENGTH_SHORT).show();
            database.close();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void createInputDialog(){
        final Dialog dialog = new Dialog(ShowDBActivity.this);
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

                    ArrayList<String> names = db.selectNamesProfile(db, database);
                    if(names.contains(name_profile.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Такой профиль существует", Toast.LENGTH_SHORT).show();
                        name_profile.setText("");
                    }else{
                        int n = 0;
                        if(dbList.getCount() > 0){
                            String s = dbList.getItem(dbList.getCount()-1);
                            String numberInBd = "";
                            for(int i = 0; i < s.length(); i++){
                                if(s.charAt(i) == ')'){
                                    break;
                                }else{
                                    numberInBd += s.charAt(i);
                                }
                            }
                            n = Integer.parseInt(numberInBd);
                        }


                        Profile p = new Profile(n+1, name_profile.getText().toString(),
                                width_profile.getText().toString(), xvaluex.getText().toString(),
                                value_rollers.getText().toString(), value_tolerance.getText().toString(),
                                jumper_magnitude.getText().toString());
                        database = db.getWritableDatabase();

                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.KEY_ID_PROFILE, p.getKEY_ID_PROFILE());
                        cv.put(DatabaseHelper.KEY_NAME_PROFILE, p.getKEY_NAME_PROFILE());
                        cv.put(DatabaseHelper.KEY_WIDTH_PROFILE, p.getKEY_WIDTH_PROFILE());
                        cv.put(DatabaseHelper.KEY_VALUE_XVALUEX, p.getKEY_VALUE_XVALUEX());
                        cv.put(DatabaseHelper.KEY_VALUE_ROLLERS, p.getKEY_VALUE_ROLLERS());
                        cv.put(DatabaseHelper.KEY_VALUE_TOLERANCE, p.getKEY_VALUE_TOLERANCE());
                        cv.put(DatabaseHelper.KEY_JUMPER_MAGNITUDE, p.getKEY_JUMPER_MAGNITUDE());
                        database.insert(DatabaseHelper.TABLE_PROFILE, null, cv);

                        dbList.add(p.toString());
                        dbList.notifyDataSetChanged();

                        MainActivity.nameList.add(p.getKEY_NAME_PROFILE());
                        MainActivity.adapter.notifyDataSetChanged();

                        database.close();
                        Toast.makeText(getApplicationContext(), "Профиль добавлен", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

    }

}
