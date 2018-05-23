package com.lxhmmc.news.ui.activity.frame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxhmmc.news.R;
import com.lxhmmc.news.domain.interview.InterviewItem;
import com.lxhmmc.news.ui.base.BaseFragment;
import com.lxhmmc.news.ui.present.frame.interview.InterviewNetModel;
import com.lxhmmc.news.ui.present.frame.interview.InterviewPresent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/13.
 */

public class FrameFragmentInterviewItem extends BaseFragment<InterviewPresent, InterviewNetModel> {


    public static final String PARAMS_REL = "PARAMS_REL";


    String strRel = null;

    int pageIndex = 0;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    TopicAdapter adapter;


    public FrameFragmentInterviewItem() {
    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frame_fragment_interview_item_list, null);
    }

    @Override
    protected void initUI() {

        Bundle arguments = getArguments();
        strRel = (String) arguments.get(PARAMS_REL);
        pageIndex = 0;


        adapter = new TopicAdapter();
        gridRv.setLayoutManager(new LinearLayoutManager(ct));
        gridRv.setAdapter(adapter);


        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN);
//        swipeRefreshLayout.setEnabled(false);

        swipeRefreshLayout.setDistanceToTriggerSync(300);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadInitData();
            }
        });

        loadInitData();
    }

    @Override
    protected void loadInitData() {

        mPresenter.getInterviewList(strRel);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class TopicAdapter extends RecyclerView.Adapter {

        List<InterviewItem> items;
        ;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interview_list_item_topic, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;

            final InterviewItem item = items.get(position);

            viewHolder.tvTitle.setText(item.title);

        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        public void setImages(List<InterviewItem> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_title)
            TextView tvTitle;

            public MyViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }
}
