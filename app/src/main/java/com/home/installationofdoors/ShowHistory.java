package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by 4 on 17.03.2016.
 */
public class ShowHistory extends AppCompatActivity {

    private ListView listViewHistory;
    private ArrayAdapter<String> dbListHistory;
    private DatabaseHelper db;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showhistory);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new DatabaseHelper(this);

        ArrayList<String> arrayListHistory = db.selectAllHistory(db, database);

        listViewHistory = (ListView)findViewById(R.id.listView2);
        registerForContextMenu(listViewHistory);
        dbListHistory = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListHistory);
        listViewHistory.setAdapter(dbListHistory);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 2, 0, "Сформировать документ");
        menu.add(0, 1, 0, "Удалить текущий");
        menu.add(0, 3, 0, "Удалить все");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        database = db.getWritableDatabase();
        switch (item.getItemId()){
            case 1:
                long n = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).id;//Номер в списке
                String s = dbListHistory.getItem((int) n);
                String date = "'";
                for(int i = 6; i < 22; i++){
                    date += s.charAt(i);
                }
                date += "'";
                database.delete(DatabaseHelper.TABLE_HISTORY, "date = " + date, null);
                dbListHistory.remove(s);
                dbListHistory.notifyDataSetChanged();
                break;
            case 2:
                Toast.makeText(this, writeSD(), Toast.LENGTH_SHORT).show();
                Log.d("myLog", "Сформировать документ");
                break;
            case 3:
                Log.d("myLog", "Удаление всех");
                database.delete(DatabaseHelper.TABLE_HISTORY, null, null);
                dbListHistory.clear();
                dbListHistory.notifyDataSetChanged();
                break;
            default:
                break;
        }
        database.close();
        return super.onContextItemSelected(item);
    }

    //метод для записи в файл истории вычислений
    private String writeSD() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { return "SD карта не доступна..."; }
        PrintWriter pw;
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/Installation of doors");
        sdPath.mkdirs();
        File sdFile = new File(sdPath, "CurrentHistory.txt");
        try {
            pw = new PrintWriter(sdFile);
            ArrayList<String> listHistoryTemp = db.selectAllHistory(db, database);
            for(int i = 0; i < listHistoryTemp.size(); i++){
                for(int j = 0; j < listHistoryTemp.get(i).length() - 1; j++){
                    if(listHistoryTemp.get(i).charAt(j) >= 'А' && listHistoryTemp.get(i).charAt(j) <= 'Я' &&
                            listHistoryTemp.get(i).charAt(j + 1) >= 'а' && listHistoryTemp.get(i).charAt(j + 1) <= 'я'){
                        pw.write("\r\n");
                    }
                    pw.write(listHistoryTemp.get(i).charAt(j));
                }
                pw.write(listHistoryTemp.get(i).charAt(listHistoryTemp.get(i).length() - 1));
                pw.write("\r\n\r\n");
            }
            pw.close();
            return "Файл сохранен...";
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка...";
        }
    }

}

