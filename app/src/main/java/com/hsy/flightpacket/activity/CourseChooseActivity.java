package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hsy.flightpacket.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/24.
 */

public class CourseChooseActivity extends BaseActivity {

    @Bind(R.id.llt_tech_ability)
    LinearLayout lltTechAbility;
    @Bind(R.id.llt_pilotage)
    LinearLayout lltPilotage;
    @Bind(R.id.llt_principle)
    LinearLayout lltPrinciple;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, CourseChooseActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_coursewares;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle(getIntent().getStringExtra("title"));
        getBaseNavigationBar().setRightBtnOne(R.mipmap.ic_search, null, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //搜索
                SearchActivity.start(CourseChooseActivity.this);
            }
        });
    }

    @OnClick({R.id.llt_tech_ability, R.id.llt_pilotage, R.id.llt_principle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_tech_ability:
                SkillActivity.start(this, "技能");
                break;
            case R.id.llt_pilotage:
                SkillActivity.start(this, "领航");
                break;
            case R.id.llt_principle:
                SkillActivity.start(this, "原理");
                break;
        }
    }
}
