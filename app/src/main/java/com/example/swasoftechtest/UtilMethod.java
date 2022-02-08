package com.example.swasoftechtest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.swasoftechtest.Api.ApplicationConstant;

public enum UtilMethod {

        INSTANCE;
        public void setLoginRefData(Context context, String value) {
                SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.setLoginRefData, context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(ApplicationConstant.INSTANCE.setLoginRefData, value);
                editor.commit();
        }
        public void setCatRefData(Context context, String value) {
                SharedPreferences prefs = context.getSharedPreferences(ApplicationConstant.INSTANCE.setCatRefData, context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(ApplicationConstant.INSTANCE.setCatRefData, value);
                editor.commit();
        }
        public String getLoginList(Activity context) {

                SharedPreferences prefs = context.getSharedPreferences("LoginPref", context.MODE_PRIVATE);
                return prefs.getString("response", null);

        }
}
