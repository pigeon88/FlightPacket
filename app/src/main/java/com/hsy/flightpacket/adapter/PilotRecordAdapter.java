package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.PilotRecordTable;
import com.hsy.flightpacket.util.TimeFormat;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class PilotRecordAdapter extends ViewHolderBase<PilotRecordTable> {
    @Bind(R.id.cb_choose6)
    CheckBox cbChoose6;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_yuji)
    TextView tvYuji;
    @Bind(R.id.tv_shiji)
    TextView tvShiji;
    @Bind(R.id.tv_check6)
    ImageView tvCheck6;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_pilot_record, null);
    }

    @Override
    public void showData(int position, PilotRecordTable pt) {
        if (pt != null) {
            tvName.setText(pt.getTitle());
            if (pt.getYuqiTime() != null) {
                tvYuji.setText("预计时刻：" + TimeFormat.getStringDate(pt.getYuqiTime()));
            } else {
                tvYuji.setVisibility(View.GONE);
            }
            if (pt.getShijiTime() != null) {
                tvShiji.setText("实际时刻" + TimeFormat.getStringDate(pt.getShijiTime()));
            } else {
                tvShiji.setVisibility(View.GONE);
            }

        }
    }
}
