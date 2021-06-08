package com.example.test.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO  {


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



}
