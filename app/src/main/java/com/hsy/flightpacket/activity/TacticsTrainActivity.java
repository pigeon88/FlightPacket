package com.hsy.flightpacket.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.hsy.flightpacket.R;
import com.hsy.flightpacket.bean.Video;
import com.hsy.flightpacket.util.CommUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/28.
 */

public class TacticsTrainActivity extends BaseActivity {


    @Bind(R.id.llt_video)
    LinearLayout lltVideo;
    @Bind(R.id.llt_document)
    LinearLayout lltDocument;
    @Bind(R.id.llt_pic)
    LinearLayout lltPic;

    private List<String> videos;

    private List<String> pics;

    public static void start(Context context) {
        Intent intent = new Intent(context, TacticsTrainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.tactics_train_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseNavigationBar().setTitle("战术训练");
        getBaseNavigationBar().setRightBtnOne(R.mipmap.ic_search, null, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //搜索
                SearchActivity.start(TacticsTrainActivity.this);
            }
        });
        videos = CommUtils.initFilePaths("FpVideo");
        pics = CommUtils.initFilePaths("FpPic");
        if (videos == null || videos.isEmpty()) {
            Toast.makeText(this, "请在指定文件夹中放入视频", Toast.LENGTH_SHORT).show();
        }
        if (pics == null || pics.isEmpty()) {
            Toast.makeText(this, "请在指定文件夹中放入图片", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.llt_pic, R.id.llt_video, R.id.llt_document})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llt_pic:
                CoursesListActivity.start(this, "训练图片", 1, (ArrayList<String>) pics);
                break;
            case R.id.llt_video:

                ArrayList<Video> vs = new ArrayList<>();
                for (int i = 0; i < videos.size(); i++) {
                    Video video = new Video(videos.get(i), false);
                    vs.add(video);
                }
                TrainVideoActivity.start(this, vs);
                break;
            case R.id.llt_document:
                CoursesListActivity.start(this, "训练文档", 0);
                break;
        }
    }


}
