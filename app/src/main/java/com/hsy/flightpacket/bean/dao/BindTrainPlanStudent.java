package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by xiongweimin on 2018/7/6.
 */
@Entity
public class BindTrainPlanStudent {
    @Id(autoincrement = true)
    private Long id;
    private Long tId;
    private Long sId;
    public Long getSId() {
        return this.sId;
    }
    public void setSId(Long sId) {
        this.sId = sId;
    }
    public Long getTId() {
        return this.tId;
    }
    public void setTId(Long tId) {
        this.tId = tId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 622458476)
    public BindTrainPlanStudent(Long id, Long tId, Long sId) {
        this.id = id;
        this.tId = tId;
        this.sId = sId;
    }
    @Generated(hash = 1944115756)
    public BindTrainPlanStudent() {
    }

}
