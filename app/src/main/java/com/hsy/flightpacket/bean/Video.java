package com.hsy.flightpacket.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by xiongweimin on 2018/7/17.
 */

public class Video implements Parcelable {
    private String url;
    private boolean isPlaying;

    public Video(String url, boolean isPlaying) {
        this.url = url;
        this.isPlaying = isPlaying;
    }

    public Video(Parcel in) {
        url = in.readString();
        isPlaying = in.readByte() != 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeByte((byte) (isPlaying ? 1 : 0));
    }
}
