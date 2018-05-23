package com.lxhmmc.news.ui.present.logo.splash;

import com.lxhmmc.news.business.net.ApiService;
import com.lxhmmc.news.business.net.LoadTaskCallBack;
import com.lxhmmc.news.business.net.NetTaskModel;
import com.lxhmmc.news.comm.LogHelper;
import com.lxhmmc.news.domain.base.BaseHR;
import com.lxhmmc.news.domain.logo.SplashLogoHR;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lxhmmc.news.comm.Config.HTTP_HOST;

/**
 * Created by Administrator on 2018/3/29.
 */

public class SplashNetModel implements NetTaskModel<String> {

    public static final String HOST = HTTP_HOST;

    public SplashNetModel() {
        LogHelper.i("SplashNetModel init");
        createRetrofit();
    }

    private Retrofit retrofit;

    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public Disposable execute(String data, final LoadTaskCallBack taskCallback) {

        ApiService weatherService = retrofit.create(ApiService.class);
        Disposable disposable = weatherService.getSplash().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseHR<SplashLogoHR>>() {
                    @Override
                    public void accept(BaseHR<SplashLogoHR> splashLogoHRBaseHR) throws Exception {

                        LogHelper.i("Consumer accept BaseHR:"+splashLogoHRBaseHR.toString());
                        if (splashLogoHRBaseHR.sysStatus != BaseHR.HTTP_OK || splashLogoHRBaseHR.apiStatus != BaseHR.HTTP_OK) {
                            taskCallback.onSysError(splashLogoHRBaseHR);
                        } else {
                            taskCallback.onSuccess(splashLogoHRBaseHR.data);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        taskCallback.onFailed();
                        LogHelper.i("Consumer accept onFailed:"+throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        taskCallback.onFinish();
                        LogHelper.i("Action run onFinish");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        taskCallback.onStart();
                        LogHelper.i("Consumer accept onStart");
                    }
                });

        return disposable;
    }
}
