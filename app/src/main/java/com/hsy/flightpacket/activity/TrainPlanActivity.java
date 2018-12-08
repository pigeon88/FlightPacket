package com.hsy.flightpacket.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.StudentListAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.DayPlan;
import com.hsy.flightpacket.bean.dao.PlanRecord;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.TeachActivity;
import com.hsy.flightpacket.bean.dao.TeachActivityChild;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.dialog.DayPlanDialog;
import com.hsy.flightpacket.dialog.SignatureDialog;
import com.hsy.flightpacket.util.TimeFormat;
import com.hsy.flightpacket.views.NoScrollListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/28.
 */

public class TrainPlanActivity extends BaseActivity {

    public static final String KEY_TRAIN_ID = "key_train_id";

    DateFormat format1 = new SimpleDateFormat("MM月dd日 yyyy");

    @Bind(R.id.tv_project_title)
    TextView tvProjectTitle;
    @Bind(R.id.tv_project_title_des)
    TextView tvProjectTitleDes;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_train_title)
    TextView tvTrainTitle;
    @Bind(R.id.tv_train_title_des)
    TextView tvTrainTitleDes;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.tv_qianzi)
    ImageView tvQianzi;
    @Bind(R.id.tv_qianzi2)
    ImageView tvQianzi2;
    @Bind(R.id.tv_super_comment)
    EditText tvSuperComment;
    @Bind(R.id.tv_team_comment)
    EditText tvTeamComment;
    TrainPlan trainPlan;
    @Bind(R.id.lst_student)
    NoScrollListView lstStudent;
    @Bind(R.id.tv_super_comment_2)
    EditText editSuperComment2;
    @Bind(R.id.tv_team_comment_2)
    EditText editComment2;
    ListViewDataAdapter<PlanRecord> dataAdapter;

    List<PlanRecord> planRecords;
    @Bind(R.id.tv_jihao)
    TextView tvJihao;
    @Bind(R.id.tv_jizhang)
    TextView tvJizhang;
    @Bind(R.id.tv_zhengjia)
    TextView tvZhengjia;
    @Bind(R.id.tv_fujia)
    TextView tvFujia;
    @Bind(R.id.llt_child_count)
    LinearLayout lltChildCount;
    @Bind(R.id.llt_try_fly_content)
    LinearLayout lltTryFlyContent;
    @Bind(R.id.tv_create_teach_activity)
    TextView tvCreateTeach;
    private Long trainId;
    private int type = 0;
    TeachActivity activity = null;
    List<TeachActivityChild> activityChilds;
    private DayPlan dayPlan;

    public static void start(Context context, Long id) {
        Intent intent = new Intent(context, TrainPlanActivity.class);
        intent.putExtra(KEY_TRAIN_ID, id);
        context.startActivity(intent);
    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_train_plan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("训练计划");
        trainId = getIntent().getLongExtra(KEY_TRAIN_ID, 0);
        trainPlan = DataBaseUtils.searchOneTrainPlan(trainId);
        tvProjectTitleDes.setText(trainPlan.getSubject());
        tvTime.setText(TimeFormat.getStringDate(trainPlan.getTrainTime()));
        tvTrainTitleDes.setText(trainPlan.getTrainContent());
        tvSuperComment.setText(trainPlan.getSupreReview());
        tvTeamComment.setText(trainPlan.getTeamReview());
        editSuperComment2.setText(trainPlan.getSupreReview2());
        editComment2.setText(trainPlan.getTeamReview2());
        //设置pic url
        if (trainPlan.getPicUrl() != null) {
            tvQianzi.setImageBitmap(BitmapFactory.decodeFile(trainPlan.getPicUrl()));
        }
        if (trainPlan.getPicUrl2() != null) {
            tvQianzi2.setImageBitmap(BitmapFactory.decodeFile(trainPlan.getPicUrl2()));
        }
        initAdapter();

    }

    /**
     * 加载适配器
     */
    private void initAdapter() {
        lstStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = DataBaseUtils.searchStudent(planRecords.get(i).getStudentId());
                //点击跳转到详情
                PlanRecordActivity.start(TrainPlanActivity.this, trainId, student.getId(), trainPlan.getSubject(), student.getName() + "(" + student.getCodeName() + ")", TimeFormat.getStringDate(trainPlan.getTrainTime()), trainPlan.getTrainContent());
            }
        });
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<PlanRecord>() {
            @Override
            public ViewHolderBase<PlanRecord> createViewHolder() {
                return new StudentListAdapter(trainId);
            }
        });
        lstStudent.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        planRecords = DataBaseUtils.searchPlanRecordByTrainId(trainId);
        //通过学生id 和当前 trainId查询记录
        dataAdapter.getDataList().clear();
        //加载数据
        dataAdapter.getDataList().addAll(planRecords);
        dataAdapter.notifyDataSetChanged();
        lltChildCount.removeAllViews();
        //获取左边非列表
        List<TeachActivity> teachActivity = DataBaseUtils.searchTeachActivityById(trainPlan.getId());
        if (teachActivity != null && !teachActivity.isEmpty()) {
            activity = teachActivity.get(0);
            tvJihao.setText(activity.getJihao());
            tvJizhang.setText(activity.getJizhanghao());
            tvZhengjia.setText(TextUtils.isEmpty(activity.getZhengjia()) ? "-" : activity.getZhengjia());
            tvFujia.setText(TextUtils.isEmpty(activity.getFujia()) ? "-" : activity.getFujia());
        }
        //如果选择为否时不展示试飞内容
        if (!activity.getShifei()) {
            lltTryFlyContent.setVisibility(View.GONE);
        }
        dayPlan = DataBaseUtils.searchDayPlanDaoTrainId(trainId);
        if (dayPlan != null) {
            tvCreateTeach.setClickable(false);
            setDayPlan(dayPlan);
        }
        getLeftTableInfo();
    }

    /**
     * 获取左表信息
     */
    private void getLeftTableInfo() {
        activityChilds = DataBaseUtils.searchTeachActivityChildById(activity.getId());
        //获取列表信息
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < activityChilds.size(); i++) {
            TeachActivityChild child = activityChilds.get(i);
            View view = inflater.inflate(R.layout.item_child, null);
            TextView lianxihao = view.findViewById(R.id.tv_lianxihao);
            TextView cishu = view.findViewById(R.id.tv_cishu);
            TextView zhengjia = view.findViewById(R.id.tv_zhengjia);
            TextView fujia = view.findViewById(R.id.tv_fujia);
            TextView gaodu = view.findViewById(R.id.tv_gaodu);
            TextView hangxianhao = view.findViewById(R.id.tv_hangxianhao);
            LinearLayout lltArrow = view.findViewById(R.id.llt_arrow);
            lianxihao.setText(child.getLianxihao());
            cishu.setText(child.getCishu());
            zhengjia.setText(child.getZhengjia());
            fujia.setText(child.getFujia());
            gaodu.setText(child.getGaodu());
            hangxianhao.setText(child.getHangxianhao());
            if (child.getJiayou()) {
                lltArrow.setVisibility(View.VISIBLE);
            } else {
                lltArrow.setVisibility(View.GONE);
            }
            lltChildCount.addView(view);
        }
    }

    DayPlanDialog dayPlanDialog;

    @OnClick({R.id.tv_create_teach_activity, R.id.tv_qianzi2, R.id.tv_qianzi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create_teach_activity:
                //获取人日计划列表
                List<DayPlan> dayPlens = new ArrayList<>();
                for (int i = 0; i < activityChilds.size(); i++) {
                    TeachActivityChild child = activityChilds.get(i);
                    DayPlan dayPlan = new DayPlan();
                    dayPlan.jihao = activity.getJihao();
                    dayPlan.jizhang = activity.getJizhanghao();
                    dayPlan.zhengjia = activity.getZhengjia();
                    dayPlan.fujia = activity.getFujia();
                    dayPlan.lianxihao = child.getLianxihao();
                    dayPlan.cishu = child.getCishu();
                    dayPlan.zhengjia1 = child.getZhengjia();
                    dayPlan.fujia1 = child.getFujia();
                    dayPlan.gaodu = child.getGaodu();
                    dayPlan.hangxianhao = child.getHangxianhao();
                    dayPlens.add(dayPlan);
                }
                dayPlanDialog = new DayPlanDialog(this, dayPlens, new DayPlanDialog.DayPlanCallBack() {
                    @Override
                    public void getDayPlan(DayPlan dayPlan) {
                        tvCreateTeach.setClickable(false);
                        dayPlan.trainPlanId = trainId;
                        setDayPlan(dayPlan);
                        DataBaseUtils.insertDayPlanDao(dayPlan);
                    }
                });
                dayPlanDialog.showDialog();
                break;
            case R.id.tv_qianzi2:
                type = 2;
                if (Build.VERSION.SDK_INT < 23) {
                    //签字
                    showSignature();
                } else {
                    //6.0以后权限处理
                    permissionForM();
                }
                break;
            case R.id.tv_qianzi:
                type = 1;
                if (Build.VERSION.SDK_INT < 23) {
                    //签字
                    showSignature();
                } else {
                    //6.0以后权限处理
                    permissionForM();
                }
                break;
        }
    }

    private void setDayPlan(DayPlan dayPlan) {
        String a = "机号：" + dayPlan.jihao + "\n机长：" + dayPlan.jizhang;
        String b = "";
        if (!TextUtils.isEmpty(dayPlan.zhengjia) && !TextUtils.isEmpty(dayPlan.fujia)) {
            b = "\n正驾驶：" + dayPlan.zhengjia + "\n副驾：" + dayPlan.fujia;
        }
        String c = "\n练习号：" + dayPlan.lianxihao + "\n次数：" + dayPlan.cishu;
        String d = "\n正驾驶（学员）：" + dayPlan.zhengjia1 + "\n副驾驶（学员）：" + dayPlan.fujia1;
        String e = "\n高度：" + dayPlan.gaodu + "\n航线号：" + dayPlan.hangxianhao;
        tvCreateTeach.setText(a + b + c + d + e);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束时
        trainPlan.setSupreReview(tvSuperComment.getText().toString());
        trainPlan.setTeamReview(tvTeamComment.getText().toString());
        trainPlan.setSupreReview2(editSuperComment2.getText().toString());
        trainPlan.setTeamReview2(editComment2.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void showSignature() {
        SignatureDialog dialog = new SignatureDialog(this, new SignatureDialog.SureSignature() {
            @Override
            public void sureSignature(String path) {
                if (type == 1) {
                    tvQianzi.setImageBitmap(BitmapFactory.decodeFile(path));
                    //设置pic url
                    trainPlan.setPicUrl(path);

                } else {
                    tvQianzi2.setImageBitmap(BitmapFactory.decodeFile(path));
                    //设置pic url
                    trainPlan.setPicUrl2(path);
                }

                DataBaseUtils.correctTrainPlan(trainPlan);
            }
        });
        dialog.showDialog();
    }

    /**
     * 安卓6.0以上版本权限处理
     */
    private void permissionForM() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        } else {
            showSignature();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showSignature();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
