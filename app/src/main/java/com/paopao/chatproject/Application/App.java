package com.paopao.chatproject.Application;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * 作者：paopao on 2019/4/4 14:58
 * <p>
 * 作用:
 */
public class App extends Application {

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences("ChatProjectSP", MODE_PRIVATE);

        editor = sp.edit();
    }

    public static void set(String key, Object value) {
        if (value instanceof String)
            editor.putString(key, (String) value);
        else if (value instanceof Integer)
            editor.putString(key, (String) value);
        else
            editor.putString(key, (String) value);
        editor.commit();
    }

    public static Object get(String key, Object value) {
        if (value instanceof String)
            return sp.getString(key, (String) value);
        else if (value instanceof Integer)
            return sp.getString(key, (String) value);
        else
            return sp.getString(key, (String) value);
    }


}
