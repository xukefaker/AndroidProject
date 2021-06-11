package com.example.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    private TextView tv_login_out;
    SharedPreferences sharedPreferences;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initVIew();
        tv_login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("token", "");
                edit.commit();


                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.drawable.question_answer_icon1)
                        .setTitle("系统提示：")
                        .setMessage("确定要退出吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "提交成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);

                                startActivity(intent);
                                finish();


                            }
                        }).create();
                alert.show();//显示对话框
            }
        });


    }

    private void initVIew() {
        tv_login_out = findViewById(R.id.tv_login_out);
        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);
        mContext = this;
    }
}
