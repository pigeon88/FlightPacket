package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.PilotBeanAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.PilotBean;
import com.hsy.flightpacket.db.DataBaseUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/8/3.
 */

public class PilotListActivity extends BaseActivity {
    @Bind(R.id.lst_pilot)
    ListView lstPilot;

    ListViewDataAdapter<PilotBean> dataAdapter;
    @Bind(R.id.tv_create_plan)
    TextView tvCreatePlan;

    private List<PilotBean> pilotBeen;

    public static void start(Context context) {
        Intent intent = new Intent(context, PilotListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("领航记录表");
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<PilotBean>() {
            @Override
            public ViewHolderBase<PilotBean> createViewHolder() {
                return new PilotBeanAdapter(true);
            }
        });
        lstPilot.setAdapter(dataAdapter);
        //设置item点击
        lstPilot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PilotBeanDetailActivity.start(PilotListActivity.this, pilotBeen.get(position).getId());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //获取所有的pilotBeanList
        pilotBeen = DataBaseUtils.searchPilotBean();
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(pilotBeen);
        dataAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_create_plan)
    public void onViewClicked() {
        PilotBeanCreateActivity.start(this);
    }
}
