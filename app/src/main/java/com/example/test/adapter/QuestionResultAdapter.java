package com.example.test.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.QuestionResultActivity;
import com.example.test.R;

public class QuestionResultAdapter extends BaseAdapter {

    private int [] backgrounds={R.drawable.correct_bg,R.drawable.error_bg,R.drawable.unanwser_bg};


    private int [] res;//当前答案 0：正确 1：错误  2：未答
    private Context mContext;//上下文
    private TextView tv_res;
    public QuestionResultAdapter(int[] res, Context context){

        this.res=res;
        this.mContext=context;
    }


    @Override
    public int getCount() {
        return res.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.gird_item, parent, false);
        tv_res=convertView.findViewById(R.id.tv_res);

        tv_res.setText(String.valueOf(position+1));
        tv_res.setBackgroundResource(backgrounds[res[position]]);
        if (res[position]==2){
            tv_res.setTextColor(R.color.grey);
        }

        return convertView;
    }
}
