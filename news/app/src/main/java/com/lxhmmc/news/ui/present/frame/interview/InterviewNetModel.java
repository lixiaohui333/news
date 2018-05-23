package com.lxhmmc.news.ui.present.frame.interview;

import com.lxhmmc.news.business.net.ApiService;
import com.lxhmmc.news.business.net.LoadTaskCallBack;
import com.lxhmmc.news.business.net.NetTaskModel;
import com.lxhmmc.news.comm.LogHelper;
import com.lxhmmc.news.domain.base.BaseDataList;
import com.lxhmmc.news.domain.base.BaseHR;
import com.lxhmmc.news.domain.base.BaseListParams;
import com.lxhmmc.news.domain.interview.InterviewItem;

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

public class InterviewNetModel implements NetTaskModel<BaseListParams> {

    public static final String HOST = HTTP_HOST;

    public InterviewNetModel() {
        createRetrofit();
    }

    private Retrofit retrofit;

    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public Disposable execute(BaseListParams data, final LoadTaskCallBack taskCallback) {

        ApiService weatherService = retrofit.create(ApiService.class);
        Disposable disposable = weatherService.getTopic(data.rel,data.page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseHR<BaseDataList<InterviewItem>>>() {
                    @Override
                    public void accept(BaseHR<BaseDataList<InterviewItem>> baseDataListBaseHR) throws Exception {

                        LogHelper.i("Consumer accept BaseHR:"+baseDataListBaseHR.toString());
                        if (baseDataListBaseHR.sysStatus != BaseHR.HTTP_OK || baseDataListBaseHR.apiStatus != BaseHR.HTTP_OK) {
                            taskCallback.onSysError(baseDataListBaseHR);
                        } else {
                            taskCallback.onSuccess(baseDataListBaseHR.data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        taskCallback.onFailed();
                        LogHelper.i("Consumer accept onFailed:" + throwable.getMessage());
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
