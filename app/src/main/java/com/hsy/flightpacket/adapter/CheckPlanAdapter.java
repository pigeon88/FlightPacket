package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.util.TimeFormat;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class CheckPlanAdapter extends ViewHolderBase<CheckPlan> {


    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.des)
    TextView des;
    @Bind(R.id.tv_check6)
    TextView tvCheck6;
    @Bind(R.id.iv_you)
    ImageView ivYou;
    @Bind(R.id.rlt_6)
    RelativeLayout rlt6;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_check_plan, null);
    }

    @Override
    public void showData(int position, CheckPlan checkPlan) {
        if (checkPlan != null) {
            title.setText(checkPlan.getName());
            des.setText(TimeFormat.getStringDate(checkPlan.getTime()));
            if (checkPlan.getStatus()==0){//未完成
                tvCheck6.setVisibility(View.VISIBLE);
                ivYou.setVisibility(View.GONE);
            }else if (checkPlan.getStatus()==1){//已完成
                tvCheck6.setVisibility(View.GONE);
                ivYou.setVisibility(View.VISIBLE);
            }
        }
    }
}
