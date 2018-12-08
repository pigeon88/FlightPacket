package com.hsy.flightpacket.bean.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiongweimin on 2018/7/5.
 */
@Entity
public class PilotRecordTable implements Serializable {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private Date yuqiTime;//预期时间
    private Date shijiTime;//实际时刻
    private Integer cihangxiang;//磁航向
    private Integer pianliu;//偏流
    private Integer cihangguiji;//磁航迹角
    private Integer disu;//地速
    private Integer kongsu;//空速
    private Integer gaodu;//高度
    private String fuji;//附记
    private Long pilotBeanId;
    private Boolean left;
    private String didianDaihao;
    private String jingdu;
    private String weidu;
    private Integer juli;
    public Integer getJuli() {
        return this.juli;
    }
    public void setJuli(Integer juli) {
        this.juli = juli;
    }
    public String getWeidu() {
        return this.weidu;
    }
    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }
    public String getJingdu() {
        return this.jingdu;
    }
    public void setJingdu(String jingdu) {
        this.jingdu = jingdu;
    }
    public String getDidianDaihao() {
        return this.didianDaihao;
    }
    public void setDidianDaihao(String didianDaihao) {
        this.didianDaihao = didianDaihao;
    }
    public Boolean getLeft() {
        return this.left;
    }
    public void setLeft(Boolean left) {
        this.left = left;
    }
    public Long getPilotBeanId() {
        return this.pilotBeanId;
    }
    public void setPilotBeanId(Long pilotBeanId) {
        this.pilotBeanId = pilotBeanId;
    }
    public String getFuji() {
        return this.fuji;
    }
    public void setFuji(String fuji) {
        this.fuji = fuji;
    }
    public Integer getGaodu() {
        return this.gaodu;
    }
    public void setGaodu(Integer gaodu) {
        this.gaodu = gaodu;
    }
    public Integer getKongsu() {
        return this.kongsu;
    }
    public void setKongsu(Integer kongsu) {
        this.kongsu = kongsu;
    }
    public Integer getDisu() {
        return this.disu;
    }
    public void setDisu(Integer disu) {
        this.disu = disu;
    }
    public Integer getCihangguiji() {
        return this.cihangguiji;
    }
    public void setCihangguiji(Integer cihangguiji) {
        this.cihangguiji = cihangguiji;
    }
    public Integer getPianliu() {
        return this.pianliu;
    }
    public void setPianliu(Integer pianliu) {
        this.pianliu = pianliu;
    }
    public Integer getCihangxiang() {
        return this.cihangxiang;
    }
    public void setCihangxiang(Integer cihangxiang) {
        this.cihangxiang = cihangxiang;
    }
    public Date getShijiTime() {
        return this.shijiTime;
    }
    public void setShijiTime(Date shijiTime) {
        this.shijiTime = shijiTime;
    }
    public Date getYuqiTime() {
        return this.yuqiTime;
    }
    public void setYuqiTime(Date yuqiTime) {
        this.yuqiTime = yuqiTime;
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
    @Generated(hash = 1190281381)
    public PilotRecordTable(Long id, String title, Date yuqiTime, Date shijiTime,
            Integer cihangxiang, Integer pianliu, Integer cihangguiji,
            Integer disu, Integer kongsu, Integer gaodu, String fuji,
            Long pilotBeanId, Boolean left, String didianDaihao, String jingdu,
            String weidu, Integer juli) {
        this.id = id;
        this.title = title;
        this.yuqiTime = yuqiTime;
        this.shijiTime = shijiTime;
        this.cihangxiang = cihangxiang;
        this.pianliu = pianliu;
        this.cihangguiji = cihangguiji;
        this.disu = disu;
        this.kongsu = kongsu;
        this.gaodu = gaodu;
        this.fuji = fuji;
        this.pilotBeanId = pilotBeanId;
        this.left = left;
        this.didianDaihao = didianDaihao;
        this.jingdu = jingdu;
        this.weidu = weidu;
        this.juli = juli;
    }
    @Generated(hash = 278540759)
    public PilotRecordTable() {
    }



}
