package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.PilotBean;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.util.TimeFormat;

/**
 * Created by xiongweimin on 2018/8/3.
 */

public class PilotBeanAdapter extends ViewHolderBase<PilotBean> {

    private View view;

    private boolean showName = false;

    public PilotBeanAdapter(boolean showName) {
        this.showName = showName;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        view = inflater.inflate(R.layout.item_comm, null);
        return view;
    }

    @Override
    public void showData(int position, PilotBean pilotBean) {
        TextView tv1 = view.findViewById(R.id.tv_1);
        TextView tv2 = view.findViewById(R.id.tv_train_name);
        tv2.setText(pilotBean.getTitle());
        tv1.setText(TimeFormat.getStringDate(pilotBean.getDateTime()));
    }
}