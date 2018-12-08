package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.hsy.flightpacket.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/27.
 */

public class PilotReadyActivity extends BaseActivity {


    @Bind(R.id.llt_map_job)
    LinearLayout lltMapJob;
    @Bind(R.id.llt_pilot_program)
    LinearLayout lltPilotProgram;
    @Bind(R.id.llt_record_table)
    LinearLayout lltRecordTable;

    public static void start(Context context) {
        Intent intent = new Intent(context, PilotReadyActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_ready;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("领航准备");
    }

    @OnClick({R.id.llt_map_job, R.id.llt_pilot_program, R.id.llt_record_table})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_map_job:
                CodingActivity.start(this, "地图作业");
                break;
            case R.id.llt_pilot_program:
                CodingActivity.start(this, "领航计划");
                break;
            case R.id.llt_record_table:
                PilotListActivity.start(this);
                break;
        }
    }
}
