package com.home.installationofdoors;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextHeightAperture, editTextWidthAperture,
                     editTextCountDoors, editTextCountOverlap;
    private Button buttonClear, buttonCalculate;
    private TextView textViewOverlapIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        /*определено текстовое поле*/
        textViewOverlapIs = (TextView)findViewById(R.id.textViewOverlapIs);

    }

    /*обработчик на кнопки*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonClean:
                cleanEditTexts();
                break;
            case R.id.buttonCalculate:
                cleanEditTexts();
                /*Происходит вычисление ))*/
                break;
        }
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

}
