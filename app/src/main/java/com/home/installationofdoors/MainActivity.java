package com.home.installationofdoors;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public EditText editTextHeightAperture, editTextWidthAperture,
                     editTextCountDoors, editTextCountOverlap;
    private Button buttonClear, buttonCalculate;
    private TextView widthProfile;
    private Spinner spinnerTypeProfile;
    private DatabaseHelper db;
    private SQLiteDatabase database;
    public static ArrayAdapter<String> adapter;
    public static ArrayList<String> nameList;
    public static Calculating calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("myLog", "зашли в oncreate в MainActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*определены все текстовые поля*/
        editTextHeightAperture = (EditText)findViewById(R.id.editTextHeightAperture);
        editTextWidthAperture = (EditText)findViewById(R.id.editTextWidthAperture);
        editTextCountDoors = (EditText)findViewById(R.id.editTextCountDoors);
        editTextCountOverlap = (EditText)findViewById(R.id.editTextCountOverlap);

        /*определены все кнопки + назначено Action*/
        buttonCalculate = (Button)findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(this);
        buttonClear = (Button)findViewById(R.id.buttonClean);
        buttonClear.setOnClickListener(this);

        /*определен лаейбл*/
        widthProfile = (TextView)findViewById(R.id.widthProfile);

        /*подключение к бд*/
        db = new DatabaseHelper(this);
        database = db.getWritableDatabase();

        /*определен выпадающий список + создан адаптер для отображения данных из бд + опеределен обработчик*/
        nameList = db.selectNamesProfile(db, database);
        spinnerTypeProfile = (Spinner)findViewById(R.id.spinnerTypeProfile);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeProfile.setAdapter(adapter);
        spinnerTypeProfile.setOnItemSelectedListener(this);

        /*Отключение от бд*/
        db.close();
        database.close();
    }

    /*обработчик на кнопки*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonClean:
                cleanEditTexts();
                break;
            case R.id.buttonCalculate:
                Log.d("myLog", "Вычисление");
                /*Проверки*/
                if(editTextCountDoors.getText().toString().length() < 1
                        || editTextCountOverlap.getText().toString().length() < 1
                        || editTextWidthAperture.getText().toString().length() < 1
                        || editTextHeightAperture.getText().toString().length() < 1){
                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(adapter.isEmpty()){
                    Toast.makeText(this, "Необходимо добавить профиль", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*вычисления*/
                calc = new Calculating();

                calc.setWidth(calc.calcWidthDoor(editTextWidthAperture.getText().toString(), widthProfile.getText().toString(),
                        editTextCountOverlap.getText().toString(), editTextCountDoors.getText().toString()));

                calc.setHeight(calc.calcHeightDoor(editTextHeightAperture.getText().toString(),
                        db.getValueRoller(spinnerTypeProfile.getSelectedItem().toString(), db)));

                calc.setInsertWidth(calc.calcInsertWidth(calc.getWidth(), widthProfile.getText().toString(),
                        db.getValueX(spinnerTypeProfile.getSelectedItem().toString(), db),
                        editTextCountDoors.getText().toString()));

                calc.setInsertHeight(calc.calcInsertHeight(calc.getHeight(), db.getTolerance(spinnerTypeProfile.getSelectedItem().toString(), db)));

                Log.d("myLog1", "Ширина двери в профиле " + calc.getWidth());
                Log.d("myLog1", "Высота двери в профиле " + calc.getHeight());
                Log.d("myLog1", "Ширина вставки " + calc.getInsertWidth());
                Log.d("myLog1", "Высота вставки " + calc.getInsertHeight());

                writeHistoryInBD();

                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
                cleanEditTexts();

                break;
        }
    }

    /*запись истории в базу*/
    private void writeHistoryInBD() {
        /*определение даты и времени в указаном формате*/
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        database = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.KEY_OPENING_HEIGHT, editTextHeightAperture.getText().toString());
        cv.put(DatabaseHelper.KEY_OPENING_WIDTH, editTextWidthAperture.getText().toString());
        cv.put(DatabaseHelper.KEY_HEIGHT_APERTURE, calc.getInsertHeight());
        cv.put(DatabaseHelper.KEY_WIDTH_APERTURE, calc.getInsertWidth());
        cv.put(DatabaseHelper.KEY_COUNT_DOORS, editTextCountDoors.getText().toString());
        cv.put(DatabaseHelper.KEY_COUNT_OVERLAP, editTextCountOverlap.getText().toString());
        cv.put(DatabaseHelper.KEY_NAME_PROFILE_IN_HISTORY, spinnerTypeProfile.getSelectedItem().toString());
        cv.put(DatabaseHelper.KEY_DATE, format1.format(d));

        database.insert(DatabaseHelper.TABLE_HISTORY, null, cv);
        cv.clear();
        database.close();
    }

    /*создание меню из menu/menu.xml*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*обработчик на кнопки в меню*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.showProfile:
                Intent intent = new Intent(this, ShowDBActivity.class);
                startActivity(intent);
                Log.d("myLog", "Просмотреть профили");
                break;
            case R.id.showHistory:
                intent = new Intent(this, ShowHistory.class);
                startActivity(intent);
                Log.d("myLog", "Просмотреть историю");
                break;
            case R.id.aboutProgramm:
                Log.d("myLog", "Просмотр 'о программе'");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*метод очищаяет поля ввода*/
    public void cleanEditTexts(){
        editTextHeightAperture.setText("");
        editTextWidthAperture.setText("");
        editTextCountDoors.setText("");
        editTextCountOverlap.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nameProfile = parent.getItemAtPosition(position).toString();
        double width = db.getWidth(nameProfile, db);
        widthProfile.setText(width + "");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getSelectedItem() == null){
            widthProfile.setText("");
        }
    }
}
