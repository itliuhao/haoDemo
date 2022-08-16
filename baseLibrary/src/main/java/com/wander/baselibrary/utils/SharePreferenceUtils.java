package com.wander.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wander.baselibrary.BaseTool;


public class SharePreferenceUtils {

    public static final String FILE_NAME = "data";

    /**
     * 保存数据
     * @param key
     * @param object
     */

    public static void put(String key, Object object) {
        SharedPreferences.Editor editor = getEditor();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        editor.commit();

    }

    /**
     * 获取数据
     * @param key
     * @param defaultValue
     * @return
     */

    public static Object get( String key, Object defaultValue) {
        SharedPreferences sp = BaseTool.mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }

        return null;
    }

    /**
     * remove key
     * @param key
     */
    public static void remove(String key){
        SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 判断是否包含key
     * @param key
     * @return
     */
    public static boolean contains(String key){
        SharedPreferences sp = BaseTool.mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return  sp.contains(key);
    }

    /**
     * 清空数据
     */
    public static void clear(){
        SharedPreferences.Editor editor  = getEditor();
        editor.clear();
        editor.commit();

    }



    public static SharedPreferences.Editor getEditor(){
        SharedPreferences sp = BaseTool.mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.edit();
    }


}
