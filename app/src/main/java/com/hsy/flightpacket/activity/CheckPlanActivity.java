package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.CheckPlanAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.views.NoScrollListView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class CheckPlanActivity extends BaseActivity {

    @Bind(R.id.lst_check_log)
    NoScrollListView lstCheckLog;
    @Bind(R.id.llt_log)
    LinearLayout lltLog;
    @Bind(R.id.lst_check_plan)
    NoScrollListView lstCheckPlan;
    @Bind(R.id.llt_check_plan)
    LinearLayout lltCheckPlan;

    ListViewDataAdapter<CheckPlan> finishPlanAdapter;
    ListViewDataAdapter<CheckPlan> unFinishPlanAdapter;
    @Bind(R.id.tv_create_plan)
    TextView tvCreatePlan;

    private List<CheckPlan> unFinish;
    private List<CheckPlan> finish;


    public static void start(Context context) {
        Intent intent = new Intent(context, CheckPlanActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_check_plan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("飞前准备");
        initUnFinishPlan();
        initFinishPlan();
    }

    /**
     * 未完成
     */
    private void initFinishPlan() {
        lstCheckPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到详情
                FlightCheckActivity.start(CheckPlanActivity.this, unFinish.get(i).getId(),0);
            }
        });
        //配置适配器
        unFinishPlanAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<CheckPlan>() {
            @Override
            public ViewHolderBase<CheckPlan> createViewHolder() {
                return new CheckPlanAdapter();
            }
        });
        lstCheckPlan.setAdapter(unFinishPlanAdapter);
    }

    /**
     * 检查日志初始化 已完成检查项目
     */
    private void initUnFinishPlan() {
        lstCheckLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到详情
                FlightCheckActivity.start(CheckPlanActivity.this, finish.get(i).getId(),1);
            }
        });
        //配置适配器
        finishPlanAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<CheckPlan>() {
            @Override
            public ViewHolderBase<CheckPlan> createViewHolder() {
                return new CheckPlanAdapter();
            }
        });
        lstCheckLog.setAdapter(finishPlanAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取已完成和未完成日志
        unFinish = DataBaseUtils.searchPlanRecordByStatus(0);//未完成
        finish = DataBaseUtils.searchPlanRecordByStatus(1);//已完成

        //获取已完成列表
        finishPlanAdapter.getDataList().clear();
        finishPlanAdapter.getDataList().addAll(finish);
        finishPlanAdapter.notifyDataSetChanged();
        //获取未完成列表
        unFinishPlanAdapter.getDataList().clear();
        unFinishPlanAdapter.getDataList().addAll(unFinish);
        unFinishPlanAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_create_plan)
    public void onViewClicked() {
        ChooseCheckPlanActivity.start(this);
    }
}
