package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by 4 on 30.03.2016.
 */
public class ResultActivity extends AppCompatActivity {

    private TextView textViewHeightDoor, textViewWidthDoor,
                    textViewInstHeight, textViewInstWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*определение лэйблов для демонстрации результата*/
        textViewHeightDoor = (TextView)findViewById(R.id.textViewHeightDoor);
        textViewWidthDoor = (TextView)findViewById(R.id.textViewWidthDoor);
        textViewInstHeight = (TextView)findViewById(R.id.textViewInstHeight);
        textViewInstWidth = (TextView)findViewById(R.id.textViewInstWidth);


        /*занесение результатов в лэйблы*/
        textViewHeightDoor.setText("Высота двери: " + MainActivity.calc.getHeight());
        textViewWidthDoor.setText("Ширина двери: " + MainActivity.calc.getWidth());
        textViewInstHeight.setText("Высота вставки: " + MainActivity.calc.getInsertHeight());
        textViewInstWidth.setText("Ширина вставки: " + MainActivity.calc.getInsertWidth());


    }

}
