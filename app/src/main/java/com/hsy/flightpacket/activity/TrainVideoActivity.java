package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.TrainVideoAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.Video;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by xiongweimin on 2018/6/28.
 */

public class TrainVideoActivity extends BaseActivity {

    public static final String KEY_VIDEOS = "key_videos";
    @Bind(R.id.gv)
    GridView gv;

    ListViewDataAdapter<Video> dataAdapter;

    private String videoUrl;

    private ArrayList<Video> videos;

    public static void start(Context context, ArrayList<Video> videos) {
        Intent intent = new Intent(context, TrainVideoActivity.class);
        intent.putExtra(KEY_VIDEOS, videos);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_train_video;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("训练视频");
        videos = (ArrayList<Video>) getIntent().getSerializableExtra(KEY_VIDEOS);
        initAdapter();
    }


    /**
     * 初始化适配器
     */
    private void initAdapter() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                videoUrl = videos.get(i).getUrl();
                //跳转到详情
                VideoPlayActivity.start(TrainVideoActivity.this, i, videos);

            }
        });
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<Video>() {
            @Override
            public ViewHolderBase<Video> createViewHolder() {
                return new TrainVideoAdapter();
            }
        });
        gv.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(videos);
        dataAdapter.notifyDataSetChanged();
    }


}
