package com.hsy.flightpacket.bean.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FLIGHT_CHECK".
*/
public class FlightCheckDao extends AbstractDao<FlightCheck, Long> {

    public static final String TABLENAME = "FLIGHT_CHECK";

    /**
     * Properties of entity FlightCheck.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Des = new Property(2, String.class, "des", false, "DES");
        public final static Property PicUrl = new Property(3, String.class, "picUrl", false, "PIC_URL");
        public final static Property IsChecked = new Property(4, Boolean.class, "isChecked", false, "IS_CHECKED");
        public final static Property CheckPlanId = new Property(5, Long.class, "checkPlanId", false, "CHECK_PLAN_ID");
    };

    private Query<FlightCheck> checkPlan_FlightChecksQuery;

    public FlightCheckDao(DaoConfig config) {
        super(config);
    }
    
    public FlightCheckDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FLIGHT_CHECK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"DES\" TEXT," + // 2: des
                "\"PIC_URL\" TEXT," + // 3: picUrl
                "\"IS_CHECKED\" INTEGER," + // 4: isChecked
                "\"CHECK_PLAN_ID\" INTEGER);"); // 5: checkPlanId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FLIGHT_CHECK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FlightCheck entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String des = entity.getDes();
        if (des != null) {
            stmt.bindString(3, des);
        }
 
        String picUrl = entity.getPicUrl();
        if (picUrl != null) {
            stmt.bindString(4, picUrl);
        }
 
        Boolean isChecked = entity.getIsChecked();
        if (isChecked != null) {
            stmt.bindLong(5, isChecked ? 1L: 0L);
        }
 
        Long checkPlanId = entity.getCheckPlanId();
        if (checkPlanId != null) {
            stmt.bindLong(6, checkPlanId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FlightCheck entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String des = entity.getDes();
        if (des != null) {
            stmt.bindString(3, des);
        }
 
        String picUrl = entity.getPicUrl();
        if (picUrl != null) {
            stmt.bindString(4, picUrl);
        }
 
        Boolean isChecked = entity.getIsChecked();
        if (isChecked != null) {
            stmt.bindLong(5, isChecked ? 1L: 0L);
        }
 
        Long checkPlanId = entity.getCheckPlanId();
        if (checkPlanId != null) {
            stmt.bindLong(6, checkPlanId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FlightCheck readEntity(Cursor cursor, int offset) {
        FlightCheck entity = new FlightCheck( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // des
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // picUrl
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // isChecked
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // checkPlanId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FlightCheck entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDes(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPicUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsChecked(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setCheckPlanId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FlightCheck entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FlightCheck entity) {
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
    
    /** Internal query to resolve the "flightChecks" to-many relationship of CheckPlan. */
    public List<FlightCheck> _queryCheckPlan_FlightChecks(Long checkPlanId) {
        synchronized (this) {
            if (checkPlan_FlightChecksQuery == null) {
                QueryBuilder<FlightCheck> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CheckPlanId.eq(null));
                checkPlan_FlightChecksQuery = queryBuilder.build();
            }
        }
        Query<FlightCheck> query = checkPlan_FlightChecksQuery.forCurrentThread();
        query.setParameter(0, checkPlanId);
        return query.list();
    }

}
