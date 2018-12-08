package com.hsy.flightpacket.component;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hsy.flightpacket.R;

/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/17 10:46.
 * 描述：默认带 内存缓存和文件缓存 文件缓存只带小图就是glide处理过的图片如果需要原图需要另外处理
 */

public class GlideManager {


    /**
     * 加载头像 带固定加载图，带加载失败图片
     *
     * @param context
     * @param imgUrl
     * @param view
     */
    public static void loadHeader(Context context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl, R.drawable.ic_head_default).into(view);
    }

    public static void loadHeader(Fragment context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.ic_head_default).into(view);
    }

    public static void loadHeader(Activity context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.ic_head_default).into(view);
    }

    /**
     * 加载crop图片 默认带磁盘缓存(仅仅保存小图)和内存缓存
     *
     * @param context
     * @param imgUrl
     * @param view
     */
    public static void loadCrop(Context context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).into(view);
    }

    public static void loadCrop(Activity context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).into(view);
    }

    public static void loadCrop(Fragment context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).into(view);
    }

    /**
     * 设置固定加载尺寸
     */
    public static void loadSize(Context context, String imgUrl, ImageView view, int width, int height) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).override(width, height).into(view);
    }

    public static void loadSize(Activity context, String imgUrl, ImageView view, int width, int height) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).override(width, height).into(view);
    }

    public static void loadSize(Fragment context, String imgUrl, ImageView view, int width, int height) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).override(width, height).into(view);
    }

    /**
     * 设置有加载动画的图片
     */
    public static void loadWithAnima(Context context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).animate(android.R.anim.accelerate_decelerate_interpolator).into(view);
    }

    public static void loadWithAnima(Activity context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).animate(android.R.anim.accelerate_decelerate_interpolator).into(view);
    }

    public static void loadWithAnima(Fragment context, String imgUrl, ImageView view) {
        loadCropResult(context, imgUrl,R.drawable.bg_loading_img).animate(android.R.anim.accelerate_decelerate_interpolator).into(view);
    }

    /**
     * GIF 加载crop图片 默认带磁盘缓存(仅仅保存小图)和内存缓存
     *
     * @param context
     * @param imgUrl
     * @param view
     */
    public static void loadCropGif(Context context, String imgUrl, ImageView view) {
        loadCropResultGIF(context, imgUrl).into(view);
    }

    public static void loadCropGif(Activity context, String imgUrl, ImageView view) {
        loadCropResultGIF(context, imgUrl).into(view);
    }

    public static void loadCropGif(Fragment context, String imgUrl, ImageView view) {
        loadCropResultGIF(context, imgUrl).into(view);
    }
    /**
     * ALL:缓存源资源和转换后的资源（即所有版本，默认行为）
     * NONE:不作任何磁盘缓存。然而，默认的它将仍然使用内存缓存！
     * SOURCE:仅缓存源资源（原来的全分辨率的图像）。
     * RESULT：缓存转换后的资源（最终的图像，即降低分辨率后的（或者是转换后的）
     */
    /**
     * 加载crop图片 自定义缓存模式
     *
     * @param context
     * @param imgUrl
     * @param strategy 图片保存方式
     */
    public static void loadCropWithCacheType(Context context, String imgUrl, ImageView imageView, DiskCacheStrategy strategy) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(strategy).into(imageView);
    }

    public static void loadCropWithCacheType(Activity context, String imgUrl, ImageView imageView, DiskCacheStrategy strategy) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(strategy).into(imageView);
    }

    public static void loadCropWithCacheType(Fragment context, String imgUrl, ImageView imageView, DiskCacheStrategy strategy) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(strategy).into(imageView);
    }

    /**
     * 仅仅保存原始图片
     *
     * @param context
     * @param imgUrl
     */
    private static void loadCropSource(Context context, String imgUrl, ImageView imageView) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    private static void loadCropSource(Activity context, String imgUrl, ImageView imageView) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    private static void loadCropSource(Fragment context, String imgUrl, ImageView imageView) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }


    /**
     * 加载crop图片 不带任何缓存 内存缓存还是有
     *
     * @param context
     * @param imgUrl
     */
    private static void loadCropNoCahce(Context context, String imgUrl, ImageView imageView) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    private static void loadCropNoCahce(Activity context, String imgUrl, ImageView imageView) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    private static void loadCropNoCahce(Fragment context, String imgUrl, ImageView imageView) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    /**
     * 跳过内存缓存
     */
    private static void loadCropSkipMCache(Context context, String imgUrl, ImageView view) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).skipMemoryCache(true).into(view);
    }

    private static void loadCropSkipMCache(Activity context, String imgUrl, ImageView view) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).skipMemoryCache(true).into(view);
    }

    private static void loadCropSkipMCache(Fragment context, String imgUrl, ImageView view) {
        getTypeRequestSetUpLoad(context, imgUrl,R.drawable.bg_loading_img).skipMemoryCache(true).into(view);
    }

    /**
     * 仅仅保存转换后的图片
     *
     * @param context
     * @param imgUrl
     */
    private static DrawableRequestBuilder<String> loadCropResult(Context context, String imgUrl, int drawable) {
        return getTypeRequestSetUpLoad(context, imgUrl,drawable).diskCacheStrategy(DiskCacheStrategy.RESULT);
    }

    private static DrawableRequestBuilder<String> loadCropResult(Fragment context, String imgUrl, int drawable) {
        return getTypeRequestSetUpLoad(context, imgUrl,drawable).diskCacheStrategy(DiskCacheStrategy.RESULT);
    }

    private static DrawableRequestBuilder<String> loadCropResult(Activity context, String imgUrl, int drawable) {
        return getTypeRequestSetUpLoad(context, imgUrl,drawable).diskCacheStrategy(DiskCacheStrategy.RESULT);
    }

    /**
     * 仅仅保存转换后的图片gif
     *
     * @param context
     * @param imgUrl
     */
    private static GifRequestBuilder loadCropResultGIF(Context context, String imgUrl) {
        return getTypeRequestSetUpGIF(context, imgUrl, true).diskCacheStrategy(DiskCacheStrategy.RESULT);
    }

    private static GifRequestBuilder loadCropResultGIF(Fragment context, String imgUrl) {
        return getTypeRequestSetUpGIF(context, imgUrl, true).diskCacheStrategy(DiskCacheStrategy.RESULT);
    }

    private static GifRequestBuilder loadCropResultGIF(Activity context, String imgUrl) {
        return getTypeRequestSetUpGIF(context, imgUrl, true).diskCacheStrategy(DiskCacheStrategy.RESULT);
    }

    /**
     * 清除磁盘缓存
     */
    public static void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        });
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }


    /**
     * 自定义的默认加载图 和加载失败图
     *
     * @param context
     * @param imgUrl
     * @param loadingImg
     * @param loadedError
     * @return
     */
    private static DrawableRequestBuilder<String> getTypeRequestDefine(Context context, String imgUrl, int loadingImg, int loadedError) {
        return Glide.with(context).load(imgUrl).centerCrop().placeholder(loadingImg).error(loadedError);
    }

    private static DrawableRequestBuilder<String> getTypeRequestDefine(Activity context, String imgUrl, int loadingImg, int loadedError) {
        return Glide.with(context).load(imgUrl).centerCrop().placeholder(loadingImg).error(loadedError);
    }

    private static DrawableRequestBuilder<String> getTypeRequestDefine(Fragment context, String imgUrl, int loadingImg, int loadedError) {
        return Glide.with(context).load(imgUrl).centerCrop().placeholder(loadingImg).error(loadedError);
    }

    /**
     * 设置固定的加载图和失败图
     *
     * @param context
     * @param imgUrl
     * @return
     */
    private static DrawableRequestBuilder<String> getTypeRequestSetUp(Context context, String imgUrl) {
        return Glide.with(context).load(imgUrl).centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
    }

    private static DrawableRequestBuilder<String> getTypeRequestSetUp(Activity context, String imgUrl) {
        return Glide.with(context).load(imgUrl).centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
    }

    private static DrawableRequestBuilder<String> getTypeRequestSetUp(Fragment context, String imgUrl) {
        return Glide.with(context).load(imgUrl).centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
    }

    /**
     * 加载 GIF
     *
     * @param context
     * @param imgUrl
     * @return
     */
    private static GifRequestBuilder getTypeRequestSetUpGIF(Context context, String imgUrl, boolean isMove) {
        if (isMove) {
            return Glide.with(context).load(imgUrl).asGif().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        } else {
            return Glide.with(context).load(imgUrl).asGif().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        }
    }

    private static GifRequestBuilder getTypeRequestSetUpGIF(Activity context, String imgUrl, boolean isMove) {
        if (isMove) {
            return Glide.with(context).load(imgUrl).asGif().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        } else {
            return Glide.with(context).load(imgUrl).asGif().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        }
    }

    private static GifRequestBuilder getTypeRequestSetUpGIF(Fragment context, String imgUrl, boolean isMove) {
        if (isMove) {
            return Glide.with(context).load(imgUrl).asGif().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        } else {
            return Glide.with(context).load(imgUrl).asGif().centerCrop().placeholder(android.R.drawable.alert_dark_frame).error(android.R.drawable.alert_dark_frame);
        }
    }

    /**
     * 设置固定的加载图
     *
     * @param context
     * @param imgUrl
     * @return
     */
    private static DrawableRequestBuilder<String> getTypeRequestSetUpLoad(Context context, String imgUrl, int drawable) {
        return Glide.with(context).load(imgUrl).placeholder(drawable).dontAnimate().centerCrop();
    }

    private static DrawableRequestBuilder<String> getTypeRequestSetUpLoad(Activity context, String imgUrl, int drawable) {
        return Glide.with(context).load(imgUrl).placeholder(drawable).dontAnimate().centerCrop();
    }

    private static DrawableRequestBuilder<String> getTypeRequestSetUpLoad(Fragment context, String imgUrl, int drawable) {
        return Glide.with(context).load(imgUrl).placeholder(drawable).dontAnimate().centerCrop();
    }
}
