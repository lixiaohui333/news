package com.lxhmmc.news.ui.activity.frame.home;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxhmmc.news.R;
import com.lxhmmc.news.ui.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/13.
 */

public class FrameFragmentThird extends BaseFragment {



    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frame_fragment_msg, null);
    }

    @Override
    protected void initUI() {
        if(viewRoot!=null) {
            TextView tv_name = viewRoot.findViewById(R.id.tv_name);

            if(tv_name!=null)
                tv_name.setText("third");
        }
    }

    @Override
    protected void loadInitData() {

    }

    @Override
    public void lazyLoad() {
        super.lazyLoad();


        Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView tv_name = viewRoot.findViewById(R.id.tv_name);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                String t=format.format(new Date());
                if(tv_name!=null)
                    tv_name.setText("lazyLoad load third "+t);
            }
        },2000);

    }
}
