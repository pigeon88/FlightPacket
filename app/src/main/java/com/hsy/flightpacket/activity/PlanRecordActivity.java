package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.util.CommUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/28.
 */

public class PlanRecordActivity extends BaseActivity {
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    public static final String KEY_TRAIN_ID = "key_train_id";
    public static final String KEY_STUDENT_ID = "key_student_id";


    public static final String KEY_TIME = "key_time";
    public static final String KEY_KEMU_TITLE = "key_kemu_title";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_KEMU_MIOSHU = "key_kemu_mioshu";
    @Bind(R.id.edt_daifei_num)
    EditText edtDaifeiNum;
    @Bind(R.id.edt_daifei_time)
    EditText edtDaifeiTime;
    @Bind(R.id.edt_check_num)
    EditText edtCheckNum;
    @Bind(R.id.edt_check_time)
    EditText edtCheckTime;
    @Bind(R.id.edt_danfei_num)
    EditText edtDanfeiNum;
    @Bind(R.id.edt_danfei_time)
    EditText edtDanfeiTime;
    @Bind(R.id.tv_sure)
    TextView tvSure;
    Long train;
    Long studentId;
    PlanRecord mPlanRecord;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_subject)
    TextView tvSubject;
    @Bind(R.id.tv_des)
    TextView tvDes;

    private String title;
    private String name;
    private String time;
    private String des;

    public static void start(Context context, Long trainId, Long studentId, String title, String name, String time, String des) {
        Intent intent = new Intent(context, PlanRecordActivity.class);
        intent.putExtra(KEY_TRAIN_ID, trainId);
        intent.putExtra(KEY_STUDENT_ID, studentId);

        intent.putExtra(KEY_TIME, time);
        intent.putExtra(KEY_KEMU_TITLE, title);
        intent.putExtra(KEY_NAME, name);
        intent.putExtra(KEY_KEMU_MIOSHU, des);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_plan_record;
    }


    /**
     * 判空
     *
     * @param num
     * @return
     */
    public String getNum(Integer num) {
        if (num == null) {
            return "";
        } else {
            return String.valueOf(num);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("计划记录");
        train = getIntent().getLongExtra(KEY_TRAIN_ID, 0);
        studentId = getIntent().getLongExtra(KEY_STUDENT_ID, 0);
        title = getIntent().getStringExtra(KEY_KEMU_TITLE);
        name = getIntent().getStringExtra(KEY_NAME);
        time = getIntent().getStringExtra(KEY_TIME);
        des = getIntent().getStringExtra(KEY_KEMU_MIOSHU);
        //自动弹出键盘
        CommUtils.showSoftInputFromWindow(this, edtDaifeiNum);
        final List<PlanRecord> planRecords = DataBaseUtils.searchPlanRecord(train, studentId);
        if (planRecords != null && planRecords.size() > 0) {
            mPlanRecord = planRecords.get(0);
            edtCheckNum.setText(getNum(mPlanRecord.getCheckNum()));
            edtCheckTime.setText(getNum(mPlanRecord.getCheckTime()));
            edtDaifeiNum.setText(getNum(mPlanRecord.getDaifeiNum()));
            edtDaifeiTime.setText(getNum(mPlanRecord.getDaifeiTime()));
            edtDanfeiNum.setText(getNum(mPlanRecord.getDanfeiNum()));
            edtDanfeiTime.setText(getNum(mPlanRecord.getDanfeiTimel()));
        }
        //设置信息
        tvTitle.setText(name);
        tvTime.setText(time);
        tvSubject.setText("科目：" + title);
        tvDes.setText("练习：" + des);
        //删除按钮
        getBaseNavigationBar().setRightBtnOne(R.mipmap.zhunbei_icon_shanchu, null, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果本地有记录则删除
                if (planRecords != null && planRecords.size() > 0) {
                    //删除本条记录
                    DataBaseUtils.deletePlanRecord(mPlanRecord.getId());
                } else {
                    Toast.makeText(PlanRecordActivity.this, "还未生成记录  无法删除", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(PlanRecordActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @OnClick(R.id.tv_sure)
    public void onViewClicked() {
        //点击确定之后
        String daifeiNum = edtDaifeiNum.getText().toString();
        String daifeiTime = edtDaifeiTime.getText().toString();
        String checkTime = edtCheckTime.getText().toString();
        String checkNum = edtCheckNum.getText().toString();
        String danfeiNum = edtDanfeiNum.getText().toString();
        String danfeiTime = edtDanfeiTime.getText().toString();
        if (TextUtils.isEmpty(daifeiNum)) {
            Toast.makeText(this, "请输入带飞次数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(daifeiTime)) {
            Toast.makeText(this, "请输入带飞时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(checkNum)) {
            Toast.makeText(this, "请输入检查次数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(checkTime)) {
            Toast.makeText(this, "请输入检查时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(danfeiNum)) {
            Toast.makeText(this, "请输入单飞次数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(danfeiTime)) {
            Toast.makeText(this, "请输入单飞时间", Toast.LENGTH_SHORT).show();
            return;
        }
        //得到学员id
        try {
            PlanRecord planRecord = new PlanRecord(null, Integer.valueOf(daifeiNum), Integer.valueOf(daifeiTime), Integer.valueOf(checkNum), Integer.valueOf(checkTime), Integer.valueOf(danfeiNum), Integer.valueOf(danfeiTime), format1.parse(time), train, studentId, true);
            if (mPlanRecord == null) {
                DataBaseUtils.insertPlanRecord(planRecord);
            } else {
                mPlanRecord.setPlanTime(format1.parse(time));
                mPlanRecord.setDanfeiNum(Integer.valueOf(danfeiNum));
                mPlanRecord.setDanfeiTimel(Integer.valueOf(danfeiTime));
                mPlanRecord.setDaifeiNum(Integer.valueOf(daifeiNum));
                mPlanRecord.setDaifeiTime(Integer.valueOf(daifeiTime));
                mPlanRecord.setCheckNum(Integer.valueOf(checkNum));
                mPlanRecord.setCheckTime(Integer.valueOf(checkTime));
                mPlanRecord.setIsFinished(true);
                DataBaseUtils.correctPlanRecord(mPlanRecord);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //关闭页面
        finish();

    }
}
