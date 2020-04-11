package com.ekattorit.kickytailor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class UserSession {

    public static final String NAME = "session";
    public static final String LOG_IN_KEY = "isLogin";

    private Context context;

    public UserSession(Context context){
        this.context = context;
    }

    public void setFirstTime(boolean b){
        SharedPreferences sp = context.getSharedPreferences(NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(LOG_IN_KEY,b);
        editor.commit();
    }

    public boolean isFirstTime(){
        SharedPreferences sp = context.getSharedPreferences(NAME, MODE_PRIVATE);
        boolean session = sp.getBoolean(LOG_IN_KEY, true);
        return session;
    }

}
