package com.hsy.flightpacket.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.util.TimeFormat;

import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class StudentProAdapter extends ViewHolderBase<Student> {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_daifei_num)
    TextView tvDaifeiNum;
    @Bind(R.id.tv_jiancha_num)
    TextView tvJianchaNum;
    @Bind(R.id.tv_danfei_num)
    TextView tvDanfeiNum;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_student_progress, null);
    }

    @Override
    public void showData(int position, Student student) {
        if (student != null) {
            tvName.setText(student.getName());
            //通过学生id查询训练了几次
            List<PlanRecord> planRecords = DataBaseUtils.searchPlanRecord(student.getId());
            if (planRecords != null && planRecords.size() > 0) {
                Date date = planRecords.get(planRecords.size() - 1).getPlanTime();
                if (date != null) {
                    tvDate.setText(TimeFormat.getStringDate(date));
                } else {
                    tvDate.setText("暂无");
                }
                tvDaifeiNum.setText(getDaifeiNum(planRecords));
                tvJianchaNum.setText(getCheckNum(planRecords));
                tvDanfeiNum.setText(getDanfeiNum(planRecords));
            }

        }
    }

    public String getDaifeiNum(List<PlanRecord> planRecords) {
        int num = 0;
        for (int i = 0; i < planRecords.size(); i++) {
            if (planRecords.get(i).getDaifeiNum() != null) {
                num += planRecords.get(i).getDaifeiNum();
            }

        }
        return String.valueOf(num);
    }

    public String getCheckNum(List<PlanRecord> planRecords) {
        int num = 0;
        for (int i = 0; i < planRecords.size(); i++) {
            if (planRecords.get(i).getCheckNum() != null) {
                num += planRecords.get(i).getCheckNum();
            }

        }
        return String.valueOf(num);
    }

    public String getDanfeiNum(List<PlanRecord> planRecords) {
        int num = 0;
        for (int i = 0; i < planRecords.size(); i++) {
            if (planRecords.get(i).getDanfeiNum() != null) {
                num += planRecords.get(i).getDanfeiNum();
            }
        }
        return String.valueOf(num);
    }
}
