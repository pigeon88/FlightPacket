package com.hsy.flightpacket.bean.dao;

import com.hsy.flightpacket.application.FlightApplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiongweimin on 2018/7/3.
 */
@Entity
public class PlanRecord {

    @Id(autoincrement = true)
    private Long id;
    private Integer daifeiNum;
    private Integer daifeiTime;
    private Integer checkNum;
    private Integer checkTime;
    private Integer danfeiNum;
    private Integer danfeiTimel;
    private Date planTime;
    //训练ID
    private Long trainPlanId;
    //学员Id
    private Long studentId;
    private boolean isFinished;
    public boolean getIsFinished() {
        return this.isFinished;
    }
    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
    public Long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getTrainPlanId() {
        return this.trainPlanId;
    }
    public void setTrainPlanId(Long trainPlanId) {
        this.trainPlanId = trainPlanId;
    }
    public Date getPlanTime() {
        return this.planTime;
    }
    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }
    public Integer getDanfeiTimel() {
        return this.danfeiTimel;
    }
    public void setDanfeiTimel(Integer danfeiTimel) {
        this.danfeiTimel = danfeiTimel;
    }
    public Integer getDanfeiNum() {
        return this.danfeiNum;
    }
    public void setDanfeiNum(Integer danfeiNum) {
        this.danfeiNum = danfeiNum;
    }
    public Integer getCheckTime() {
        return this.checkTime;
    }
    public void setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
    }
    public Integer getCheckNum() {
        return this.checkNum;
    }
    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }
    public Integer getDaifeiTime() {
        return this.daifeiTime;
    }
    public void setDaifeiTime(Integer daifeiTime) {
        this.daifeiTime = daifeiTime;
    }
    public Integer getDaifeiNum() {
        return this.daifeiNum;
    }
    public void setDaifeiNum(Integer daifeiNum) {
        this.daifeiNum = daifeiNum;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1164642377)
    public PlanRecord(Long id, Integer daifeiNum, Integer daifeiTime,
            Integer checkNum, Integer checkTime, Integer danfeiNum,
            Integer danfeiTimel, Date planTime, Long trainPlanId, Long studentId,
            boolean isFinished) {
        this.id = id;
        this.daifeiNum = daifeiNum;
        this.daifeiTime = daifeiTime;
        this.checkNum = checkNum;
        this.checkTime = checkTime;
        this.danfeiNum = danfeiNum;
        this.danfeiTimel = danfeiTimel;
        this.planTime = planTime;
        this.trainPlanId = trainPlanId;
        this.studentId = studentId;
        this.isFinished = isFinished;
    }
    @Generated(hash = 1016952216)
    public PlanRecord() {
    }
}
