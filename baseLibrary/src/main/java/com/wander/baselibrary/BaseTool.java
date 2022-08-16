package com.wander.baselibrary;

import android.content.Context;

import com.wander.baselibrary.utils.NfcUtils;

public class BaseTool {

    public static Context mContext = null;
    public static boolean Debug = true;

    public static void init(Context context) {
        mContext = context;
    }

    public static void init(Context context,boolean isDebug) {
        mContext = context;
        Debug = isDebug;
    }
}
