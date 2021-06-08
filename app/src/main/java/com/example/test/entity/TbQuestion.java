package com.example.test.entity;

import java.util.Date;

public class TbQuestion {
    /**
     * id
     */
    private Long id;

    /**
     * 项目编号
     */
    private String questionDes;

    /**
     * 
     */
    private String aDes;

    /**
     * 
     */
    private String bDes;

    /**
     * 
     */
    private String cDes;

    /**
     * 
     */
    private String dDes;

    /**
     * 
     */
    private String answerHead;

    /**
     * 
     */
    private String answerDetial;

    /**
     * COM 计算机类 FAE 财经类 REC 招录类 QUA 资格类
     */
    private String type;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 项目编号
     * @return question_des 项目编号
     */
    public String getQuestionDes() {
        return questionDes;
    }

    /**
     * 项目编号
     * @param questionDes 项目编号
     */
    public void setQuestionDes(String questionDes) {
        this.questionDes = questionDes;
    }

    /**
     * 
     * @return a_des 
     */
    public String getaDes() {
        return aDes;
    }

    /**
     * 
     * @param aDes 
     */
    public void setaDes(String aDes) {
        this.aDes = aDes;
    }

    /**
     * 
     * @return b_des 
     */
    public String getbDes() {
        return bDes;
    }

    /**
     * 
     * @param bDes 
     */
    public void setbDes(String bDes) {
        this.bDes = bDes;
    }

    /**
     * 
     * @return c_des 
     */
    public String getcDes() {
        return cDes;
    }

    /**
     * 
     * @param cDes 
     */
    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    /**
     * 
     * @return d_des 
     */
    public String getdDes() {
        return dDes;
    }

    /**
     * 
     * @param dDes 
     */
    public void setdDes(String dDes) {
        this.dDes = dDes;
    }

    /**
     * 
     * @return answer_head 
     */
    public String getAnswerHead() {
        return answerHead;
    }

    /**
     * 
     * @param answerHead 
     */
    public void setAnswerHead(String answerHead) {
        this.answerHead = answerHead;
    }

    /**
     * 
     * @return answer_detial 
     */
    public String getAnswerDetial() {
        return answerDetial;
    }

    /**
     * 
     * @param answerDetial 
     */
    public void setAnswerDetial(String answerDetial) {
        this.answerDetial = answerDetial;
    }

    /**
     * COM 计算机类 FAE 财经类 REC 招录类 QUA 资格类
     * @return type COM 计算机类 FAE 财经类 REC 招录类 QUA 资格类
     */
    public String getType() {
        return type;
    }

    /**
     * COM 计算机类 FAE 财经类 REC 招录类 QUA 资格类
     * @param type COM 计算机类 FAE 财经类 REC 招录类 QUA 资格类
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 更新时间
     * @return modify_time 更新时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 更新时间
     * @param modifyTime 更新时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}