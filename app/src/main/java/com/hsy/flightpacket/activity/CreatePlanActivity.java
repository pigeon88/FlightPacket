package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.dao.PClass;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.TeachActivity;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.dialog.ClassPickDialog;
import com.hsy.flightpacket.util.CommUtils;
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
 * Created by xiongweimin on 2018/6/28.
 */

public class CreatePlanActivity extends BaseActivity {

    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.iv_you)
    ImageView ivYou;
    @Bind(R.id.rlt_date)
    RelativeLayout rltDate;
    @Bind(R.id.tv_check2)
    TextView tvCheck2;
    @Bind(R.id.cb1)
    CheckBox cb1;
    @Bind(R.id.cb2)
    CheckBox cb2;
    @Bind(R.id.cb3)
    CheckBox cb3;
    @Bind(R.id.cb4)
    CheckBox cb4;
    @Bind(R.id.cb5)
    CheckBox cb5;
    @Bind(R.id.cb6)
    CheckBox cb6;
    @Bind(R.id.tv_result_date)
    TextView tvResultDate;
    @Bind(R.id.edt_subject)
    EditText edtSubject;
    @Bind(R.id.edt_practice)
    EditText edtPractice;
    @Bind(R.id.tv_create_plan)
    TextView tvCreatePlan;
    @Bind(R.id.edt_plan_name)
    EditText edtPlanName;
    @Bind(R.id.tv_class_choose)
    TextView tvClassChoose;


    private List<CheckBox> checkBoxes = new ArrayList<>();

    private String time;

    private String className;

    private Long classId;


    public static void start(Context context) {
        Intent intent = new Intent(context, CreatePlanActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_create_plan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("创建计划");
        checkBoxes.add(cb1);
        checkBoxes.add(cb2);
        checkBoxes.add(cb3);
        checkBoxes.add(cb4);
        checkBoxes.add(cb5);
        checkBoxes.add(cb6);
        //自动弹出键盘
       // CommUtils.showSoftInputFromWindow(this, edtPlanName);
    }

    TrainPlan trainPlan;

    boolean isInserted;

    @OnClick({R.id.tv_class_choose, R.id.rlt_date, R.id.tv_check2, R.id.tv_create_plan, R.id.tv_create_teach_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create_teach_activity:
                //创建计划
                getTrainPlan();
                if (trainPlan == null) return;
                if (!isInserted) {
                    //插入多条学生信息
                    DataBaseUtils.insertTrainPlan(trainPlan);
                }
                //通过id查询是否已经有数据
                List<TeachActivity> activities = DataBaseUtils.searchTeachActivityById(trainPlan.getId());
                if (activities == null || activities.size() == 0) {
                    isInserted = true;
                    //创建活动计划
                    TeachActivityPlanActivity.start(this, trainPlan.getId());
                } else {
                    Toast.makeText(this, "已创建教学活动计划", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_class_choose:
                //班级选择
                ClassPickDialog pickDialog = new ClassPickDialog(this, new ClassPickDialog.ClassCheck() {
                    @Override
                    public void classChoose(PClass pClass) {
                        tvClassChoose.setText(pClass.getClassName());
                        className = pClass.getClassName();
                        classId = pClass.getId();
                    }
                });
                pickDialog.showDialog();
                break;
            case R.id.tv_create_plan:
                if (trainPlan != null) {
                    List<TeachActivity> teachActivities = DataBaseUtils.searchTeachActivityById(trainPlan.getId());
                    if (teachActivities != null && teachActivities.size() > 0) {
                        //更新创建计划
                        getTrainPlan();
                        if (trainPlan == null) return;
                        //插入多条学生信息
                        DataBaseUtils.correctTrainPlan(trainPlan);
                        //选择学员
                        boolean choose = false;
                        for (int i = 0; i < checkBoxes.size(); i++) {
                            if (checkBoxes.get(i).isChecked()) {
                                choose = true;
                            }
                        }
                        if (!choose) {
                            Toast.makeText(this, "请选择学员", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //此处添加PlanRecord信息
                        addPlanRecord();
                        finish();
                    } else {
                        Toast.makeText(this, "请创建教学活动", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.rlt_date:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tvResultDate.setText(TimeFormat.getStringDate(date));
                        time = TimeFormat.getStringDate(date);
                    }
                });
                dialog.show();
                break;
            case R.id.tv_check2:
                for (int i = 0; i < checkBoxes.size(); i++) {
                    if (!checkBoxes.get(i).isChecked()) {
                        checkBoxes.get(i).setChecked(true);
                    }
                }
                break;
        }
    }

    /**
     * 创建学生训练计划
     */
    private void addPlanRecord() {
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).isChecked()) {
                List<Student> students = DataBaseUtils.searchStudent();
                Student student = students.get(i);
                PlanRecord planRecord = new PlanRecord(null, null, null, null, null, null, null, null, trainPlan.getId(), student.getId(), false);
                DataBaseUtils.insertPlanRecord(planRecord);
            }
        }
    }


    /**
     * 更新tranPlan信息
     *
     * @return
     */
    @Nullable
    private void getTrainPlan() {
        String subject = edtSubject.getText().toString();
        String practice = edtPractice.getText().toString();
        String planName = edtPlanName.getText().toString();
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(this, "选择时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(planName)) {
            Toast.makeText(this, "请输入计划名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(className)) {
            Toast.makeText(this, "请输入班级名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(subject)) {
            Toast.makeText(this, "请输入科目", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(practice)) {
            Toast.makeText(this, "请输入练习", Toast.LENGTH_SHORT).show();
            return;
        }
        if (trainPlan == null) {
            //插入一条计划信息
            trainPlan = new TrainPlan();
        }
        trainPlan.setTrainPlanName(planName);
        trainPlan.setClassId(classId);
        trainPlan.setSubject(subject);//设置科目
        trainPlan.setTrainContent(practice);//设置训练内容
        try {
            trainPlan.setTrainTime(format1.parse(tvResultDate.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        trainPlan.setTeamReview("");
        trainPlan.setSupreReview("");
    }


}
