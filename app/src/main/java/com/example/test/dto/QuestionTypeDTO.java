package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTypeDTO {


    /**
     * 问题类型
     */
    private String type;

    /**
     * 问题类型描述
     */
    private String typeDes;



}
