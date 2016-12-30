package com.inveno.sharesdk.share.wx;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.utils.LogTools;
import com.inveno.sharesdk.utils.StringTools;
import com.inveno.sharesdk.utils.Tools;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by zheng.hu on 2016/4/8.
 */
public class WXShare implements Sharer {

    protected IWXAPI api;
    protected SendMessageToWX.Req req;

    public WXShare(Context context, INPlatform platform) {
        //初始化SDK
        String APP_ID = platform.getAppId();
        if (StringTools.isNotEmpty(APP_ID) && api == null) {
            api = WXAPIFactory.createWXAPI(context, APP_ID, true);
            if(api.isWXAppInstalled() && api.isWXAppSupportAPI()){
                boolean b = api.registerApp(APP_ID);
                LogTools.showLog("注册微信APP_ID为：" + APP_ID + " -- 注册成功：" + b);
            }else{
                Toast.makeText(context,"未安装微信或版本不支持！",Toast.LENGTH_LONG).show();
            }
        } else {
            LogTools.showLog("未在xml中注册微信APP_ID");
        }
    }

    @Override
    public void share(ShareData sd) {
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
        } else if (shareType == ShareData.SHARETYPE_VOICE) {
            shareVoice(sd);
        }
        send();
    }

    @Override
    public void shareText(ShareData sd) {

        WXTextObject wxt = new WXTextObject();
        wxt.text = sd.getText();

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = wxt;
        msg.description = sd.getDescription();

        req = new SendMessageToWX.Req();
        req.transaction = Tools.buildTransaction("text");
        req.message = msg;
    }

    @Override
    public void shareBmp(ShareData sd) {
        Bitmap bmp = sd.getBitmap();
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, 50, true);
//        bmp.recycle();
        msg.thumbData = Tools.bmpToByteArray(thumbBmp, true);

        req = new SendMessageToWX.Req();
        req.transaction = Tools.buildTransaction("img");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
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
//        req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("music");
//        req.message = msg;
        LogTools.showLog("微信暂时不支持音乐分享");
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
//        req = new SendMessageToWX.Req();
//        req.transaction = Tools.buildTransaction("video");
//        req.message = msg;

        LogTools.showLog("微信暂时不支持视频分享");
    }

    @Override
    public void shareWeb(ShareData sd) {
        WXWebpageObject web = new WXWebpageObject();
        web.webpageUrl = sd.getTargetUrl();

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = web;
        msg.title = sd.getTitle();
        msg.description = sd.getDescription();
        msg.thumbData = Tools.bmpToByteArray(sd.getBitmap(), true);

        req = new SendMessageToWX.Req();
        req.transaction = Tools.buildTransaction("webpage");
        req.message = msg;
    }

    @Override
    public void shareApp(ShareData sd) {
        LogTools.showLog("微信暂时不支持app分享");
    }

    @Override
    public void shareVoice(ShareData sd) {
        LogTools.showLog("微信暂时不支持voice分享");
    }

    @Override
    public void send() {
    }


}
