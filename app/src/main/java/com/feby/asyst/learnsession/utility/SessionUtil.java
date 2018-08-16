package com.feby.asyst.learnsession.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionUtil {


    Context mCOntext;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionUtil (Context context){
        this.mCOntext = context;

        preferences = context.getSharedPreferences("training", 0);
        editor = preferences.edit();
    }

    public void saveName(String name){
        editor.putString(Constant.KEY_NAME,name);
        editor.commit();
    }

    public String loadName(){
        String name = preferences.getString(Constant.KEY_NAME, "");

        return name;
    }

    public void saveGender(String gender){
        editor.putString(Constant.KEY_GENDER,gender);
        editor.commit();
    }

    public String loadGender(){
        String gender = preferences.getString(Constant.KEY_GENDER, "");

        return gender;
    }

    public void saveAddress(String address){
        editor.putString(Constant.KEY_ADDRESS,address);
        editor.commit();
    }

    public String loadAddress(){
        String address = preferences.getString(Constant.KEY_ADDRESS, "");

        return address;
    }

    public void saveEducation(String education){
        editor.putString(Constant.KEY_EDUCATION,education);
        editor.commit();
    }

    public String loadEducation(){
        String education = preferences.getString(Constant.KEY_EDUCATION, "");

        return education;
    }


}
