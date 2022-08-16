package com.wander.baselibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.wander.baselibrary.BaseTool;

public class NfcUtils {

    private NfcAdapter mNfcAdapter;

    private NfcUtils() {
        init();
    }

    private static volatile NfcUtils instance;

    public static NfcUtils getInstance() {
        if (instance == null) {
            synchronized (NfcUtils.class) {
                if (instance == null) {
                    instance = new NfcUtils();
                }
            }
        }
        return instance;
    }

    public void init() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(BaseTool.mContext);
        if (mNfcAdapter == null) {
            throw new RuntimeException("设备不支持NFC!");
        }
        if (!mNfcAdapter.isEnabled()) {
            throw new RuntimeException("请在系统设置中先启用NFC功能!");
        }
    }

    //支持的标签类型
    private final int flag = NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS
            | NfcAdapter.FLAG_READER_NFC_A
            | NfcAdapter.FLAG_READER_NFC_B
            | NfcAdapter.FLAG_READER_NFC_BARCODE
            | NfcAdapter.FLAG_READER_NFC_F
            | NfcAdapter.FLAG_READER_NFC_V;

    @SuppressLint("NewApi")
    public void startNfc(Activity activity,NfcResult nfcResult) {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        mNfcAdapter.enableReaderMode(activity, new NfcAdapter.ReaderCallback() {
            @Override
            public void onTagDiscovered(Tag tag) {
                byte[] tagId = tag.getId();
                String str = ByteArrayToHexString(tagId);
                String nfcStr = flipHexStr(str);

                if (nfcResult!=null) {
                    nfcResult.onNfcResult(nfcStr);
                }
            }
        }, flag, null);
    }

    public void stopNfc(Activity activity) {
        if (mNfcAdapter != null && mNfcAdapter.isEnabled())
            mNfcAdapter.disableForegroundDispatch(activity);
    }

    //转为16进制字符串
    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F" };
        String out = "";


        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    // 重新排序组合
    private String flipHexStr(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = s.length() - 2; i >= 0; i -= 2) {
            result.append(new StringBuilder(s.substring(i, i + 2)).reverse());
        }
        return result.reverse().toString();
    }

    public interface NfcResult {
        void onNfcResult(String result);
    }
}
