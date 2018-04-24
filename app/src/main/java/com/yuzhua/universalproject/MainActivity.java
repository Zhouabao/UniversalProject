package com.yuzhua.universalproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yuzhua.universalinvinciblesdk.base.BaseActivity;
import com.yuzhua.universalinvinciblesdk.util.ToastUtil;
import com.yuzhua.universalproject.util.ShareSDKUtils;
import com.yuzhua.universalproject.zxing.app.CaptureActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, PlatformActionListener {
    private static int REQUEST_CODE_CAMERA = 1001;
    String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @BindView(R.id.userName)
    TextView userName;
    private static final int REQUEST_QRCODE = 0x01;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        setSettingBtnImg(R.mipmap.bar_code_scan_icon);
        setTitle("测试主页");
        setSettingBtn(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
                    doOpenCamera();
                } else {
                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
                }
            }
        });
    }

    public void doOpenCamera() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }
    /**
     * 申请指定的权限.
     */
    public void requestPermission(int code, String... permissions) {

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, code);
        }
    }

    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ContextCompat.checkSelfPermission(this, permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @OnClick({R.id.cl1, R.id.cl2, R.id.cl3, R.id.cl4, R.id.cl5, R.id.cl6, R.id.cl7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl1://微信好友
                ShareSDKUtils.shareWX("分享内容", "", "", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=275372394,2501331271&fm=27&gp=0.jpg",
                        Wechat.NAME, Platform.SHARE_IMAGE, MainActivity.this);
                break;
            case R.id.cl2://微信朋友圈
                if (!EasyPermissions.hasPermissions(this, perms)) {
                    EasyPermissions.requestPermissions(this, "内存读取权限", REQUEST_CODE_CAMERA, perms);
                } else {
                    ShareSDKUtils.shareWX("分享内容", "分享测试", "https://www.baidu.com",
                            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=275372394,2501331271&fm=27&gp=0.jpg",
                            WechatMoments.NAME, Platform.SHARE_IMAGE, MainActivity.this);
                }
                break;
            case R.id.cl3://QQ好友
                ShareSDKUtils.shareQQ("分享内容", "https://www.baidu.com", "分享测试",
                        "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=275372394,2501331271&fm=27&gp=0.jpg",
                        "", "", QQ.NAME, MainActivity.this);
                break;
            case R.id.cl4://QQ空间
                ShareSDKUtils.shareQQ("分享内容", "https://www.baidu.com",
                        "分享测试", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=275372394,2501331271&fm=27&gp=0.jpg",
                        "测试APP", "https://www.baidu.com"
                        , QZone.NAME, new PlatformActionListener() {
                            @Override
                            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                Toast.makeText(MainActivity.this, "分享成功！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Platform platform, int i, Throwable throwable) {
                                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCancel(Platform platform, int i) {

                            }
                        });
                break;
            case R.id.cl5://微博
                ShareSDKUtils.shareSina("分享一个测试文本", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=275372394,2501331271&fm=27&gp=0.jpg", MainActivity.this);
                break;
            case R.id.cl6:
                ShareSDKUtils.thridPartyLogin(QQ.NAME, true, new PlatformActionListener() {
                    @Override

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        if (platform.getName().equals(QQ.NAME)) {
                            userName.setText(hashMap.get("nickname").toString());
                        }
                        Toast.makeText(MainActivity.this, "QQ授权成功！", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
            case R.id.cl7:
                ShareSDKUtils.thridPartyLogin(QQ.NAME, false, new PlatformActionListener() {
                    @Override

                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        /*if (platform.getName().equals(QQ.NAME)) {
                            hashMap.get("userName");
                            userName.setText(platform.getDb().getUserName());
                        }*/
                        Toast.makeText(MainActivity.this, "取消QQ授权成功！", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == REQUEST_CODE_CAMERA)
            ShareSDKUtils.shareWX("分享内容", "分享测试", "https://www.baidu.com",
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=275372394,2501331271&fm=27&gp=0.jpg",
                    WechatMoments.NAME, Platform.SHARE_IMAGE, MainActivity.this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (QQ.NAME.equals(platform.getName())) {
            Toast.makeText(this, "QQ分享成功！", Toast.LENGTH_SHORT).show();
        } else if (QZone.NAME.equals(platform.getName())) {
            Toast.makeText(this, "QQ空间分享成功！", Toast.LENGTH_SHORT).show();
        } else if (Wechat.NAME.equals(platform.getName())) {
            Toast.makeText(this, "微信分享成功！", Toast.LENGTH_SHORT).show();
        } else if (WechatMoments.NAME.equals(platform.getName())) {
            Toast.makeText(this, "微信朋友圈分享成功！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    String code = data.getStringExtra("SCAN_RESULT");
                    if (code.contains("http") || code.contains("https")) {
                        ToastUtil.toastSuccess(this,code,false);
                    } else {
                        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


}
