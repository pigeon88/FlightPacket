package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiongweimin on 2018/8/3.
 */
@Entity
public class PilotBean implements Serializable {
    @Id(autoincrement = true)
    private Long id;
    private Date dateTime;//时间
    private String title;//标题
    private String model;//机型
    private String flyer;//飞行员
    private String pilot;//领航员
    private String goOutPic;//出航图
    private String returnPic;//返航图
    private String naviInfo;//通信导航资料
    private Date landing;//着陆
    private Date renewal;//续航

    public Date getRenewal() {
        return this.renewal;
    }

    public void setRenewal(Date renewal) {
        this.renewal = renewal;
    }

    public Date getLanding() {
        return this.landing;
    }

    public void setLanding(Date landing) {
        this.landing = landing;
    }

    public String getNaviInfo() {
        return this.naviInfo;
    }

    public void setNaviInfo(String naviInfo) {
        this.naviInfo = naviInfo;
    }

    public String getReturnPic() {
        return this.returnPic;
    }

    public void setReturnPic(String returnPic) {
        this.returnPic = returnPic;
    }

    public String getGoOutPic() {
        return this.goOutPic;
    }

    public void setGoOutPic(String goOutPic) {
        this.goOutPic = goOutPic;
    }

    public String getPilot() {
        return this.pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public String getFlyer() {
        return this.flyer;
    }

    public void setFlyer(String flyer) {
        this.flyer = flyer;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 513125116)
    public PilotBean(Long id, Date dateTime, String title, String model,
                     String flyer, String pilot, String goOutPic, String returnPic,
                     String naviInfo, Date landing, Date renewal) {
        this.id = id;
        this.dateTime = dateTime;
        this.title = title;
        this.model = model;
        this.flyer = flyer;
        this.pilot = pilot;
        this.goOutPic = goOutPic;
        this.returnPic = returnPic;
        this.naviInfo = naviInfo;
        this.landing = landing;
        this.renewal = renewal;
    }

    @Generated(hash = 432505912)
    public PilotBean() {
    }
}
