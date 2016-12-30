package com.inveno.sharesdk.share.wx;

import android.content.Context;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.utils.LogTools;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

/**
 * 微信好友
 * Created by zheng.hu on 2016/4/8.
 */
public class WXSSShare extends WXShare {

    public WXSSShare(Context context, INPlatform platform) {
        super(context, platform);
    }

    @Override
    public void shareText(ShareData sd) {
        LogTools.showLog("纯文字不能分享给好友！");
    }

    @Override
    public void send() {
        if (req == null) return;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
        LogTools.showLog("分享到：微信好友");
    }
}
