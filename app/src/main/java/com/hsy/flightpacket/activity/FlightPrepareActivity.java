package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.PrepareAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.PClass;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.views.NoScrollListView;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/27.
 */

public class FlightPrepareActivity extends BaseActivity {
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    @Bind(R.id.tv_create_plan)
    TextView tvCreatePlan;
    @Bind(R.id.lst_prepare1)
    NoScrollListView lstPrepare1;
    @Bind(R.id.lst_prepare2)
    NoScrollListView lstPrepare2;
    @Bind(R.id.lst_prepare3)
    NoScrollListView lstPrepare3;
    @Bind(R.id.tv_class_name1)
    TextView tvClassName1;
    @Bind(R.id.tv_class_name2)
    TextView tvClassName2;
    @Bind(R.id.tv_class_name3)
    TextView tvClassName3;
    ListViewDataAdapter<TrainPlan> dataAdapter1;
    ListViewDataAdapter<TrainPlan> dataAdapter2;
    ListViewDataAdapter<TrainPlan> dataAdapter3;
    @Bind(R.id.rlt_1)
    LinearLayout rlt1;
    @Bind(R.id.rlt_2)
    LinearLayout rlt2;
    @Bind(R.id.rlt_3)
    LinearLayout rlt3;
    @Bind(R.id.tv_create_remind)
    TextView tvCreateRemind;

    private List<PClass> pClasses;


