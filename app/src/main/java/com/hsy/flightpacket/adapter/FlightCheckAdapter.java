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
 * Created by xiongweimin on 2018/7/4.
 */

public class FlightCheckAdapter extends ViewHolderBase<FlightCheck> {


    @Bind(R.id.cb_choose6)
    CheckBox cbChoose6;
    @Bind(R.id.cb_point_6)
    CheckBox cbPoint6;
    @Bind(R.id.tv_check6)
    TextView tvCheck6;
    @Bind(R.id.rlt_6)
    RelativeLayout rlt6;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.des)
    TextView des;
    int type;
    @Bind(R.id.cb_delete)
    CheckBox cbDelete;
    ChooseListener chooseListener;

    public FlightCheckAdapter(int type, ChooseListener chooseListener) {
        this.type = type;
        this.chooseListener = chooseListener;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_check, null);
    }

    @Override
    public void showData(final int position, FlightCheck flightCheck) {
        if (flightCheck != null) {
            title.setText(flightCheck.getTitle());
            des.setText(flightCheck.getDes());
            if (type == 0) {
                if (flightCheck.isShowDelete) {
                    cbChoose6.setVisibility(View.GONE);
                    cbDelete.setVisibility(View.VISIBLE);
                    if (flightCheck.isChooseDelete) {
                        cbDelete.setChecked(true);
                    } else {
                        cbDelete.setChecked(false);
                    }
                } else {
                    cbChoose6.setVisibility(View.VISIBLE);
                    cbDelete.setVisibility(View.GONE);
                    if (flightCheck.getIsChecked()) {
                        cbChoose6.setChecked(true);
                        cbPoint6.setChecked(true);
                    } else {
                        cbChoose6.setChecked(false);
                        cbPoint6.setChecked(false);
                    }
                }

            } else if (type == 1) {
                cbDelete.setVisibility(View.GONE);
                cbChoose6.setVisibility(View.VISIBLE);
                cbChoose6.setChecked(true);
                cbPoint6.setChecked(true);
            }
            //删除选择
            cbDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (chooseListener != null) {
                        chooseListener.checked(position, b);
                    }
                }
            });
        }
    }


    public interface ChooseListener {
        void checked(int index, boolean check);
    }


}
