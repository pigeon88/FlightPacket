package com.hsy.flightpacket.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hsy.flightpacket.R;
import com.hsy.flightpacket.adapter.VideoChooseAdapter;
import com.hsy.flightpacket.baseAdapter.ListViewDataAdapter;
import com.hsy.flightpacket.baseAdapter.ViewHolderBase;
import com.hsy.flightpacket.baseAdapter.ViewHolderCreator;
import com.hsy.flightpacket.bean.Video;
import com.hsy.flightpacket.util.CommUtils;
import com.hsy.flightpacket.util.PixelUtil;
import com.hsy.flightpacket.views.CustomVideoView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by xiongweimin on 2018/6/29.
 */

public class VideoPlayActivity extends BaseActivity {

    public static final int UPDATE_UI = 1;
    public static final String KEY_VIDEOS = "key_videos";
    public static final String KEY_START_INDEX = "key_start_index";
    @Bind(R.id.tv_list)
    TextView tvList;
    @Bind(R.id.llt_course_lst)
    LinearLayout lltCourseLst;
    @Bind(R.id.lst_video)
    ListView listView;
    @Bind(R.id.llt_top)
    LinearLayout lltTop;
    @Bind(R.id.llt_bottom)
    LinearLayout lltBottom;
    @Bind(R.id.title)
    TextView tvTitle;
    private CustomVideoView videoView;
    private LinearLayout controller_layout;
    private ImageView play_controller_img, volume_img;
    private TextView time_current_tv, time_total_tv;
    private SeekBar play_seek, volume_seek;
    private int screen_width, screen_height;
    private RelativeLayout videoLayout;
    private AudioManager mAudioManager;
    private ImageView operation_bg, operation_percent;
    private LinearLayout progress_layout;
    private boolean isAdjust = false;
    private int threshold = 108;
    private boolean isFullScreen = true;
    private boolean isShowingControl = true;
    private ArrayList<Video> videos;
    private int videoIndex;

    ListViewDataAdapter<Video> dataAdapter;

