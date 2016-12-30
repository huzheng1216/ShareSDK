package com.inveno.sharesdk.share.yixin;

import android.app.Activity;
import android.graphics.Bitmap;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;
import com.inveno.sharesdk.utils.Tools;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;

/**
 * Created by zheng.hu on 2016/8/16.
 */
public class YiXinShare implements Sharer {

    protected Activity context;
    protected String message;

    public YiXinShare(Activity context, INPlatform platform) {
        this.context = context;
    }

    @Override
    public void share(ShareData sd) {
//        int shareType = sd.getShareType();
//        if (shareType == ShareData.SHARETYPE_TEXT) {
//            shareText(sd);
//        } else if (shareType == ShareData.SHARETYPE_IMAGE) {
//            shareBmp(sd);
//        } else if (shareType == ShareData.SHARETYPE_IMAGEANDTEXT) {
//            shareTextBmp(sd);
//        } else if (shareType == ShareData.SHARETYPE_MUSIC) {
//            shareMusic(sd);
//        } else if (shareType == ShareData.SHARETYPE_VIDEO) {
//            shareVideo(sd);
//        } else if (shareType == ShareData.SHARETYPE_WEB) {
//            shareWeb(sd);
//        } else if (shareType == ShareData.SHARETYPE_VOICE) {
//            shareVoice(sd);
//        }
//        send();
    }

    @Override
    public void shareText(ShareData sd) {

//        WXTextObject wxt = new WXTextObject();
//        wxt.text = sd.getText();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = wxt;
//        msg.description = sd.getDescription();
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("text");
//        req.message = msg;
    }

    @Override
    public void shareBmp(ShareData sd) {
//        Bitmap bmp = sd.getBitmap();
//        WXImageObject imgObj = new WXImageObject(bmp);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgObj;
//
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
////        bmp.recycle();
//        msg.thumbData = Tools.bmpToByteArray(thumbBmp, true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("img");
//        req.message = msg;
//        req.scene = SendMessageToWX.Req.WXSceneSession;
////        api.sendReq(req);
    }

    @Override
    public void shareTextBmp(ShareData sd) {
        LogTools.showLog("微信暂时不支持图文分享");
    }

    @Override
    public void shareMusic(ShareData sd) {
//        WXMusicObject music = new WXMusicObject();
//        music.musicDataUrl = sd.getMusicUrl();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = music;
//        msg.title = sd.getTitle();
//        msg.description = sd.getDescription();
//        Bitmap bitmap = sd.getBitmap();
//        msg.thumbData = Tools.bmpToByteArray(bitmap, true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("music");
//        req.message = msg;
//        LogTools.showLog("微信暂时不支持音乐分享");
    }

    @Override
    public void shareVideo(ShareData sd) {
//        WXVideoObject video = new WXVideoObject();
//        video.videoUrl = sd.getVideoUrl();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = video;
//        msg.title = sd.getTitle();
//        msg.description = sd.getDescription();
//        Bitmap bitmap = sd.getBitmap();
//        msg.thumbData = Tools.bmpToByteArray(bitmap, true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("video");
//        req.message = msg;
//
//        LogTools.showLog("微信暂时不支持视频分享");
    }

    @Override
    public void shareWeb(ShareData sd) {
//        WXWebpageObject web = new WXWebpageObject();
//        web.webpageUrl = sd.getTargetUrl();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = web;
//        msg.title = sd.getTitle();
//        msg.description = sd.getDescription();
//        msg.thumbData = Tools.bmpToByteArray(sd.getBitmap(), true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("webpage");
//        req.message = msg;
    }

    @Override
    public void shareApp(ShareData sd) {

//                WXWebpageObject web = new WXWebpageObject();
//        web.webpageUrl = sd.getTargetUrl();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = web;
//        msg.title = sd.getTitle();
//        msg.description = sd.getDescription();
//        msg.thumbData = Tools.bmpToByteArray(sd.getBitmap(), true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("webpage");
//        req.message = msg;
//        LogTools.showLog("微信暂时不支持app分享");
    }

    @Override
    public void shareVoice(ShareData sd) {
//                WXWebpageObject web = new WXWebpageObject();
//        web.webpageUrl = sd.getTargetUrl();
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = web;
//        msg.title = sd.getTitle();
//        msg.description = sd.getDescription();
//        msg.thumbData = Tools.bmpToByteArray(sd.getBitmap(), true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("webpage");
//        req.message = msg;
//        LogTools.showLog("微信暂时不支持voice分享");
    }

    @Override
    public void send() {
    }
}
