package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Created by xiongweimin on 2018/8/9.
 */
@Entity
public class TeachActivity {
    @Id(autoincrement = true)
    private Long id;//id
    private String jihao;
    private String jizhanghao;
    private Boolean shifei;
    private String zhengjia;
    private String fujia;
    private Long planId;

    public String getFujia() {
        return this.fujia;
    }

    public void setFujia(String fujia) {
        this.fujia = fujia;
    }

    public String getZhengjia() {
        return this.zhengjia;
    }

    public void setZhengjia(String zhengjia) {
        this.zhengjia = zhengjia;
    }

    public Boolean getShifei() {
        return this.shifei;
    }

    public void setShifei(Boolean shifei) {
        this.shifei = shifei;
    }

    public String getJizhanghao() {
        return this.jizhanghao;
    }

    public void setJizhanghao(String jizhanghao) {
        this.jizhanghao = jizhanghao;
    }

    public String getJihao() {
        return this.jihao;
    }

    public void setJihao(String jihao) {
        this.jihao = jihao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return this.planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Generated(hash = 78069223)
    public TeachActivity(Long id, String jihao, String jizhanghao, Boolean shifei,
            String zhengjia, String fujia, Long planId) {
        this.id = id;
        this.jihao = jihao;
        this.jizhanghao = jizhanghao;
        this.shifei = shifei;
        this.zhengjia = zhengjia;
        this.fujia = fujia;
        this.planId = planId;
    }

    @Generated(hash = 1213871707)
    public TeachActivity() {
    }
}
