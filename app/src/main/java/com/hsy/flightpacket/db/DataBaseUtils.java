package com.hsy.flightpacket.db;

import com.hsy.flightpacket.application.FlightApplication;
import com.hsy.flightpacket.bean.DayPlan;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.bean.dao.CheckPlanDao;
import com.hsy.flightpacket.bean.dao.DayPlanDao;
import com.hsy.flightpacket.bean.dao.FlightCheck;
import com.hsy.flightpacket.bean.dao.FlightCheckDao;
import com.hsy.flightpacket.bean.dao.PClass;
import com.hsy.flightpacket.bean.dao.PClassDao;
import com.hsy.flightpacket.bean.dao.PilotBean;
import com.hsy.flightpacket.bean.dao.PilotBeanDao;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.bean.dao.PilotRecordTableDao;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.PlanRecordDao;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.StudentDao;
import com.hsy.flightpacket.bean.dao.TeachActivity;
import com.hsy.flightpacket.bean.dao.TeachActivityChild;
import com.hsy.flightpacket.bean.dao.TeachActivityChildDao;
import com.hsy.flightpacket.bean.dao.TeachActivityDao;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.bean.dao.TrainPlanDao;

import java.util.Date;
import java.util.List;

/**
 * Created by xiongweimin on 2018/7/4.
 */

public class DataBaseUtils {

    public static TrainPlanDao trainPlanDao = FlightApplication.getInstance().getDaoSession().getTrainPlanDao();


    /**
     * 插入一条计划信息
     *
     * @param trainPlan
     * @return
     */


    public static long insertTrainPlan(TrainPlan trainPlan) {
        return trainPlanDao.insert(trainPlan);
    }


    /**
     * 删除某条用户信息
     *
     * @param id
     */


    public static void deleteTrainPlan(long id) {
        trainPlanDao.deleteByKey(id);
    }


    /**
     * 修改某条计划信息
     *
     * @param trainPlan
     */


    public static void correctTrainPlan(TrainPlan trainPlan) {
        trainPlanDao.update(trainPlan);
    }

    /**
     * 根据trainId获取学生列表
     *
     * @param id
     * @return
     */
    public static TrainPlan loadTrainPlan(Long id) {
        return trainPlanDao.load(id);
    }

    /**
     * 查询所有训练计划
     *
     * @return
     */


    public static List<TrainPlan> searchTrainPlan() {
        List<TrainPlan> list = trainPlanDao.queryBuilder().listLazy();
        return list;
    }

    /**
     * 通过classid 获取训练列表
     *
     * @param classId
     * @return
     */
    public static List<TrainPlan> searchTrainPlanByClassId(Long classId) {
        List<TrainPlan> list = trainPlanDao.queryBuilder().where(TrainPlanDao.Properties.ClassId.eq(classId)).list();
        return list;
    }

    /**
     * 搜索训练列表通过时间
     *
     * @param date
     * @return
     */
    public static List<TrainPlan> searchTrainPlanByDate(Date date, Long classId) {
        List<TrainPlan> list = trainPlanDao.queryBuilder().where(TrainPlanDao.Properties.TrainTime.eq(date)).where(TrainPlanDao.Properties.ClassId.eq(classId)).list();
        return list;
    }

    /**
     * 通过Id 查询单条信息
     *
     * @param key
     * @return
     */
    public static TrainPlan searchOneTrainPlan(Long key) {
        TrainPlan trainPlan = trainPlanDao.load(key);
        return trainPlan;
    }


    //-------------------------------------------------------------------------------------------------------------------------
    public static StudentDao studentDao = FlightApplication.getInstance().getDaoSession().getStudentDao();

    /**
     * 插入一条计划信息
     *
     * @param student
     * @return
     */


    public static long insertStudent(Student student) {
        return studentDao.insert(student);
    }


    /**
     * 通过学生id搜索学生信息
     *
     * @param key
     * @return
     */
    public static Student searchStudent(Long key) {
        Student student = studentDao.load(key);
        return student;
    }


    /**
     * 删除某条用户信息
     *
     * @param id
     */


    public static void deleteStudent(long id) {
        studentDao.deleteByKey(id);
    }


    /**
     * 修改某条计划信息
     *
     * @param student
     */


    public static void correctstudentDao(Student student) {
        studentDao.update(student);
    }


    /**
     * 查询所有训练计划
     *
     * @return
     */


    public static List<Student> searchStudent() {
        List<Student> list = studentDao.queryBuilder().listLazy();
        return list;
    }

    /* *//**
     * 根据trainId获取学生列表
     *
     * @param trainId
     * @return
     *//*
    public static List<Student> searchStudentById(Long trainId) {
        List<Student> list = studentDao.queryBuilder().where(StudentDao.Properties.TrainPlanId.eq(trainId)).list();
        return list;
    }
*/

