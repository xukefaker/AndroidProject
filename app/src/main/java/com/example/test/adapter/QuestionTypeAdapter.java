package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.dto.QuestionTypeDTO;

import java.util.List;

public class QuestionTypeAdapter extends BaseAdapter {

    private List<QuestionTypeDTO> mData ;
    private Context mContext;
    private int [] images={R.drawable.list_type_icon1,R.drawable.list_type_icon2,R.drawable.list_type_icon3,R.drawable.list_type_icon4};
    public QuestionTypeAdapter(List<QuestionTypeDTO> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        TextView txType = convertView.findViewById(R.id.tx_type);

        txType.setText(mData.get(position).getTypeDes());

        ImageView imageView=convertView.findViewById(R.id.iv_type);
        imageView.setImageResource(images[position]);

        return convertView;
    }
}
