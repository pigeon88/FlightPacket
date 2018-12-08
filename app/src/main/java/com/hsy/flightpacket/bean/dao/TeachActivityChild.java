package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiongweimin on 2018/8/9.
 */
@Entity
public class TeachActivityChild {
    @Id(autoincrement = true)
    private Long id;//id
    private String lianxihao;
    private String cishu;
    private String zhengjia;
    private String fujia;
    private String gaodu;
    private String hangxianhao;
    private Boolean jiayou;
    private Long teachactivityid;
    public Long getTeachactivityid() {
        return this.teachactivityid;
    }
    public void setTeachactivityid(Long teachactivityid) {
        this.teachactivityid = teachactivityid;
    }
    public Boolean getJiayou() {
        return this.jiayou;
    }
    public void setJiayou(Boolean jiayou) {
        this.jiayou = jiayou;
    }
    public String getHangxianhao() {
        return this.hangxianhao;
    }
    public void setHangxianhao(String hangxianhao) {
        this.hangxianhao = hangxianhao;
    }
    public String getGaodu() {
        return this.gaodu;
    }
    public void setGaodu(String gaodu) {
        this.gaodu = gaodu;
    }
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
    public String getCishu() {
        return this.cishu;
    }
    public void setCishu(String cishu) {
        this.cishu = cishu;
    }
    public String getLianxihao() {
        return this.lianxihao;
    }
    public void setLianxihao(String lianxihao) {
        this.lianxihao = lianxihao;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1589631595)
    public TeachActivityChild(Long id, String lianxihao, String cishu,
            String zhengjia, String fujia, String gaodu, String hangxianhao,
            Boolean jiayou, Long teachactivityid) {
        this.id = id;
        this.lianxihao = lianxihao;
        this.cishu = cishu;
        this.zhengjia = zhengjia;
        this.fujia = fujia;
        this.gaodu = gaodu;
        this.hangxianhao = hangxianhao;
        this.jiayou = jiayou;
        this.teachactivityid = teachactivityid;
    }
    @Generated(hash = 499298095)
    public TeachActivityChild() {
    }

}
