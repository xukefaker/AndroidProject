package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.constrant.ENetworkPath;
import com.example.test.utils.MsgToCommonResultUtil;
import com.example.test.utils.PostRequestUtil;
import com.powater.common.util.CommonResult;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private Button mBtn_login, mBtn_register;
    private EditText mEt_name;
    private EditText mEt_password;
    private EditText mEt_password_repeat;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initVIew();
        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEt_name.getText().toString().trim();
                String password = mEt_password.getText().toString().trim();
                String password_repeat = mEt_password_repeat.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password_repeat)) {
                    Toast.makeText(RegisterActivity.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userName", name);
                    jsonObject.put("userPassword", password);
                    String json = jsonObject.toString();
                    String msg = PostRequestUtil.commonPost(ENetworkPath.USER_REGISTER, json, sharedPreferences);

                    CommonResult commonResult = MsgToCommonResultUtil.strToCommonResult(msg);
                    if (commonResult.getCode() == 1) {
                        Toast.makeText(RegisterActivity.this, "注册成功，请登录！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        if (!TextUtils.isEmpty(msg))
                            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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
        mEt_password_repeat = findViewById(R.id.et_password_repeat);
        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);

    }
}
