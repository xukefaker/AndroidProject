package com.example.test.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.test.QuestionAnswerActivity;
import com.example.test.R;
import com.example.test.dto.QuestionDTO;

import java.util.List;

@SuppressLint("ValidFragment")
public class QuestionShowFragment extends Fragment {



    private QuestionDTO questionDTO;

    public QuestionShowFragment(QuestionDTO questionDTO, int index) {
        this.questionDTO = questionDTO;
        this.index=index;
    }

    public String result;
    List<String> postValuesList;//存储每道题目的答案
    int index;
    private boolean a = false, b = false, c = false, d = false; //a,b,c,d值为false,表示未被选择；true 表示已经被选择
    private boolean isSingle = true;//是否为单选
    TextView tv_a_des;
    TextView tv_b_des;
    TextView tv_c_des;
    TextView tv_d_des;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_show, container, false);
        TextView tv_question = view.findViewById(R.id.tv_question);
        if (questionDTO.getQuestionDes().contains("多选")) isSingle = false;
        tv_a_des = view.findViewById(R.id.tv_a_des);
        tv_b_des = view.findViewById(R.id.tv_b_des);
        tv_c_des = view.findViewById(R.id.tv_c_des);
        tv_d_des = view.findViewById(R.id.tv_d_des);
        tv_question.setText(questionDTO.getQuestionDes());
        tv_a_des.setText(questionDTO.getADes());
        tv_b_des.setText(questionDTO.getBDes());
        tv_c_des.setText(questionDTO.getCDes());
        tv_d_des.setText(questionDTO.getDDes());

        postValuesList = ((QuestionAnswerActivity) (getActivity())).getPostValuesList();
        if (postValuesList.size() > index) {
            if (a)
                tv_a_des.setTextColor(this.getResources().getColor(R.color.th1));
            if (b)
                tv_b_des.setTextColor(this.getResources().getColor(R.color.th1));
            if (c)
                tv_c_des.setTextColor(this.getResources().getColor(R.color.th1));
            if (d)
                tv_d_des.setTextColor(this.getResources().getColor(R.color.th1));
        }
        bandAction();
        return view;

    }


    //将选项还原为原来的背景颜色
    public void back() {
        tv_a_des.setTextColor(this.getResources().getColor(R.color.black));
        tv_b_des.setTextColor(this.getResources().getColor(R.color.black));
        tv_c_des.setTextColor(this.getResources().getColor(R.color.black));
        tv_d_des.setTextColor(this.getResources().getColor(R.color.black));

        a = false;
        b = false;
        c = false;
        d = false;
    }

    public QuestionShowFragment that;

    //绑定事件的方法
    public void bandAction() {
        that = this;
        //给 A 选项绑定点击事件
        tv_a_des.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isSingle) {//如果是单选
                    back();
                    if (a) {
                        a = false;
                        tv_a_des.setTextColor(that.getResources().getColor(R.color.black));
                    } else {
                        a = true;
                        tv_a_des.setTextColor(that.getResources().getColor(R.color.th1));
                    }
                } else {

                    if (a) {
                        a = false;
                        tv_a_des.setTextColor(that.getResources().getColor(R.color.th1));
                    } else {
                        a = true;
                        tv_a_des.setTextColor(that.getResources().getColor(R.color.black));
                    }

                }
                setAnswer();//将选择的结果设置在  postValuesList中
            }
        });
        //给 B 选项绑定点击事件

        tv_b_des.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isSingle) {//如果是单选
                    back();
                    if (b) {
                        b = false;
                        tv_b_des.setTextColor(that.getResources().getColor(R.color.black));
                    } else {
                        b = true;
                        tv_b_des.setTextColor(that.getResources().getColor(R.color.th1));
                    }
                } else {

                    if (b) {
                        b = false;
                        tv_b_des.setTextColor(that.getResources().getColor(R.color.th1));
                    } else {
                        b = true;
                        tv_b_des.setTextColor(that.getResources().getColor(R.color.black));
                    }
                }
                setAnswer();//将选择的结果设置在  postValuesList中

            }
        });
        //给 C 选项绑定点击事件
        tv_c_des.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isSingle) {//如果是单选
                    back();
                    if (c) {
                        c = false;
                        tv_c_des.setTextColor(that.getResources().getColor(R.color.black));
                    } else {
                        c = true;
                        tv_c_des.setTextColor(that.getResources().getColor(R.color.th1));
                    }
                } else {

                    if (c) {
                        c = false;
                        tv_c_des.setTextColor(that.getResources().getColor(R.color.th1));
                    } else {
                        c = true;
                        tv_c_des.setTextColor(that.getResources().getColor(R.color.black));
                    }
                }
                setAnswer();//将选择的结果设置在  postValuesList中
            }
        });
        //给 D 选项绑定点击事件
        tv_d_des.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isSingle) {//如果是单选
                    back();
                    if (d) {
                        d = false;
                        tv_d_des.setTextColor(that.getResources().getColor(R.color.black));
                    } else {
                        d = true;
                        tv_d_des.setTextColor(that.getResources().getColor(R.color.th1));
                    }
                } else {

                    if (d) {
                        d = false;
                        tv_d_des.setTextColor(that.getResources().getColor(R.color.th1));
                    } else {
                        d = true;
                        tv_d_des.setTextColor(that.getResources().getColor(R.color.black));
                    }
                }
                setAnswer();//将选择的结果设置在  postValuesList中
            }
        });
    }

    //将选择的结果设置在  postValuesList中
    public void setAnswer() {
        if (isSingle) {//如果是单选
            if (a) postValuesList.set(index, "A");
            if (b) postValuesList.set(index, "B");
            if (c) postValuesList.set(index, "C");
            if (d) postValuesList.set(index, "D");
        } else {//如果是多选
            String temp = "";
            if (a) temp += "A,";
            if (b) temp += "B,";
            if (c) temp += "C,";
            if (d) temp += "D,";
            if (temp.length() > 0) postValuesList.set(index, temp.substring(0, temp.length() - 1));
        }
    }
}
