package com.inveno.sharesdk.share.tencent;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.share.mail.MailShare;


/**
 * Created by zheng.hu on 2016/4/8.
 */
public class TencentWeiBoSharerFactory implements ShareFactory {
    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new TencentWeiBoShare(context, platform);
    }
}
