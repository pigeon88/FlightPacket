package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.util.TimeFormat;

/**
 * Created by xiongweimin on 2018/7/4.
 */

public class PrepareAdapter extends ViewHolderBase<TrainPlan> {

    private View view;

    private boolean showName = false;

    public PrepareAdapter(boolean showName) {
        this.showName = showName;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        view = inflater.inflate(R.layout.item_comm, null);
        return view;
    }

    @Override
    public void showData(int position, TrainPlan trainPlan) {
        TextView tv1 = view.findViewById(R.id.tv_1);
        tv1.setText(TimeFormat.getStringDate(trainPlan.getTrainTime()));
        TextView tv2 = view.findViewById(R.id.tv_train_name);
        if (showName) {
            tv2.setText(trainPlan.getTrainPlanName());
            tv2.setVisibility(View.VISIBLE);
        } else {
            tv2.setVisibility(View.GONE);
        }


    }

}
