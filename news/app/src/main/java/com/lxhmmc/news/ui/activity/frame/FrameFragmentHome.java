package com.lxhmmc.news.ui.activity.frame;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxhmmc.news.R;
import com.lxhmmc.news.ui.activity.frame.home.FrameFragmentFirst;
import com.lxhmmc.news.ui.activity.frame.home.FrameFragmentFourth;
import com.lxhmmc.news.ui.activity.frame.home.FrameFragmentSecond;
import com.lxhmmc.news.ui.activity.frame.home.FrameFragmentThird;
import com.lxhmmc.news.ui.base.BaseFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/13.
 */

public class FrameFragmentHome extends BaseFragment {


    @BindView(R.id.tab_main)
    TabLayout tabMain;

    Unbinder unbinder;

    @BindView(R.id.vp_frame)
    ViewPager vpFrame;

    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frame_fragment_interview, null);
    }

    @Override
    protected void initUI() {

//        selectViewPage();

        initPageView();



    }

    private void initPageView() {

        tabMain.addTab(tabMain.newTab().setText("推荐"));
        tabMain.addTab(tabMain.newTab().setText("快讯"));
        tabMain.addTab(tabMain.newTab().setText("热点"));
        tabMain.addTab(tabMain.newTab().setText("分析"));

        vpFrame.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMain));

        MyTabAdapter tabAdapter = new MyTabAdapter(getFragmentManager());
        vpFrame.setAdapter(tabAdapter);

        vpFrame.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMain));

        tabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpFrame.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void loadInitData() {

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


    class MyTabAdapter extends FragmentPagerAdapter{

        private HashMap<Integer, BaseFragment> mFragmentHashMap = new HashMap<>();

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
            mFragmentHashMap=new HashMap<>();
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {

            BaseFragment fragment = createFragment(position);
            return fragment;
        }



        private BaseFragment createFragment(int pos) {

            BaseFragment fragment = mFragmentHashMap.get(pos);

            if (fragment == null) {
                switch (pos) {
                    case 0:
                        fragment = new FrameFragmentFirst();
                        break;
                    case 1:
                        fragment = new FrameFragmentSecond();
                        break;
                    case 2:
                        fragment = new FrameFragmentThird();
                        break;
                    case 3:
                        fragment = new FrameFragmentFourth();
                        break;
                }
                mFragmentHashMap.put(pos, fragment);
            }
            return fragment;
        }

    }


}
