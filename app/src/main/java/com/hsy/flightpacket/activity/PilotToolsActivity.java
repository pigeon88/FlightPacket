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
 * Created by xiongweimin on 2018/7/4.
 */

public class PilotToolsActivity extends BaseActivity {

    @Bind(R.id.llt_calculator)
    LinearLayout lltCalculator;
    @Bind(R.id.llt_rule)
    LinearLayout lltRule;

    public static void start(Context context) {
        Intent intent = new Intent(context, PilotToolsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_tools;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("领航工具");
    }

    @OnClick({R.id.llt_calculator, R.id.llt_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_calculator:
                CodingActivity.start(this,"领航计算器");
                break;
            case R.id.llt_rule:
                CodingActivity.start(this,"领航计算尺");
                break;
        }
    }
}
