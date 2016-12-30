package com.inveno.sharesdk.share.mail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;

/**
 * Created by zheng.hu on 2016/4/12.
 */
public class MailShare implements Sharer {

    protected Activity context;
    protected Intent intent;

    public MailShare(Activity context, INPlatform platform) {
        this.context = context;
    }

    @Override
    public void share(ShareData sd) {
        shareText(sd);
        send();
    }

    @Override
    public void shareText(ShareData sd) {
        intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("plain/text");
        String emailSubject = sd.getTitle();

        //设置邮件默认地址
        // intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
        //设置邮件默认标题
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
        //设置要默认发送的内容
        intent.putExtra(android.content.Intent.EXTRA_TEXT, sd.getText());
    }

    @Override
    public void shareBmp(ShareData sd) {
        LogTools.showLog("暂时不支持纯图片分享");
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
        //调用系统的邮件系统
        LogTools.showLog("邮件分享");
        context.startActivityForResult(Intent.createChooser(intent, "请选择邮件发送软件"), 1001);
    }
}
