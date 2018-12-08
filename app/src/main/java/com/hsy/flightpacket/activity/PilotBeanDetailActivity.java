package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.hsy.flightpacket.R;
import com.hsy.flightpacket.activity.report.PilotRecordsActivity;
import com.hsy.flightpacket.adapter.PilotBeanDetailAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.dao.PilotBean;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.util.TimeFormat;
import com.hsy.flightpacket.views.NoScrollListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/8/3.
 */

public class PilotBeanDetailActivity extends BaseActivity {

    public static final String KEY_PILOT_ID = "key_pilot_id";
    @Bind(R.id.pilot_bean_detail_input_time)
    TextView pilotBeanDetailInputTime;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.title_3)
    TextView title3;
    @Bind(R.id.pilot_bean_detail_input_model)
    TextView pilotBeanDetailInputModel;
    @Bind(R.id.title4)
    TextView title4;
    @Bind(R.id.pilot_bean_detail_input_flyer_num)
    TextView pilotBeanDetailInputFlyerNum;
    @Bind(R.id.tv_title5)
    TextView tvTitle5;
    @Bind(R.id.pilot_bean_detail_input_piloter_num)
    TextView pilotBeanDetailInputPiloterNum;
    @Bind(R.id.tv_title7)
    TextView tvTitle7;
    @Bind(R.id.tv_out)
    ImageView tvOut;
    @Bind(R.id.tv_title6)
    TextView tvTitle6;
    @Bind(R.id.tv_detail_back)
    ImageView tvDetailBack;
    @Bind(R.id.tv_navi_detail_content)
    TextView tvNaviDetailContent;
    @Bind(R.id.nlv_left)
    NoScrollListView nlvLeft;
    @Bind(R.id.nlv_right)
    NoScrollListView nlvRight;
    @Bind(R.id.pilot_bean_detail_input_time1)
    TextView pilotBeanDetailInputTime1;
    @Bind(R.id.pilot_bean_detail_input_time2)
    TextView pilotBeanDetailInputTime2;

    private Long pilotId;

    private PilotBean pilotBean;

    private List<PilotRecordTable> recordTablesLeft;
    private List<PilotRecordTable> recordTablesRight;
    private ListViewDataAdapter<PilotRecordTable> dataAdapter1;
    private ListViewDataAdapter<PilotRecordTable> dataAdapter2;

    private String mTimeZhuo;
    private String mTimeXu;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_detail;
    }


    public static void start(Context context, Long id) {
        Intent intent = new Intent(context, PilotBeanDetailActivity.class);
        intent.putExtra(KEY_PILOT_ID, id);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("领航记录表");
        getBaseNavigationBar().setRightBtnOne(R.mipmap.ic_table, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //左表数据
                List<PilotRecordTable> recordTablesLeft = DataBaseUtils.searchPilotRecordTableByIdLeft(pilotId, true);
                //右表数据
                List<PilotRecordTable> recordTablesRight = DataBaseUtils.searchPilotRecordTableByIdLeft(pilotId, false);
                //左表底部信息\右表 (机型 、飞行员、领航员等信息)
                PilotBean pilotBean = DataBaseUtils.loadPilotBean(pilotId);

                PilotRecordsActivity.start(PilotBeanDetailActivity.this, recordTablesLeft, pilotBean, recordTablesRight);
            }
        });
        pilotId = getIntent().getLongExtra(KEY_PILOT_ID, 0);
        //加载对应Id信息
        pilotBean = DataBaseUtils.loadPilotBean(pilotId);
        recordTablesLeft = DataBaseUtils.searchPilotRecordTableByIdLeft(pilotId, true);
        recordTablesRight = DataBaseUtils.searchPilotRecordTableByIdLeft(pilotId, false);
        //配置适配器
        dataAdapter1 = new ListViewDataAdapter<>(new ViewHolderCreator<PilotRecordTable>() {
            @Override
            public ViewHolderBase<PilotRecordTable> createViewHolder() {
                return new PilotBeanDetailAdapter();
            }
        });
        nlvLeft.setAdapter(dataAdapter1);
        nlvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PilotRecordInputActivity.start(PilotBeanDetailActivity.this, recordTablesLeft.get(position).getId(),true);
            }
        });
        //配置适配器
        dataAdapter2 = new ListViewDataAdapter<>(new ViewHolderCreator<PilotRecordTable>() {
            @Override
            public ViewHolderBase<PilotRecordTable> createViewHolder() {
                return new PilotBeanDetailAdapter();
            }
        });
        nlvRight.setAdapter(dataAdapter2);
        nlvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PilotRecordInputActivity.start(PilotBeanDetailActivity.this, recordTablesRight.get(position).getId(),false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<PilotRecordTable> recordTables1 = DataBaseUtils.searchPilotRecordTableByIdLeft(pilotBean.getId(), true);
        //获取数据列表左
        dataAdapter1.getDataList().clear();
        dataAdapter1.getDataList().addAll(recordTables1);
        dataAdapter1.notifyDataSetChanged();
        //获取列表数据右
        List<PilotRecordTable> recordTables2 = DataBaseUtils.searchPilotRecordTableByIdLeft(pilotBean.getId(), false);
        dataAdapter2.getDataList().clear();
        dataAdapter2.getDataList().addAll(recordTables2);
        dataAdapter2.notifyDataSetChanged();

        //设置标题
        pilotBeanDetailInputTime.setText(TimeFormat.getStringYMDHM(pilotBean.getDateTime()) + "  " + pilotBean.getTitle());
        pilotBeanDetailInputModel.setText(pilotBean.getModel());
        pilotBeanDetailInputFlyerNum.setText(pilotBean.getFlyer());
        pilotBeanDetailInputPiloterNum.setText(pilotBean.getPilot());
        tvOut.setImageBitmap(BitmapFactory.decodeFile(pilotBean.getGoOutPic()));
        tvDetailBack.setImageBitmap(BitmapFactory.decodeFile(pilotBean.getReturnPic()));
        tvNaviDetailContent.setText(pilotBean.getNaviInfo());
        //获取时间
        if (pilotBean.getLanding() != null) {
            pilotBeanDetailInputTime1.setText(TimeFormat.getStringYMDHM(pilotBean.getLanding()));
        }
        if (pilotBean.getRenewal() != null) {
            pilotBeanDetailInputTime2.setText(TimeFormat.getStringYMDHM(pilotBean.getRenewal()));
        }

    }

    @OnClick({R.id.pilot_bean_detail_input_time1, R.id.pilot_bean_detail_input_time2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pilot_bean_detail_input_time1:
                DatePickDialog dialog1 = new DatePickDialog(this);
                //设置上下年分限制
                dialog1.setYearLimt(5);
                //设置标题
                dialog1.setTitle("选择时间");
                //设置类型
                dialog1.setType(DateType.TYPE_YMDHM);
                //设置消息体的显示格式，日期格式
                dialog1.setMessageFormat("yyyy-MM-dd HH:mm");
                //设置选择回调
                dialog1.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog1.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        pilotBeanDetailInputTime1.setText(TimeFormat.getStringYMDHM(date));
                        mTimeZhuo = TimeFormat.getStringYMDHM(date);
                        pilotBean.setLanding(date);
                        DataBaseUtils.updatePilotBean(pilotBean);
                    }
                });
                dialog1.show();
                break;
            case R.id.pilot_bean_detail_input_time2:
                DatePickDialog dialog2 = new DatePickDialog(this);
                //设置上下年分限制
                dialog2.setYearLimt(5);
                //设置标题
                dialog2.setTitle("选择时间");
                //设置类型
                dialog2.setType(DateType.TYPE_YMDHM);
                //设置消息体的显示格式，日期格式
                dialog2.setMessageFormat("yyyy-MM-dd HH:mm");
                //设置选择回调
                dialog2.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog2.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        pilotBeanDetailInputTime2.setText(TimeFormat.getStringYMDHM(date));
                        mTimeXu = TimeFormat.getStringYMDHM(date);
                        pilotBean.setRenewal(date);
                        DataBaseUtils.updatePilotBean(pilotBean);
                    }
                });
                dialog2.show();
                break;
        }
    }
}
