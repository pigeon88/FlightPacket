package com.hsy.flightpacket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.bean.Video;
import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/7/17.
 */

public class TrainVideoAdapter extends ViewHolderBase<Video> {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_train_video, null);
    }

    @Override
    public void showData(int position, Video video) {
        if (video != null) {
            tvTitle.setText(getFileNames(video.getUrl()));
        }
    }

    /**
     * 获取文件名
     *
     * @return
     */
    public String getFileNames(String name) {
        return name.substring(name.lastIndexOf("/") + 1, name.length());
    }
}
