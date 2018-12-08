package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by xiongweimin on 2018/7/3.
 */
@Entity
public class Student {

    @Id(autoincrement = true)
    private Long id;//id
    private String name;//姓名
    private String codeName;//代号
    private String marshalling;//编组
    private String nativePlace;//籍贯
    private String nation;//民族
    private Date birth;//出生时间
    private Date enlist;//入伍时间
    private Date join;//入党入团时间
    private String education;//学历
    @ToMany
    @JoinEntity(entity = BindTrainPlanStudent.class,
            sourceProperty = "sId",
            targetProperty = "tId")
    private List<TrainPlan> trainPlanList;
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
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1669617897)
    public synchronized void resetTrainPlanList() {
        trainPlanList = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 582716897)
    public List<TrainPlan> getTrainPlanList() {
        if (trainPlanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TrainPlanDao targetDao = daoSession.getTrainPlanDao();
            List<TrainPlan> trainPlanListNew = targetDao._queryStudent_TrainPlanList(id);
            synchronized (this) {
                if(trainPlanList == null) {
                    trainPlanList = trainPlanListNew;
                }
            }
        }
        return trainPlanList;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getEducation() {
        return this.education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public Date getJoin() {
        return this.join;
    }
    public void setJoin(Date join) {
        this.join = join;
    }
    public Date getEnlist() {
        return this.enlist;
    }
    public void setEnlist(Date enlist) {
        this.enlist = enlist;
    }
    public Date getBirth() {
        return this.birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getNation() {
        return this.nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getNativePlace() {
        return this.nativePlace;
    }
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    public String getMarshalling() {
        return this.marshalling;
    }
    public void setMarshalling(String marshalling) {
        this.marshalling = marshalling;
    }
    public String getCodeName() {
        return this.codeName;
    }
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 991546622)
    public Student(Long id, String name, String codeName, String marshalling,
            String nativePlace, String nation, Date birth, Date enlist, Date join,
            String education) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.marshalling = marshalling;
        this.nativePlace = nativePlace;
        this.nation = nation;
        this.birth = birth;
        this.enlist = enlist;
        this.join = join;
        this.education = education;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }


}
