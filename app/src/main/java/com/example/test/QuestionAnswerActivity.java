package com.example.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.dto.QuestionDTO;
import com.example.test.fragment.QuestionFragment;

import java.util.List;

public class QuestionAnswerActivity extends Activity {
    private List<QuestionDTO> mData = null;
    private Context mContext;
    SharedPreferences sharedPreferences;
    private TextView tv_last;
    private TextView tv_next;
    private FragmentManager fManager;
    private QuestionFragment[] questionFragments;
   private int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        initVIew();
        tv_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index>0){
                    index--;
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    hideAll(fTransaction);
                    fTransaction.show(questionFragments[index]);
                    fTransaction.commit();
                }
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index<questionFragments.length-1){
                    index++;
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    hideAll(fTransaction);
                    fTransaction.show(questionFragments[index]);
                    fTransaction.commit();
                }
            }
        });

    }

    private void initVIew() {
        tv_last=findViewById(R.id.tv_last);
        tv_next=findViewById(R.id.tv_next);
        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);
        mContext = QuestionAnswerActivity.this;
        mData = getIntent().getParcelableArrayListExtra("questions");
        questionFragments = new QuestionFragment[mData.size()];
        fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        for (int i = 0; i < mData.size(); i++) {
            QuestionDTO questionDTO = mData.get(i);
            questionFragments[i] = new QuestionFragment(questionDTO);
            fTransaction.add(R.id.ll_question, questionFragments[i]);
        }
        hideAll(fTransaction);
        fTransaction.show(questionFragments[index]);
        fTransaction.commit();

    }


    public void hideAll(FragmentTransaction fragmentTransaction) {
        for (int i = 0; i <questionFragments.length; i++) {
           fragmentTransaction.hide(questionFragments[i]);
        }


    }
}
