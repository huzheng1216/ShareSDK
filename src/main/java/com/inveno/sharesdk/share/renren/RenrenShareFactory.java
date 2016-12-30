package com.inveno.sharesdk.share.renren;

import android.app.Activity;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.poster.ShareFactory;
import com.inveno.sharesdk.poster.Sharer;

/**
 * Created by zheng.hu on 2016/7/25.
 */
public class RenrenShareFactory implements ShareFactory {
    @Override
    public Sharer produceSharer(Activity context, INPlatform platform) {
        return new RenrenShare(context, platform);
    }
}
