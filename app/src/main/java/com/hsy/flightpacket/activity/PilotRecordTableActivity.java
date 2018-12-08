package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.PilotRecordAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.db.DataBaseUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/6/27.
 */

public class PilotRecordTableActivity extends BaseActivity {
    public static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final String KEY_PILOT_ID = "key_pilot_id";
    public static final String KEY_LEFT_ID = "key_left_id";
    ListViewDataAdapter<PilotRecordTable> dataAdapter;
    @Bind(R.id.lst_content)
    ListView lstContent;

    private List<PilotRecordTable> recordTables = new ArrayList<>();

    private Long pilotBeanId;

    private Boolean isLeft;

    public static void start(Context context, long pilotBeanId, Boolean isLeft) {
        Intent intent = new Intent(context, PilotRecordTableActivity.class);
        intent.putExtra(KEY_PILOT_ID, pilotBeanId);
        intent.putExtra(KEY_LEFT_ID, isLeft);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_record;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("领航记录表");
        isLeft = getIntent().getBooleanExtra(KEY_LEFT_ID, false);
        pilotBeanId = getIntent().getLongExtra(KEY_PILOT_ID, 0);
        lstContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //PilotRecordInputActivity.start(PilotRecordTableActivity.this, recordTables.get(i).getId());
            }
        });
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<PilotRecordTable>() {
            @Override
            public ViewHolderBase<PilotRecordTable> createViewHolder() {
                return new PilotRecordAdapter();
            }
        });
        lstContent.setAdapter(dataAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            List<PilotRecordTable> pilotRecordTables = DataBaseUtils.searchPilotRecord();
            if (pilotRecordTables.size() == 0) {
                PilotRecordTable recordTable1 = new PilotRecordTable(null, "起飞", format1.parse("2018-07-05 12:00"), null, 0, 0, 0, 0, 0, 0, null, pilotBeanId, isLeft, null, null, null, null);
                PilotRecordTable recordTable2 = new PilotRecordTable(null, "起点", format1.parse("2018-07-05 12:00"), null, 0, 0, 0, 0, 0, 0, null, pilotBeanId, isLeft, null, null, null, null);
                PilotRecordTable recordTable3 = new PilotRecordTable(null, "孔滩镇", format1.parse("2018-07-05 12:00"), null, 0, 0, 0, 0, 0, 0, null, pilotBeanId, isLeft, null, null, null, null);
                PilotRecordTable recordTable4 = new PilotRecordTable(null, "JD", format1.parse("2018-07-05 12:00"), null, 0, 0, 0, 0, 0, 0, null, pilotBeanId, isLeft, null, null, null, null);
                PilotRecordTable recordTable5 = new PilotRecordTable(null, "自贡", format1.parse("2018-07-05 12:00"), null, 0, 0, 0, 0, 0, 0, null, pilotBeanId, isLeft, null, null, null, null);
                PilotRecordTable recordTable6 = new PilotRecordTable(null, "WD", format1.parse("2018-07-05 12:00"), null, 0, 0, 0, 0, 0, 0, null, pilotBeanId, isLeft, null, null, null, null);
                recordTables.add(recordTable1);
                recordTables.add(recordTable2);
                recordTables.add(recordTable3);
                recordTables.add(recordTable4);
                recordTables.add(recordTable5);
                recordTables.add(recordTable6);
                DataBaseUtils.savePilotRecord(recordTables);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        recordTables = DataBaseUtils.searchPilotRecord();
        //清除所有列表
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(recordTables);
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
