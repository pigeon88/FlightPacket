package com.hsy.flightpacket.application;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.studio.os.LogCat;

import com.hsy.flightpacket.activity.MainActivity;
import com.hsy.flightpacket.bean.dao.DaoMaster;
import com.hsy.flightpacket.bean.dao.DaoSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongweimin on 2018/6/21.
 */

public class FlightApplication extends Application {

    public static Boolean IS_PRODUCT_NET = Boolean.FALSE;

    private static FlightApplication app;

    private List<Activity> listActivityNeedFinish = new ArrayList<>();

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public FlightApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //设置是否有debug日志
        LogCat.setDebug(!IS_PRODUCT_NET);
        //设置数据库
        setDatabase();
    }

    public static FlightApplication getInstance() {
        if (app == null) {
            app = new FlightApplication();
        }
        return app;
    }

    /**
     * 添加activity到列表中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        listActivityNeedFinish.add(activity);
    }

    /**
     * 清除main activity 之前顶上的activity
     */
    public void clearBeforeMainAvtivity() {
        for (Activity activity : listActivityNeedFinish) {
            if (activity != null && !(activity instanceof MainActivity)) {
                activity.finish();
            }
        }
    }

    public void removeActivity(Activity activity) {
        listActivityNeedFinish.remove(activity);
    }


    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }


}
