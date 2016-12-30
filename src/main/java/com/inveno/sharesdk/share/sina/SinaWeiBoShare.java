package com.inveno.sharesdk.share.sina;

import android.app.Activity;
import android.content.Intent;

import com.inveno.sharesdk.auth.InvenoAuthActivity;
import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;

/**
 * Created by zheng.hu on 2016/4/12.
 */
public class SinaWeiBoShare implements Sharer {

    protected Activity context;
    protected boolean canShare;
    protected ShareData shareData;

    public SinaWeiBoShare(Activity context, INPlatform platform) {
        this.context = context;
    }

    @Override
    public void share(ShareData sd) {
        shareData = sd;
        int shareType = sd.getShareType();
        if (shareType == ShareData.SHARETYPE_IMAGEANDTEXT) {
            shareTextBmp(sd);
        } else {
            canShare = true;
        }
        send();
    }

    @Override
    public void shareText(ShareData sd) {
        canShare = true;
    }

    @Override
    public void shareBmp(ShareData sd) {
        canShare = true;
    }

    @Override
    public void shareTextBmp(ShareData sd) {
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
        if (!canShare) {
            return;
        }
        Intent intent = new Intent(context, InvenoAuthActivity.class);
        intent.putExtra("action", InvenoAuthActivity.SINA_AUTH);
        intent.putExtra("sharedata", shareData);
        context.startActivity(intent);
        LogTools.showLog("分享新浪微博");
    }

}
