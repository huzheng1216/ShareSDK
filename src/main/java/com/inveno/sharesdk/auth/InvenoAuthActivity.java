package com.inveno.sharesdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.inveno.sharesdk.R;
import com.inveno.sharesdk.data.Constants;
import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.utils.LogTools;
import com.inveno.sharesdk.utils.Tools;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 用于腾讯微博，新浪微博的授权认证
 * 必须在Menifest注册
 * Created by zheng.hu on 2016/4/19.
 */
public class InvenoAuthActivity extends Activity {

    public static final int TENCENT_AUTH = 1;
    public static final int SINA_AUTH = 2;

    private ShareData shareData;

    //----------------------------------------------------------sina微博-----------------------------------
    private AuthInfo mAuthInfo;
    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;
    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;
    /**
     * 微博分享的接口实例
     */
    private IWeiboShareAPI mWeiboShareAPI;
//-----------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        Intent intent = getIntent();
        shareData = intent.getParcelableExtra("sharedata");
        int action = intent.getIntExtra("action", 0);
        switch (action) {
            case TENCENT_AUTH:
                initTencentWB(intent);
                break;
            case SINA_AUTH:
                initSinaWB();
                break;
        }
    }

    /**
     * 腾讯微博
     *
     * @param intent
     */
    private void initTencentWB(Intent intent) {
        INPlatform platform = null;
        for (INPlatform form : INPlatform.values()) {
            if ("tencentweibo".equals(form.getName()))
                platform = form;
        }

    }

    /**
     * 新浪微博初始化
     */
    private void initSinaWB() {
        INPlatform platform = null;
        for (INPlatform form : INPlatform.values()) {
            if ("sinaweibo".equals(form.getName())) {
                platform = form;
            }
        }
        LogTools.showLog("新浪微博分享：appkey=" + platform.getAppId() + " redirecturl=" + platform.getAppRedirectUrl());
        mAuthInfo = new AuthInfo(this, platform.getAppId(), platform.getAppRedirectUrl(), Constants.SCOPE);
        mSsoHandler = new SsoHandler(InvenoAuthActivity.this, mAuthInfo);
        // 创建微博 SDK 接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
        // 获取微博客户端相关信息，如是否安装、支持 SDK 的版本
        boolean isInstalledWeibo = mWeiboShareAPI.isWeiboAppInstalled();
        int supportApiLevel = mWeiboShareAPI.getWeiboAppSupportAPI();

        //获取token
        // SSO 授权, ALL IN ONE   如果手机安装了微博客户端则使用客户端授权,没有则进行网页授权
        mSsoHandler.authorize(new AuthListener());
    }

    /**
     * 注册app到新浪微博
     */
    private void registerApp() {
        // 注册到新浪微博
        mWeiboShareAPI.registerApp();
        Toast.makeText(this,
                R.string.weibosdk_demo_toast_register_app_to_weibo, Toast.LENGTH_LONG).show();
        shareToSinaWeiBo();
    }

    private void shareToSinaWeiBo() {
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        // 1. 初始化微博的分享消息
        if (shareData.getShareType() == ShareData.SHARETYPE_TEXT) {
            TextObject textObject = new TextObject();
            textObject.text = shareData.getText();
            weiboMessage.textObject = textObject;
        } else if (shareData.getShareType() == ShareData.SHARETYPE_IMAGE) {
            ImageObject imageObject = new ImageObject();
            //设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
            imageObject.setImageObject(shareData.getBitmap());
            weiboMessage.imageObject = imageObject;
        } else
            // 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
            if (shareData.getShareType() == ShareData.SHARETYPE_WEB) {
                WebpageObject mediaObject = new WebpageObject();
                mediaObject.identify = Utility.generateGUID();
                mediaObject.title = shareData.getTitle();
                mediaObject.description = shareData.getDescription();

                // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
                mediaObject.setThumbImage(shareData.getBitmap());
                mediaObject.actionUrl = shareData.getTargetUrl();
                mediaObject.defaultText = "Webpage 默认文案";
                weiboMessage.mediaObject = mediaObject;
            } else if (shareData.getShareType() == ShareData.SHARETYPE_MUSIC) {
                // 创建媒体消息
                MusicObject musicObject = new MusicObject();
                musicObject.identify = Utility.generateGUID();
                musicObject.title = shareData.getTitle();
                musicObject.description = shareData.getDescription();

                // 设置 Bitmap 类型的图片到视频对象里        设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
                musicObject.setThumbImage(shareData.getBitmap());
                musicObject.actionUrl = shareData.getTargetUrl();
                musicObject.dataUrl = "www.weibo.com";
                musicObject.dataHdUrl = "www.weibo.com";
                musicObject.duration = 10;
                musicObject.defaultText = "Music 默认文案";
                weiboMessage.mediaObject = musicObject;
            } else if (shareData.getShareType() == ShareData.SHARETYPE_VIDEO) {
                // 创建媒体消息
                VideoObject videoObject = new VideoObject();
                videoObject.identify = Utility.generateGUID();
                videoObject.title = shareData.getTitle();
                videoObject.description = shareData.getDescription();

                Bitmap bitmap = shareData.getBitmap();
                // 设置 Bitmap 类型的图片到视频对象里  设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
                ByteArrayOutputStream os = null;
                try {
                    os = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, os);
                    System.out.println("kkkkkkk    size  " + os.toByteArray().length);
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.e("Weibo.BaseMediaObject", "put thumb failed");
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                videoObject.setThumbImage(bitmap);
                videoObject.actionUrl = shareData.getTargetUrl();
                videoObject.dataUrl = "www.weibo.com";
                videoObject.dataHdUrl = "www.weibo.com";
                videoObject.duration = 10;
                videoObject.defaultText = "Vedio 默认文案";
                weiboMessage.mediaObject = videoObject;
            } else if (shareData.getShareType() == ShareData.SHARETYPE_VOICE) {
                // 创建媒体消息
                VoiceObject voiceObject = new VoiceObject();
                voiceObject.identify = Utility.generateGUID();
                voiceObject.title = shareData.getTitle();
                voiceObject.description = shareData.getDescription();
                Bitmap bitmap = shareData.getBitmap();
                // 设置 Bitmap 类型的图片到视频对象里      设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
                voiceObject.setThumbImage(bitmap);
                voiceObject.actionUrl = shareData.getTargetUrl();
                voiceObject.dataUrl = "www.weibo.com";
                voiceObject.dataHdUrl = "www.weibo.com";
                voiceObject.duration = 10;
                voiceObject.defaultText = "Voice 默认文案";
                weiboMessage.mediaObject = voiceObject;
            }

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        // 3. 发送请求消息到微博，唤起微博分享界面
        AuthInfo authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
        String token = "";
        if (accessToken != null) {
            token = accessToken.getToken();
        }
        mWeiboShareAPI.sendRequest(this, request, authInfo, token, new WeiboAuthListener() {
            @Override
            public void onWeiboException(WeiboException arg0) {
                LogTools.showLog("onWeiboException: "+arg0.getMessage());
            }

            @Override
            public void onComplete(Bundle bundle) {
                // TODO Auto-generated method stub
                Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
                AccessTokenKeeper.writeAccessToken(getApplicationContext(), newToken);
                Toast.makeText(getApplicationContext(), "onAuthorizeComplete token = " + newToken.getToken(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                LogTools.showLog("分享被取消");
            }
        });
        finish();
    }

    /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     *
     * @see {@link Activity#onActivityResult}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {onActivityResult} 中调用 {SsoHandler#authorizeCallBack} 后，
     * 该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息
            String phoneNum = mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(InvenoAuthActivity.this, mAccessToken);
                Toast.makeText(InvenoAuthActivity.this,
                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                registerApp();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = InvenoAuthActivity.this.getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(InvenoAuthActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(InvenoAuthActivity.this,
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
            InvenoAuthActivity.this.finish();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(InvenoAuthActivity.this,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
            InvenoAuthActivity.this.finish();
        }
    }
}
