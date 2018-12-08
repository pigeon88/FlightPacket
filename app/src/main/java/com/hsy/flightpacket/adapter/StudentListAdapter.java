package com.hsy.flightpacket.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.db.DataBaseUtils;

import java.util.List;

/**
 * Created by xiongweimin on 2018/7/4.
 */

public class StudentListAdapter extends ViewHolderBase<PlanRecord> {

    private View view;

    private Long trainPlanId;

    public StudentListAdapter(Long trainPlanId) {
        this.trainPlanId = trainPlanId;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        view = inflater.inflate(R.layout.item_student, null);
        return view;
    }

    @Override
    public void showData(int position, PlanRecord record) {
        TextView tv1 = view.findViewById(R.id.tv_name);
        tv1.setText(DataBaseUtils.searchStudent(record.getStudentId()).getName());
        //记录状态
        TextView tv2 = view.findViewById(R.id.tv_status);
        if (record != null) {
            //通过学生id和学生
            if (record.getIsFinished()) {
                tv2.setText("已完成");
                tv2.setTextColor(Color.parseColor("#999999"));
            } else {
                tv2.setText("记录");
                tv2.setTextColor(Color.parseColor("#01c6f0"));
            }
        } else {
            tv2.setText("记录");
            tv2.setTextColor(Color.parseColor("#01c6f0"));
        }
    }
}
