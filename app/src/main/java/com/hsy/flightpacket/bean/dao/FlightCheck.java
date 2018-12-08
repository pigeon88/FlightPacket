package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by xiongweimin on 2018/7/4.
 */
@Entity
public class FlightCheck {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String des;
    private String picUrl;
    private Boolean isChecked;
    private Long checkPlanId;
    @Transient
    public boolean isChoose;
    @Transient
    public boolean isShowDelete;
    @Transient
    public boolean isChooseDelete;

    public Long getCheckPlanId() {
        return this.checkPlanId;
    }
    public void setCheckPlanId(Long checkPlanId) {
        this.checkPlanId = checkPlanId;
    }
    public Boolean getIsChecked() {
        return this.isChecked;
    }
    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 137301300)
    public FlightCheck(Long id, String title, String des, String picUrl,
            Boolean isChecked, Long checkPlanId) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.picUrl = picUrl;
        this.isChecked = isChecked;
        this.checkPlanId = checkPlanId;
    }
    @Generated(hash = 1146625900)
    public FlightCheck() {
    }


}
