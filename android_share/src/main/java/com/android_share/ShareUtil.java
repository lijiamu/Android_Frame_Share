package com.android_share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ShareUtil{
    private Activity activity;
    private UmShareBack umShareBack;
    private UMShareListener umShareListener;
    public ShareUtil(Activity activity, UmShareBack umShareBack) {
        this.activity = activity;
        this.umShareBack = umShareBack;
        initLister();
    }
    private void initLister(){
          umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Log.d("plat","platform"+platform);
                if(platform.name().equals("WEIXIN_FAVORITE")){
                    Toast.makeText(activity,platform + " 收藏成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(activity, platform + " 分享成功", Toast.LENGTH_SHORT).show();
                    if (umShareBack!=null)
                        umShareBack.UmShareSuccess();
                }
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(activity,platform + " 分享失败", Toast.LENGTH_SHORT).show();
                if(t!=null){
                    Log.e("mylog","throw:"+t.getMessage());
                    if (umShareBack!=null)
                        umShareBack.UmShareError(platform,t);
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(activity,platform + " 分享取消", Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void Share(final String title, final String content, final String ImageUrl, final String clickUrl){
        UMWeb web = new UMWeb(clickUrl);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(activity, ImageUrl));  //缩略图
        web.setDescription(content);//描述
        new ShareAction(activity).setDisplayList( SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withText(title)
                .withText(content)
                .withMedia(new UMImage(activity, ImageUrl))
                .withMedia(web)
                .setCallback(umShareListener)
                .open();

    }
    public void Share(final String title, final String content, final int imageid, final String clickUrl){
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),imageid);
        UMWeb web = new UMWeb(clickUrl);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(activity, bitmap));  //缩略图
        web.setDescription(content);//描述

        new ShareAction(activity).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withText(title)
                .withText(content)
                .withMedia(new UMImage(activity, bitmap))
                .withMedia(web)
                .setCallback(umShareListener)
                .open();

    }
   /* @Override
    public void activity.onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }*/
    public interface UmShareBack{
       void UmShareSuccess();
       void UmShareError(SHARE_MEDIA platform, Throwable t);
   }
}
