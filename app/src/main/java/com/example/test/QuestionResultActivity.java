package com.example.test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.dto.QuestionDTO;

import java.util.List;

public class QuestionResultActivity extends AppCompatActivity {
    private List<QuestionDTO> mData = null;;
    private List<String> postValuesList=null;//存储每道题目的答案
    private TextView tv_res=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_result);
        initView();
        mData = getIntent().getParcelableArrayListExtra("questions");
        postValuesList=getIntent().getStringArrayListExtra("results");
        String res="";
        if (postValuesList!=null){
            for (int i = 0; i < postValuesList.size(); i++) {
                String temp=""+(i+1)+".";
                if (postValuesList.get(i).equals(mData.get(i).getAnswerHead())){
                    temp+="√"+"     ";
                }else {
                    temp+="×"+"     ";
                }
                res+=temp;

            }
        }
        tv_res.setText(res);


    }
    public void initView(){
        tv_res=findViewById(R.id.tv_res);

    }
}
