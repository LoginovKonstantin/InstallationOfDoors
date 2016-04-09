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
        menu.add(0, 1, 0, "Удалить из истории");
        menu.add(0, 2, 0, "Сформировать документ");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case 1:

                database = db.getWritableDatabase();
                long n = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).id;//Номер в списке
                String s = dbListHistory.getItem((int) n);


                String date = "";
                for(int i = 6; i < s.length(); i++){
                    if(s.charAt(i) == '\n'){
                        break;
                    }else{
                        date += s.charAt(i);
                    }
                }
                Log.d("myLog", date);
                database.delete(DatabaseHelper.TABLE_HISTORY, "date=" + date, null);
                dbListHistory.remove(s);
                dbListHistory.notifyDataSetChanged();
                database.close();

                break;
            case 2:
                Log.d("myLog", "Сформировать документ");
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}

