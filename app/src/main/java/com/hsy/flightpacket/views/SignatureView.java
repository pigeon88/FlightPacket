package com.hsy.flightpacket.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author xiongweimin.
 * @email 1007029049@qq.com
 * @time 2017/3/24 16:59.
 */
@SuppressLint("ClickableViewAccessibility")
public class SignatureView extends View {
    private static final String TAG = SignatureView.class.getSimpleName();
    private Context mContext;
    /**
     * 笔画X坐标起点
     */
    private float mX;
    /**
     * 笔画Y坐标起点
     */
    private float mY;
    /**
     * 手写画笔
     */
    private final Paint mGesturePaint = new Paint();
    /**
     * 路径
     */
    private final Path mPath = new Path();
    /**
     * 背景画布
     */
    private Canvas cacheCanvas;
    /**
     * 背景Bitmap缓存
     */
    private Bitmap cachebBitmap;
    /**
     * 是否已经签名
     */
    private boolean isTouched = false;


    /**
     * 画笔宽度 px；
     */
    private int mPaintWidth = 10;

    /**
     * 前景色
     */
    private int mPenColor = Color.BLACK;

    private int mBackColor = 0xedf6ff;

    private String path;

    private File file;

    public SignatureView(Context context) {
        super(context);
        init(context);
    }

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SignatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Style.STROKE);
        mGesturePaint.setStrokeWidth(mPaintWidth);
        mGesturePaint.setColor(mPenColor);
        //设置笔头是圆形
        mGesturePaint.setStrokeCap(Paint.Cap.ROUND);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cachebBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        cacheCanvas = new Canvas(cachebBitmap);
        cacheCanvas.drawColor(mBackColor);
        isTouched = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                isTouched = true;
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(mPath, mGesturePaint);
                mPath.reset();
                break;
        }
        // 更新绘制
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(cachebBitmap, 0, 0, mGesturePaint);
        // 通过画布绘制多点形成的图形
        canvas.drawPath(mPath, mGesturePaint);
    }

    // 手指点下屏幕时调用
    private void touchDown(MotionEvent event) {

        // mPath.rewind();
        // 重置绘制路线，即隐藏之前绘制的轨迹
        mPath.reset();
        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;
        // mPath绘制的绘制起点
        mPath.moveTo(x, y);
    }

    // 手指在屏幕上滑动时调用
    private void touchMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        final float previousX = mX;
        final float previousY = mY;
        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);
        // 两点之间的距离大于等于3时，生成贝塞尔绘制曲线
        if (dx >= 3 || dy >= 3) {
            // 设置贝塞尔曲线的操作点为起点和终点的一半
            float cX = (x + previousX) / 2;
            float cY = (y + previousY) / 2;
            // 二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
            mPath.quadTo(previousX, previousY, cX, cY);
            // 第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
            mX = x;
            mY = y;
        }
    }

    /**
     * 清除画板
     */
    public void clear() {
        if (cacheCanvas != null) {
            isTouched = false;
            mGesturePaint.setColor(mPenColor);
            cacheCanvas.drawColor(mBackColor, PorterDuff.Mode.CLEAR);
            cacheCanvas.drawColor(mBackColor);
            invalidate();
        }
    }


    /**
     * 保存画板
     */

    public void save() {
        save(false, 0);
    }

    /**
     * 保存画板
     *
     * @param listener 保存成功后回调
     */
    public void save(OnSaveFinishListener listener) {
        save(false, 0, listener);
    }


    /* public void save(String path, boolean clearBlank, int blank) throws IOException {
         this.path = path;
         Bitmap bitmap = cachebBitmap;
         //BitmapUtil.createScaledBitmapByHeight(srcBitmap, 300);//  压缩图片
         if (clearBlank) {
             bitmap = clearBlank(bitmap, blank);
         }
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
         byte[] buffer = bos.toByteArray();
         if (buffer != null) {
             File file = new File(path);
             if (file.exists()) {
                 file.delete();
             }
             OutputStream outputStream = new FileOutputStream(file);
             outputStream.write(buffer);
             outputStream.close();
         }
     }*/
    public void save(boolean clearBlank, int blank) {
        save(clearBlank, blank, null);
    }

    /**
     * 保存画板
     *
     * @param clearBlank 是否清除空白区域
     * @param blank      边缘空白区域
     */
    public void save(boolean clearBlank, int blank, OnSaveFinishListener listener) {
        Bitmap bmp = getBitMap();
        if (clearBlank) {
            bmp = clearBlank(bmp, blank);
        }
        File appDir = new File(Environment.getExternalStorageDirectory(), "hsyimg");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".PNG";
        file = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            if (listener != null) {
                listener.onSaveFinish(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取画板的bitmap
     *
     * @return
     */

    public Bitmap getBitMap() {
        setDrawingCacheEnabled(true);
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        buildDrawingCache();
        Bitmap bitmap = getDrawingCache();
        bitmap = bitmap.createBitmap(bitmap);
        setDrawingCacheEnabled(false);
        return bitmap;
    }


    /**
     * 逐行扫描 清楚边界空白。
     *
     * @param bp
     * @param blank 边距留多少个像素
     * @return
     */
    private Bitmap clearBlank(Bitmap bp, int blank) {
        int HEIGHT = bp.getHeight();
        int WIDTH = bp.getWidth();
        int top = 0, left = 0, right = 0, bottom = 0;
        int[] pixs = new int[WIDTH];
        boolean isStop;
        for (int y = 0; y < HEIGHT; y++) {
            bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
            isStop = false;
            for (int pix : pixs) {
                if (pix != mBackColor) {
                    top = y;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        for (int y = HEIGHT - 1; y >= 0; y--) {
            bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
            isStop = false;
            for (int pix : pixs) {
                if (pix != mBackColor) {
                    bottom = y;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        pixs = new int[HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
            isStop = false;
            for (int pix : pixs) {
                if (pix != mBackColor) {
                    left = x;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        for (int x = WIDTH - 1; x > 0; x--) {
            bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
            isStop = false;
            for (int pix : pixs) {
                if (pix != mBackColor) {
                    right = x;
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                break;
            }
        }
        if (blank < 0) {
            blank = 0;
        }
        left = left - blank > 0 ? left - blank : 0;
        top = top - blank > 0 ? top - blank : 0;
        right = right + blank > WIDTH - 1 ? WIDTH - 1 : right + blank;
        bottom = bottom + blank > HEIGHT - 1 ? HEIGHT - 1 : bottom + blank;
        return Bitmap.createBitmap(bp, left, top, right - left, bottom - top);
    }

    /**
     * 设置画笔宽度 默认宽度为10px
     *
     * @param mPaintWidth
     */
    public void setPaintWidth(int mPaintWidth) {
        mPaintWidth = mPaintWidth > 0 ? mPaintWidth : 10;
        this.mPaintWidth = mPaintWidth;
        mGesturePaint.setStrokeWidth(mPaintWidth);

    }


    /**
     * 设置签名画布颜色
     *
     * @param backColor
     */
    public void setBackColor(@ColorInt int backColor) {
        mBackColor = backColor;
    }


    /**
     * 设置画笔颜色
     *
     * @param mPenColor
     */
    public void setPenColor(int mPenColor) {
        this.mPenColor = mPenColor;
        mGesturePaint.setColor(mPenColor);
    }

    /**
     * 是否有签名
     *
     * @return
     */
    public boolean getTouched() {
        return isTouched;
    }

    /**
     * 获取签名地址
     *
     * @return
     */
    public File getSaveFile() {
        if (file != null && file.exists()) {
            return file;
        }
        return null;
    }

    /**
     * 获取签名之后的图片
     */
    public Bitmap getSignatrueBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        return bm;
    }

    public interface OnSaveFinishListener {
        public void onSaveFinish(File file);
    }
}
