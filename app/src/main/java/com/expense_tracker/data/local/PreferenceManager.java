package com.expense_tracker.data.local;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

  private static final String PREF_NAME = "ExpenseTrackerPrefs";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_LOGIN_TIME = "loginTime";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SharedPreferences getPreference(Context context){
        return context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }

   public static void saveLoginData(Context context, String userId,String username){
        SharedPreferences prefs = getPreference(context);
        SharedPreferences.Editor  edit = prefs.edit();
        edit.putString(KEY_USER_ID,userId);
        edit.putString(KEY_USERNAME,username);
        edit.putBoolean(KEY_IS_LOGGED_IN,true);
        edit.putLong(KEY_LOGIN_TIME,System.currentTimeMillis());
        edit.apply();
   }

   public static String getUsername(Context context){
        SharedPreferences prefs = getPreference(context);
       return  prefs.getString(KEY_USERNAME,"");
   }

   public static String getKeyUserId(Context context){
        SharedPreferences prefs = getPreference(context);
        return  prefs.getString(KEY_USER_ID,"");
   }

   public static  boolean isLoggedIn(Context context){
        SharedPreferences refs = getPreference(context);
        return  refs.getBoolean(KEY_IS_LOGGED_IN,false);
   }

   public static long getLogInTime(Context context){
        SharedPreferences prefs = getPreference(context);
        return  prefs.getLong(KEY_LOGIN_TIME,0);
   }

   public static boolean isLoginExpired(Context context){
        long currentTime = System.currentTimeMillis();
        long expiryPeriod = 3 * 24 * 60 * 60 * 1000L;
        return (currentTime-getLogInTime(context) > expiryPeriod);
   }

   public static void clear(Context context){
        SharedPreferences refs =  getPreference(context);
        SharedPreferences.Editor edit = refs.edit();
        edit.clear();
        edit.apply();
   }





}
