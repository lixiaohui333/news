package com.lxhmmc.news.ui.base;

import com.lxhmmc.news.domain.base.BaseHR;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface BaseNetView {

    void hideProgress();

    void showProgress();

    void error();

    void apiError(BaseHR baseHR);
}
