package com.inveno.sharesdk.share.shortmessage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;
import com.inveno.sharesdk.utils.Tools;
import com.tencent.open.utils.Util;

import java.io.File;

/**
 * Created by zheng.hu on 2016/4/12.
 */
public class ShortMessageShare implements Sharer {

    protected Activity context;
    protected Intent intent;
    protected ShareData sd;

    public ShortMessageShare(Activity context, INPlatform platform) {
        this.context = context;
    }

    @Override
    public void share(ShareData sd) {
        int shareType = sd.getShareType();
        if (shareType == ShareData.SHARETYPE_IMAGE) {
            shareBmp(sd);
        } else if (shareType == ShareData.SHARETYPE_TEXT) {
            shareText(sd);
        }
        this.sd = sd;
        send();
    }

    @Override
    public void shareText(ShareData sd) {

        Uri smsToUri = Uri.parse("smsto:");
        intent = new Intent("android.intent.action.VIEW", smsToUri);
        if (TextUtils.isEmpty(sd.getTargetUrl()))
            intent.putExtra("sms_body", sd.getText());
        else
            intent.putExtra("sms_body", sd.getText() + sd.getTargetUrl());
        intent.setType("vnd.android-dir/mms-sms");
    }

    @Override
    public void shareBmp(ShareData sd) {

        intent = new Intent("android.intent.action.SEND");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(sd.getImageUrl())));
        intent.putExtra("subject", sd.getTitle());
        if (TextUtils.isEmpty(sd.getTargetUrl()))
            intent.putExtra("sms_body", sd.getText());
        else
            intent.putExtra("sms_body", sd.getText() + sd.getTargetUrl());
        intent.putExtra("android.intent.extra.TEXT", "it's EXTRA_TEXT");
        intent.setType("image/*");
        intent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");

    }

    @Override
    public void shareTextBmp(ShareData sd) {
        LogTools.showLog("暂时不支持纯图文分享");
    }

    @Override
    public void shareMusic(ShareData sd) {
        LogTools.showLog("暂时不支持纯音乐分享");
    }

    @Override
    public void shareVideo(ShareData sd) {
        LogTools.showLog("暂时不支持纯视频分享");
    }

    @Override
    public void shareWeb(ShareData sd) {
        LogTools.showLog("暂时不支持纯网页分享");
    }

    @Override
    public void shareApp(ShareData sd) {
        LogTools.showLog("暂时不支持纯app分享");
    }

    @Override
    public void shareVoice(ShareData sd) {
        LogTools.showLog("暂时不支持纯voice分享");
    }

    @Override
    public void send() {
        LogTools.showLog("短信分享");
        try {
            context.startActivityForResult(intent, 1002);
        } catch (Exception e) {
            Tools.openSystemShare(context, sd);
        }
    }
}
