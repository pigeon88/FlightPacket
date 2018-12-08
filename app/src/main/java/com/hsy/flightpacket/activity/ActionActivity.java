package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hsy.flightpacket.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/24.
 */

public class ActionActivity extends BaseActivity {


    @Bind(R.id.llt_1)
    LinearLayout llt1;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.llt_2)
    LinearLayout llt2;
    @Bind(R.id.llt_3)
    LinearLayout llt3;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, ActionActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.action_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle(getIntent().getStringExtra("title"));
    }

    @OnClick({R.id.llt_1, R.id.llt_2, R.id.llt_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_1:
                CoursesListActivity.start(this, "大坡度转弯",0);
                break;
            case R.id.llt_2:
                CoursesListActivity.start(this, "失速",0);
                break;
            case R.id.llt_3:
                CoursesListActivity.start(this,"慢速飞行",0);
                break;
        }
    }
}
