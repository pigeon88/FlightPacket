package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.StudentProAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.views.NoScrollListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class StuTrainProActivity extends BaseActivity {


    @Bind(R.id.lst_student)
    NoScrollListView lstStudent;

    ListViewDataAdapter<Student> dataAdapter;

    List<Student> students;

    public static void start(Context context) {
        Intent intent = new Intent(context, StuTrainProActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_stu_train_pro;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("学员训练进度");
        initAdapter();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<Student>() {
            @Override
            public ViewHolderBase<Student> createViewHolder() {
                return new StudentProAdapter();
            }
        });
        lstStudent.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取所有学员
        students = DataBaseUtils.searchStudent();
        //加到列表中
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(students);
        dataAdapter.notifyDataSetChanged();
    }
}
