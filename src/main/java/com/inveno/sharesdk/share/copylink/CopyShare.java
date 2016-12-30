package com.inveno.sharesdk.share.copylink;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;

import com.inveno.sharesdk.R;
import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;
import com.tencent.open.utils.Util;

/**
 * Created by zheng.hu on 2016/4/12.
 */
public class CopyShare implements Sharer {

    protected Activity context;
    protected String message;

    public CopyShare(Activity context, INPlatform platform) {
        this.context = context;
    }

    @Override
    public void share(ShareData sd) {
        shareText(sd);
        send();
    }

    @Override
    public void shareText(ShareData sd) {
        /**
         * 组装要copy的文字信息
         */
        message = sd.getTargetUrl();
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
        if (Build.VERSION.SDK_INT >= 11) {
            android.content.ClipboardManager clip = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clip.setPrimaryClip(ClipData.newPlainText("link", this.message));
            if (clip.hasPrimaryClip()) {
                Toast.makeText(context, context.getResources().getString(R.string.copy_success), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.copy_deny), Toast.LENGTH_LONG).show();
            }
        } else {
            android.text.ClipboardManager clip = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clip.setText(this.message);
            if (clip.hasText()) {
                Toast.makeText(context, context.getResources().getString(R.string.copy_success), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.copy_deny), Toast.LENGTH_LONG).show();
            }
        }
    }
}
