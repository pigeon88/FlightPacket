package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by xiongweimin on 2018/7/11.
 */
@Entity
public class PClass {
    @Id(autoincrement = true)
    private Long id;
    private String className;
    @ToMany(referencedJoinProperty = "classId")
    private List<TrainPlan> trainPlen;
    /** Used for active entity operations. */
    @Generated(hash = 1221717652)
    private transient PClassDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getClassName() {
        return this.className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    @Generated(hash = 1096283840)
    public synchronized void resetTrainPlen() {
        trainPlen = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 32241986)
    public List<TrainPlan> getTrainPlen() {
        if (trainPlen == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TrainPlanDao targetDao = daoSession.getTrainPlanDao();
            List<TrainPlan> trainPlenNew = targetDao._queryPClass_TrainPlen(id);
            synchronized (this) {
                if(trainPlen == null) {
                    trainPlen = trainPlenNew;
                }
            }
        }
        return trainPlen;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1093850039)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPClassDao() : null;
    }
    @Generated(hash = 77017453)
    public PClass(Long id, String className) {
        this.id = id;
        this.className = className;
    }
    @Generated(hash = 415652951)
    public PClass() {
    }
}