    /**
     * 批量插入或修改用户信息
     *
     * @param list 用户信息列表
     */
    public static void saveStudents(final List<Student> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        //studentDao.insertInTx(list);
        studentDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Student user = list.get(i);
                    studentDao.insertOrReplace(user);
                }
            }
        });
    }

    //-------------------------------------------------------------------------------------------------------------------------
    public static PlanRecordDao planRecordDao = FlightApplication.getInstance().getDaoSession().getPlanRecordDao();

    /**
     * 插入一条计划信息
     *
     * @param planRecord
     * @return
     */


    public static long insertPlanRecord(PlanRecord planRecord) {
        return planRecordDao.insert(planRecord);
    }


    /**
     * 删除某条用户信息
     *
     * @param id
     */


    public static void deletePlanRecord(long id) {
        planRecordDao.deleteByKey(id);
    }


    /**
     * 修改某条计划信息
     *
     * @param planRecord
     */


    public static void correctPlanRecord(PlanRecord planRecord) {
        planRecordDao.update(planRecord);
    }


    /**
     * 查询所有训练计划
     *
     * @return
     */


    public static List<PlanRecord> searchPlanRecord() {
        List<PlanRecord> list = planRecordDao.queryBuilder().listLazy();
        return list;
    }

    /**
     * @param planId
     * @return
     */
    public static List<PlanRecord> searchPlanRecord(Long planId, Long studentId) {
        List<PlanRecord> list = planRecordDao.queryBuilder().where(PlanRecordDao.Properties.TrainPlanId.eq(planId)).where(PlanRecordDao.Properties.StudentId.eq(studentId)).list();
        return list;
    }

    /**
     * @param trainPlanId
     * @return
     */
    public static List<PlanRecord> searchPlanRecordByTrainId(Long trainPlanId) {
        List<PlanRecord> list = planRecordDao.queryBuilder().where(PlanRecordDao.Properties.TrainPlanId.eq(trainPlanId)).list();
        return list;
    }

    /**
     * 通过学生id查询他的训练记录
     *
     * @param studentId
     * @return
     */
    public static List<PlanRecord> searchPlanRecord(Long studentId) {
        List<PlanRecord> list = planRecordDao.queryBuilder().where(PlanRecordDao.Properties.StudentId.eq(studentId)).list();
        return list;
    }

    /**
     * 通过日期和id查询
     *
     * @param studentId
     * @return
     */
    public static List<PlanRecord> searchPlanRecordByDate(Long studentId, Date date) {
        List<PlanRecord> list = planRecordDao.queryBuilder().where(PlanRecordDao.Properties.StudentId.eq(studentId)).where(PlanRecordDao.Properties.PlanTime.eq(date)).list();
        return list;
    }

    /**
     * 批量插入或修改用户信息
     *
     * @param list 用户信息列表
     */
    public static void savePlanRecords(final List<PlanRecord> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        planRecordDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    PlanRecord user = list.get(i);
                    planRecordDao.insertOrReplace(user);
                }
            }
        });
    }

    //-------------------------------------------------------------------------------------------------------------------------
    public static FlightCheckDao flightCheckDao = FlightApplication.getInstance().getDaoSession().getFlightCheckDao();

    /**
     * 插入一条计划信息
     *
     * @param flightCheck
     * @return
     */


    public static long insertFlightCheck(FlightCheck flightCheck) {
        return flightCheckDao.insert(flightCheck);
    }


    public static void deleteFlightCheck(long id) {
        flightCheckDao.deleteByKey(id);
    }


    public static FlightCheck loadFlightCheckById(long id) {
        return flightCheckDao.load(id);
    }


    public static void correctFlightCheck(FlightCheck flightCheck) {
        flightCheckDao.update(flightCheck);
    }


    public static List<FlightCheck> searchFlightCheck() {
        List<FlightCheck> list = flightCheckDao.queryBuilder().listLazy();
        return list;
    }

    public static List<FlightCheck> searchFlightCheckById(long checkPlanId) {
        List<FlightCheck> list = flightCheckDao.queryBuilder().where(FlightCheckDao.Properties.CheckPlanId.eq(checkPlanId)).list();
        return list;
    }


    /**
     * 批量插入或修改飞行检查数据
     *
     * @param list 用户信息列表
     */
    public static void saveFlightCheck(final List<FlightCheck> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        flightCheckDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    FlightCheck user = list.get(i);
                    flightCheckDao.insertOrReplaceInTx(user);
                }
            }
        });
    }

    //-------------------------------------------------------------------------------------------------------------------------
    public static CheckPlanDao checkPlanDao = FlightApplication.getInstance().getDaoSession().getCheckPlanDao();

    //插入一条
    public static long insertCheckPlanDao(CheckPlan checkPlan) {
        return checkPlanDao.insert(checkPlan);
    }

    //删除一条
    public static void deleteCheckPlanDao(long planId) {
        checkPlanDao.deleteByKey(planId);
    }

    //更新一条信息
    public static void correctCheckPlan(CheckPlan checkPlan) {
        checkPlanDao.update(checkPlan);
    }

    //加载一条信息
    public static CheckPlan loadCheckPlan(Long id) {
        return checkPlanDao.load(id);
    }

    //通过状态查询
    public static List<CheckPlan> searchPlanRecordByStatus(Integer status) {
        List<CheckPlan> list = checkPlanDao.queryBuilder().where(CheckPlanDao.Properties.Status.eq(status)).list();
        return list;
    }

    //------------------------------------领航记录-------------------------------------------------------------------------------------
    public static PilotRecordTableDao recordTableDao = FlightApplication.getInstance().getDaoSession().getPilotRecordTableDao();

    //插入一条
    public static long insertPilotRecord(PilotRecordTable pilotRecordTable) {
        return recordTableDao.insert(pilotRecordTable);
    }

    //删除一条
    public static void deletePilotRecord(long planId) {
        recordTableDao.deleteByKey(planId);
    }

    //更新一条信息
    public static void correctPilotRecord(PilotRecordTable pilotRecordTable) {
        recordTableDao.update(pilotRecordTable);
    }

    //加载一条信息
    public static PilotRecordTable loadPilotRecord(Long id) {
        return recordTableDao.load(id);
    }

    //领航记录
    public static List<PilotRecordTable> searchPilotRecord() {
        List<PilotRecordTable> list = recordTableDao.queryBuilder().listLazy();
        return list;
    }

    public static List<PilotRecordTable> searchPilotRecordTableByIdLeft(long pilotBeanId, boolean isLeft) {
        List<PilotRecordTable> list = recordTableDao.queryBuilder().where(PilotRecordTableDao.Properties.PilotBeanId.eq(pilotBeanId)).where(PilotRecordTableDao.Properties.Left.eq(isLeft)).list();
        return list;
    }

    /**
     * 批量插入领航记录
     */
    public static void savePilotRecord(final List<PilotRecordTable> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        recordTableDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    PilotRecordTable recordTable = list.get(i);
                    recordTableDao.insertOrReplace(recordTable);
                }
            }
        });
    }

    //------------------------------------查询领航记录表信息----------------------------------------------------------
    public static PilotBeanDao pilotBeanDao = FlightApplication.getInstance().getDaoSession().getPilotBeanDao();

    //获取领航记录表信息
    public static List<PilotBean> searchPilotBean() {
        List<PilotBean> list = pilotBeanDao.queryBuilder().listLazy();
        return list;
    }

    //插入一条
    public static long insertPilotBean(PilotBean PilotBean) {
        return pilotBeanDao.insert(PilotBean);
    }

    /**
     * 更新
     *
     * @param PilotBean
     */
    public static void updatePilotBean(PilotBean PilotBean) {
        pilotBeanDao.update(PilotBean);
    }

    //加载一条信息
    public static PilotBean loadPilotBean(Long id) {
        return pilotBeanDao.load(id);
    }

    //------------------------------------班级创建-----------------------------------------------------------------------------
    public static PClassDao pClassDao = FlightApplication.getInstance().getDaoSession().getPClassDao();

    //获取class信息
    public static List<PClass> searchPClass() {
        List<PClass> list = pClassDao.queryBuilder().listLazy();
        return list;
    }

    /**
     * 批量保存班级数据
     *
     * @param pClasses
     */
    public static void savePClass(final List<PClass> pClasses) {
        if (pClasses == null || pClasses.isEmpty()) {
            return;
        }
        pClassDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < pClasses.size(); i++) {
                    PClass recordTable = pClasses.get(i);
                    pClassDao.insertOrReplace(recordTable);
                }
            }
        });
    }

    //------------------------------------创建活动计划-----------------------------------------------------

    public static TeachActivityDao teachActivityDao = FlightApplication.getInstance().getDaoSession().getTeachActivityDao();


    //插入一条
    public static long insertTeachActivity(TeachActivity teachActivity) {
        return teachActivityDao.insert(teachActivity);
    }

    //查询多条
    public static List<TeachActivity> searchTeachActivityById(Long planId) {
        List<TeachActivity> list = teachActivityDao.queryBuilder().where(TeachActivityDao.Properties.PlanId.eq(planId)).list();
        return list;
    }
    //------------------------------------创建活动计划子项-----------------------------------------------------

    public static TeachActivityChildDao teachActivityChildDao = FlightApplication.getInstance().getDaoSession().getTeachActivityChildDao();

    //插入一条
    public static long insertTeachActivityChildDao(TeachActivityChild teachActivityChild) {
        return teachActivityChildDao.insert(teachActivityChild);
    }

    //查询多条
    public static List<TeachActivityChild> searchTeachActivityChildById(Long teachactivityid) {
        List<TeachActivityChild> list = teachActivityChildDao.queryBuilder().where(TeachActivityChildDao.Properties.Teachactivityid.eq(teachactivityid)).list();
        return list;
    }

    //------------------------------------创建活动日计划项-----------------------------------------------------
    public static DayPlanDao dayPlanDao = FlightApplication.getInstance().getDaoSession().getDayPlanDao();

    //插入一条
    public static long insertDayPlanDao(DayPlan dayPlan) {
        return dayPlanDao.insert(dayPlan);
    }

    //查询一条
    public static DayPlan searchDayPlanDaoTrainId(Long trainId) {
        List<DayPlan> list = dayPlanDao.queryBuilder().where(DayPlanDao.Properties.TrainPlanId.eq(trainId)).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
