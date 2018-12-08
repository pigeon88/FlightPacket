package com.hsy.flightpacket.bean.dao;

import com.alibaba.fastjson.JSON;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * Created by xiongweimin on 2018/7/4.
 */

public class StudentConvert implements PropertyConverter<List<Student>, String> {
    @Override
    public List<Student> convertToEntityProperty(String databaseValue) {
        return JSON.parseArray(databaseValue, Student.class);
    }

    @Override
    public String convertToDatabaseValue(List<Student> entityProperty) {
        return JSON.toJSONString(entityProperty);
    }
}
