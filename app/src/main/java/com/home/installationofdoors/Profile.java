package com.home.installationofdoors;

/**
 * Created by 4 on 20.03.2016.
 */
public class Profile {
    private int KEY_ID_PROFILE;
    private String KEY_NAME_PROFILE;//название профиля
    private String KEY_WIDTH_PROFILE;//ширина профиля
    private String KEY_VALUE_XVALUEX;//величена вычета для получения ширины профиля для вставки
    private String KEY_VALUE_ROLLERS;//величина для установки роликов
    private String KEY_VALUE_TOLERANCE;//величина допуска
    private String KEY_JUMPER_MAGNITUDE;//величина перемычки

    public Profile(int KEY_ID_PROFILE, String KEY_NAME_PROFILE, String KEY_WIDTH_PROFILE, String KEY_VALUE_XVALUEX, String KEY_VALUE_ROLLERS, String KEY_VALUE_TOLERANCE, String KEY_JUMPER_MAGNITUDE) {
        this.KEY_ID_PROFILE = KEY_ID_PROFILE;
        this.KEY_NAME_PROFILE = KEY_NAME_PROFILE;
        this.KEY_WIDTH_PROFILE = KEY_WIDTH_PROFILE;
        this.KEY_VALUE_XVALUEX = KEY_VALUE_XVALUEX;
        this.KEY_VALUE_ROLLERS = KEY_VALUE_ROLLERS;
        this.KEY_VALUE_TOLERANCE = KEY_VALUE_TOLERANCE;
        this.KEY_JUMPER_MAGNITUDE = KEY_JUMPER_MAGNITUDE;
    }

    public int getKEY_ID_PROFILE() {
        return KEY_ID_PROFILE;
    }

    public String getKEY_NAME_PROFILE() {
        return KEY_NAME_PROFILE;
    }

    public String getKEY_WIDTH_PROFILE() {
        return KEY_WIDTH_PROFILE;
    }

    public String getKEY_VALUE_XVALUEX() {
        return KEY_VALUE_XVALUEX;
    }

    public String getKEY_VALUE_ROLLERS() {
        return KEY_VALUE_ROLLERS;
    }

    public String getKEY_VALUE_TOLERANCE() {
        return KEY_VALUE_TOLERANCE;
    }

    public String getKEY_JUMPER_MAGNITUDE() {
        return KEY_JUMPER_MAGNITUDE;
    }

    @Override
    public String toString() {
        return
                KEY_ID_PROFILE + ") " +
                KEY_NAME_PROFILE + '\n' +
                "ширина профиля: " + KEY_WIDTH_PROFILE + '\n' +
                "величина вычета для получения ширины профиля для вставки: " + KEY_VALUE_XVALUEX + '\n' +
                "величина для установки роликов: " + KEY_VALUE_ROLLERS + '\n' +
                "величина допуска: " + KEY_VALUE_TOLERANCE + '\n' +
                "величина перемычки: " + KEY_JUMPER_MAGNITUDE;
    }
}
