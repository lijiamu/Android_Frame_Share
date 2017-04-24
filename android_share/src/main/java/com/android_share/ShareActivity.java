package com.android_share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ShareActivity extends AppCompatActivity {

    public void Share(final String title, final String content, final String ImageUrl, final String clickUrl){
        UMWeb web = new UMWeb(clickUrl);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(ShareActivity.this, ImageUrl));  //缩略图
        web.setDescription(content);//描述
        new ShareAction(ShareActivity.this).setDisplayList( SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withText(title)
                .withText(content)
                .withMedia(new UMImage(ShareActivity.this, ImageUrl))
                .withMedia(web)
                .setCallback(umShareListener)
                .open();

    }
    public void Share(final String title, final String content, final int imageid, final String clickUrl){
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),imageid);
        UMWeb web = new UMWeb(clickUrl);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(ShareActivity.this, bitmap));  //缩略图
        web.setDescription(content);//描述

        new ShareAction(ShareActivity.this).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withText(title)
                .withText(content)
                .withMedia(new UMImage(ShareActivity.this, bitmap))
                .withMedia(web)
                .setCallback(umShareListener)
                .open();

    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(ShareActivity.this,platform + " 收藏成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ShareActivity.this, platform + " 分享成功", Toast.LENGTH_SHORT).show();
                UmShareSuccess();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            UmShareError(platform,t);
            Toast.makeText(ShareActivity.this,platform + " 分享失败", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.e("mylog","throw:"+t.getMessage());

            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,platform + " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 分享成功
     */
    protected  void UmShareSuccess(){

    }

    /**
     * 分享失败
     */
    protected  void UmShareError(SHARE_MEDIA platform, Throwable t){

    }
}
