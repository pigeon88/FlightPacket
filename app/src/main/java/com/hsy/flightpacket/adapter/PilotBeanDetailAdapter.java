package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;

/**
 * Created by xiongweimin on 2018/8/3.
 */

public class PilotBeanDetailAdapter extends ViewHolderBase<PilotRecordTable> {

    private View view;

    private boolean showName = false;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        view = inflater.inflate(R.layout.item_comm, null);
        return view;
    }

    @Override
    public void showData(int position, PilotRecordTable trainPlan) {
        TextView tv1 = view.findViewById(R.id.tv_1);
        tv1.setText(trainPlan.getTitle());

    }
}
