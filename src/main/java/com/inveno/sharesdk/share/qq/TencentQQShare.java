package com.inveno.sharesdk.share.qq;

import android.app.Activity;
import android.os.Bundle;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Created by zheng.hu on 2016/4/12.
 */
public class TencentQQShare implements Sharer {

    protected Activity context;
    protected Bundle params;
    protected IUiListener uiListener;
    protected boolean canPost;

    public TencentQQShare(Activity context, INPlatform platform){
        this.context = context;
        params = new Bundle();
        uiListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                LogTools.showLog("QQ分享结束。");
            }
            @Override
            public void onError(UiError uiError) {
                LogTools.showLog("QQ分享出错："+uiError.errorMessage+" --- 错误码："+uiError.errorCode+" --- 详情："+uiError.errorDetail);
            }
            @Override
            public void onCancel() {
                LogTools.showLog("QQ分享取消。");
            }
        };
    }

    @Override
    public void share(ShareData sd) {
        int shareType = sd.getShareType();
        if(shareType==ShareData.SHARETYPE_TEXT){
            shareText(sd);
        }else if (shareType==ShareData.SHARETYPE_IMAGE){
            shareBmp(sd);
        }else if (shareType==ShareData.SHARETYPE_IMAGEANDTEXT){
            shareTextBmp(sd);
        }else if (shareType==ShareData.SHARETYPE_MUSIC){
            shareMusic(sd);
        }else if (shareType==ShareData.SHARETYPE_VIDEO){
            shareVideo(sd);
        }else if (shareType==ShareData.SHARETYPE_WEB){
            shareWeb(sd);
        }
        send();
    }

    @Override
    public void shareText(ShareData sd) {
        LogTools.showLog("暂时不支持纯文字分享");
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
    }
}
