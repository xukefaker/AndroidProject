package com.example.test.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.dto.QuestionDTO;

import java.util.List;

@SuppressLint("ValidFragment")
public class QuestionAnalysisFragment extends Fragment {

    private TextView tv_answer_detail,tv_my_answer,tv_correct_answer,tv_question;
  private TextView tv_answer_a,tv_answer_b,tv_answer_c,tv_answer_d;
    private QuestionDTO questionDTO;
    private int index;
    private String postValue  ;

    @SuppressLint("ValidFragment")
    public QuestionAnalysisFragment(QuestionDTO questionDTO,  String postValue , int index) {
        this.questionDTO = questionDTO;
        this.index=index;
        this.postValue=postValue;
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question_analysis, container, false);
        tv_answer_detail=view.findViewById(R.id.tv_answer_detail);
        tv_my_answer=view.findViewById(R.id.tv_my_answer);
        tv_correct_answer=view.findViewById(R.id.tv_correct_answer);
        tv_question=view.findViewById(R.id.tv_question);

        tv_answer_detail.setText(questionDTO.getAnswerDetial());
        tv_question.setText(questionDTO.getQuestionDes());
        tv_my_answer.setText(postValue);
        tv_correct_answer.setText(questionDTO.getAnswerHead());
        if (postValue.equals(""))tv_my_answer.setText("未答");
        if (!postValue.equals(questionDTO.getAnswerHead()))tv_my_answer.setTextColor(this.getResources().getColor(R.color.red));
        tv_answer_a=view.findViewById(R.id.tv_answer_a);
        tv_answer_b=view.findViewById(R.id.tv_answer_b);
        tv_answer_c=view.findViewById(R.id.tv_answer_c);
        tv_answer_d=view.findViewById(R.id.tv_answer_d);
        tv_answer_a.setText(questionDTO.getADes());
        tv_answer_b.setText(questionDTO.getBDes());
        tv_answer_c.setText(questionDTO.getCDes());
        tv_answer_d.setText(questionDTO.getDDes());



        return view;
    }


}
