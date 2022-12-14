package com.wander.baselibrary.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.wander.baselibrary.BaseTool;
import com.wander.baselibrary.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    private static boolean LOG_DEBUG = BaseTool.Debug;
    private static String LOG_TAG = "HLog";
    private static final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void v(String text) {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        if (LOG_DEBUG && !TextUtils.isEmpty(text)) {
            Log.i(LOG_TAG, text + "\n" + s[3].toString());
        }
    }

    public static void d(String text) {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        if (LOG_DEBUG && !TextUtils.isEmpty(text)) {
            Log.e(LOG_TAG, text + "\n" + s[3].toString());
        }
    }

    public static void i(String text) {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        if (LOG_DEBUG && !TextUtils.isEmpty(text)) {
            Log.i(LOG_TAG, text + "\n" + s[3].toString());
        }
    }

    public static void w(String text) {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        if (LOG_DEBUG && !TextUtils.isEmpty(text)) {
            Log.i(LOG_TAG, text + "\n" + s[3].toString());
        }
    }

    public static void e(String text) {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        if (LOG_DEBUG && !TextUtils.isEmpty(text)) {
            Log.e(LOG_TAG, text + "\n" + s[3].toString());
        }
    }

    public static void e(String text, boolean save) {
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        if (LOG_DEBUG && !TextUtils.isEmpty(text)) {
            Log.e(LOG_TAG, text + "\n" + s[3].toString());
            if (save) {
                writeToFile(text);
            }
        }
    }

    /**
     * ???log???????????????
     *
     * @param text
     */
    private static void writeToFile(String text) {
        // ????????????
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            // ????????????
            String fileRoot = Environment.getExternalStorageDirectory().getPath() + "/Meet/";
            String fileName = "Meet.log";
            // ??????????????? + ??????
            String log = mSimpleDateFormat.format(new Date()) + " " + text + "\n";
            // ???????????????????????????
            File fileGroup = new File(fileRoot);
            // ???????????????
            if (!fileGroup.exists()) {
                // ???????????????????????????
                fileGroup.mkdirs();
            }
            fileOutputStream = new FileOutputStream(fileRoot + fileName, true);
            // ?????????????????????GBK???????????????????????????
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(fileOutputStream, Charset.forName("gbk"))
            );
            bufferedWriter.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //??????
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

