package com.home.installationofdoors;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
//        menu.add(0, 2, 0, "Сформировать документ");
        menu.add(0, 1, 0, "Удалить текущий");
        menu.add(0, 3, 0, "Удалить все");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        database = db.getWritableDatabase();
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
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

//            case 2:
//                Log.d("myLog", "Сформировать документ");
//                break;
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
}

