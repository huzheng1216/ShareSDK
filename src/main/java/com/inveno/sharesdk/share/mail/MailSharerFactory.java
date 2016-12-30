package com.inveno.sharesdk.share.mail;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;
import com.inveno.sharesdk.share.qq.TencentQSSShare;

/**
 * Created by zheng.hu on 2016/4/8.
 */
public class MailSharerFactory implements ShareFactory {
    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new MailShare(context, platform);
    }
}
