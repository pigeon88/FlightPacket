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

public class SkillActivity extends BaseActivity {

    @Bind(R.id.llt_1)
    LinearLayout llt1;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.llt_2)
    LinearLayout llt2;
    @Bind(R.id.llt_3)
    LinearLayout llt3;
    @Bind(R.id.llt_4)
    LinearLayout llt4;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.llt_5)
    LinearLayout llt5;
    @Bind(R.id.llt_6)
    LinearLayout llt6;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, SkillActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_skill;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle(getIntent().getStringExtra("title"));

    }

    @OnClick({R.id.llt_1, R.id.llt_2, R.id.llt_3,R.id.llt_4, R.id.llt_5, R.id.llt_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_1:
                ActionActivity.start(this, "山地飞行");
                break;
            case R.id.llt_2:
                ActionActivity.start(this, "夜间飞行");
                break;
            case R.id.llt_3:
                ActionActivity.start(this, "机动动作");
                break;
            case R.id.llt_4:
                ActionActivity.start(this, "装备性能");
                break;
            case R.id.llt_5:
                ActionActivity.start(this, "气流变化");
                break;
            case R.id.llt_6:
                ActionActivity.start(this, "安全返航");
                break;
        }
    }

}
