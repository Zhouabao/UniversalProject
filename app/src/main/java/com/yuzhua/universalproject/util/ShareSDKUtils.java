package com.yuzhua.universalproject.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Toast;

import com.mob.MobSDK;
import com.yuzhua.universalinvinciblesdk.R;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * @author Zhou Fengmei
 * @create 2018/4/20
 * @Describe shareSDK工具类
 */
public class ShareSDKUtils {
    /**
     * 分享到QQ
     *
     * @param title
     * @param titleUrl
     * @param txt
     * @param imgUrl
     * @param site                   (仅在QQ空间使用)
     * @param siteUrl                (仅在QQ空间使用)
     * @param platform               QZone.NAME(QQ空间):title tileUrl site siteUrl必须   QQ.NAME(QQ)
     * @param platformActionListener
     */
    public static void shareQQ(String title, String titleUrl, String txt, String imgUrl, String site, String siteUrl, String platform, PlatformActionListener platformActionListener) {
        Platform.ShareParams sp = new Platform.ShareParams();
        if (!TextUtils.isEmpty(title))
            sp.setTitle(title);
        if (!TextUtils.isEmpty(titleUrl))
            sp.setTitleUrl(titleUrl);// 标题的超链接
        if (!TextUtils.isEmpty(txt))
            sp.setText(txt);
        if (!TextUtils.isEmpty(imgUrl))
            sp.setImageUrl(imgUrl);
        if (!TextUtils.isEmpty(site))
            sp.setSite(site);//QQ空间的字段，标记分享应用的名称
        if (!TextUtils.isEmpty(siteUrl))
            sp.setSiteUrl(siteUrl);//QQ空间的字段，标记分享应用的网页地址

        Platform qq = ShareSDK.getPlatform(platform);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        qq.setPlatformActionListener(platformActionListener);
        // 执行图文分享
        qq.share(sp);
    }


