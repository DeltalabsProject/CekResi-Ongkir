package id.delta.cekresi.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DELTA on 8/20/2016.
 */
public class BasePrefs {
    private static final String PRE_LOAD = "preLoad";
    private static final String PREFS_NAME = "prefs";
    private static BasePrefs instance;
    private final SharedPreferences sharedPreferences;

    public BasePrefs(Context context) {

        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static BasePrefs with(Context context) {

        if (instance == null) {
            instance = new BasePrefs(context);
        }
        return instance;
    }

    public void setPreLoad(boolean totalTime) {

        sharedPreferences
                .edit()
                .putBoolean(PRE_LOAD, totalTime)
                .apply();
    }

    public boolean getPreLoad(){
        return sharedPreferences.getBoolean(PRE_LOAD, false);
    }
}
