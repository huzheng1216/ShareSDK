package com.inveno.sharesdk.share.qq;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.utils.LogTools;
import com.inveno.sharesdk.utils.StringTools;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

/**
 * QQ好友
 * Created by zheng.hu on 2016/4/12.
 */
public class TencentQSSShare extends TencentQQShare {

    private Tencent mTencent;

    public TencentQSSShare(Activity context, INPlatform platform) {
        super(context, platform);
        String APP_ID = platform.getAppId();
        if (StringTools.isNotEmpty(APP_ID)) {
            this.mTencent = Tencent.createInstance(APP_ID, context);
            LogTools.showLog("注册QQ APP_ID为：" + APP_ID);
        } else {
            LogTools.showLog("未在xml中注册QQ APP_ID");
        }
    }

    @Override
    public void shareBmp(ShareData sd) {
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, sd.getImageUrl());
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, sd.getAppName());
        canPost = true;
    }

    @Override
    public void shareTextBmp(ShareData sd) {
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, sd.getTitle());
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, sd.getDescription());
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, sd.getTargetUrl());
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, sd.getImageUrl());
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, sd.getAppName());
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "其他附加功能");
        canPost = true;
    }

    @Override
    public void shareMusic(ShareData sd) {
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, sd.getTitle());
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, sd.getDescription());
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, sd.getTargetUrl());
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, sd.getImageUrl());
        params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, sd.getMusicUrl());
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, sd.getAppName());
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        canPost = true;
    }

    @Override
    public void shareApp(ShareData sd) {
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, sd.getTitle());
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, sd.getDescription());
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, sd.getImageUrl());
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, sd.getAppName());
        canPost = true;
    }

    @Override
    public void send() {
        if (canPost)
            mTencent.shareToQQ(context, params, uiListener);
        LogTools.showLog("分享到：QQ好友");
    }
}
