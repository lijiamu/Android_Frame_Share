# Android_Frame_Share
关于UMeng分享的集成，减少app里面资源 以及提供了一些分享的公共方法
好处：你不会再看到app里面的一堆Umeng资源和你的项目资源混杂在一起，简单好用
集成方法：

1.build.gradle 下android节点添加
  allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
    
2. compile 'com.github.lijiamu:Android_Frame_Share:1.0'

3.AndroidManifest.xml配置权限

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
    
4.注册Umeng 分享所需Activity和meta-data

 <!-- 分享相关 -->
        <meta-data
            android:name="UMENG_APPKEY"
            android:value="${UMENG_APPKEY}" />
        <meta-data
            android:name="UMENG_CHANNEL"
            android:value="Umeng" />
        <activity
            android:name="com.umeng.qq.tencent.AuthActivity"
            android:launchMode="singleTask"
            android:noHistory="true">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data android:scheme="${QQ_APP_ID}" />
            </intent-filter>
        </activity>
        <activity
            android:name="com.umeng.qq.tencent.AssistActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />

        <!-- 注册新浪和微信的回调Activity -->
        <activity
            android:name="你的包名.wxapi.WXEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
        <activity
            android:name="你的包名.WBShareActivity"
            android:configChanges="keyboardHidden|orientation"
            android:screenOrientation="portrait">
            <intent-filter>
                <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        
5.在Application中添加代码块

     {
        PlatformConfig.setQQZone(BuildConfig.QQ_APP_ID, BuildConfig.QQ_APP_KEY);
        PlatformConfig.setWeixin(BuildConfig.WX_APP_ID, BuildConfig.WX_APP_Secret);
        PlatformConfig.setSinaWeibo(BuildConfig.XinLang_APPKEY, BuildConfig.XinLang_Secret,"http://sns.whalecloud.com");
    }
  
  onCreate中初始化SDK
  UMShareAPI.get(this);
  
 6.调用有2种方法
   a.继承ShareActivity 
   
   重写UmShareSuccess（）和UmShareError（SHARE_MEDIA platform, Throwable t），如果不需要监听分享失败和分享成功可以不写
   分享调用Share（）方法就可以了
   
   b.使用分享工具类
   
   实现ShareUtil.UmShareBack （PS：主要是监听分享失败或者成功的回调）. 创建ShareUtil 对象，调用ShareUtil 对象.Share()方法，在当前Activity重写
   
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
 
