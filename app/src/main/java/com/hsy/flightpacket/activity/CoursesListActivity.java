package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hsy.flightpacket.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/24.
 */

public class CoursesListActivity extends BaseActivity {
    public static final String KEY_COURSE_PIC = "key_course_pic";
    @Bind(R.id.rlt_1)
    RelativeLayout rlt1;
    @Bind(R.id.rlt_2)
    RelativeLayout rlt2;
    @Bind(R.id.rlt_3)
    RelativeLayout rlt3;
    @Bind(R.id.rlt_4)
    RelativeLayout rlt4;
    @Bind(R.id.rlt_5)
    RelativeLayout rlt5;
    @Bind(R.id.rlt_6)
    RelativeLayout rlt6;
    @Bind(R.id.rlt_7)
    RelativeLayout rlt7;

    private List<String> pics;


    public static void start(Context context, String title, int type, ArrayList<String> pics) {
        Intent intent = new Intent(context, CoursesListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.putStringArrayListExtra(KEY_COURSE_PIC, pics);
        context.startActivity(intent);
    }

    public static void start(Context context, String title, int type) {
        Intent intent = new Intent(context, CoursesListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_course_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle(getIntent().getStringExtra("title"));
        pics = getIntent().getExtras().getStringArrayList(KEY_COURSE_PIC);
    }

    @OnClick({R.id.rlt_1, R.id.rlt_2, R.id.rlt_3, R.id.rlt_4, R.id.rlt_5, R.id.rlt_6, R.id.rlt_7, R.id.rlt_8, R.id.rlt_9, R.id.rlt_10,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlt_1:
            case R.id.rlt_2:
            case R.id.rlt_3:
            case R.id.rlt_4:
            case R.id.rlt_5:
            case R.id.rlt_6:
            case R.id.rlt_7:
            case R.id.rlt_8:
            case R.id.rlt_9:
            case R.id.rlt_10:
                CourseDetailActivity.start(this, getIntent().getIntExtra("type", 0), (ArrayList<String>) pics);
                break;
        }
    }
}
