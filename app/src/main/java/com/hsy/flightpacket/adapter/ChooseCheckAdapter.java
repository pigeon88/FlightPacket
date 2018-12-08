package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.dao.FlightCheck;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/5.
 */

public class ChooseCheckAdapter extends ViewHolderBase<FlightCheck> {
    @Bind(R.id.cb_choose6)
    CheckBox cbChoose6;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.cb_point_6)
    CheckBox cbPoint6;
    @Bind(R.id.des)
    TextView des;
    @Bind(R.id.rlt_6)
    RelativeLayout rlt6;
    CheckListener checkListener;

    public ChooseCheckAdapter(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_choose_check, null);
    }

    @Override
    public void showData(final int position, final FlightCheck flightCheck) {
        if (flightCheck != null) {
            title.setText(flightCheck.getTitle());
            des.setText(flightCheck.getDes());
            cbChoose6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (checkListener != null) {
                        flightCheck.isChoose = b;
                        checkListener.checked(position, b);
                    }
                }
            });
        }
    }

    public interface CheckListener {
        void checked(int index, boolean check);
    }
}
