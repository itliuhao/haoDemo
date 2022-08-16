package com.example.haodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wander.baselibrary.BaseTool;
import com.wander.baselibrary.utils.LogUtils;
import com.wander.baselibrary.utils.NfcUtils;
import com.wander.baselibrary.utils.SharePreferenceUtils;
import com.wander.baselibrary.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharePreferenceUtils.put("test","adadadasdadada");
    }

    public void nfc(View view) {
        String test = (String) SharePreferenceUtils.get("test", "");
        ToastUtil.showToast(test);
//        startActivity(new Intent(this, NfcActivity.class));
        NfcUtils.getInstance().startNfc(this, new NfcUtils.NfcResult() {
            @Override
            public void onNfcResult(String result) {
//                Log.e("liuhao","-onNfcResult--"+result);
                LogUtils.i(result);
            }
        });
    }


}