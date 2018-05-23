package com.lxhmmc.news.business.net;

import com.lxhmmc.news.domain.base.BaseDataList;
import com.lxhmmc.news.domain.base.BaseHR;
import com.lxhmmc.news.domain.interview.InterviewItem;
import com.lxhmmc.news.domain.logo.SplashLogoHR;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/3/29.
 */

public interface ApiService {

    @GET("/api/api.interview.logo.splap")
    Observable<BaseHR<SplashLogoHR>> getSplash();


    @GET("/api/api.base")
    Observable<BaseHR> feedback();


    @GET("/api/api.interview.list.item")
    Observable<BaseHR<BaseDataList<InterviewItem>>> getTopic(String rel, int page);

}
