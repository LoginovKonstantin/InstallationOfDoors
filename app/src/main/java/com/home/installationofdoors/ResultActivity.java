package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by 4 on 30.03.2016.
 */
public class ResultActivity extends AppCompatActivity {

    private TextView textViewHeightDoor, textViewWidthDoor,
                    textViewInstHeight, textViewInstWidth, textViewDefaultFree;

    private RadioGroup radio_group;

    private LinearLayout linaerForTextView;

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
        textViewDefaultFree = (TextView)findViewById(R.id.textViewDefaultFree);
        textViewDefaultFree.setTextColor(Color.BLACK);

        /*определение области для картинки*/
        imageView = (ImageView)findViewById(R.id.imageView);

        /*определение компонента под динамические TextView*/
        linaerForTextView = (LinearLayout)findViewById(R.id.linearForTextView);
        linaerForTextView.setGravity(Gravity.CENTER);

        /*занесение результатов в лэйблы*/
        textViewHeightDoor.setText("Высота двери: " + MainActivity.calc.getHeight());
        textViewWidthDoor.setText("Ширина двери: " + MainActivity.calc.getWidth());
        textViewInstHeight.setText("Высота вставки: " + MainActivity.calc.getInsertHeight());
        textViewInstWidth.setText("Ширина вставки: " + MainActivity.calc.getInsertWidth());
        textViewDefaultFree.setText(MainActivity.calc.getInsertHeight() - 2 + "");

        /*определение группы с radiobutton и назначение обработчика событий*/
        radio_group = (RadioGroup)findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                double value = 0;
                linaerForTextView.removeAllViews();
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lparams.gravity = Gravity.CENTER;
                lparams.weight = 1;

                switch (checkedId){

                    case R.id.radioButtonFree:

                        value = MainActivity.calc.getInsertHeight() - 2;
                        imageView.setImageResource(R.drawable.image2);
                        TextView[] textViews = new TextView[]{new TextView(getApplicationContext())};
                        setGravityColorTextAndAddOnView(textViews, value, linaerForTextView, lparams);
                        break;

                    case R.id.radioButtonHalf:
                        value = (MainActivity.calc.getInsertHeight() - 2) / 2;
                        imageView.setImageResource(R.drawable.image3);
                        textViews = new TextView[]{
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext())
                        };
                        setGravityColorTextAndAddOnView(textViews, value, linaerForTextView, lparams);
                        break;

                    case R.id.radioButtonOneThird:
                        value = Math.rint(100.0 * ((MainActivity.calc.getInsertHeight() - 4) / 3)) / 100.0;
                        imageView.setImageResource(R.drawable.image4);
                        textViews = new TextView[]{
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext())
                        };
                        setGravityColorTextAndAddOnView(textViews, value, linaerForTextView, lparams);
                        break;

                    case R.id.radioButtonOneQuarter:
                        value = Math.rint(100.0 * ((MainActivity.calc.getInsertHeight() - 4) / 4)) / 100.0;
                        double valueTemp = Math.rint(100.0 * ((MainActivity.calc.getInsertHeight() - 4) / 2)) / 100.0;
                        imageView.setImageResource(R.drawable.image5);

                        TextView textViewOneQuarter1 = new TextView(getApplicationContext());
                        TextView textViewOneQuarter2 = new TextView(getApplicationContext());
                        TextView textViewOneQuarter3 = new TextView(getApplicationContext());

                        textViewOneQuarter1.setGravity(Gravity.CENTER);
                        textViewOneQuarter1.setTextColor(Color.BLACK);
                        textViewOneQuarter1.setText(value + "");
                        lparams.weight = (float) 0.25;
                        linaerForTextView.addView(textViewOneQuarter1, lparams);

                        textViewOneQuarter2.setGravity(Gravity.CENTER);
                        textViewOneQuarter2.setTextColor(Color.BLACK);
                        textViewOneQuarter2.setText(valueTemp + "");
                        lparams.weight = (float) 0.5;
                        linaerForTextView.addView(textViewOneQuarter2, lparams);

                        textViewOneQuarter3.setGravity(Gravity.CENTER);
                        textViewOneQuarter3.setTextColor(Color.BLACK);
                        textViewOneQuarter3.setText(value + "");
                        lparams.weight = (float) 0.25;
                        linaerForTextView.addView(textViewOneQuarter3, lparams);

                        break;

                    case R.id.radioButtonOneQuarter2:
                        value = Math.rint(100.0 * ((MainActivity.calc.getInsertHeight() - 6) / 4)) / 100.0;
                        imageView.setImageResource(R.drawable.image6);
                        textViews = new TextView[]{
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext())
                        };
                        setGravityColorTextAndAddOnView(textViews, value, linaerForTextView, lparams);
                        break;

                    case R.id.radioButtonOneFifth:
                        value = Math.rint(100.0 * ((MainActivity.calc.getInsertHeight() - 8) / 5)) / 100.0;
                        imageView.setImageResource(R.drawable.image7);
                        textViews = new TextView[]{
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext()),
                                new TextView(getApplicationContext())
                        };
                        setGravityColorTextAndAddOnView(textViews, value, linaerForTextView, lparams);
                        break;
                }
            }
        });
    }

    private static void setGravityColorTextAndAddOnView(TextView [] textViews, double value, LinearLayout linearForTextView, LinearLayout.LayoutParams lparams){
        for(int i = 0; i < textViews.length; i++){
            textViews[i].setGravity(Gravity.CENTER);
            textViews[i].setTextColor(Color.BLACK);
            textViews[i].setText(value + "");
            linearForTextView.addView(textViews[i], lparams);
        }
    }

}
