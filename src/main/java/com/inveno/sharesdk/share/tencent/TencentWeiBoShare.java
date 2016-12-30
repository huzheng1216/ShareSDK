package com.inveno.sharesdk.share.tencent;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;

/**
 * Created by zheng.hu on 2016/4/12.
 */
public class TencentWeiBoShare implements Sharer {

    protected Activity context;
    protected boolean canShare;
    protected ShareData shareData;

    public TencentWeiBoShare(Activity context, INPlatform platform) {
        this.context = context;
    }

    @Override
    public void share(ShareData sd) {
        shareData = sd;
        int shareType = sd.getShareType();
        if (shareType == ShareData.SHARETYPE_TEXT) {
            shareText(sd);
        } else if (shareType == ShareData.SHARETYPE_IMAGE) {
            shareBmp(sd);
        } else if (shareType == ShareData.SHARETYPE_IMAGEANDTEXT) {
            shareTextBmp(sd);
        } else if (shareType == ShareData.SHARETYPE_MUSIC) {
            shareMusic(sd);
        } else if (shareType == ShareData.SHARETYPE_VIDEO) {
            shareVideo(sd);
        } else if (shareType == ShareData.SHARETYPE_WEB) {
            shareWeb(sd);
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

        canShare = true;
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
        LogTools.showLog("暂时不支持纯app分享");
    }

    @Override
    public void send() {
        if(!canShare){
            return;
        }
        LogTools.showLog("分享腾讯微博");
    }
}
