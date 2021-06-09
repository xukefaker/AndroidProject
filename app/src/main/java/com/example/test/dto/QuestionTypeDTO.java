package com.example.test.dto;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTypeDTO implements Parcelable {


    /**
     * 问题类型
     */
    private String type;

    /**
     * 问题类型描述
     */
    private String typeDes;


    protected QuestionTypeDTO(Parcel in) {
        type = in.readString();
        typeDes = in.readString();
    }

    public static final Creator<QuestionTypeDTO> CREATOR = new Creator<QuestionTypeDTO>() {
        @Override
        public QuestionTypeDTO createFromParcel(Parcel in) {
            return new QuestionTypeDTO(in);
        }

        @Override
        public QuestionTypeDTO[] newArray(int size) {
            return new QuestionTypeDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(typeDes);
    }
}
