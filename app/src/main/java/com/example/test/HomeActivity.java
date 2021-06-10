package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.test.constrant.ENetworkPath;
import com.example.test.dto.QuestionDTO;
import com.example.test.dto.QuestionTypeDTO;
import com.example.test.utils.MsgToCommonResultUtil;
import com.example.test.utils.PostRequestUtil;
import com.powater.common.util.CommonResult;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
   private LinearLayout ll_train;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        initVIew();
        String token = sharedPreferences.getString("token","");
        ll_train.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             try {
                 String msg = null;
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("token", token);
                 String json = jsonObject.toString();
                 msg = PostRequestUtil.commonPost(ENetworkPath.QUESTION_TYPE_ALL, json, sharedPreferences);
                 CommonResult commonResult = MsgToCommonResultUtil.strToCommonResult(msg);
                 if (commonResult.getCode()==1) {
                     Toast.makeText(HomeActivity.this,msg, Toast.LENGTH_SHORT).show();
                 }else {
                     Toast.makeText(HomeActivity.this,msg, Toast.LENGTH_SHORT).show();
                 }

                 String s = String.valueOf(commonResult.getObject());
                 List<QuestionTypeDTO> questionTypeDTOS = JSON.parseArray(s, QuestionTypeDTO.class);

                Intent intent=new Intent(HomeActivity.this,QuestionTypeActivity.class);
                intent.putParcelableArrayListExtra("question_type", (ArrayList<? extends Parcelable>) questionTypeDTOS);
                 startActivity(intent);


             } catch (Exception e) {
                 e.printStackTrace();
             }

         }
     });

    }

    private void initVIew() {
         ll_train=findViewById(R.id.ll_train);
        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);
    }

}
