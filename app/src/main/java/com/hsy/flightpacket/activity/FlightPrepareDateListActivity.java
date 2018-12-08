package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.PrepareAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.Student;
import com.hsy.flightpacket.bean.dao.TrainPlan;
import com.hsy.flightpacket.db.DataBaseUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/11.
 */

public class FlightPrepareDateListActivity extends BaseActivity {
    @Bind(R.id.lst_date)
    ListView listView;
    ListViewDataAdapter<TrainPlan> dataAdapter1;

    public static final String KEY_DATE = "KEY_DATE";
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_CLASS_ID = "key_class_id";
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    private String date;
    private Date mDate;
    private String title;
    private Long classId;

    public static void start(Context context, String date, Long classId, String title) {
        Intent intent = new Intent(context, FlightPrepareDateListActivity.class);
        intent.putExtra(KEY_DATE, date);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_CLASS_ID, classId);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_flight_prepare_date_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("起飞准备");
        date = getIntent().getStringExtra(KEY_DATE);
        title = getIntent().getStringExtra(KEY_TITLE);
        classId = getIntent().getLongExtra(KEY_CLASS_ID, 0);
        try {
            mDate = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        getBaseNavigationBar().setTitle(title);

        //一班
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //点击跳转到详情
                TrainPlanActivity.start(FlightPrepareDateListActivity.this, dataAdapter1.getDataList().get(i).getId());
            }
        });
        //配置适配器
        dataAdapter1 = new ListViewDataAdapter<>(new ViewHolderCreator<TrainPlan>() {
            @Override
            public ViewHolderBase<TrainPlan> createViewHolder() {
                return new PrepareAdapter(true);
            }
        });
        listView.setAdapter(dataAdapter1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<TrainPlan> trainPlen1 = DataBaseUtils.searchTrainPlanByDate(mDate, classId);
        if (trainPlen1.size() > 0) {
            //清除所有列表
            dataAdapter1.getDataList().clear();
            dataAdapter1.getDataList().addAll(trainPlen1);
            dataAdapter1.notifyDataSetChanged();
        }
    }

}