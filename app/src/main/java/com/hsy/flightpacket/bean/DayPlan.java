package com.hsy.flightpacket.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiongweimin on 2018/8/17.
 */
@Entity
public class DayPlan {
    @Id(autoincrement = true)
    private Long id;
    public String jihao;
    public String jizhang;
    public String zhengjia;
    public String fujia;
    public String lianxihao;
    public String cishu;
    public String zhengjia1;
    public String fujia1;
    public String gaodu;
    public String hangxianhao;
    public boolean isJiayou;
    public Long trainPlanId;
    public Long getTrainPlanId() {
        return this.trainPlanId;
    }
    public void setTrainPlanId(Long trainPlanId) {
        this.trainPlanId = trainPlanId;
    }
    public boolean getIsJiayou() {
        return this.isJiayou;
    }
    public void setIsJiayou(boolean isJiayou) {
        this.isJiayou = isJiayou;
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
    public String getFujia1() {
        return this.fujia1;
    }
    public void setFujia1(String fujia1) {
        this.fujia1 = fujia1;
    }
    public String getZhengjia1() {
        return this.zhengjia1;
    }
    public void setZhengjia1(String zhengjia1) {
        this.zhengjia1 = zhengjia1;
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
    public String getJizhang() {
        return this.jizhang;
    }
    public void setJizhang(String jizhang) {
        this.jizhang = jizhang;
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
    @Generated(hash = 898009990)
    public DayPlan(Long id, String jihao, String jizhang, String zhengjia,
            String fujia, String lianxihao, String cishu, String zhengjia1,
            String fujia1, String gaodu, String hangxianhao, boolean isJiayou,
            Long trainPlanId) {
        this.id = id;
        this.jihao = jihao;
        this.jizhang = jizhang;
        this.zhengjia = zhengjia;
        this.fujia = fujia;
        this.lianxihao = lianxihao;
        this.cishu = cishu;
        this.zhengjia1 = zhengjia1;
        this.fujia1 = fujia1;
        this.gaodu = gaodu;
        this.hangxianhao = hangxianhao;
        this.isJiayou = isJiayou;
        this.trainPlanId = trainPlanId;
    }
    @Generated(hash = 1444131124)
    public DayPlan() {
    }
}
