package com.inveno.sharesdk.share.wx;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;

/**
 * Created by zheng.hu on 2016/4/8.
 */
public class WXSSSharerFactory implements ShareFactory {
    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new WXSSShare(context, platform);
    }
}
