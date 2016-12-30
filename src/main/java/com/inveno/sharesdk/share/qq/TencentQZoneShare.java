package com.inveno.sharesdk.share.qq;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.utils.LogTools;
import com.inveno.sharesdk.utils.StringTools;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

/**
 * QQ空间
 * Created by zheng.hu on 2016/4/12.
 */
public class TencentQZoneShare extends TencentQQShare {

    private Tencent mTencent;

    public TencentQZoneShare(Activity context, INPlatform platform) {
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
    public void shareTextBmp(ShareData sd) {
        //分享类型
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, sd.getTitle());//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, sd.getDescription());//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, sd.getTargetUrl());//必填
        params.putString(QzoneShare.SHARE_TO_QQ_IMAGE_URL, sd.getImageUrl());
//        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, sd.getImageUrl());
        canPost = true;
    }

    @Override
    public void send() {
        if (canPost)
            mTencent.shareToQzone(context, params, uiListener);
        LogTools.showLog("分享到：QQ空间");
    }
}
