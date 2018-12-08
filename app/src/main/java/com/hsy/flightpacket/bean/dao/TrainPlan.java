package com.hsy.flightpacket.bean.dao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


/**
 * Created by xiongweimin on 2018/7/3.
 * 训练计划
 */
@Entity
public class TrainPlan {

    @Id(autoincrement = true)
    private Long id;//计划id
    private String subject;//科目
    private String trainContent;//练习内容
    private Date trainTime;//训练时间
    private String supreReview;//上级讲评
    private String teamReview;//小组讲评
    private String picUrl;//签字本地连接
    private String supreReview2;//上级
    private String teamReview2;//小组讲评
    private String picUrl2;//签字本地连接
    @ToMany
    @JoinEntity(entity = BindTrainPlanStudent.class,
            sourceProperty = "tId",
            targetProperty = "sId")
    private List<Student> students;//学生列表

    private Long classId;

    private String trainPlanName;

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 238993120)
    public synchronized void resetStudents() {
        students = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1780806342)
    public List<Student> getStudents() {
        if (students == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> studentsNew = targetDao._queryTrainPlan_Students(id);
            synchronized (this) {
                if (students == null) {
                    students = studentsNew;
                }
            }
        }
        return students;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 633562541)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTrainPlanDao() : null;
    }

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 39859544)
    private transient TrainPlanDao myDao;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public Long getClassId() {
        return this.classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTeamReview() {
        return this.teamReview;
    }

    public void setTeamReview(String teamReview) {
        this.teamReview = teamReview;
    }

    public String getSupreReview() {
        return this.supreReview;
    }

    public void setSupreReview(String supreReview) {
        this.supreReview = supreReview;
    }

    public Date getTrainTime() {
        return this.trainTime;
    }

    public void setTrainTime(Date trainTime) {
        this.trainTime = trainTime;
    }

    public String getTrainContent() {
        return this.trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainPlanName() {
        return this.trainPlanName;
    }

    public void setTrainPlanName(String trainPlanName) {
        this.trainPlanName = trainPlanName;
    }

    public String getPicUrl2() {
        return this.picUrl2;
    }

    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    public String getTeamReview2() {
        return this.teamReview2;
    }

    public void setTeamReview2(String teamReview2) {
        this.teamReview2 = teamReview2;
    }

    public String getSupreReview2() {
        return this.supreReview2;
    }

    public void setSupreReview2(String supreReview2) {
        this.supreReview2 = supreReview2;
    }

    @Generated(hash = 1143114243)
    public TrainPlan(Long id, String subject, String trainContent, Date trainTime,
            String supreReview, String teamReview, String picUrl, String supreReview2,
            String teamReview2, String picUrl2, Long classId, String trainPlanName) {
        this.id = id;
        this.subject = subject;
        this.trainContent = trainContent;
        this.trainTime = trainTime;
        this.supreReview = supreReview;
        this.teamReview = teamReview;
        this.picUrl = picUrl;
        this.supreReview2 = supreReview2;
        this.teamReview2 = teamReview2;
        this.picUrl2 = picUrl2;
        this.classId = classId;
        this.trainPlanName = trainPlanName;
    }

    @Generated(hash = 169548247)
    public TrainPlan() {
    }

}

