package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hsy.flightpacket.R;

/**
 * Created by xiongweimin on 2018/6/25.
 */

public class CodingActivity extends BaseActivity {


    public static void start(Context context, String title) {
        Intent intent = new Intent(context, CodingActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_coding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle(getIntent().getStringExtra("title"));
    }
}
