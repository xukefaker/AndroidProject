package com.example.test;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.dto.QuestionDTO;
import com.example.test.fragment.QuestionAnalysisFragment;
import com.example.test.fragment.QuestionShowFragment;

import java.util.List;

public class QuestionAnalysisActivity extends AppCompatActivity {
    private FragmentManager fManager;
    private QuestionAnalysisFragment[] questionAnalysisFragments;//题目fragment 数组
    private List<QuestionDTO> mData = null;
    private List<String> postValuesList = null;//存储每道题目的答案
    private int index;
    private TextView tv_last, tv_next;
    private ImageView iv_black;
    private Context that;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question_analysis);
        initView();
        tv_last.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                if (index > 0) {
                    index--;
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    hideAll(fTransaction);
                    fTransaction.show(questionAnalysisFragments[index]);
                    fTransaction.commit();

                }
                tv_next.setBackground(that.getResources().getDrawable(R.drawable.question_analysis_icon1));

                if (index == 0)
                    tv_last.setBackground(that.getResources().getDrawable(R.drawable.question_analysis_icon2));

            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (index < questionAnalysisFragments.length - 1) {
                    index++;
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    hideAll(fTransaction);
                    fTransaction.show(questionAnalysisFragments[index]);
                    fTransaction.commit();
                    tv_last.setBackground(that.getResources().getDrawable(R.drawable.question_analysis_icon1));

                    if (index == questionAnalysisFragments.length - 1)
                        tv_next.setBackground(that.getResources().getDrawable(R.drawable.question_analysis_icon2));


                }
            }
        });
        iv_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void hideAll(FragmentTransaction fragmentTransaction) {
        for (int i = 0; i < questionAnalysisFragments.length; i++) {
            fragmentTransaction.hide(questionAnalysisFragments[i]);
        }

    }

    @SuppressLint("ResourceType")
    public void initView() {
        that = this;
        iv_black=findViewById(R.id.iv_back);
        tv_last = findViewById(R.id.tv_last);
        tv_next = findViewById(R.id.tv_next);
        mData = getIntent().getParcelableArrayListExtra("questions");
        postValuesList = getIntent().getStringArrayListExtra("results");
        questionAnalysisFragments = new QuestionAnalysisFragment[mData.size()];
        fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        for (int i = 0; i < mData.size(); i++) {
            QuestionDTO questionDTO = mData.get(i);
            String postValue = postValuesList.get(i);
            questionAnalysisFragments[i] = new QuestionAnalysisFragment(questionDTO, postValue, i);
            fTransaction.add(R.id.ll_analysis, questionAnalysisFragments[i]);
        }
        hideAll(fTransaction);
        fTransaction.show(questionAnalysisFragments[index]);
        fTransaction.commit();

        tv_last.setBackground(that.getResources().getDrawable(R.drawable.question_analysis_icon2));

    }

}
