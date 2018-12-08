package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.activity.report.StudentBean;
import com.hsy.flightpacket.activity.report.StudentTrain;
import com.hsy.flightpacket.activity.report.StudentTrainingProgressActivity;
import com.hsy.flightpacket.bean.dao.CheckPlan;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.util.TimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/27.
 */

public class StudentListActivity extends BaseActivity {

    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    @Bind(R.id.tv_item1)
    TextView tvItem1;
    @Bind(R.id.tv_item2)
    TextView tvItem2;
    @Bind(R.id.tv_item3)
    TextView tvItem3;
    @Bind(R.id.tv_item4)
    TextView tvItem4;
    @Bind(R.id.tv_item5)
    TextView tvItem5;
    @Bind(R.id.tv_item6)
    TextView tvItem6;
    @Bind(R.id.tv_check2)
    TextView tvCheck2;

    List<Student> students;

    List<StudentBean> studentBeans;
    List<String> titleDates = new ArrayList<>();

    public static void start(Context context) {
        Intent intent = new Intent(context, StudentListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_student_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("学员列表");
        //所有学生列表
        students = DataBaseUtils.searchStudent();
        try {
            studentBeans = new ArrayList<>();
            //获取所有记录列表
            List<PlanRecord> planRecordsAll = DataBaseUtils.searchPlanRecord();
            if (planRecordsAll.isEmpty()) {
                return;
            }
            //标题
            titleDates = removeDuplicate(planRecordsAll);
            if (titleDates == null || titleDates.isEmpty()) {
                return;
            }
            for (Student student : students) {
                //获取对应学生的记录
                List<PlanRecord> planRecords = DataBaseUtils.searchPlanRecord(student.getId());
                if (planRecords.isEmpty()) {
                    return;
                }
                //获取去重日期
                List<String> dates = removeDuplicate(planRecords);
                List<StudentTrain>  studentTrains = new ArrayList<>();
                //再通过日期查询信息
                for (String date : dates) {
                    Date date1 = format1.parse(date);
                    List<PlanRecord> prs = DataBaseUtils.searchPlanRecordByDate(student.getId(), date1);
                    int countTime = 0;
                    int countTimes = 0;
                    for (int i = 0; i < prs.size(); i++) {
                        //求时间总和
                        countTime += prs.get(i).getDaifeiTime();
                        countTime += prs.get(i).getDanfeiTimel();
                        //求次数总和
                        countTimes += prs.get(i).getDaifeiNum();
                        countTimes += prs.get(i).getDanfeiNum();
                    }
                    //添加学生数据信息
                    StudentTrain studentTrain = new StudentTrain(date, String.valueOf(countTime), String.valueOf(countTimes));
                    studentTrains.add(studentTrain);
                }
                if (studentTrains!=null){
                    //添加总列表信息
                    StudentBean studentBean = new StudentBean(student.getName(), studentTrains);
                    studentBeans.add(studentBean);
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 去重
     *
     * @param list
     * @return
     */
    public List<String> removeDuplicate(List<PlanRecord> list) {
        List<String> listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (listTemp.size() > 0) {
                boolean isSame = false;
                for (int j = 0; j < listTemp.size(); j++) {
                    String time1 = listTemp.get(j);
                    String time2 = list.get(i).getPlanTime() == null ? "" : format1.format(list.get(i).getPlanTime());
                    if (time1.equals(time2)) {
                        isSame = true;
                    }
                }
                if (!isSame) {
                    Date planTime = list.get(i).getPlanTime();
                    if (planTime != null) {
                        listTemp.add(TimeFormat.getStringDate(planTime));
                    }

                }
            } else {
                Date planTime = list.get(i).getPlanTime();
                if (planTime != null) {
                    listTemp.add(TimeFormat.getStringDate(planTime));
                }

            }

        }
        return listTemp;
    }


    @OnClick({R.id.tv_check2, R.id.tv_item1, R.id.tv_item2, R.id.tv_item3, R.id.tv_item4, R.id.tv_item5, R.id.tv_item6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_item1:
            case R.id.tv_item2:
            case R.id.tv_item3:
            case R.id.tv_item4:
            case R.id.tv_item5:
            case R.id.tv_item6:
                StudentInfoActivity.start(this);
                break;
            case R.id.tv_check2:
                if (titleDates != null && studentBeans != null) {
                    StudentTrainingProgressActivity.start(this, titleDates, studentBeans);
                }
                break;
        }
    }

}
