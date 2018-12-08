package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hsy.flightpacket.R;

/**
 * Created by xiongweimin on 2018/6/28.
 */

public class StudentInfoActivity extends BaseActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, StudentInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_student_info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("学员信息");
    }
}
