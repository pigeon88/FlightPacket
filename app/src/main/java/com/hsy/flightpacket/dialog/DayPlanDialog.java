package com.hsy.flightpacket.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.ComDayPlanAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.DayPlan;
import com.hsy.flightpacket.views.NoScrollListView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/8/17.
 */

public class DayPlanDialog extends BaseDialogManager {

    @Bind(R.id.nslv_day_plan)
    NoScrollListView nslvDayPlan;

    private List<DayPlan> mStrings;

    private Context mContext;

    ListViewDataAdapter<DayPlan> dataAdapter;


    DayPlanCallBack mCallBack;

    public DayPlanDialog(Context context, List<DayPlan> strings, DayPlanCallBack callBack) {
        super(context);
        this.mStrings = strings;
        this.mContext = context;
        this.mCallBack = callBack;
        setTitle("请选择日计划");
        setSubTitleGone();
        setBottonBarGone();
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<DayPlan>() {
            @Override
            public ViewHolderBase<DayPlan> createViewHolder() {
                return new ComDayPlanAdapter();
            }
        });
        nslvDayPlan.setAdapter(dataAdapter);
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(mStrings);
        dataAdapter.notifyDataSetChanged();
        nslvDayPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mCallBack != null) {
                    DayPlanDialog.this.dismissDialog();
                    mCallBack.getDayPlan(mStrings.get(position));
                }
            }
        });
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.dialog_layout_day_plan;
    }

    public interface DayPlanCallBack {
        void getDayPlan(DayPlan dayPlan);
    }
}
