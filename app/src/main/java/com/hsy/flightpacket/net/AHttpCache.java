package com.hsy.flightpacket.net;


import android.studio.os.LogCat;

import com.hsy.flightpacket.application.FlightApplication;
import com.hsy.flightpacket.config.AppConfig;
import com.loopj.android.http.RequestParams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/17 10:46.
 */
public class AHttpCache {

    public static final int CacheNone = 1;
    public static final int CacheRead = 1 << 1;
    public static final int CacheWrite = 1 << 2;
    public static final int CacheReadWrite = CacheRead & CacheWrite;


    public static void putCache(String url, byte[] response) {
        pruneIfNeeded(response.length);
        File file = getFileForKey(url);
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            out.write(response);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入缓存
     *
     * @param url
     * @param responseString
     */
    public static void putCache(String url, String responseString) {
        File file = getFileForKey(url);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(responseString);
            writer.close();
        } catch (IOException e) {
        }
    }

    public static byte[] getCacheBytes(String url) {
        File file = getFileForKey(url);
        if (!file.exists()) {
            return null;
        }

        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bout.write(buffer, 0, bytesRead);
            }

            bis.close();
            bout.close();
            return bout.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public static String getCache(String url) {
        File file = getFileForKey(url);
        if (!file.exists()) {
            return null;
        }

        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static File getFileForKey(String url) {
        String cacheKey = getCacheKey(url);
        File cacheDir = AppConfig.createDefaultCacheDir(FlightApplication.getInstance());
        return new File(cacheDir, cacheKey);
    }

    public static String getCacheKey(String url) {
        //return Integer.toHexString(new StringBuilder(url.length() + 12).append("#JSON").append(url).hashCode());
        int firstHalfLength = url.length() / 2;
        String localFilename = String.valueOf(url.substring(0, firstHalfLength).hashCode());
        localFilename += String.valueOf(url.substring(firstHalfLength).hashCode());
        return localFilename;
    }

    public static String newUrl(String url, RequestParams params) {
        String userId = AppConfig.getIEMI();
        return String.format("[%s]%s->%s", userId, url, params);
    }

    private static void pruneIfNeeded(int neededSpace) {
        File mRootDirectory = AppConfig.createDefaultCacheDir(FlightApplication.getInstance());
        if (!mRootDirectory.exists()) {
            if (!mRootDirectory.mkdirs()) {
                LogCat.e("Unable to create cache dir %s", mRootDirectory.getAbsolutePath());
            }
            return;
        }

        File[] files = mRootDirectory.listFiles();
        if (files == null) {
            return;
        }

        List<File> result = new ArrayList<>();
        Collections.addAll(result, files);
        Collections.sort(result, new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {
                return lhs.lastModified() < rhs.lastModified() ? -1 : (lhs.lastModified() == rhs.lastModified() ? 0 : 1);
            }
        });

        if (result.size() < 100) {
            return;
        }

        Iterator<File> iterator = result.iterator();
        while (iterator.hasNext()) {
            File entry = iterator.next();
            entry.delete();
            iterator.remove();

            if (result.size() < 100) {
                break;
            }
        }
    }
}
