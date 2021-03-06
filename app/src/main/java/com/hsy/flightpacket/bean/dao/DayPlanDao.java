package com.hsy.flightpacket.bean.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hsy.flightpacket.bean.DayPlan;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DAY_PLAN".
*/
public class DayPlanDao extends AbstractDao<DayPlan, Long> {

    public static final String TABLENAME = "DAY_PLAN";

    /**
     * Properties of entity DayPlan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Jihao = new Property(1, String.class, "jihao", false, "JIHAO");
        public final static Property Jizhang = new Property(2, String.class, "jizhang", false, "JIZHANG");
        public final static Property Zhengjia = new Property(3, String.class, "zhengjia", false, "ZHENGJIA");
        public final static Property Fujia = new Property(4, String.class, "fujia", false, "FUJIA");
        public final static Property Lianxihao = new Property(5, String.class, "lianxihao", false, "LIANXIHAO");
        public final static Property Cishu = new Property(6, String.class, "cishu", false, "CISHU");
        public final static Property Zhengjia1 = new Property(7, String.class, "zhengjia1", false, "ZHENGJIA1");
        public final static Property Fujia1 = new Property(8, String.class, "fujia1", false, "FUJIA1");
        public final static Property Gaodu = new Property(9, String.class, "gaodu", false, "GAODU");
        public final static Property Hangxianhao = new Property(10, String.class, "hangxianhao", false, "HANGXIANHAO");
        public final static Property IsJiayou = new Property(11, boolean.class, "isJiayou", false, "IS_JIAYOU");
        public final static Property TrainPlanId = new Property(12, Long.class, "trainPlanId", false, "TRAIN_PLAN_ID");
    };


    public DayPlanDao(DaoConfig config) {
        super(config);
    }
    
    public DayPlanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DAY_PLAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"JIHAO\" TEXT," + // 1: jihao
                "\"JIZHANG\" TEXT," + // 2: jizhang
                "\"ZHENGJIA\" TEXT," + // 3: zhengjia
                "\"FUJIA\" TEXT," + // 4: fujia
                "\"LIANXIHAO\" TEXT," + // 5: lianxihao
                "\"CISHU\" TEXT," + // 6: cishu
                "\"ZHENGJIA1\" TEXT," + // 7: zhengjia1
                "\"FUJIA1\" TEXT," + // 8: fujia1
                "\"GAODU\" TEXT," + // 9: gaodu
                "\"HANGXIANHAO\" TEXT," + // 10: hangxianhao
                "\"IS_JIAYOU\" INTEGER NOT NULL ," + // 11: isJiayou
                "\"TRAIN_PLAN_ID\" INTEGER);"); // 12: trainPlanId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DAY_PLAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DayPlan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String jihao = entity.getJihao();
        if (jihao != null) {
            stmt.bindString(2, jihao);
        }
 
        String jizhang = entity.getJizhang();
        if (jizhang != null) {
            stmt.bindString(3, jizhang);
        }
 
        String zhengjia = entity.getZhengjia();
        if (zhengjia != null) {
            stmt.bindString(4, zhengjia);
        }
 
        String fujia = entity.getFujia();
        if (fujia != null) {
            stmt.bindString(5, fujia);
        }
 
        String lianxihao = entity.getLianxihao();
        if (lianxihao != null) {
            stmt.bindString(6, lianxihao);
        }
 
        String cishu = entity.getCishu();
        if (cishu != null) {
            stmt.bindString(7, cishu);
        }
 
        String zhengjia1 = entity.getZhengjia1();
        if (zhengjia1 != null) {
            stmt.bindString(8, zhengjia1);
        }
 
        String fujia1 = entity.getFujia1();
        if (fujia1 != null) {
            stmt.bindString(9, fujia1);
        }
 
        String gaodu = entity.getGaodu();
        if (gaodu != null) {
            stmt.bindString(10, gaodu);
        }
 
        String hangxianhao = entity.getHangxianhao();
        if (hangxianhao != null) {
            stmt.bindString(11, hangxianhao);
        }
        stmt.bindLong(12, entity.getIsJiayou() ? 1L: 0L);
 
        Long trainPlanId = entity.getTrainPlanId();
        if (trainPlanId != null) {
            stmt.bindLong(13, trainPlanId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DayPlan entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String jihao = entity.getJihao();
        if (jihao != null) {
            stmt.bindString(2, jihao);
        }
 
        String jizhang = entity.getJizhang();
        if (jizhang != null) {
            stmt.bindString(3, jizhang);
        }
 
        String zhengjia = entity.getZhengjia();
        if (zhengjia != null) {
            stmt.bindString(4, zhengjia);
        }
 
        String fujia = entity.getFujia();
        if (fujia != null) {
            stmt.bindString(5, fujia);
        }
 
        String lianxihao = entity.getLianxihao();
        if (lianxihao != null) {
            stmt.bindString(6, lianxihao);
        }
 
        String cishu = entity.getCishu();
        if (cishu != null) {
            stmt.bindString(7, cishu);
        }
 
        String zhengjia1 = entity.getZhengjia1();
        if (zhengjia1 != null) {
            stmt.bindString(8, zhengjia1);
        }
 
        String fujia1 = entity.getFujia1();
        if (fujia1 != null) {
            stmt.bindString(9, fujia1);
        }
 
        String gaodu = entity.getGaodu();
        if (gaodu != null) {
            stmt.bindString(10, gaodu);
        }
 
        String hangxianhao = entity.getHangxianhao();
        if (hangxianhao != null) {
            stmt.bindString(11, hangxianhao);
        }
        stmt.bindLong(12, entity.getIsJiayou() ? 1L: 0L);
 
        Long trainPlanId = entity.getTrainPlanId();
        if (trainPlanId != null) {
            stmt.bindLong(13, trainPlanId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DayPlan readEntity(Cursor cursor, int offset) {
        DayPlan entity = new DayPlan( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // jihao
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // jizhang
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // zhengjia
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // fujia
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // lianxihao
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // cishu
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // zhengjia1
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // fujia1
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // gaodu
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // hangxianhao
            cursor.getShort(offset + 11) != 0, // isJiayou
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12) // trainPlanId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DayPlan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setJihao(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setJizhang(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setZhengjia(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFujia(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLianxihao(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCishu(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setZhengjia1(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFujia1(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setGaodu(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setHangxianhao(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIsJiayou(cursor.getShort(offset + 11) != 0);
        entity.setTrainPlanId(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DayPlan entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DayPlan entity) {
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
