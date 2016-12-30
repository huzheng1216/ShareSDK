package com.inveno.sharesdk.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.inveno.sharesdk.R;
import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.utils.LogTools;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 处理微信回调（需要在Manifest注册）
 * Created by zheng.hu on 2016/4/12.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        INPlatform wechat = INPlatform.getPlatformByName("WECHAT");
        String APP_ID = wechat.getAppId();
        // 注册微信SDK
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    public void onReq(BaseReq arg0) {
    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK: // 分享成功
                result = R.string.share_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:// 取消分享
                result = R.string.share_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:// 分享失败
                result = R.string.share_deny;
                break;
            default:
                result = R.string.share_deny;
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        LogTools.showLog("微信分享回调： "+resp.errStr);
        finish();
    }
}
