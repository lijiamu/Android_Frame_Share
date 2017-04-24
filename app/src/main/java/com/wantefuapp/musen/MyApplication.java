package com.wantefuapp.musen;

import android.app.Application;

import com.android_frame_share.example.BuildConfig;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by ${Andy.li} on 2017/3/15.
 */

public class MyApplication extends Application {

    {
        PlatformConfig.setQQZone(BuildConfig.QQ_APP_ID, BuildConfig.QQ_APP_KEY);
        PlatformConfig.setWeixin(BuildConfig.WX_APP_ID, BuildConfig.WX_APP_Secret);
        PlatformConfig.setSinaWeibo(BuildConfig.XinLang_APPKEY, BuildConfig.XinLang_Secret,"http://sns.whalecloud.com");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Config.isJumptoAppStore = true;
        UMShareAPI.get(this);
    }
}
