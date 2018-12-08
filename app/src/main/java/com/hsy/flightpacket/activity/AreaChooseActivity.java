package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.Area;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/25.
 */

public class AreaChooseActivity extends BaseActivity {

    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.llt_1)
    TextView llt1;
    @Bind(R.id.llt_2)
    TextView llt2;
    @Bind(R.id.llt_3)
    TextView llt3;
    @Bind(R.id.llt_4)
    TextView llt4;
    @Bind(R.id.llt_5)
    TextView llt5;
    @Bind(R.id.llt_6)
    TextView llt6;
    @Bind(R.id.llt_7)
    TextView llt7;
    @Bind(R.id.llt_8)
    TextView llt8;
    @Bind(R.id.llt_9)
    TextView llt9;
    @Bind(R.id.llt_10)
    TextView llt10;
    @Bind(R.id.llt_11)
    TextView llt11;
    @Bind(R.id.llt_12)
    TextView llt12;
    @Bind(R.id.llt_13)
    TextView llt13;
    @Bind(R.id.llt_14)
    TextView llt14;
    @Bind(R.id.llt_15)
    TextView llt15;
    @Bind(R.id.llt_16)
    TextView llt16;
    @Bind(R.id.llt_17)
    TextView llt17;
    @Bind(R.id.llt_18)
    TextView llt18;

    public static void start(Context context) {
        Intent intent = new Intent(context, AreaChooseActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_area_choose;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("切换地区");
    }

    @OnClick({R.id.llt_1, R.id.llt_2, R.id.llt_3, R.id.llt_4, R.id.llt_5, R.id.llt_6, R.id.llt_7, R.id.llt_8, R.id.llt_9, R.id.llt_10, R.id.llt_11, R.id.llt_12, R.id.llt_13, R.id.llt_14, R.id.llt_15, R.id.llt_16, R.id.llt_17, R.id.llt_18})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_1:
            case R.id.llt_2:
            case R.id.llt_3:
            case R.id.llt_4:
            case R.id.llt_5:
            case R.id.llt_6:
            case R.id.llt_7:
            case R.id.llt_8:
            case R.id.llt_9:
            case R.id.llt_10:
            case R.id.llt_11:
            case R.id.llt_12:
            case R.id.llt_13:
            case R.id.llt_14:
            case R.id.llt_15:
            case R.id.llt_16:
            case R.id.llt_17:
            case R.id.llt_18:
                EventBus.getDefault().post(new Area(((TextView) view).getText().toString()));
                finish();
                break;
        }
    }
}
