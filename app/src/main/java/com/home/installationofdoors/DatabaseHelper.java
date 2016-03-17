package com.home.installationofdoors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String KEY_VALUE_XVALUEX = "xvaluex";//величена вычета для получения ширины профиля для вставки
    public static final String KEY_VALUE_ROLLERS = "value_rollers";//величина для установки роликов
    public static final String KEY_VALUE_TOLERANCE = "value_tolerance";//величина допуска
    public static final String KEY_JUMPER_MAGNITUDE = "jumper_magnitude";//величина перемычки

    /*таблица историй вычислений и ее поля*/
    public static final String TABLE_HISTORY = "history";
    public static final String KEY_ID_HISTORY = "_id";
    public static final String KEY_HEIGHT_APERTURE = "height_aperture";
    public static final String KEY_WIDTH_APERTURE = "width_aperture";
    public static final String KEY_COUNT_DOORS = "count_doors";
    public static final String KEY_COUNT_OVERLAP = "count_overlap";
    public static final String KEY_ID_PROFILE_IN_HISTORY = "_id_profile";
    public static final String KEY_OVERLAP = "overlap";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
