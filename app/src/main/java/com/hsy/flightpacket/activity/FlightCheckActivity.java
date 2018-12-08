package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.FlightCheckAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.bean.dao.FlightCheck;
import com.hsy.flightpacket.db.DataBaseUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/22.
 */

public class FlightCheckActivity extends BaseActivity {

    private static final String KEY_FLIGHT_CHECK_PLAN = "key_flight_check_plan";

    private static final String KEY_CHECK_TYPE_PLAN = "key_check_type_plan";
    ListViewDataAdapter<FlightCheck> dataAdapter;
    @Bind(R.id.lst_check)
    ListView lstCheck;
    @Bind(R.id.cb_choose_all)
    CheckBox cbChooseAll;
    @Bind(R.id.tv_create_plan)
    TextView tvCreatePlan;
    @Bind(R.id.llt_bottom)
    RelativeLayout lltBottom;
    private List<FlightCheck> flightChecks;
    private Long checkPlanId;
    private int type;

    public static void start(Context context, Long checkPlan, int type) {
        Intent intent = new Intent(context, FlightCheckActivity.class);
        intent.putExtra(KEY_FLIGHT_CHECK_PLAN, checkPlan);
        intent.putExtra(KEY_CHECK_TYPE_PLAN, type);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_check;
    }

    private boolean showDelete = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("飞前检查");
        checkPlanId = getIntent().getLongExtra(KEY_FLIGHT_CHECK_PLAN, 0);
        type = getIntent().getIntExtra(KEY_CHECK_TYPE_PLAN, 0);
        //是否显示删除
        flightChecks = DataBaseUtils.searchFlightCheckById(checkPlanId);
        //需要显示
        if (flightChecks.size() > 0 && flightChecks.get(0).isShowDelete) {
            lltBottom.setVisibility(View.VISIBLE);
            showDelete = true;
        } else {
            lltBottom.setVisibility(View.GONE);
            showDelete = false;
        }
        //设置删除逻辑
        setRightOne();
        //设置适配器
        initAdapter();
        //删除选中全部
        cbChooseAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < flightChecks.size(); i++) {
                    flightChecks.get(i).isChooseDelete = b;
                }
                dataAdapter.notifyDataSetChanged();
            }
        });
    }


    /**
     * 设置删除是的显示
     */
    private void setRightOne() {
        //未完成时可以选删除
        if (type == 0) {
            getBaseNavigationBar().setRightBtnOne(R.mipmap.zhunbei_icon_shanchu, null, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //显示时
                    setRightShowDelete();
                }
            });
        }
    }

    /**
     * 显示删除结果
     */
    private void setRightShowDelete() {
        if (showDelete) {
            //设置 不显示
            if (flightChecks.size() > 0) {
                for (int i = 0; i < flightChecks.size(); i++) {
                    flightChecks.get(i).isShowDelete = false;
                }
            }
            showDelete = false;
            lltBottom.setVisibility(View.GONE);
        } else {
            //删除 设置显示
            if (flightChecks.size() > 0) {
                for (int i = 0; i < flightChecks.size(); i++) {
                    flightChecks.get(i).isShowDelete = true;
                }
            }
            showDelete = true;
            lltBottom.setVisibility(View.VISIBLE);
        }
        //清除所有列表
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(flightChecks);
        dataAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        lstCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到详情
                CheckOptionActivity.start(FlightCheckActivity.this, flightChecks.get(i).getId());
            }
        });
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<FlightCheck>() {
            @Override
            public ViewHolderBase<FlightCheck> createViewHolder() {
                return new FlightCheckAdapter(type, new FlightCheckAdapter.ChooseListener() {
                    @Override
                    public void checked(int index, boolean check) {
                        //更新页面
                        flightChecks.get(index).isChooseDelete = check;
                    }
                });
            }
        });
        lstCheck.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取所有数据列表
        flightChecks = DataBaseUtils.searchFlightCheckById(checkPlanId);
        //清除所有列表
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(flightChecks);
        dataAdapter.notifyDataSetChanged();
        //列表找那个数据是否都检查完毕
        boolean checked = true;
        for (int i = 0; i < flightChecks.size(); i++) {
            if (!flightChecks.get(i).getIsChecked()) {
                checked = false;
            }
        }
        //检查完毕更新计划信息
        if (checked) {
            //得到所有列表如果此项检查完成更改状态
            CheckPlan checkPlan = DataBaseUtils.loadCheckPlan(checkPlanId);
            checkPlan.setStatus(1);
            DataBaseUtils.correctCheckPlan(checkPlan);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //设置 不显示 此处强制不显示是因为   @Transient 无效
        if (flightChecks.size() > 0) {
            for (int i = 0; i < flightChecks.size(); i++) {
                flightChecks.get(i).isShowDelete = false;
            }
        }
    }

    @OnClick(R.id.tv_create_plan)
    public void onViewClicked() {
        boolean isChooseOne = false;
        for (int i = 0; i < flightChecks.size(); i++) {
            if (flightChecks.get(i).isChooseDelete) {
                isChooseOne = true;
            }
        }
        if (isChooseOne) {
            //删除选中的列表
            for (int i = 0; i < flightChecks.size(); i++) {
                if (flightChecks.get(i).isChooseDelete) {
                    //删除按钮
                    DataBaseUtils.deleteFlightCheck(flightChecks.get(i).getId());
                }
            }
            //全部删除了的话关闭当前页面 并把词条checkPlanId删除
            flightChecks = DataBaseUtils.searchFlightCheckById(checkPlanId);
            if (flightChecks.size() == 0) {
                DataBaseUtils.deleteCheckPlanDao(checkPlanId);
                finish();

            }
            dataAdapter.getDataList().clear();
            dataAdapter.getDataList().addAll(flightChecks);
            dataAdapter.notifyDataSetChanged();
            Toast.makeText(this, "已删除", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "您未选中删除项目", Toast.LENGTH_SHORT).show();
        }

    }
}
