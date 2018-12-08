package com.hsy.flightpacket.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.Video;
import com.hsy.flightpacket.util.CommUtils;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/17.
 */

public class VideoChooseAdapter extends ViewHolderBase<Video> {
    @Bind(R.id.tv_video_1)
    TextView tv1;

    private Context mContext;

    public VideoChooseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_video_choose, null);
    }

    @Override
    public void showData(int position, Video video) {
        if (video != null) {
            if (video.isPlaying()) {
                Drawable drawableRight = mContext.getResources().getDrawable(
                        R.mipmap.zhanshu_icon_zbofangzhong);
                tv1.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, drawableRight, null);
                tv1.setTextColor(mContext.getResources().getColor(R.color.blue_comm));
                tv1.setText(CommUtils.getFileNames(video.getUrl()));
            } else {
                tv1.setCompoundDrawablesWithIntrinsicBounds(null,
                        null, null, null);
                tv1.setTextColor(mContext.getResources().getColor(R.color.weather_temp));
                tv1.setText(CommUtils.getFileNames(video.getUrl()));
            }
        }
    }

}
