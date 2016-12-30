package com.inveno.sharesdk.auth;

import com.inveno.sharesdk.data.INPlatform;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zheng.hu on 2016/4/19.
 */
public class SinaAuther {

    private AuthInfo mAuthInfo;
    /**
     * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
     */
    private SsoHandler mSsoHandler;
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;

    public Observable<Oauth2AccessToken> getAuther(INPlatform platform){
        Observable<Oauth2AccessToken> observable = Observable.create(new Observable.OnSubscribe<Oauth2AccessToken>() {
            @Override
            public void call(Subscriber<? super Oauth2AccessToken> subscriber) {

            }
        });
        return observable;
    }

}
