package com.hsy.flightpacket.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.DayPlan;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/8/3.
 */

public class ComDayPlanAdapter extends ViewHolderBase<DayPlan> {


    @Bind(R.id.tv_show_jihao)
    TextView tvShowJihao;
    @Bind(R.id.tv_show_jizhang)
    TextView tvShowJizhang;
    @Bind(R.id.tv_show_zhengjia)
    TextView tvShowZhengjia;
    @Bind(R.id.tv_show_fujia)
    TextView tvShowFujia;
    @Bind(R.id.llt_try_fly_content)
    LinearLayout lltTryFlyContent;
    @Bind(R.id.tv_show_lianxihao)
    TextView tvShowLianxihao;
    @Bind(R.id.tv_show_cishu)
    TextView tvShowCishu;
    @Bind(R.id.tv_zhengjia)
    TextView tvZhengjia;
    @Bind(R.id.tv_fujia)
    TextView tvFujia;
    @Bind(R.id.tv_show_gaodu)
    TextView tvShowGaodu;
    @Bind(R.id.tv_show_hangxianhao)
    TextView tvShowHangxianhao;
    @Bind(R.id.llt_show_arrow)
    LinearLayout lltShowArrow;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_string, null);
    }

    @Override
    public void showData(int position, DayPlan dayPlan) {
        if (dayPlan != null) {
            tvShowJihao.setText(dayPlan.jihao);
            tvShowJizhang.setText(dayPlan.jizhang);
            if (!TextUtils.isEmpty(dayPlan.zhengjia) && !TextUtils.isEmpty(dayPlan.fujia)) {
                tvShowZhengjia.setText(dayPlan.zhengjia);
                tvShowFujia.setText(dayPlan.fujia);
                lltTryFlyContent.setVisibility(View.VISIBLE);
            } else {
                lltTryFlyContent.setVisibility(View.GONE);
            }
            tvShowLianxihao.setText(dayPlan.lianxihao);
            tvShowCishu.setText(dayPlan.cishu);
            tvZhengjia.setText(dayPlan.zhengjia1);
            tvFujia.setText(dayPlan.fujia1);
            tvShowGaodu.setText(dayPlan.gaodu);
            tvShowHangxianhao.setText(dayPlan.hangxianhao);
            lltShowArrow.setVisibility(dayPlan.isJiayou ? View.VISIBLE : View.GONE);
        }
    }


}
