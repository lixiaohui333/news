package com.lxhmmc.news.ui.present.logo.splash;

import com.lxhmmc.news.domain.logo.SplashLogoHR;
import com.lxhmmc.news.ui.base.BaseNetView;
import com.lxhmmc.news.ui.base.BasePresenter;

/**
 * Created by Administrator on 2018/3/29.
 */

public class SplashContract {

    public interface NetView extends BaseNetView {

        void setSplash(SplashLogoHR splash);

    }

    public interface Presenter extends BasePresenter<NetView> {
        void getSplashByNet();
    }

}
