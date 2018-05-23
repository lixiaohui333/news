package com.lxhmmc.news.business.net;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface NetTaskModel<T> {

    Disposable execute(T data,LoadTaskCallBack callBack);
}