    public static void start(Context context) {
        Intent intent = new Intent(context, FlightPrepareActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_preare;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("起飞准备");
        //添加学生信息
        addStudentList();
        //添加班级信息
        PClass pClass1 = new PClass(null, "九期一班");
        PClass pClass2 = new PClass(null, "九期二班");
        PClass pClass3 = new PClass(null, "九期三班");
        List<PClass> p1 = new ArrayList<>();
        p1.add(pClass1);
        p1.add(pClass2);
        p1.add(pClass3);
        pClasses = DataBaseUtils.searchPClass();
        if (pClasses.size() == 0) {
            DataBaseUtils.savePClass(p1);
        }
        //搜索班级
        pClasses = DataBaseUtils.searchPClass();
        //一班
        lstPrepare1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TrainPlan trainPlan = dataAdapter1.getDataList().get(i);
                String date = format1.format(trainPlan.getTrainTime());
                //点击跳转到详情
                FlightPrepareDateListActivity.start(FlightPrepareActivity.this, date, trainPlan.getClassId(), "九期一班");
            }
        });
        //配置适配器
        dataAdapter1 = new ListViewDataAdapter<>(new ViewHolderCreator<TrainPlan>() {
            @Override
            public ViewHolderBase<TrainPlan> createViewHolder() {
                return new PrepareAdapter(false);
            }
        });
        lstPrepare1.setAdapter(dataAdapter1);
        //一班
        lstPrepare2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TrainPlan trainPlan = dataAdapter2.getDataList().get(i);
                String date = format1.format(trainPlan.getTrainTime());
                //点击跳转到详情
                FlightPrepareDateListActivity.start(FlightPrepareActivity.this, date, trainPlan.getClassId(), "九期二班");
            }
        });
        //配置适配器
        dataAdapter2 = new ListViewDataAdapter<>(new ViewHolderCreator<TrainPlan>() {
            @Override
            public ViewHolderBase<TrainPlan> createViewHolder() {
                return new PrepareAdapter(false);
            }
        });
        lstPrepare2.setAdapter(dataAdapter2);
        //一班
        lstPrepare3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TrainPlan trainPlan = dataAdapter3.getDataList().get(i);
                String date = format1.format(trainPlan.getTrainTime());
                //点击跳转到详情
                FlightPrepareDateListActivity.start(FlightPrepareActivity.this, date, trainPlan.getClassId(), "九期三班");
            }
        });
        //配置适配器
        dataAdapter3 = new ListViewDataAdapter<>(new ViewHolderCreator<TrainPlan>() {
            @Override
            public ViewHolderBase<TrainPlan> createViewHolder() {
                return new PrepareAdapter(false);
            }
        });
        lstPrepare3.setAdapter(dataAdapter3);
        //人员进度
        getBaseNavigationBar().setRightBtnOne(R.drawable.zhunbei_icon_xueyuan, null, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentListActivity.start(FlightPrepareActivity.this);
            }
        });
    }

    Format f = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 去重
     *
     * @param list
     * @return
     */
    public List<TrainPlan> removeDuplicate(List<TrainPlan> list) {
        List<TrainPlan> listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (listTemp.size() > 0) {
                boolean isSame = false;
                for (int j = 0; j < listTemp.size(); j++) {
                    String time1 = f.format(listTemp.get(j).getTrainTime());
                    String time2 = f.format(list.get(i).getTrainTime());
                    if (time1.equals(time2)) {
                        isSame = true;
                    }
                }
                if (!isSame) {
                    listTemp.add(list.get(i));
                }
            } else {
                listTemp.add(list.get(i));
            }

        }
        return listTemp;
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<TrainPlan> trainPlen1 = DataBaseUtils.searchTrainPlanByClassId(pClasses.get(0).getId());
        if (trainPlen1.size() > 0) {
            //清除所有列表
            dataAdapter1.getDataList().clear();
            dataAdapter1.getDataList().addAll(removeDuplicate(trainPlen1));
            dataAdapter1.notifyDataSetChanged();
            tvClassName1.setText(pClasses.get(0).getClassName());
            lstPrepare1.setVisibility(View.VISIBLE);
            rlt1.setVisibility(View.VISIBLE);
        }
        List<TrainPlan> trainPlen2 = DataBaseUtils.searchTrainPlanByClassId(pClasses.get(1).getId());
        if (trainPlen2.size() > 0) {
            //清除所有列表
            dataAdapter2.getDataList().clear();
            dataAdapter2.getDataList().addAll(removeDuplicate(trainPlen2));
            dataAdapter2.notifyDataSetChanged();
            tvClassName2.setText(pClasses.get(1).getClassName());
            lstPrepare2.setVisibility(View.VISIBLE);
            rlt2.setVisibility(View.VISIBLE);
        }

        List<TrainPlan> trainPlen3 = DataBaseUtils.searchTrainPlanByClassId(pClasses.get(2).getId());
        if (trainPlen3.size() > 0) {
            //清除所有列表
            dataAdapter3.getDataList().clear();
            dataAdapter3.getDataList().addAll(removeDuplicate(trainPlen3));
            dataAdapter3.notifyDataSetChanged();
            tvClassName3.setText(pClasses.get(2).getClassName());
            lstPrepare3.setVisibility(View.VISIBLE);
            rlt3.setVisibility(View.VISIBLE);
        }

        //显示创建view
        if (dataAdapter1.getDataList().size() > 0 || dataAdapter2.getDataList().size() > 0 || dataAdapter3.getDataList().size() > 0) {
            tvCreateRemind.setVisibility(View.GONE);
        } else {
            tvCreateRemind.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.tv_create_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create_plan:
                CreatePlanActivity.start(this);
                break;
        }
    }


    /**
     * 加载学员并保存
     */
    private void addStudentList() {
        //加载6名学员
        try {
            List<Student> students = new ArrayList<>();
            if (students.size() == 0) {
                Student student1 = new Student(null, "王一山", "LH060301", "113", "山东青岛", "汉", format1.parse("1991-05-13"), format1.parse("2017-06-03"), format1.parse("2015-06-06"), "本科");
                Student student2 = new Student(null, "王二山", "LH060301", "113", "山东青岛", "汉", format1.parse("1991-05-13"), format1.parse("2017-06-03"), format1.parse("2015-06-06"), "本科");
                Student student3 = new Student(null, "王三山", "LH060301", "113", "山东青岛", "汉", format1.parse("1991-05-13"), format1.parse("2017-06-03"), format1.parse("2015-06-06"), "本科");
                Student student4 = new Student(null, "王四山", "LH060301", "113", "山东青岛", "汉", format1.parse("1991-05-13"), format1.parse("2017-06-03"), format1.parse("2015-06-06"), "本科");
                Student student5 = new Student(null, "王五山", "LH060301", "113", "山东青岛", "汉", format1.parse("1991-05-13"), format1.parse("2017-06-03"), format1.parse("2015-06-06"), "本科");
                Student student6 = new Student(null, "王六山", "LH060301", "113", "山东青岛", "汉", format1.parse("1991-05-13"), format1.parse("2017-06-03"), format1.parse("2015-06-06"), "本科");
                students.add(student1);
                students.add(student2);
                students.add(student3);
                students.add(student4);
                students.add(student5);
                students.add(student6);
                if (DataBaseUtils.searchStudent().size() == 0) {
                    DataBaseUtils.saveStudents(students);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
