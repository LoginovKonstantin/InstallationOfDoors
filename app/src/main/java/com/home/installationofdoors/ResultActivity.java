package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * Created by 4 on 30.03.2016.
 */
public class ResultActivity extends AppCompatActivity {

    private TextView textViewHeightDoor, textViewWidthDoor,
                    textViewInstHeight, textViewInstWidth;

    private RadioButton rbWithoutGlass, rbFree, rbHalf,
                        rbOneThird, rbOneQuarter, rbOneQuarter2,
                        rbOneFifth;

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

        /*определение radio button*/
        rbWithoutGlass = (RadioButton)findViewById(R.id.radioButtonWithoutGlass);
        rbFree = (RadioButton)findViewById(R.id.radioButtonFree);
        rbHalf = (RadioButton)findViewById(R.id.radioButtonHalf);
        rbOneThird = (RadioButton)findViewById(R.id.radioButtonOneThird);
        rbOneQuarter = (RadioButton)findViewById(R.id.radioButtonOneQuarter);
        rbOneQuarter2 = (RadioButton)findViewById(R.id.radioButtonOneQuarter2);
        rbOneFifth = (RadioButton)findViewById(R.id.radioButtonOneFifth);


    }

}
