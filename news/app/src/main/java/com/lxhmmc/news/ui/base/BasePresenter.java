package com.lxhmmc.news.ui.base;

import com.lxhmmc.news.business.net.NetTaskModel;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface BasePresenter<T extends BaseNetView> {
//    void start();

//    NetTaskModel netTask;
//    BaseNetView netTask;

    void bindTaskAndView(NetTaskModel netTask, T baseNetView);

    void onDestroy();


}
