package com.hsy.flightpacket.activity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.Area;
import com.hsy.flightpacket.bean.dao.FlightCheck;
import com.hsy.flightpacket.util.CommUtils;
import com.hsy.flightpacket.views.GobildTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by xiongweimin on 2018/6/21.
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.llt_ready)
    LinearLayout lltReady;
    @Bind(R.id.llt_check)
    LinearLayout lltCheck;
    @Bind(R.id.llt_data_sync)
    LinearLayout lltDataSync;
    @Bind(R.id.llt_pilot_ready)
    LinearLayout lltPilotReady;
    @Bind(R.id.llt_pilot_tools)
    LinearLayout lltPilotTools;
    @Bind(R.id.llt_pilot_fly)
    ImageView lltPilotFly;
    @Bind(R.id.llt_course_ware)
    LinearLayout lltCourseWare;
    @Bind(R.id.llt_tactical_training)
    LinearLayout lltTacticalTraining;
    @Bind(R.id.llt_simulation_training)
    LinearLayout lltSimulationTraining;
    @Bind(R.id.rlt_weather)
    RelativeLayout rltWeather;
    @Bind(R.id.iv_home_bg)
    ImageView ivHomeBg;
    @Bind(R.id.llt_pilot_linghang)
    LinearLayout lltPilotLinghang;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.rlt_top)
    RelativeLayout rltTop;
    private List<String> videos;

    private List<String> pics;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setVisibility(View.GONE);
        EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT < 23) {
            initFilePaths();
        } else {
            //6.0以后权限处理
            permissionForM();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Area area) {
        tvArea.setText(area.area);
    }

    @OnClick({R.id.llt_shouce, R.id.llt_yuan, R.id.tv_area, R.id.rlt_top, R.id.llt_pilot_linghang, R.id.rlt_weather, R.id.llt_ready, R.id.llt_check, R.id.llt_data_sync, R.id.llt_pilot_ready, R.id.llt_pilot_tools, R.id.llt_pilot_fly, R.id.llt_course_ware, R.id.llt_tactical_training, R.id.llt_simulation_training})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlt_weather:
            case R.id.rlt_top:
                WeatherActivity.start(this);
                break;
            case R.id.tv_area:
                AreaChooseActivity.start(this);
                break;
            case R.id.llt_pilot_linghang:
                CodingActivity.start(this, "飞行领航");
                break;
            case R.id.llt_pilot_tools:
                PilotToolsActivity.start(this);
                break;
            case R.id.llt_check:
                CheckPlanActivity.start(this);
                break;
            case R.id.llt_data_sync:
                CodingActivity.start(this, "飞前数据同步");
                break;
            case R.id.llt_pilot_ready:
                PilotReadyActivity.start(this);
                break;
            case R.id.llt_shouce:
                CourseChooseActivity.start(this,"飞行手册");
                break;
            case R.id.llt_yuan:
                CourseChooseActivity.start(this,"特情处理预案");
                break;
            case R.id.llt_pilot_fly:
                CodingActivity.start(this, "飞行领航");
                break;
            case R.id.llt_tactical_training:
                //签字
                TacticsTrainActivity.start(this);
                break;
            case R.id.llt_simulation_training:
                CodingActivity.start(this, "模拟训练");
                break;
            case R.id.llt_ready:
                FlightPrepareActivity.start(this);
                break;
            case R.id.llt_course_ware:
                CourseChooseActivity.start(this,"教学课件");
                break;
            default:
                break;
        }
    }

    private void initFilePaths() {
        videos = CommUtils.initFilePaths("FpVideo");
        pics = CommUtils.initFilePaths("FpPic");
        if (videos == null || videos.isEmpty()) {
            Toast.makeText(this, "请在指定文件夹中放入视频", Toast.LENGTH_SHORT).show();
        }
        if (pics == null || pics.isEmpty()) {
            Toast.makeText(this, "请在指定文件夹中放入图片", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 安卓6.0以上版本权限处理
     */
    private void permissionForM() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        } else {
            initFilePaths();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                initFilePaths();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
