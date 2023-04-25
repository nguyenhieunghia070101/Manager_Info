package com.example.appqlkh;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "MyAppPreferences"; // Tên của SharedPreferences
    private static final String PASSWORD_KEY = "password"; // Tên của key để lưu trữ mật khẩu
    private static final String KEY_IS_PASSWORD_CREATED = "is_password_created";

    // Phương thức để lưu trữ mật khẩu
    public static void setPassword(Context context, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PASSWORD_KEY, password);
        editor.apply();
    }

    // Phương thức để truy xuất mật khẩu
    public static String getPassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PASSWORD_KEY, "");
    }

    // Phương thức để xóa mật khẩu

    /*public static void deletePassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PASSWORD_KEY);
        editor.apply();
    }*/

    // Đánh dấu rằng mật khẩu đã được tạo
    public static void setPasswordCreated(Context context, boolean isCreated) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_PASSWORD_CREATED, isCreated);
        editor.apply();
    }

    // Kiểm tra xem mật khẩu đã được tạo hay chưa
    public static boolean isPasswordCreated(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_IS_PASSWORD_CREATED, false);
    }
}

