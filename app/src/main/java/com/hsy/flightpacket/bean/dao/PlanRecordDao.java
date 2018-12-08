package com.hsy.flightpacket.bean.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PLAN_RECORD".
*/
public class PlanRecordDao extends AbstractDao<PlanRecord, Long> {

    public static final String TABLENAME = "PLAN_RECORD";

    /**
     * Properties of entity PlanRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DaifeiNum = new Property(1, Integer.class, "daifeiNum", false, "DAIFEI_NUM");
        public final static Property DaifeiTime = new Property(2, Integer.class, "daifeiTime", false, "DAIFEI_TIME");
        public final static Property CheckNum = new Property(3, Integer.class, "checkNum", false, "CHECK_NUM");
        public final static Property CheckTime = new Property(4, Integer.class, "checkTime", false, "CHECK_TIME");
        public final static Property DanfeiNum = new Property(5, Integer.class, "danfeiNum", false, "DANFEI_NUM");
        public final static Property DanfeiTimel = new Property(6, Integer.class, "danfeiTimel", false, "DANFEI_TIMEL");
        public final static Property PlanTime = new Property(7, java.util.Date.class, "planTime", false, "PLAN_TIME");
        public final static Property TrainPlanId = new Property(8, Long.class, "trainPlanId", false, "TRAIN_PLAN_ID");
        public final static Property StudentId = new Property(9, Long.class, "studentId", false, "STUDENT_ID");
        public final static Property IsFinished = new Property(10, boolean.class, "isFinished", false, "IS_FINISHED");
    };


    public PlanRecordDao(DaoConfig config) {
        super(config);
    }
    
    public PlanRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLAN_RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DAIFEI_NUM\" INTEGER," + // 1: daifeiNum
                "\"DAIFEI_TIME\" INTEGER," + // 2: daifeiTime
                "\"CHECK_NUM\" INTEGER," + // 3: checkNum
                "\"CHECK_TIME\" INTEGER," + // 4: checkTime
                "\"DANFEI_NUM\" INTEGER," + // 5: danfeiNum
                "\"DANFEI_TIMEL\" INTEGER," + // 6: danfeiTimel
                "\"PLAN_TIME\" INTEGER," + // 7: planTime
                "\"TRAIN_PLAN_ID\" INTEGER," + // 8: trainPlanId
                "\"STUDENT_ID\" INTEGER," + // 9: studentId
                "\"IS_FINISHED\" INTEGER NOT NULL );"); // 10: isFinished
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAN_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PlanRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer daifeiNum = entity.getDaifeiNum();
        if (daifeiNum != null) {
            stmt.bindLong(2, daifeiNum);
        }
 
        Integer daifeiTime = entity.getDaifeiTime();
        if (daifeiTime != null) {
            stmt.bindLong(3, daifeiTime);
        }
 
        Integer checkNum = entity.getCheckNum();
        if (checkNum != null) {
            stmt.bindLong(4, checkNum);
        }
 
        Integer checkTime = entity.getCheckTime();
        if (checkTime != null) {
            stmt.bindLong(5, checkTime);
        }
 
        Integer danfeiNum = entity.getDanfeiNum();
        if (danfeiNum != null) {
            stmt.bindLong(6, danfeiNum);
        }
 
        Integer danfeiTimel = entity.getDanfeiTimel();
        if (danfeiTimel != null) {
            stmt.bindLong(7, danfeiTimel);
        }
 
        java.util.Date planTime = entity.getPlanTime();
        if (planTime != null) {
            stmt.bindLong(8, planTime.getTime());
        }
 
        Long trainPlanId = entity.getTrainPlanId();
        if (trainPlanId != null) {
            stmt.bindLong(9, trainPlanId);
        }
 
        Long studentId = entity.getStudentId();
        if (studentId != null) {
            stmt.bindLong(10, studentId);
        }
        stmt.bindLong(11, entity.getIsFinished() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PlanRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer daifeiNum = entity.getDaifeiNum();
        if (daifeiNum != null) {
            stmt.bindLong(2, daifeiNum);
        }
 
        Integer daifeiTime = entity.getDaifeiTime();
        if (daifeiTime != null) {
            stmt.bindLong(3, daifeiTime);
        }
 
        Integer checkNum = entity.getCheckNum();
        if (checkNum != null) {
            stmt.bindLong(4, checkNum);
        }
 
        Integer checkTime = entity.getCheckTime();
        if (checkTime != null) {
            stmt.bindLong(5, checkTime);
        }
 
        Integer danfeiNum = entity.getDanfeiNum();
        if (danfeiNum != null) {
            stmt.bindLong(6, danfeiNum);
        }
 
        Integer danfeiTimel = entity.getDanfeiTimel();
        if (danfeiTimel != null) {
            stmt.bindLong(7, danfeiTimel);
        }
 
        java.util.Date planTime = entity.getPlanTime();
        if (planTime != null) {
            stmt.bindLong(8, planTime.getTime());
        }
 
        Long trainPlanId = entity.getTrainPlanId();
        if (trainPlanId != null) {
            stmt.bindLong(9, trainPlanId);
        }
 
        Long studentId = entity.getStudentId();
        if (studentId != null) {
            stmt.bindLong(10, studentId);
        }
        stmt.bindLong(11, entity.getIsFinished() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PlanRecord readEntity(Cursor cursor, int offset) {
        PlanRecord entity = new PlanRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // daifeiNum
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // daifeiTime
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // checkNum
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // checkTime
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // danfeiNum
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // danfeiTimel
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // planTime
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // trainPlanId
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // studentId
            cursor.getShort(offset + 10) != 0 // isFinished
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PlanRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDaifeiNum(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDaifeiTime(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setCheckNum(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setCheckTime(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setDanfeiNum(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setDanfeiTimel(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setPlanTime(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setTrainPlanId(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setStudentId(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setIsFinished(cursor.getShort(offset + 10) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PlanRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PlanRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}