    public static void start(Context context, int index, List<Video> videos) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(KEY_VIDEOS, (Serializable) videos);
        intent.putExtra(KEY_START_INDEX, index);
        context.startActivity(intent);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏无状态栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        getBaseNavigationBar().setVisibility(View.GONE);
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        videos = (ArrayList<Video>) getIntent().getSerializableExtra(KEY_VIDEOS);
        videoIndex = getIntent().getIntExtra(KEY_START_INDEX, 0);
        PixelUtil.initContext(this);
        initUI();
        //设置uri
        // videoView.setVideoURI(Uri.parse("android.resource://com.hsy.flightpacket/" + R.raw.video));
        if (videos != null && !videos.isEmpty()) {
            tvTitle.setText(CommUtils.getFileNames(videos.get(videoIndex).getUrl()));
            Uri uri = Uri.parse(videos.get(videoIndex).getUrl());
            //videoView.setVideoPath(videos.get(videoIndex).getUrl());
            videoView.setVideoURI(uri);
            videos.get(videoIndex).setPlaying(true);
            videoView.start();
            //初始化view
            UIhandler.sendEmptyMessage(UPDATE_UI);
            videoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isShowingControl) {
                        if (View.VISIBLE == lltCourseLst.getVisibility()) {
                            lltCourseLst.setVisibility(View.GONE);
                            return;
                        }
                        lltBottom.setVisibility(View.GONE);
                        lltTop.setVisibility(View.GONE);
                        isShowingControl = false;
                    } else {
                        lltBottom.setVisibility(View.VISIBLE);
                        lltTop.setVisibility(View.VISIBLE);
                        isShowingControl = true;
                    }
                }
            });
        }
        //
        initAdapter();
    }


    @Override
    protected void onResume() {
        super.onResume();
        dataAdapter.getDataList().clear();
        dataAdapter.getDataList().addAll(videos);
        dataAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //设置标题
                tvTitle.setText(CommUtils.getFileNames(videos.get(i).getUrl()));
                Uri uri = Uri.parse(videos.get(i).getUrl());
                //设置视频路径
                // videoView.setVideoPath(videos.get(i).getUrl());
                videoView.setVideoURI(uri);
                for (int j = 0; j < videos.size(); j++) {
                    videos.get(j).setPlaying(false);
                }
                videos.get(i).setPlaying(true);
                dataAdapter.notifyDataSetChanged();

            }
        });
        //配置适配器
        dataAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<Video>() {
            @Override
            public ViewHolderBase<Video> createViewHolder() {
                return new VideoChooseAdapter(VideoPlayActivity.this);
            }
        });
        listView.setAdapter(dataAdapter);
    }


    private void initUI() {
        videoView = findViewById(R.id.video);
        controller_layout = findViewById(R.id.controllerbar_layout);
        play_controller_img = findViewById(R.id.pause_img);
        volume_img = findViewById(R.id.volume_img);
        time_total_tv = findViewById(R.id.time_total_tv);
        time_current_tv = findViewById(R.id.time_current_tv);
        play_seek = findViewById(R.id.play_seek);
        volume_seek = findViewById(R.id.volume_seek);
        videoLayout = findViewById(R.id.videoLayout);
        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;
        operation_bg = findViewById(R.id.operation_bg);
        operation_percent = findViewById(R.id.operation_percent);
        progress_layout = findViewById(R.id.progress_layout);

        /*
         * 当前设备的最大音量
         */
        int streamMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        /*
         * 获取设备当前的音量
         */
        int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume_seek.setMax(streamMaxVolume);
        volume_seek.setProgress(streamVolume);

        //播放控件
        play_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                int progress = seekBar.getProgress();
                // 令视频播放进度遵循seekBar停止拖动这一刻的进度
                videoView.seekTo(progress);
                play_controller_img.setImageResource(R.drawable.zhanshu_icon_play);
                videoView.start();
                UIhandler.sendEmptyMessage(UPDATE_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                UIhandler.removeMessages(UPDATE_UI);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                updateTextViewWithTimeFormat(time_current_tv, progress);
            }
        });

        volume_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //设置当前设备的的音量
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
        });


        /*videoView.setOnTouchListener(new View.OnTouchListener() {

            private float lastX;
            private float lastY;
            private float x;
            private float y;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (isFullScreen)
                    switch (event.getAction()) {

                        // 手指落下屏幕的那一刻，只会调用一次
                        case MotionEvent.ACTION_DOWN: {
                            x = event.getRawX();
                            y = event.getRawY();
                            lastX = x;
                            lastY = y;
                            break;
                        }
                        // 手指在屏幕上移动，调用多次
                        case MotionEvent.ACTION_MOVE: {
                            x = event.getRawX();
                            y = event.getRawY();
                            float detlaX = x - lastX;
                            float detlaY = y - lastY;
                            float absdetlaX = Math.abs(detlaX);
                            float absdetlaY = Math.abs(detlaY);
                            if (absdetlaX > threshold && absdetlaY > threshold) {
                                if (absdetlaX < absdetlaY) {
                                    isAdjust = true;
                                } else {
                                    isAdjust = false;
                                }
                            } else if (absdetlaX < threshold && absdetlaY > threshold) {
                                isAdjust = true;
                            } else if (absdetlaX > threshold && absdetlaY < threshold) {
                                isAdjust = false;
                            }

                            if (isAdjust) {
                        *//*
                         * 在判断好当前手势事件已经合法的前提下，去区分此时手势应该调节亮度还是调节声音
                         *//*
                                if (x < screen_height / 2) {

                                    changeBrightness(-detlaY);
                                } else {
                                    // 调节声音
                                    changeVolume(-detlaY);
                                }
                            }
                            break;
                        }
                        // 手指从屏幕上抬起
                        case MotionEvent.ACTION_UP: {
                            progress_layout.setVisibility(View.GONE);
                            break;
                        }
                    }
                return true;
            }
        });*/

        /**
         * 开始播放事件
         */
        setPlayerEvent();
    }

    private void changeBrightness(float detlaY) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        float mBrightness = attributes.screenBrightness;
        float index = detlaY / screen_height / 3;
        mBrightness += index;
        if (mBrightness > 1.0f) {
            mBrightness = 1.0f;
        }
        if (mBrightness < 0.01f) {
            mBrightness = 0.01f;
        }
        attributes.screenBrightness = mBrightness;
        if (progress_layout.getVisibility() == View.GONE) {
            progress_layout.setVisibility(View.VISIBLE);
        }
        operation_bg.setImageResource(R.drawable.sound);
        ViewGroup.LayoutParams layoutParams = operation_percent.getLayoutParams();
        layoutParams.width = (int) (PixelUtil.dp2px(120) * mBrightness);
        operation_percent.setLayoutParams(layoutParams);
        getWindow().setAttributes(attributes);
    }


    private void changeVolume(float detalY) {
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int index = (int) (detalY / screen_height * max * 3);
        int volume = Math.max(current + index, 0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        if (progress_layout.getVisibility() == View.GONE) {
            progress_layout.setVisibility(View.VISIBLE);
        }
        if (volume > max) {
            volume = max;
        }
        operation_bg.setImageResource(R.drawable.sound);
        ViewGroup.LayoutParams layoutParams = operation_percent.getLayoutParams();
        layoutParams.width = (int) (PixelUtil.dp2px(120) * (float) volume / max);
        operation_percent.setLayoutParams(layoutParams);
        volume_seek.setProgress(volume);
    }

    private void updateTextViewWithTimeFormat(TextView textView, int millisecond) {
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String str = null;
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
        textView.setText(str);
    }


    /**
     * 开始播放
     */
    private void setPlayerEvent() {
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                play_controller_img.setImageResource(R.drawable.zhanshu_icon_pause);

            }
        });

        /*
         * 控制视频的暂停与播放
         */
        play_controller_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    play_controller_img.setImageResource(R.drawable.zhanshu_icon_pause);
                    // 暂停播放
                    videoView.pause();
                    UIhandler.removeMessages(UPDATE_UI);
                } else {
                    play_controller_img.setImageResource(R.drawable.zhanshu_icon_play);
                    // 继续播放
                    videoView.start();
                    UIhandler.sendEmptyMessage(UPDATE_UI);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        play_controller_img.setImageResource(R.drawable.zhanshu_icon_pause);
        // 暂停播放
        videoView.pause();
        UIhandler.removeMessages(UPDATE_UI);
    }


    private Handler UIhandler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == UPDATE_UI) {
                // 获取视频当前的播放时间
                int currentPosition = videoView.getCurrentPosition();
                // 获取视频播放的总时间
                int totalDuration = videoView.getDuration();

                // 格式化视频播放时间
                updateTextViewWithTimeFormat(time_current_tv, currentPosition);
                updateTextViewWithTimeFormat(time_total_tv, totalDuration);

                play_seek.setMax(totalDuration);
                play_seek.setProgress(currentPosition);

                UIhandler.sendEmptyMessageDelayed(UPDATE_UI, 300);
            }
        }
    };


    @OnClick({R.id.tv_list, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_list:
                lltCourseLst.setVisibility(View.VISIBLE);
                break;
        }

    }

}
