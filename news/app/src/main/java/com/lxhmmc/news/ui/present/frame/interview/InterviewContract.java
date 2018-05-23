package com.lxhmmc.news.ui.present.frame.interview;

import com.lxhmmc.news.domain.base.BaseDataList;
import com.lxhmmc.news.domain.interview.InterviewItem;
import com.lxhmmc.news.ui.base.BaseNetView;
import com.lxhmmc.news.ui.base.BasePresenter;

/**
 * Created by Administrator on 2018/3/29.
 */

public class InterviewContract {

    public interface NetView extends BaseNetView {
        void setInterviewList(BaseDataList<InterviewItem> dataList);
    }

    public interface Presenter extends BasePresenter<NetView> {

        void getInterviewList(String rel);

        void getInterviewListMore(String rel,int page);
    }

}
