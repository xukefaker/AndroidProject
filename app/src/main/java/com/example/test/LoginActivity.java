package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.constrant.ENetworkPath;
import com.example.test.utils.MsgToCommonResultUtil;
import com.example.test.utils.PostRequestUtil;
import com.powater.common.util.CommonResult;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;

public class LoginActivity extends Activity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Button mBtn_login, mBtn_register;
    private EditText mEt_name;
    private EditText mEt_password;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //对控件进行初始化操作
        initVIew();
//        String token = sharedPreferences.getString("token","");
//
//        if (!TextUtils.isEmpty(token)){//尝试 不进行登录
//            try {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("token", token);
//
//                String json = jsonObject.toString();
//
//                String msg = PostRequestUtil.commonPost(ENetworkPath.USER_GET, json, sharedPreferences);
//                CommonResult commonResult = MsgToCommonResultUtil.strToCommonResult(msg);
////                Toast.makeText(LoginActivity.this,commonResult.getMessage(),Toast.LENGTH_LONG).show();
//
//                  if (commonResult.getCode()==1) {
////                      Toast.makeText(LoginActivity.this, commonResult.getMessage(), Toast.LENGTH_SHORT).show();
//                      Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                      startActivity(intent);
//                      finish();
//                  }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }

        //绑定事件
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEt_name.getText().toString().trim();
                String password = mEt_password.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }



                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", name);
                    jsonObject.put("password", password);
                    String json = jsonObject.toString();
                    String msg = PostRequestUtil.loginPost(ENetworkPath.USER_LOGIN, json, sharedPreferences);
//                    if (!TextUtils.isEmpty(msg))
//                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    CommonResult commonResult = MsgToCommonResultUtil.strToCommonResult(msg);
                    if (commonResult.getCode() == 1) {
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, commonResult.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initVIew() {
        mBtn_login = findViewById(R.id.btn_login);
        mBtn_register = findViewById(R.id.btn_register);
        mEt_name = findViewById(R.id.et_name);
        mEt_password = findViewById(R.id.et_password);
        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);

    }

}