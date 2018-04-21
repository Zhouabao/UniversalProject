package com.yuzhua.universalinvinciblesdk.glide;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.yuzhua.universalinvinciblesdk.R;

import java.io.File;

/**
 * @author Zhou Fengmei
 * @create 2018/4/20
 * @Describe glide模板
 */

public class GlideUtil {
    private static RequestOptions getOptions() {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.empty_icon)
                .error(R.drawable.empty_icon)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        return options;
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param tartgetImg
     */
    public static void loadRoundImg(Context context, String url, ImageView tartgetImg) {
        GlideApp.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(1000))//渐显效果
                .into(tartgetImg);

    }

    /**
     * 根据资源ID加载图片
     *
     * @param context
     * @param resourceId
     * @param target
     * @param defaultId
     */
    public static void loadResourseImg(Context context, int resourceId, ImageView target, int defaultId) {
        Glide.with(context)
                .load(resourceId)
                .apply(getOptions())
                .transition(new DrawableTransitionOptions().crossFade(200))
                .into(target);
    }


    /**
     * 加载图片不需要缓存的
     *
     * @param context
     * @param url
     * @param target
     */
    public static void loadSourseImgWithNoCache(Context context, String url, ImageView target) {
        Glide.with(context)
                .load(url)
                .apply(getOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true))
                .transition(new DrawableTransitionOptions().crossFade(200))
                .into(target);
    }


    /**
     * 根据图片路径加载图片
     *
     * @param context
     * @param imgFile
     * @param target
     * @param defaultId
     */
    public static void loadFileImg(Context context, File imgFile, ImageView target, int defaultId) {
        Glide.with(context)
                .load(imgFile)
                .apply(getOptions())
                .into(target);
    }

    /**
     * 加载Gif为一张静态图片
     *
     * @param context
     * @param url
     */
    public static void LoadGiftAsBitmap(Context context, String url, ImageView imageView) {
        Glide.with(context).asBitmap().apply(getOptions()).load(url).into(imageView);
    }

    /**
     * 你想只有加载对象是Gif时才能加载成功
     *
     * @param context
     * @param url
     */
    public static void LoadGiftAsGist(Context context, String url, ImageView imageView, int erroId) {
        Glide.with(context).asGif().apply(getOptions()).load(url).into(imageView);
    }

    /**
     * 加载缩略图,会自动与传入的fragment绑定生命周期,加载请求现在会自动在onStop中暂停在，onStart中重新开始。
     * 需要保证 ScaleType 的设置是正确的。
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public static void LoadThumbNail(Fragment fragment, String url, ImageView imageView) {
        Glide.with(fragment).load(url).thumbnail(0.1f).into(imageView);
    }

    /**
     * 上传一张大小为xPx*yPx像素的用户头像的图片bytes数据
     *
     * @param context
     * @param url
     * @param xPx
     * @param yPx
     */
    public static void decodeResorse(Context context, File url, int xPx, int yPx) {
        Glide.with(context)
                .load(url)
                .into(new SimpleTarget<Drawable>(xPx, yPx) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        //上传动作
                    }
                });
    }

    /**
     * 显示本地视频(网络视频无效)
     *
     * @param context
     * @param filePath
     * @param imageView
     */
    public static void LoadShowLocalVidio(Context context, String filePath, ImageView imageView) {
        Glide.with(context).load(Uri.fromFile(new File(filePath))).into(imageView);
    }

    /**
     * 在通知栏中显示从网络上请求的图片
     *
     * @param context
     * @param remoteViews
     * @param viewId
     * @param notification
     * @param notificationId
     * @param url
     */
    public static void ShowImgInNotification(Context context, RemoteViews remoteViews, int viewId, Notification
            notification, int notificationId, String url) {
        NotificationTarget target = new NotificationTarget(context, viewId, remoteViews, notification, notificationId);
        Glide.with(context.getApplicationContext()).asBitmap().load(url).into(target);
    }

    /**
     * 下载图片,耗时操作不能放在主线程中进行
     *
     * @param context
     * @param url
     */
    public static void downLoadImage(Context context, String url) {

        try {
            Glide.with(context).asBitmap().apply(getOptions()).load(url).listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean
                        isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource
                        dataSource, boolean isFirstResource) {
                    return false;
                }
            }).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 清除缓存
     *
     * @param context
     */
    public void clearCache(final Context context) {
        clearMemoryCache(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                clearDiskCache(context);
            }
        }).start();
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

}
