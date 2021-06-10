package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.constrant.ENetworkPath;
import com.example.test.utils.MsgToCommonResultUtil;
import com.example.test.utils.PostRequestUtil;
import com.powater.common.util.CommonResult;

import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    boolean isJump = false;
    CommonResult commonResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initVIew();
        String token = sharedPreferences.getString("token", "");
        if (!TextUtils.isEmpty(token)) {//尝试 不进行登录
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);

                String json = jsonObject.toString();

                String msg = PostRequestUtil.commonPost(ENetworkPath.USER_GET, json, sharedPreferences);
                commonResult = MsgToCommonResultUtil.strToCommonResult(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);//休眠3秒


                        if (commonResult.getCode() == 1) {
                            isJump = true;

                            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (
                            Exception e) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        e.printStackTrace();
                    }
                }
            }.

                    start();
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }

    private void initVIew() {

        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);


    }
}
