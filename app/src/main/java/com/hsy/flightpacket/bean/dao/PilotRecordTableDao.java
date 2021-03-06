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
 * DAO for table "PILOT_RECORD_TABLE".
*/
public class PilotRecordTableDao extends AbstractDao<PilotRecordTable, Long> {

    public static final String TABLENAME = "PILOT_RECORD_TABLE";

    /**
     * Properties of entity PilotRecordTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property YuqiTime = new Property(2, java.util.Date.class, "yuqiTime", false, "YUQI_TIME");
        public final static Property ShijiTime = new Property(3, java.util.Date.class, "shijiTime", false, "SHIJI_TIME");
        public final static Property Cihangxiang = new Property(4, Integer.class, "cihangxiang", false, "CIHANGXIANG");
        public final static Property Pianliu = new Property(5, Integer.class, "pianliu", false, "PIANLIU");
        public final static Property Cihangguiji = new Property(6, Integer.class, "cihangguiji", false, "CIHANGGUIJI");
        public final static Property Disu = new Property(7, Integer.class, "disu", false, "DISU");
        public final static Property Kongsu = new Property(8, Integer.class, "kongsu", false, "KONGSU");
        public final static Property Gaodu = new Property(9, Integer.class, "gaodu", false, "GAODU");
        public final static Property Fuji = new Property(10, String.class, "fuji", false, "FUJI");
        public final static Property PilotBeanId = new Property(11, Long.class, "pilotBeanId", false, "PILOT_BEAN_ID");
        public final static Property Left = new Property(12, Boolean.class, "left", false, "LEFT");
        public final static Property DidianDaihao = new Property(13, String.class, "didianDaihao", false, "DIDIAN_DAIHAO");
        public final static Property Jingdu = new Property(14, String.class, "jingdu", false, "JINGDU");
        public final static Property Weidu = new Property(15, String.class, "weidu", false, "WEIDU");
        public final static Property Juli = new Property(16, Integer.class, "juli", false, "JULI");
    };


    public PilotRecordTableDao(DaoConfig config) {
        super(config);
    }
    
    public PilotRecordTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PILOT_RECORD_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"YUQI_TIME\" INTEGER," + // 2: yuqiTime
                "\"SHIJI_TIME\" INTEGER," + // 3: shijiTime
                "\"CIHANGXIANG\" INTEGER," + // 4: cihangxiang
                "\"PIANLIU\" INTEGER," + // 5: pianliu
                "\"CIHANGGUIJI\" INTEGER," + // 6: cihangguiji
                "\"DISU\" INTEGER," + // 7: disu
                "\"KONGSU\" INTEGER," + // 8: kongsu
                "\"GAODU\" INTEGER," + // 9: gaodu
                "\"FUJI\" TEXT," + // 10: fuji
                "\"PILOT_BEAN_ID\" INTEGER," + // 11: pilotBeanId
                "\"LEFT\" INTEGER," + // 12: left
                "\"DIDIAN_DAIHAO\" TEXT," + // 13: didianDaihao
                "\"JINGDU\" TEXT," + // 14: jingdu
                "\"WEIDU\" TEXT," + // 15: weidu
                "\"JULI\" INTEGER);"); // 16: juli
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PILOT_RECORD_TABLE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PilotRecordTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        java.util.Date yuqiTime = entity.getYuqiTime();
        if (yuqiTime != null) {
            stmt.bindLong(3, yuqiTime.getTime());
        }
 
        java.util.Date shijiTime = entity.getShijiTime();
        if (shijiTime != null) {
            stmt.bindLong(4, shijiTime.getTime());
        }
 
        Integer cihangxiang = entity.getCihangxiang();
        if (cihangxiang != null) {
            stmt.bindLong(5, cihangxiang);
        }
 
        Integer pianliu = entity.getPianliu();
        if (pianliu != null) {
            stmt.bindLong(6, pianliu);
        }
 
        Integer cihangguiji = entity.getCihangguiji();
        if (cihangguiji != null) {
            stmt.bindLong(7, cihangguiji);
        }
 
        Integer disu = entity.getDisu();
        if (disu != null) {
            stmt.bindLong(8, disu);
        }
 
        Integer kongsu = entity.getKongsu();
        if (kongsu != null) {
            stmt.bindLong(9, kongsu);
        }
 
        Integer gaodu = entity.getGaodu();
        if (gaodu != null) {
            stmt.bindLong(10, gaodu);
        }
 
        String fuji = entity.getFuji();
        if (fuji != null) {
            stmt.bindString(11, fuji);
        }
 
        Long pilotBeanId = entity.getPilotBeanId();
        if (pilotBeanId != null) {
            stmt.bindLong(12, pilotBeanId);
        }
 
        Boolean left = entity.getLeft();
        if (left != null) {
            stmt.bindLong(13, left ? 1L: 0L);
        }
 
        String didianDaihao = entity.getDidianDaihao();
        if (didianDaihao != null) {
            stmt.bindString(14, didianDaihao);
        }
 
        String jingdu = entity.getJingdu();
        if (jingdu != null) {
            stmt.bindString(15, jingdu);
        }
 
        String weidu = entity.getWeidu();
        if (weidu != null) {
            stmt.bindString(16, weidu);
        }
 
        Integer juli = entity.getJuli();
        if (juli != null) {
            stmt.bindLong(17, juli);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PilotRecordTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        java.util.Date yuqiTime = entity.getYuqiTime();
        if (yuqiTime != null) {
            stmt.bindLong(3, yuqiTime.getTime());
        }
 
        java.util.Date shijiTime = entity.getShijiTime();
        if (shijiTime != null) {
            stmt.bindLong(4, shijiTime.getTime());
        }
 
        Integer cihangxiang = entity.getCihangxiang();
        if (cihangxiang != null) {
            stmt.bindLong(5, cihangxiang);
        }
 
        Integer pianliu = entity.getPianliu();
        if (pianliu != null) {
            stmt.bindLong(6, pianliu);
        }
 
        Integer cihangguiji = entity.getCihangguiji();
        if (cihangguiji != null) {
            stmt.bindLong(7, cihangguiji);
        }
 
        Integer disu = entity.getDisu();
        if (disu != null) {
            stmt.bindLong(8, disu);
        }
 
        Integer kongsu = entity.getKongsu();
        if (kongsu != null) {
            stmt.bindLong(9, kongsu);
        }
 
        Integer gaodu = entity.getGaodu();
        if (gaodu != null) {
            stmt.bindLong(10, gaodu);
        }
 
        String fuji = entity.getFuji();
        if (fuji != null) {
            stmt.bindString(11, fuji);
        }
 
        Long pilotBeanId = entity.getPilotBeanId();
        if (pilotBeanId != null) {
            stmt.bindLong(12, pilotBeanId);
        }
 
        Boolean left = entity.getLeft();
        if (left != null) {
            stmt.bindLong(13, left ? 1L: 0L);
        }
 
        String didianDaihao = entity.getDidianDaihao();
        if (didianDaihao != null) {
            stmt.bindString(14, didianDaihao);
        }
 
        String jingdu = entity.getJingdu();
        if (jingdu != null) {
            stmt.bindString(15, jingdu);
        }
 
        String weidu = entity.getWeidu();
        if (weidu != null) {
            stmt.bindString(16, weidu);
        }
 
        Integer juli = entity.getJuli();
        if (juli != null) {
            stmt.bindLong(17, juli);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PilotRecordTable readEntity(Cursor cursor, int offset) {
        PilotRecordTable entity = new PilotRecordTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // yuqiTime
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // shijiTime
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // cihangxiang
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // pianliu
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // cihangguiji
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // disu
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // kongsu
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // gaodu
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // fuji
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11), // pilotBeanId
            cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12) != 0, // left
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // didianDaihao
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // jingdu
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // weidu
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16) // juli
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PilotRecordTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setYuqiTime(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setShijiTime(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setCihangxiang(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setPianliu(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setCihangguiji(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setDisu(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setKongsu(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setGaodu(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setFuji(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPilotBeanId(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.setLeft(cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12) != 0);
        entity.setDidianDaihao(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setJingdu(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setWeidu(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setJuli(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PilotRecordTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PilotRecordTable entity) {
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
