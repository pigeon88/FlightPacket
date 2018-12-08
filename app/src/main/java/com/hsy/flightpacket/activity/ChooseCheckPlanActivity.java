package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.ChooseCheckAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.bean.dao.FlightCheck;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.dialog.CommInputTextDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class ChooseCheckPlanActivity extends BaseActivity {
    @Bind(R.id.tv_create_plan)
    TextView tvCreatePlan;
    @Bind(R.id.lst_choose_check)
    ListView lstChooseCheck;


    ListViewDataAdapter<FlightCheck> dataAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChooseCheckPlanActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_choose_check;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("选择检查项");

        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<FlightCheck>() {
            @Override
            public ViewHolderBase<FlightCheck> createViewHolder() {
                return new ChooseCheckAdapter(new ChooseCheckAdapter.CheckListener() {
                    @Override
                    public void checked(int index, boolean check) {
                        //下标  选中状态
                    }
                });
            }
        });
        lstChooseCheck.setAdapter(dataAdapter);

        //飞前检查数据
        FlightCheck flightCheck1 = new FlightCheck(null, "脚蹬行程检查", "处于中立位", null, false, null);
        FlightCheck flightCheck2 = new FlightCheck(null, "驾驶杆操作自如", "中立加摩擦", null, false, null);
        FlightCheck flightCheck3 = new FlightCheck(null, "总杆距行程正常", "最低加摩擦", null, false, null);
        FlightCheck flightCheck4 = new FlightCheck(null, "协调电门、油门", "关闭", null, false, null);
        FlightCheck flightCheck5 = new FlightCheck(null, "保险电门", "除地平仪外，其他全部按下", null, false, null);
        FlightCheck flightCheck6 = new FlightCheck(null, "汽化器加温杆", "按下", null, false, null);
        //设置数据
        List<FlightCheck> flightChecks1 = new ArrayList<>();
        flightChecks1.add(flightCheck1);
        flightChecks1.add(flightCheck2);
        flightChecks1.add(flightCheck3);
        flightChecks1.add(flightCheck4);
        flightChecks1.add(flightCheck5);
        flightChecks1.add(flightCheck6);
        //清除所有列表
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(flightChecks1);
        dataAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_create_plan)
    public void onViewClicked() {
        CommInputTextDialog nameDialog = new CommInputTextDialog(this, new CommInputTextDialog.PlanNameListener() {
            @Override
            public void planName(String name) {
                //弹窗输入检查计划
                List<FlightCheck> flightChecks = dataAdapter.getDataList();
                //所有勾选检查保存到数据库 先保存检查计划
                CheckPlan checkPlan = new CheckPlan(null, name, new Date(), 0);
                DataBaseUtils.insertCheckPlanDao(checkPlan);
                //插入数据
                List<FlightCheck> unCheckList = new ArrayList<>();
                for (int i = 0; i < flightChecks.size(); i++) {
                    FlightCheck flightCheck = flightChecks.get(i);
                    //设置计划id
                    flightCheck.setCheckPlanId(checkPlan.getId());
                    if (flightCheck.isChoose) {
                        unCheckList.add(flightCheck);
                    }
                }
                DataBaseUtils.saveFlightCheck(unCheckList);
                //关闭当前页面
                ChooseCheckPlanActivity.this.finish();

            }
        }, "请输入计划名称");
        nameDialog.showDialog();
    }
}
