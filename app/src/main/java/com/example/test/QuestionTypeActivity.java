package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.test.adapter.QuestionTypeAdapter;
import com.example.test.constrant.ENetworkPath;
import com.example.test.dto.QuestionDTO;
import com.example.test.dto.QuestionTypeDTO;
import com.example.test.utils.MsgToCommonResultUtil;
import com.example.test.utils.PostRequestUtil;
import com.powater.common.util.CommonResult;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionTypeActivity extends AppCompatActivity {

    private List<QuestionTypeDTO> mData = null;
    private Context mContext;
    private ListView lv_type;
    SharedPreferences sharedPreferences;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_type);
        initVIew();
        lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type = mData.get(position).getType();
                String token = sharedPreferences.getString("token", "");
                try {
                    String msg = null;
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("token", token);
                    jsonObject.put("type", type);
                    jsonObject.put("num", 25);
                    String json = jsonObject.toString();
                    msg = PostRequestUtil.commonPost(ENetworkPath.LIST_QUESTION_BY_QUERY, json, sharedPreferences);
                    CommonResult commonResult = MsgToCommonResultUtil.strToCommonResult(msg);

                    String s = String.valueOf(commonResult.getObject());
                    List<QuestionDTO> questionDTOS = JSON.parseArray(s, QuestionDTO.class);
                    builder = new AlertDialog.Builder(mContext);
                    alert = builder.setIcon(R.drawable.question_type_icon1)
                            .setTitle("系统提示：")
                            .setMessage("是否进入"+mData.get(position).getTypeDes()+"的考试？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "你将有45分钟的时间完成考试", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(QuestionTypeActivity.this, QuestionAnswerActivity.class);
                                    intent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) questionDTOS);
                                    startActivity(intent);
                                }
                            }).create();
                    alert.show();//显示对话框


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initVIew() {

        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);
        mContext = QuestionTypeActivity.this;
        mData = getIntent().getParcelableArrayListExtra("question_type");
        lv_type = findViewById(R.id.lv_type);
        lv_type.setAdapter(new QuestionTypeAdapter(mData, mContext));
    }
}