    /**
     * 微信分享
     *
     * @param title                  分享标题
     * @param text                   分享内容
     * @param url                    分享的链接地址
     * @param imgUrl                 分享的图片链接
     * @param platform               平台类型   WechatMoments.NAME(朋友圈) Wechat.NAME(微信好友)
     * @param shareType              Platform.SHARE_TEXT（分享文本），
     *                               Platform.SHARE_IMAGE（分享图片），
     *                               Platform.SHARE_WEBPAGE（分享网页，既图文分享），
     *                               Platform.SHARE_MUSIC（分享音频），
     *                               Platform.SHARE_VIDEO（分享视频），
     *                               Platform.SHARE_APPS（分享应用，仅微信支持），
     *                               Platform.SHARE_FILE（分享文件，仅微信支持）
     *                               Platform.SHARE_EMOJI（分享表情，仅微信支持）
     * @param platformActionListener 分享结果回调
     */
    public static void shareWX(String title, String text, String url, String imgUrl, String platform, int shareType, PlatformActionListener platformActionListener) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setShareType(shareType);// 一定要设置分享属性,仅限制微信否则分享只是文字
        if (!TextUtils.isEmpty(title))
            shareParams.setTitle(title);
        if (!TextUtils.isEmpty(url))
            shareParams.setUrl(url);
        if (!TextUtils.isEmpty(imgUrl))
            shareParams.setImageUrl(imgUrl);
        if (!TextUtils.isEmpty(text)) {
            shareParams.setText(text);
        }
        Platform wechat = ShareSDK.getPlatform(platform);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        wechat.setPlatformActionListener(platformActionListener);
        // 执行图文分享
        wechat.share(shareParams);
    }

    /**
     * 分享到微博
     *
     * @param text   文本内容
     * @param picUrl 网络图片 （通过审核后才能添加）
     */
    public static void shareSina(String text, String picUrl, PlatformActionListener mPlatformActionListener) {
        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
        sp.setText(text);
        if (picUrl != null) {
            sp.setImageUrl(picUrl);
        }
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(mPlatformActionListener); // 设置分享事件回调
        //执行图文分享
        weibo.share(sp);
    }


    /**
     * 判断是否安装了微博
     *
     * @param context
     * @return
     */
    public static boolean isWeiboInstalled(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否安装了微信
     *
     * @param context
     * @return
     */
    public static boolean isWeixinInstalled(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否安装了QQ
     *
     * @param context
     * @return
     */
    public static boolean isQQClientInstalled(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /*  //关闭sso授权
    oks.disableSSOWhenAuthorize();
    // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
    oks.setTitle(title);
    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
    oks.setTitleUrl("http://www.jnjyzy.com/course/" + courseid + "/reviews");
    // text是分享文本，所有平台都需要这个字段
    oks.setText(String.valueOf(Html.fromHtml(about)));
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    oks.setImagePath(largePicture);//确保SDcard下面存在此张图片*//*
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.jnjyzy.com/course/" + courseid + "/reviews");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
*//*        oks.setComment("我是测试评论文本");*//*
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.jnjyzy.com/course/" + courseid + "/reviews");

        // 启动分享GUI
        oks.show(this);*/
    //一键分享
    public static void showShare(Context context, Map<String, String> map) {
        OnekeyShare oks = new OnekeyShare();
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo(context, map));
        oks.show(context);

    }

    static class ShareContentCustomizeDemo implements ShareContentCustomizeCallback {
        private Map<String, String> map;
        private String pubTitle, pubText, url;
        private Context c;

        public ShareContentCustomizeDemo(Context c, Map<String, String> map) {
            this.map = map;
            this.c = c;
            pubTitle = this.map.get("title");  ///公共的  写在这
            pubText = this.map.get("text");
            url = this.map.get("url");

        }

        public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
            // 改写twitter分享内容中的text字段，否则会超长，
            // 因为twitter会将图片地址当作文本的一部分去计算长度
            if (QQ.NAME.equals(platform.getName())) {
                if (!TextUtils.isEmpty(pubTitle)) {
                    paramsToShare.setTitle(pubTitle);
                } else {
                    paramsToShare.setTitle("");
                }
                if (!TextUtils.isEmpty(url)) {
                    paramsToShare.setTitleUrl(url);
                }
                // text是分享文本，所有平台都需要这个字段
                if (!TextUtils.isEmpty(pubText)) {
                    paramsToShare.setText(String.valueOf(Html.fromHtml(pubText)));
                } else {
                    paramsToShare.setText("");//如果这个为空有可能调qq掉不起来
                }
                if (!TextUtils.isEmpty(map.get("pic"))) {
                    paramsToShare.setImagePath(map.get("pic"));
                }

            } else if (QZone.NAME.equals(platform.getName())) {
                paramsToShare.setTitle(pubTitle);

                paramsToShare.setTitleUrl(url);
                // text是分享文本，所有平台都需要这个字段
                paramsToShare.setSiteUrl(url);
                paramsToShare.setSite(c.getString(R.string.app_name));
                if (!TextUtils.isEmpty(map.get("pic"))) {
                    paramsToShare.setImagePath(map.get("pic"));
                }
                if (!TextUtils.isEmpty(pubText)) {

                    paramsToShare.setText(String.valueOf(Html.fromHtml(pubText)));
                } else {
                    paramsToShare.setText("");//如果这个为空调用qq掉不起来
                }
            } else if (Wechat.NAME.equals(platform.getName())) {
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);// 一定要设置分享属性
                paramsToShare.setTitle(pubTitle);
                // text是分享文本，所有平台都需要这个字段
                paramsToShare.setUrl(url);
                if (!TextUtils.isEmpty(map.get("pic"))) {
                    paramsToShare.setImagePath(map.get("pic"));
                }
                if (!TextUtils.isEmpty(pubText)) {
                    paramsToShare.setText(String.valueOf(Html.fromHtml(pubText)));
                } else {
                    paramsToShare.setText("");//如果这个为空调用qq掉不起来
                }
            } else if (WechatMoments.NAME.equals(platform.getName())) {
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);// 一定要设置分享属性
                paramsToShare.setTitle(pubTitle);
                // text是分享文本，所有平台都需要这个字段
                paramsToShare.setUrl(url);
                if (!TextUtils.isEmpty(map.get("pic"))) {
                    paramsToShare.setImagePath(map.get("pic"));
                }
                if (!TextUtils.isEmpty(pubText)) {

                    paramsToShare.setText(String.valueOf(Html.fromHtml(pubText)));
                } else {
                    paramsToShare.setText("");//如果这个为空调用qq掉不起来
                }
            }
        }
    }


    /**
     * 第三方登录
     *
     * @param name                    QQ.NAME  Wechat.NAME  SinaWeibo.NAME
     * @param mPlatformActionListener Java
     *                                <p>
     *                                Platform qzone = ShareSDK.getPlatform(QZone.NAME);
     *                                String accessToken = qzone.getDb().getToken(); // 获取授权token
     *                                String openId = qzone.getDb().getUserId(); // 获取用户在此平台的ID
     *                                String nickname = qzone.getDb().getUserName(); // 获取用户昵称
     *                                //部分没有封装的字段可以通过键值获取，例如下面微信的unionid字段
     *                                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
     *                                String unionid = wechat.getDb().get("unionid");
     */
    public static void thridPartyLogin(String platform, boolean authorize, PlatformActionListener mPlatformActionListener) {
        Platform mPlatform = ShareSDK.getPlatform(platform);
        if (mPlatform.isAuthValid() && !authorize) {
            mPlatform.removeAccount(true);
        }
        mPlatform.SSOSetting(false);  //设置false表示使用SSO授权方式
        mPlatform.setPlatformActionListener(mPlatformActionListener);
        mPlatform.showUser(null);//授权并获取用户信息
    }


}
