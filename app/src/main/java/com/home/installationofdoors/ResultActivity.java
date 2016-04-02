package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by 4 on 30.03.2016.
 */
public class ResultActivity extends AppCompatActivity {

    private TextView textViewHeightDoor, textViewWidthDoor,
                    textViewInstHeight, textViewInstWidth;

    private RadioGroup radio_group;

    private ImageView imageView;

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

        /*определение области для картинки*/
        imageView = (ImageView)findViewById(R.id.imageView);

        /*занесение результатов в лэйблы*/
        textViewHeightDoor.setText("Высота двери: " + MainActivity.calc.getHeight());
        textViewWidthDoor.setText("Ширина двери: " + MainActivity.calc.getWidth());
        textViewInstHeight.setText("Высота вставки: " + MainActivity.calc.getInsertHeight());
        textViewInstWidth.setText("Ширина вставки: " + MainActivity.calc.getInsertWidth());

        radio_group = (RadioGroup)findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonFree:
                        imageView.setImageResource(R.drawable.image2);
                        break;
                    case R.id.radioButtonHalf:
                        imageView.setImageResource(R.drawable.image3);
                        break;
                    case R.id.radioButtonOneThird:
                        imageView.setImageResource(R.drawable.image4);
                        break;
                    case R.id.radioButtonOneQuarter:
                        imageView.setImageResource(R.drawable.image5);
                        break;
                    case R.id.radioButtonOneQuarter2:
                        imageView.setImageResource(R.drawable.image6);
                        break;
                    case R.id.radioButtonOneFifth:
                        imageView.setImageResource(R.drawable.image7);
                        break;
                }
            }
        });


    }

}
