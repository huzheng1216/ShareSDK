package com.inveno.sharesdk.poster;

import com.inveno.sharesdk.share.copylink.CopySharerFactory;
import com.inveno.sharesdk.share.mail.MailSharerFactory;
import com.inveno.sharesdk.share.qq.TencentQSSSharerFactory;
import com.inveno.sharesdk.share.qq.TencentQZoneSharerFactory;
import com.inveno.sharesdk.share.shortmessage.ShortMessageFactory;
import com.inveno.sharesdk.share.tencent.TencentWeiBoSharerFactory;
import com.inveno.sharesdk.share.wx.WXSFSharerFactory;
import com.inveno.sharesdk.share.wx.WXSSSharerFactory;
import com.inveno.sharesdk.share.wx.WXSTSharerFactory;
import com.inveno.sharesdk.share.sina.SinaWeiBoSharerFactory;

public class PosterFactory {
    public static ShareFactory produceWXSSFactory() {
        return new WXSSSharerFactory();
    }

    public static ShareFactory produceWXSTFactory() {
        return new WXSTSharerFactory();
    }

    public static ShareFactory produceWXSFFactory() {
        return new WXSFSharerFactory();
    }

    public static ShareFactory produceTencentQSSFactory() {
        return new TencentQSSSharerFactory();
    }

    public static ShareFactory produceTencentQZoneFactory() {
        return new TencentQZoneSharerFactory();
    }

    public static ShareFactory produceTencentWeiBoFactory() {
        return new TencentWeiBoSharerFactory();
    }

    public static ShareFactory produceShortMessageFactory() {
        return new ShortMessageFactory();
    }

    public static ShareFactory produceMailFactory() {
        return new MailSharerFactory();
    }
    public static ShareFactory produceSinaWeiBoFactory() {
        return new SinaWeiBoSharerFactory();
    }
    public static ShareFactory produceCopyFactory() {
        return new CopySharerFactory();
    }
}
