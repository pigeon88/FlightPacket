package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.db.DataBaseUtils;
import com.hsy.flightpacket.util.TimeFormat;

import java.text.ParseException;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/27.
 */

public class PilotRecordInputActivity extends BaseActivity {

    public static final String KEY_PILOT_ID = "key_pilot_id";
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.llt_bottom)
    LinearLayout lltBottom;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_yuqi)
    TextView tvYuqi;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_title2)
    TextView tvTitle2;
    @Bind(R.id.end2)
    TextView end2;
    @Bind(R.id.tv_cihangxiang)
    EditText tvCihangxiang;
    @Bind(R.id.title_3)
    TextView title3;
    @Bind(R.id.end3)
    TextView end3;
    @Bind(R.id.tv_pianliu)
    EditText tvPianliu;
    @Bind(R.id.title4)
    TextView title4;
    @Bind(R.id.end4)
    TextView end4;
    @Bind(R.id.tv_cihangguiji)
    EditText tvCihangguiji;
    @Bind(R.id.tv_title5)
    TextView tvTitle5;
    @Bind(R.id.end5)
    TextView end5;
    @Bind(R.id.tv_disu)
    EditText tvDisu;
    @Bind(R.id.tv_title6)
    TextView tvTitle6;
    @Bind(R.id.end6)
    TextView end6;
    @Bind(R.id.tv_kongsu)
    EditText tvKongsu;
    @Bind(R.id.tv_title7)
    TextView tvTitle7;
    @Bind(R.id.end7)
    TextView end7;
    @Bind(R.id.tv_gaodu)
    EditText tvGaodu;
    @Bind(R.id.tv_fuji)
    EditText tvFuji;
    @Bind(R.id.tv_didian_daihao)
    EditText edtDidianDaihao;
    @Bind(R.id.tv_jingdu)
    EditText tvJingdu;
    @Bind(R.id.tv_weidu)
    EditText tvWeidu;
    @Bind(R.id.tv_juli)
    EditText edtJuli;
    @Bind(R.id.rlt_juli)
    RelativeLayout rlt_juli;


    private long pilotId;

    private PilotRecordTable recordTable;

    private Date mShijiTime;
    private Date mYuQiTime;
    private boolean isLeft;

    public static void start(Context context, long id, boolean isLeft) {
        Intent intent = new Intent(context, PilotRecordInputActivity.class);
        intent.putExtra(KEY_PILOT_ID, id);
        intent.putExtra("isLeft", isLeft);
        context.startActivity(intent);
    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_pilot_record_input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("领航记录");
        // CommUtils.showSoftInputFromWindow(this, tvHangxiang);
        pilotId = getIntent().getLongExtra(KEY_PILOT_ID, 0);
        isLeft = getIntent().getBooleanExtra("isLeft", false);
        //通过id获取本地记录
        recordTable = DataBaseUtils.loadPilotRecord(pilotId);
        //设置标题
        if (recordTable.getShijiTime() != null) {
            mYuQiTime = recordTable.getYuqiTime();
            tvYuqi.setText(TimeFormat.getStringYMDHM(recordTable.getYuqiTime()));
        }
        if (recordTable.getShijiTime() != null) {
            mShijiTime = recordTable.getShijiTime();
            tvTime.setText(TimeFormat.getStringYMDHM(recordTable.getShijiTime()));
        }
        tvTitle.setText(recordTable.getTitle());
        tvCihangxiang.setText(recordTable.getCihangxiang() == 0 ? "" : recordTable.getCihangxiang() + "");
        tvPianliu.setText(recordTable.getPianliu() == 0 ? "" : recordTable.getPianliu() + "");
        tvCihangguiji.setText(recordTable.getCihangguiji() == 0 ? "" : recordTable.getCihangguiji() + "");
        tvDisu.setText(recordTable.getDisu() == 0 ? "" : recordTable.getDisu() + "");
        tvKongsu.setText(recordTable.getKongsu() == 0 ? "" : recordTable.getKongsu() + "");
        tvGaodu.setText(recordTable.getGaodu() == 0 ? "" : recordTable.getGaodu() + "");
        tvFuji.setText(TextUtils.isEmpty(recordTable.getFuji()) ? "" : recordTable.getFuji() + "");
        edtDidianDaihao.setText(TextUtils.isEmpty(recordTable.getDidianDaihao()) ? "" : recordTable.getDidianDaihao() + "");
        tvJingdu.setText(TextUtils.isEmpty(recordTable.getJingdu()) ? "" : recordTable.getJingdu());
        tvWeidu.setText(TextUtils.isEmpty(recordTable.getWeidu()) ? "" : recordTable.getWeidu());
        if (isLeft) {
            rlt_juli.setVisibility(View.VISIBLE);
            edtJuli.setText(recordTable.getJuli() == 0 ? "" : String.valueOf(recordTable.getJuli()));
        } else {
            rlt_juli.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.tv_yuqi, R.id.tv_time, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yuqi:
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
                        tvYuqi.setText(TimeFormat.getStringYMDHM(date));
                        mYuQiTime = date;
                    }
                });
                dialog1.show();
                break;
            case R.id.tv_time:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMDHM);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd HH:mm");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tvTime.setText(TimeFormat.getStringYMDHM(date));
                        mShijiTime = date;
                    }
                });
                dialog.show();
                break;
            case R.id.tv_save:
                String time = tvTime.getText().toString();
                String yuqi = tvYuqi.getText().toString();
                String cihangxiang = tvCihangxiang.getText().toString();
                String Pianliu = tvPianliu.getText().toString();
                String cihangguiji = tvCihangguiji.getText().toString();
                String Disu = tvDisu.getText().toString();
                String Kongsu = tvKongsu.getText().toString();
                String Gaodu = tvGaodu.getText().toString();
                String Fuji = tvFuji.getText().toString();
                String didianDaihao = edtDidianDaihao.getText().toString();
                String jingdu = tvJingdu.getText().toString();
                String weidu = tvWeidu.getText().toString();
                String juli = edtJuli.getText().toString();
                if (mYuQiTime == null) {
                    Toast.makeText(this, "请输入预期时刻", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    recordTable.setYuqiTime(PilotRecordTableActivity.format1.parse(yuqi));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (mShijiTime == null) {
                    Toast.makeText(this, "请输入实际时刻", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    recordTable.setShijiTime(PilotRecordTableActivity.format1.parse(time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(didianDaihao)) {
                    Toast.makeText(this, "请输入地点代号", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setDidianDaihao(didianDaihao);

                if (TextUtils.isEmpty(jingdu)) {
                    Toast.makeText(this, "请输入经度", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setJingdu(jingdu);
                if (TextUtils.isEmpty(weidu)) {
                    Toast.makeText(this, "请输入纬度", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setWeidu(weidu);
                if (isLeft) {
                    if (TextUtils.isEmpty(juli)) {
                        Toast.makeText(this, "请输入距离", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    recordTable.setJuli(Integer.valueOf(juli));
                }
                if (TextUtils.isEmpty(cihangxiang)) {
                    Toast.makeText(this, "请输入磁偏航", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setCihangxiang(Integer.valueOf(cihangxiang));
                if (TextUtils.isEmpty(Pianliu)) {
                    Toast.makeText(this, "请输入偏流角度", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setPianliu(Integer.valueOf(Pianliu));
                if (TextUtils.isEmpty(cihangguiji)) {
                    Toast.makeText(this, "请输入轨迹角", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setCihangguiji(Integer.valueOf(cihangguiji));
                if (TextUtils.isEmpty(Disu)) {
                    Toast.makeText(this, "请输入地速", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setDisu(Integer.valueOf(Disu));
                if (TextUtils.isEmpty(Kongsu)) {
                    Toast.makeText(this, "请输入空速", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setKongsu(Integer.valueOf(Kongsu));
                if (TextUtils.isEmpty(Gaodu)) {
                    Toast.makeText(this, "请输入高度", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setGaodu(Integer.valueOf(Gaodu));
                if (TextUtils.isEmpty(Fuji)) {
                    Toast.makeText(this, "请输入附记", Toast.LENGTH_SHORT).show();
                    return;
                }
                recordTable.setFuji(Fuji);
                DataBaseUtils.correctPilotRecord(recordTable);
                finish();
                break;
        }
    }
}
