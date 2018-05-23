package com.lxhmmc.news.ui.activity.frame;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;

import com.lxhmmc.news.R;
import com.lxhmmc.news.ui.base.BaseFragmentActivity;
import com.yanzhenjie.sofia.Sofia;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrameActivityMain extends BaseFragmentActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    FrameFragmentHome frameFragmentInterview;
    FrameFragmentBlock frameFrameFragmentBlock;
    FrameFragmentMsg frameFrameFragmentMsg;
    FrameFragmentProfile frameFrameFragmentProfile;


    @Override
    protected boolean intentData() {
        return true;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.frame_activity_main);
    }

    @Override
    protected void initUI() {

        setSupportActionBar(toolbar);

        Sofia.with(this)
                .invasionStatusBar()
                .statusBarBackground(ContextCompat.getColor(this, android.R.color.transparent))
                .navigationBarBackground(ContextCompat.getColor(this, R.color.colorPrimary));


        setMyRightIcon(R.drawable.me_tab, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("hahah right");
            }
        }, toolbar);



        selectFragment(R.id.rb_interview);

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectFragment(checkedId);
            }
        });

    }

    private void setFrameTitle(int checkedId) {


        switch (checkedId) {
            case R.id.rb_interview:
                setMyTitle("新闻", toolbar);
                toolbar.setVisibility(View.VISIBLE);

                break;
            case R.id.rb_block:
                setMyTitle("数说天下", toolbar);
                toolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_msg:
                toolbar.setVisibility(View.GONE);
                break;
            case R.id.rb_profile:
                toolbar.setVisibility(View.GONE);
                break;
            default:
                break;
        }

    }


    private void selectFragment(int checkedId) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        hideFragment(transaction);

        switch (checkedId) {
            case R.id.rb_interview:
                if (frameFragmentInterview == null) {
                    frameFragmentInterview = new FrameFragmentHome();
                    transaction.add(R.id.fl_frame, frameFragmentInterview);
                } else {
                    transaction.show(frameFragmentInterview);
                }
                break;
            case R.id.rb_block:
                if (frameFrameFragmentBlock == null) {
                    frameFrameFragmentBlock = new FrameFragmentBlock();
                    transaction.add(R.id.fl_frame, frameFrameFragmentBlock);
                } else {
                    transaction.show(frameFrameFragmentBlock);
                }
                break;
            case R.id.rb_msg:
                if (frameFrameFragmentMsg == null) {
                    frameFrameFragmentMsg = new FrameFragmentMsg();
                    transaction.add(R.id.fl_frame, frameFrameFragmentMsg);
                } else {
                    transaction.show(frameFrameFragmentMsg);
                }
                break;
            case R.id.rb_profile:
                if (frameFrameFragmentProfile == null) {
                    frameFrameFragmentProfile = new FrameFragmentProfile();
                    transaction.add(R.id.fl_frame, frameFrameFragmentProfile);
                } else {
                    transaction.show(frameFrameFragmentProfile);
                }
                break;
            default:
                break;
        }
        transaction.commit();
        setFrameTitle(checkedId);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(frameFrameFragmentBlock!=null)
            transaction.hide(frameFrameFragmentBlock);
        if(frameFragmentInterview!=null)
            transaction.hide(frameFragmentInterview);
        if(frameFrameFragmentMsg!=null)
            transaction.hide(frameFrameFragmentMsg);
        if(frameFrameFragmentProfile!=null)
            transaction.hide(frameFrameFragmentProfile);

    }

    @Override
    protected void setUI() {

    }

    @Override
    protected void loadInitData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
