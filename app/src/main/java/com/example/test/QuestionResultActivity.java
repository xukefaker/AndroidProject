package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.adapter.QuestionResultAdapter;
import com.example.test.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionResultActivity extends AppCompatActivity {
    private List<QuestionDTO> mData = null;
    private List<String> postValuesList = null;//存储每道题目的答案

    private int correct_num;//正确的个数
    private int error_num;//错误的个数
    private int unanswered_num;//未答的个数
    private TextView tv_correct;
    private TextView tv_error;
    private TextView tv_unanswer;
    private  TextView tv_lv;

    private GridView gv_res;
    private int [] res;
    private TextView tv_analysis;
    private ImageView iv_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_result);
        initView();


        if (postValuesList != null) {
            for (int i = 0; i < postValuesList.size(); i++) {


                if (postValuesList.get(i).equals("")) {
                    unanswered_num++;
                    res[i]=2;
                } else {
                    if (postValuesList.get(i).equals(mData.get(i).getAnswerHead())) {
                        correct_num++;
                        res[i]=0;

                    } else {
                        error_num++;
                        res[i]=1;
                    }
                }
            }
        }
        gv_res.setAdapter(new QuestionResultAdapter(res,this));
        tv_correct.setText("共答对"+correct_num+"题");
        tv_error.setText("共答错"+error_num+"题");
        tv_unanswer.setText("未答"+unanswered_num+"题");
        double correct_lv=correct_num*100/(mData.size()*1.0);
        tv_lv.setText("正确率："+String.format("%.2f",correct_lv)+"%");

        tv_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionResultActivity.this, QuestionAnalysisActivity.class);
                intent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) mData);
                intent.putStringArrayListExtra("results", (ArrayList<String>) postValuesList);
                startActivity(intent);
            }
        });


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void initView() {
        tv_analysis=findViewById(R.id.tv_analysis);
        gv_res=findViewById(R.id.gv_res);
        tv_correct=findViewById(R.id.tv_correct);
        tv_error=findViewById(R.id.tv_error);
        tv_unanswer=findViewById(R.id.tv_unanswer);
        tv_lv=findViewById(R.id.tv_lv);
        mData = getIntent().getParcelableArrayListExtra("questions");
        postValuesList = getIntent().getStringArrayListExtra("results");
        res=new int[mData.size()];
        iv_back=findViewById(R.id.iv_back);

    }
}
