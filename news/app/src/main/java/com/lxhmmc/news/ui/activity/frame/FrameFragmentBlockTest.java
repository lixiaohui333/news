package com.lxhmmc.news.ui.activity.frame;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxhmmc.news.R;
import com.lxhmmc.news.ui.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/13.
 */

public class FrameFragmentBlockTest extends BaseFragment {



    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frame_fragment_block, null);
    }

    @Override
    protected void initUI() {
        if(viewRoot!=null) {
            TextView tv_name = viewRoot.findViewById(R.id.tv_name);
            if(tv_name!=null)
                tv_name.setText("hahahah");
        }
    }

    @Override
    protected void loadInitData() {

    }

}
