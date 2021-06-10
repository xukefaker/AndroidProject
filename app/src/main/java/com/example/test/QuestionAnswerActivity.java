package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.dto.QuestionDTO;
import com.example.test.fragment.QuestionFragment;
import com.example.test.utils.CodeToStringUtil;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerActivity extends AppCompatActivity {
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private List<QuestionDTO> mData = null;
    private Context mContext;
    SharedPreferences sharedPreferences;
    private TextView tv_last;
    private TextView tv_next;
    private TextView tv_curPosition;
    private TextView tv_down_time;
    private FragmentManager fManager;
    private QuestionFragment[] questionFragments;//题目fragment 数组
    private int index;//当前 题目 的下标
    private List<String> postValuesList;//存储每道题目的回答的答案

    public List<String> getPostValuesList() {
        return postValuesList;
    }

    public int getIndex() {
        return index;
    }

    public int remindNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);
        initVIew();
        tv_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    hideAll(fTransaction);
                    fTransaction.show(questionFragments[index]);
                    fTransaction.commit();
                    tv_curPosition.setText((index + 1) + "/" + mData.size());
                    tv_next.setText("下一题");
                }

            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < questionFragments.length - 1) {
                    index++;
                    FragmentTransaction fTransaction = fManager.beginTransaction();
                    hideAll(fTransaction);
                    fTransaction.show(questionFragments[index]);
                    fTransaction.commit();
                    tv_curPosition.setText((index + 1) + "/" + mData.size());//设置显示当前题目位置数的控件的显示  1/25

                }
                remindNum=getRemind();
                String msg="确认提交吗？";
                if (remindNum>0)msg+="你还有"+remindNum+"道题未写";
                if (tv_next.getText().equals("提交")) {
                    builder = new AlertDialog.Builder(mContext);
                    alert = builder.setIcon(R.drawable.question_answer_icon1)
                            .setTitle("系统提示：")
                            .setMessage(msg)
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
                                    Intent intent = new Intent(QuestionAnswerActivity.this, QuestionResultActivity.class);
                                    intent.putParcelableArrayListExtra("questions", (ArrayList<? extends Parcelable>) mData);
                                    intent.putStringArrayListExtra("results", (ArrayList<String>) postValuesList);
                                    startActivity(intent);
                                    finish();

                                }
                            }).create();
                    alert.show();//显示对话框

                } else {
                    if (index == mData.size() - 1) {
                        tv_next.setText("提交");
                    }


                }


            }
        });
        countDownTimer.start();
    }


    //初始化组件和变量
    private void initVIew() {

        tv_down_time = findViewById(R.id.tv_down_time);
        tv_last = findViewById(R.id.tv_last);
        tv_next = findViewById(R.id.tv_next);
        tv_curPosition = findViewById(R.id.tv_curPosition);
        sharedPreferences = this.getSharedPreferences("Session", MODE_PRIVATE);
        mContext = QuestionAnswerActivity.this;
        mData = getIntent().getParcelableArrayListExtra("questions");
        postValuesList = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {//初始化答案集合
            postValuesList.add("");
        }
        questionFragments = new QuestionFragment[mData.size()];
        fManager = getFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        for (int i = 0; i < mData.size(); i++) {
            QuestionDTO questionDTO = mData.get(i);
            questionFragments[i] = new QuestionFragment(questionDTO,i);
            fTransaction.add(R.id.ll_question, questionFragments[i]);
        }
        hideAll(fTransaction);
        Bundle bundle = new Bundle();

        fTransaction.show(questionFragments[index]);
        fTransaction.commit();

    }


    public void hideAll(FragmentTransaction fragmentTransaction) {
        for (int i = 0; i < questionFragments.length; i++) {
            fragmentTransaction.hide(questionFragments[i]);
        }


    }

    private final int TOTAL_TIME = 45 * 60 * 1000;
    private final int ONECE_TIME = 1 * 1000;

    /**
     * CountDownTimer 实现倒计时
     */
    private CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONECE_TIME) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            long second = millisUntilFinished / 1000 % 60;
            long minute = millisUntilFinished / 1000 / 60 % 60;
            String vSecond = String.valueOf(second);
            String vMinute = String.valueOf(minute);
            if (vSecond.length() < 2) vSecond = "0" + vSecond;
            if (vMinute.length() < 2) vMinute = "0" + vMinute;

            tv_down_time.setText(vMinute + ":" + vSecond);
        }

        @Override
        public void onFinish() {
//            tv_down_time.setText(getResources().getString(R.string.done));
            tv_down_time.setText("00:00");
        }
    };

    //获取还有多少题目未写
    public int getRemind() {
        int res = 0;
        for (int i = 0; i < postValuesList.size(); i++) {
            if (postValuesList.get(i).equals("")) {
                res++;
            }
        }

       return  res;
    }

}
