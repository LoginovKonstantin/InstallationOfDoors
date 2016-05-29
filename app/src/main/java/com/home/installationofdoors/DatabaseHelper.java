package com.home.installationofdoors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 4 on 13.03.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /*версия базы данных*/
    public static final int DATABASE_VERSION = 1;

    /*наименование базы данных*/
    public static final String DATABASE_NAME = "DBprofile.db";

    /*таблица профилей и ее поля*/
    public static final String TABLE_PROFILE = "profile";
    public static final String KEY_ID_PROFILE = "_id";
    public static final String KEY_NAME_PROFILE = "name_profile";//название профиля
    public static final String KEY_WIDTH_PROFILE = "width_profile";//ширина профиля
    public static final String KEY_VALUE_XVALUEX = "xvaluex";//величина вычета для получения ширины профиля для вставки
    public static final String KEY_VALUE_ROLLERS = "value_rollers";//величина для установки роликов
    public static final String KEY_VALUE_TOLERANCE = "value_tolerance";//величина допуска
    public static final String KEY_JUMPER_MAGNITUDE = "jumper_magnitude";//величина перемычки

    /*таблица историй вычислений и ее поля*/
    public static final String TABLE_HISTORY = "history";
    public static final String KEY_ID_HISTORY = "_id";
    public static final String KEY_OPENING_HEIGHT = "opening_height";
    public static final String KEY_OPENING_WIDTH = "opening_width";
    public static final String KEY_HEIGHT_APERTURE = "height_aperture";
    public static final String KEY_WIDTH_APERTURE = "width_aperture";
    public static final String KEY_COUNT_DOORS = "count_doors";
    public static final String KEY_COUNT_OVERLAP = "count_overlap";
    public static final String KEY_NAME_PROFILE_IN_HISTORY = "name_profile";
    public static final String KEY_DATE = "date";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("myLog", "Зашли в метод Oncreate");
        db.execSQL("create table " + TABLE_PROFILE + "(" + KEY_ID_PROFILE + " integer primary key,"
                + KEY_NAME_PROFILE + " text," + KEY_WIDTH_PROFILE + " float," + KEY_VALUE_XVALUEX + " float,"
                + KEY_VALUE_ROLLERS + " float," + KEY_VALUE_TOLERANCE + " float," + KEY_JUMPER_MAGNITUDE + " float" + ")");
        db.execSQL("create table " + TABLE_HISTORY + "(" + KEY_ID_HISTORY + " integer primary key,"
                + KEY_HEIGHT_APERTURE + " float," + KEY_OPENING_HEIGHT + " float," + KEY_OPENING_WIDTH + " float," + KEY_WIDTH_APERTURE + " float,"
                + KEY_COUNT_DOORS + " float," + KEY_COUNT_OVERLAP + " float," + KEY_NAME_PROFILE_IN_HISTORY + " string," + KEY_DATE + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_PROFILE);
        db.execSQL("drop table if exists" + TABLE_HISTORY);
        onCreate(db);

    }

    /*метод выборки всех значений из таблицы профили*/
    public ArrayList<Profile> selectAll(DatabaseHelper db, SQLiteDatabase database) {
        database = db.getWritableDatabase();
        Cursor cursor = database.query(db.TABLE_PROFILE, null, null, null, null, null, null);
        ArrayList<Profile> arr = new ArrayList<Profile>();
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(db.KEY_ID_PROFILE);
                int nameProfileIndex = cursor.getColumnIndex(db.KEY_NAME_PROFILE);
                int widthProfileIndex = cursor.getColumnIndex(db.KEY_WIDTH_PROFILE);
                int xvaluexIndex = cursor.getColumnIndex(db.KEY_VALUE_XVALUEX);
                int valueRollersIndex = cursor.getColumnIndex(db.KEY_VALUE_ROLLERS);
                int valueTolleranceIndex = cursor.getColumnIndex(db.KEY_VALUE_TOLERANCE);
                int jumperMagnitudeIndex = cursor.getColumnIndex(db.KEY_JUMPER_MAGNITUDE);

                arr.add(new Profile(cursor.getInt(idIndex), cursor.getString(nameProfileIndex),cursor.getString(widthProfileIndex),
                        cursor.getString(xvaluexIndex), cursor.getString(valueRollersIndex), cursor.getString(valueTolleranceIndex),
                        cursor.getString(jumperMagnitudeIndex)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arr;
    }

    /*метод выборки всех значений из таблицы истории*/
    public ArrayList<String> selectAllHistory(DatabaseHelper db, SQLiteDatabase database) {
        database = db.getWritableDatabase();
        Cursor cursor = database.query(db.TABLE_HISTORY, null, null, null, null, null, null);
        ArrayList<String> arr = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                int openingHeight = cursor.getColumnIndex(db.KEY_OPENING_HEIGHT);
                int openingWidth = cursor.getColumnIndex(db.KEY_OPENING_WIDTH);
                int heightAperture = cursor.getColumnIndex(db.KEY_HEIGHT_APERTURE);
                int widthAperture = cursor.getColumnIndex(db.KEY_WIDTH_APERTURE);
                int countDoors = cursor.getColumnIndex(db.KEY_COUNT_DOORS);
                int countOverlap = cursor.getColumnIndex(db.KEY_COUNT_OVERLAP);
                int nameProfileInHistory = cursor.getColumnIndex(db.KEY_NAME_PROFILE_IN_HISTORY);
                int date = cursor.getColumnIndex(db.KEY_DATE);

                arr.add("Дата: " + cursor.getString(date) + "\n" +
                        "Высота проема: " +  cursor.getString(openingHeight) + "\n" +
                        "Ширина проема: " + cursor.getString(openingWidth) + "\n" +
                        "Высота вставки: " + cursor.getString(heightAperture) + "\n" +
                        "Ширина вставки: " + cursor.getString(widthAperture) + "\n" +
                        "Количество дверей: " + cursor.getString(countDoors) + "\n" +
                        "Количество перехлестов: " + cursor.getString(countOverlap) + "\n" +
                        "Название профиля: " + cursor.getString(nameProfileInHistory));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arr;
    }

    // Метод выборки всех наименований профилей
    public ArrayList<String> selectNamesProfile(DatabaseHelper db, SQLiteDatabase database) {
        database = db.getWritableDatabase();
        Cursor cursor = database.query(db.TABLE_PROFILE, null, null, null, null, null, null);
        ArrayList<String> arr = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                int nameProfileIndex = cursor.getColumnIndex(db.KEY_NAME_PROFILE);
                arr.add(cursor.getString(nameProfileIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arr;
    }

    /*Метод выборки ширины профиля по имени*/
    public double getWidth(String profileName, DatabaseHelper db){
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + db.KEY_WIDTH_PROFILE + " FROM "
                + db.TABLE_PROFILE + " WHERE TRIM(" + db.KEY_NAME_PROFILE + ") = '" + profileName.trim() + "'", null);
        cursor.moveToFirst();
        double width = cursor.getDouble(cursor.getColumnIndex(db.KEY_WIDTH_PROFILE));
        cursor.close();
        database.close();
        return width;
    }

    /*Метод выборки величины для установки роликов по имени*/
    public double getValueRoller(String profileName, DatabaseHelper db){
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + db.KEY_VALUE_ROLLERS + " FROM "
                + db.TABLE_PROFILE + " WHERE TRIM(" + db.KEY_NAME_PROFILE + ") = '" + profileName.trim() + "'", null);
        cursor.moveToFirst();
        double valueRoller = cursor.getDouble(cursor.getColumnIndex(db.KEY_VALUE_ROLLERS));
        cursor.close();
        database.close();
        return valueRoller;
    }

    /*Метод выборки величины вычета для получения ширины профиля для вставки по имени*/
    public double getValueX(String profileName, DatabaseHelper db){
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + db.KEY_VALUE_XVALUEX + " FROM "
                + db.TABLE_PROFILE + " WHERE TRIM(" + db.KEY_NAME_PROFILE + ") = '" + profileName.trim() + "'", null);
        cursor.moveToFirst();
        double valueX = cursor.getDouble(cursor.getColumnIndex(db.KEY_VALUE_XVALUEX));
        cursor.close();
        database.close();
        return valueX;
    }

    /*Метод выборки величина допуска по имени*/
    public double getTolerance(String profileName, DatabaseHelper db){
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + db.KEY_VALUE_TOLERANCE + " FROM "
                + db.TABLE_PROFILE + " WHERE TRIM(" + db.KEY_NAME_PROFILE + ") = '" + profileName.trim() + "'", null);
        cursor.moveToFirst();
        double tolerance = cursor.getDouble(cursor.getColumnIndex(db.KEY_VALUE_TOLERANCE));
        cursor.close();
        database.close();
        return tolerance;
    }


}
