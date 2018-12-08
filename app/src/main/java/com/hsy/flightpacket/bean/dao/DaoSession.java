package com.hsy.flightpacket.bean.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hsy.flightpacket.bean.dao.BindTrainPlanStudent;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.bean.dao.FlightCheck;
import com.hsy.flightpacket.bean.dao.PClass;
import com.hsy.flightpacket.bean.dao.PilotBean;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.TeachActivity;
import com.hsy.flightpacket.bean.dao.TeachActivityChild;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.bean.DayPlan;

import com.hsy.flightpacket.bean.dao.BindTrainPlanStudentDao;
import com.hsy.flightpacket.bean.dao.CheckPlanDao;
import com.hsy.flightpacket.bean.dao.FlightCheckDao;
import com.hsy.flightpacket.bean.dao.PClassDao;
import com.hsy.flightpacket.bean.dao.PilotBeanDao;
import com.hsy.flightpacket.bean.dao.PilotRecordTableDao;
import com.hsy.flightpacket.bean.dao.PlanRecordDao;
import com.hsy.flightpacket.bean.dao.StudentDao;
import com.hsy.flightpacket.bean.dao.TeachActivityDao;
import com.hsy.flightpacket.bean.dao.TeachActivityChildDao;
import com.hsy.flightpacket.bean.dao.TrainPlanDao;
import com.hsy.flightpacket.bean.dao.DayPlanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bindTrainPlanStudentDaoConfig;
    private final DaoConfig checkPlanDaoConfig;
    private final DaoConfig flightCheckDaoConfig;
    private final DaoConfig pClassDaoConfig;
    private final DaoConfig pilotBeanDaoConfig;
    private final DaoConfig pilotRecordTableDaoConfig;
    private final DaoConfig planRecordDaoConfig;
    private final DaoConfig studentDaoConfig;
    private final DaoConfig teachActivityDaoConfig;
    private final DaoConfig teachActivityChildDaoConfig;
    private final DaoConfig trainPlanDaoConfig;
    private final DaoConfig dayPlanDaoConfig;

    private final BindTrainPlanStudentDao bindTrainPlanStudentDao;
    private final CheckPlanDao checkPlanDao;
    private final FlightCheckDao flightCheckDao;
    private final PClassDao pClassDao;
    private final PilotBeanDao pilotBeanDao;
    private final PilotRecordTableDao pilotRecordTableDao;
    private final PlanRecordDao planRecordDao;
    private final StudentDao studentDao;
    private final TeachActivityDao teachActivityDao;
    private final TeachActivityChildDao teachActivityChildDao;
    private final TrainPlanDao trainPlanDao;
    private final DayPlanDao dayPlanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bindTrainPlanStudentDaoConfig = daoConfigMap.get(BindTrainPlanStudentDao.class).clone();
        bindTrainPlanStudentDaoConfig.initIdentityScope(type);

        checkPlanDaoConfig = daoConfigMap.get(CheckPlanDao.class).clone();
        checkPlanDaoConfig.initIdentityScope(type);

        flightCheckDaoConfig = daoConfigMap.get(FlightCheckDao.class).clone();
        flightCheckDaoConfig.initIdentityScope(type);

        pClassDaoConfig = daoConfigMap.get(PClassDao.class).clone();
        pClassDaoConfig.initIdentityScope(type);

        pilotBeanDaoConfig = daoConfigMap.get(PilotBeanDao.class).clone();
        pilotBeanDaoConfig.initIdentityScope(type);

        pilotRecordTableDaoConfig = daoConfigMap.get(PilotRecordTableDao.class).clone();
        pilotRecordTableDaoConfig.initIdentityScope(type);

        planRecordDaoConfig = daoConfigMap.get(PlanRecordDao.class).clone();
        planRecordDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        teachActivityDaoConfig = daoConfigMap.get(TeachActivityDao.class).clone();
        teachActivityDaoConfig.initIdentityScope(type);

        teachActivityChildDaoConfig = daoConfigMap.get(TeachActivityChildDao.class).clone();
        teachActivityChildDaoConfig.initIdentityScope(type);

        trainPlanDaoConfig = daoConfigMap.get(TrainPlanDao.class).clone();
        trainPlanDaoConfig.initIdentityScope(type);

        dayPlanDaoConfig = daoConfigMap.get(DayPlanDao.class).clone();
        dayPlanDaoConfig.initIdentityScope(type);

        bindTrainPlanStudentDao = new BindTrainPlanStudentDao(bindTrainPlanStudentDaoConfig, this);
        checkPlanDao = new CheckPlanDao(checkPlanDaoConfig, this);
        flightCheckDao = new FlightCheckDao(flightCheckDaoConfig, this);
        pClassDao = new PClassDao(pClassDaoConfig, this);
        pilotBeanDao = new PilotBeanDao(pilotBeanDaoConfig, this);
        pilotRecordTableDao = new PilotRecordTableDao(pilotRecordTableDaoConfig, this);
        planRecordDao = new PlanRecordDao(planRecordDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);
        teachActivityDao = new TeachActivityDao(teachActivityDaoConfig, this);
        teachActivityChildDao = new TeachActivityChildDao(teachActivityChildDaoConfig, this);
        trainPlanDao = new TrainPlanDao(trainPlanDaoConfig, this);
        dayPlanDao = new DayPlanDao(dayPlanDaoConfig, this);

        registerDao(BindTrainPlanStudent.class, bindTrainPlanStudentDao);
        registerDao(CheckPlan.class, checkPlanDao);
        registerDao(FlightCheck.class, flightCheckDao);
        registerDao(PClass.class, pClassDao);
        registerDao(PilotBean.class, pilotBeanDao);
        registerDao(PilotRecordTable.class, pilotRecordTableDao);
        registerDao(PlanRecord.class, planRecordDao);
        registerDao(Student.class, studentDao);
        registerDao(TeachActivity.class, teachActivityDao);
        registerDao(TeachActivityChild.class, teachActivityChildDao);
        registerDao(TrainPlan.class, trainPlanDao);
        registerDao(DayPlan.class, dayPlanDao);
    }
    
    public void clear() {
        bindTrainPlanStudentDaoConfig.getIdentityScope().clear();
        checkPlanDaoConfig.getIdentityScope().clear();
        flightCheckDaoConfig.getIdentityScope().clear();
        pClassDaoConfig.getIdentityScope().clear();
        pilotBeanDaoConfig.getIdentityScope().clear();
        pilotRecordTableDaoConfig.getIdentityScope().clear();
        planRecordDaoConfig.getIdentityScope().clear();
        studentDaoConfig.getIdentityScope().clear();
        teachActivityDaoConfig.getIdentityScope().clear();
        teachActivityChildDaoConfig.getIdentityScope().clear();
        trainPlanDaoConfig.getIdentityScope().clear();
        dayPlanDaoConfig.getIdentityScope().clear();
    }

    public BindTrainPlanStudentDao getBindTrainPlanStudentDao() {
        return bindTrainPlanStudentDao;
    }

    public CheckPlanDao getCheckPlanDao() {
        return checkPlanDao;
    }

    public FlightCheckDao getFlightCheckDao() {
        return flightCheckDao;
    }

    public PClassDao getPClassDao() {
        return pClassDao;
    }

    public PilotBeanDao getPilotBeanDao() {
        return pilotBeanDao;
    }

    public PilotRecordTableDao getPilotRecordTableDao() {
        return pilotRecordTableDao;
    }

    public PlanRecordDao getPlanRecordDao() {
        return planRecordDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public TeachActivityDao getTeachActivityDao() {
        return teachActivityDao;
    }

    public TeachActivityChildDao getTeachActivityChildDao() {
        return teachActivityChildDao;
    }

    public TrainPlanDao getTrainPlanDao() {
        return trainPlanDao;
    }

    public DayPlanDao getDayPlanDao() {
        return dayPlanDao;
    }

}