package com.wander.baselibrary.utils;

import android.content.Context;
import android.widget.Toast;

import com.wander.baselibrary.BaseTool;


public class ToastUtil {
    private static Toast toast = null; //Toast的对象！

    public static void showToast(Context context,String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(BaseTool.mContext, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}