package com.lxhmmc.news.ui.activity.logo;

import android.app.Dialog;
import android.widget.ImageView;

import com.lxhmmc.news.R;
import com.lxhmmc.news.business.gilde.GlideUtil;
import com.lxhmmc.news.comm.LogHelper;
import com.lxhmmc.news.domain.logo.SplashLogoHR;
import com.lxhmmc.news.ui.activity.frame.FrameActivityMain;
import com.lxhmmc.news.ui.base.BaseFragmentActivity;
import com.lxhmmc.news.ui.present.logo.splash.SplashContract;
import com.lxhmmc.news.ui.present.logo.splash.SplashNetModel;
import com.lxhmmc.news.ui.present.logo.splash.SplashPresent;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashLogoActivity extends BaseFragmentActivity<SplashPresent,SplashNetModel> implements SplashContract.NetView {


//    SplashContract.Presenter splashPresenter;

    Dialog dialog;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @Override
    protected boolean intentData() {
        return true;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.logo_activity_splash);
    }

    @Override
    protected void initUI() {

        //test
        goactivity(FrameActivityMain.class);
        finish();
//        loadInitData();
    }

    @Override
    protected void setUI() {

    }

    @Override
    protected void loadInitData() {

        mPresenter.getSplashByNet();

    }


    @Override
    public void hideProgress() {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void error() {
        LogHelper.i(TAG + " error");
    }


    @Override
    public void setSplash(SplashLogoHR splash) {
        GlideUtil.display(ivLogo, splash.adUrl);
        baseHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                goactivity(FrameActivityMain.class);
                finish();

            }


        }, splash.splashDuration);

    }


    @OnClick(R.id.iv_logo)
    public void onViewClickedTest() {
        loadInitData();
    }

}
