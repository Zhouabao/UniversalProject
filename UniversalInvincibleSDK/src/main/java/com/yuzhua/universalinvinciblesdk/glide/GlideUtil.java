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
 * glide工具类
 * Created by Zhou Fengmei on 2018/4/12.
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
    返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
     传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
     切记不要胡乱强转！
     */

    public static void loadImg(Context context, Object url, ImageView tartgetImg) {
        RequestOptions options = new RequestOptions()
                .error(R.drawable.empty_icon)
                .priority(Priority.HIGH);
        GlideApp.with(context)
                .load(url)
                .apply(options)
                .into(tartgetImg);
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param tartgetImg
     */
    public static void loadRoundImg(Context context, String url, ImageView tartgetImg) {
        Glide.with(context)
                .load(url)
                .apply(getOptions())
                .into(tartgetImg);
    }

    /**
     * 圆角图片加载
     * @author leibing
     * @createTime 2016/8/15
     * @lastModify 2016/8/15
     * @param context 上下文
     * @param imageView 图片显示控件
     * @param url 图片链接
     * @param defaultImage 默认占位图片
     * @param errorImage 加载失败后图片
     * @param radius 图片圆角半径
     * @return
     */
    public static void loadRoundImg(Context context, String url, ImageView imageView, int defaultImage,
                            int errorImage , int radius){
        //RequestOptions 设置请求参数，通过apply方法设置
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new CornersTranform(radius));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url).apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);

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
