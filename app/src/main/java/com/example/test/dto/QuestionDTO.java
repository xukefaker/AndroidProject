package com.example.test.dto;


import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO implements Parcelable {


    /**
     * 项目编号
     */
    private String questionDes;

    /**
     *a选项描述
     */
    private String aDes;

    /**
     * b选项描述
     */
    private String bDes;

    /**
     * c选项描述
     */
    private String cDes;

    /**
     * b选项描述
     */
    private String dDes;

    /**
     * 答案选项
     */
    private String answerHead;

    /**
     * 答案详解
     */
    private String answerDetial;

    /**
     * COM 计算机类 FAE 财经类 REC 招录类 QUA 资格类
     */
    private String type;


    protected QuestionDTO(Parcel in) {
        questionDes = in.readString();
        aDes = in.readString();
        bDes = in.readString();
        cDes = in.readString();
        dDes = in.readString();
        answerHead = in.readString();
        answerDetial = in.readString();
        type = in.readString();
    }

    public static final Creator<QuestionDTO> CREATOR = new Creator<QuestionDTO>() {
        @Override
        public QuestionDTO createFromParcel(Parcel in) {
            return new QuestionDTO(in);
        }

        @Override
        public QuestionDTO[] newArray(int size) {
            return new QuestionDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionDes);
        dest.writeString(aDes);
        dest.writeString(bDes);
        dest.writeString(cDes);
        dest.writeString(dDes);
        dest.writeString(answerHead);
        dest.writeString(answerDetial);
        dest.writeString(type);
    }
}
