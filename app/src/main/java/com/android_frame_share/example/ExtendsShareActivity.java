package com.android_frame_share.example;

import android.os.Bundle;
import android.view.View;

import com.android_share.ShareActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExtendsShareActivity extends ShareActivity {
    private String img_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493012725113&di=5ca0446256d3cf2b12faa5f9c3142c87&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fpeople%2F201607%2F16%2F20160716193049_ABZn4.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extends_share);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //图片资源ID
                 Share("标题","内容",R.mipmap.ic_launcher,"https://www.baidu.com/");
                break;
            case R.id.btn2:
                //图片链接
                Share("标题", "内容", img_url, "https://www.baidu.com/");
                break;
        }
    }

    @Override
    protected void UmShareSuccess() {
        super.UmShareSuccess();
    }

    @Override
    protected void UmShareError(SHARE_MEDIA platform, Throwable t) {
        super.UmShareError(platform, t);
    }
}
