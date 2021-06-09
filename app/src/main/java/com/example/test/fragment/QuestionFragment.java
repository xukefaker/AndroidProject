package com.example.test.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.dto.QuestionDTO;

@SuppressLint("ValidFragment")
public class QuestionFragment extends Fragment {


    private QuestionDTO questionDTO;

    public QuestionFragment(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_show, container, false);
        TextView tv_question = view.findViewById(R.id.tv_question);
        TextView tv_a_des = view.findViewById(R.id.tv_a_des);
        TextView tv_b_des = view.findViewById(R.id.tv_b_des);
        TextView tv_c_des = view.findViewById(R.id.tv_c_des);
        TextView tv_d_des = view.findViewById(R.id.tv_d_des);
        tv_question.setText(questionDTO.getQuestionDes());
        tv_a_des.setText(questionDTO.getADes());
        tv_b_des.setText(questionDTO.getBDes());
        tv_c_des.setText(questionDTO.getCDes());
        tv_d_des.setText(questionDTO.getDDes());


        return view;

    }
}
