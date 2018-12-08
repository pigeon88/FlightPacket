package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by xiongweimin on 2018/7/5.
 */
@Entity
public class CheckPlan {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private Date time;
    private Integer status;// 0未完成  1已完成
    @ToMany(referencedJoinProperty = "checkPlanId")
    private List<FlightCheck> flightChecks;
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
    @Generated(hash = 1837784461)
    public synchronized void resetFlightChecks() {
        flightChecks = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 496141416)
    public List<FlightCheck> getFlightChecks() {
        if (flightChecks == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FlightCheckDao targetDao = daoSession.getFlightCheckDao();
            List<FlightCheck> flightChecksNew = targetDao._queryCheckPlan_FlightChecks(id);
            synchronized (this) {
                if(flightChecks == null) {
                    flightChecks = flightChecksNew;
                }
            }
        }
        return flightChecks;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 470542201)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCheckPlanDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 371875995)
    private transient CheckPlanDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getTime() {
        return this.time;
    }
    public void setTime(Date time) {
        this.time = time;
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
    @Generated(hash = 743815002)
    public CheckPlan(Long id, String name, Date time, Integer status) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.status = status;
    }
    @Generated(hash = 1753940593)
    public CheckPlan() {
    }

}
