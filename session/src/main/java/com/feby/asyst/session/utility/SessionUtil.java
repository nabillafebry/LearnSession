package com.feby.asyst.session.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtil {

    Context mContext;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionUtil(Context mContext) {
        this.mContext = mContext;
        preferences = mContext.getSharedPreferences("training", 0);
        editor = preferences.edit();
    }

    public void saveUsername(String username){
        editor.putString(Constant.KEY_USERNAME,username);
        editor.commit();
    }
    public String loadUsername(){
        String username = preferences.getString(Constant.KEY_USERNAME, "");

        return username;
    }

    public void savePassword(String password){
        editor.putString(Constant.KEY_PASSWORD,password);
        editor.commit();
    }
    public String loadPassword(){
        String password = preferences.getString(Constant.KEY_PASSWORD, "");

        return password;
    }
}
