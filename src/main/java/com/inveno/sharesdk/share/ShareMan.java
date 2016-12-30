package com.inveno.sharesdk.share;

import android.app.Activity;
import android.content.Context;

import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.data.ShareData;
import com.inveno.sharesdk.poster.PosterFactory;

/**
 * 像男人一样
 * Created by zheng.hu on 2016/4/11.
 */
public class ShareMan {

    /**
     * 执行分享工作
     * @param platform
     * @param shareData
     */
    public static void doShare(Activity context, INPlatform platform, ShareData shareData){

        if(platform == INPlatform.PLATFORM_WECHAT){
            PosterFactory.produceWXSSFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_WECHATFAVORITE){
            PosterFactory.produceWXSFFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_WECHATMOMENTS){
            PosterFactory.produceWXSTFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_EMAIL){
            PosterFactory.produceMailFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_QQ){
            PosterFactory.produceTencentQSSFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_QZONE){
            PosterFactory.produceTencentQZoneFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_TENCENTWEIBO){
            PosterFactory.produceTencentWeiBoFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_COPYLINK){
            PosterFactory.produceCopyFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_KAIXIN){

        }else if(platform == INPlatform.PLATFORM_RENREN){

        }else if(platform == INPlatform.PLATFORM_SHORTMESSAGE){
            PosterFactory.produceShortMessageFactory().produceSharer(context, platform).share(shareData);
        }else if(platform == INPlatform.PLATFORM_SINAWEIBO){
            PosterFactory.produceSinaWeiBoFactory().produceSharer(context, platform).share(shareData);
        }
    }
}
