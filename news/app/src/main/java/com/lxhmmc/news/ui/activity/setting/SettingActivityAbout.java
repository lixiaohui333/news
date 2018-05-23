package com.lxhmmc.news.ui.activity.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lxhmmc.news.R;
import com.lxhmmc.news.ui.base.BaseFragmentActivity;
import com.lxhmmc.news.ui.base.GotoTool;
import com.lxhmmc.news.ui.base.UiUtil;
import com.lxhmmc.news.ui.dialog.CommonDialogFragment;
import com.lxhmmc.news.ui.dialog.DialogFragmentHelper;
import com.lxhmmc.news.ui.dialog.IDialogResultListener;
import com.lxhmmc.news.ui.present.setting.about.AboutContract;
import com.lxhmmc.news.ui.present.setting.about.AboutNetModel;
import com.lxhmmc.news.ui.present.setting.about.AboutPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lxhmmc.news.comm.Config.ALIPAY_ADDRESS;
import static com.lxhmmc.news.comm.Config.GITHUB_ADDRESS;

public class SettingActivityAbout extends BaseFragmentActivity<AboutPresent,AboutNetModel> implements AboutContract.NetView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;

//    AboutPresent aboutPresent;

    CommonDialogFragment commonDialogFragment = null;

    @Override
    protected boolean intentData() {
        return true;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.setting_activity_about);
    }

    @Override
    protected void initUI() {

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String version = UiUtil.getVersionName();

        tvVersionName.setText(version);


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

    @OnClick({R.id.rl_github, R.id.rl_version, R.id.rl_alipay, R.id.rl_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_github:
                //

                GotoTool.webByBrowser(GITHUB_ADDRESS);

                break;
            case R.id.rl_version:


                break;
            case R.id.rl_alipay:
                GotoTool.goAlipay(ALIPAY_ADDRESS);

                break;
            case R.id.rl_feedback:
                DialogFragmentHelper.showIntervalInsertDialog(getSupportFragmentManager(), "意见反馈", new IDialogResultListener<String>() {
                    @Override
                    public void onDataResult(String result) {
                        sendFeedback(result);
                    }
                }, false);

                break;
        }
    }

    private void sendFeedback(String result) {

        if (TextUtils.isEmpty(result)) {
            return;
        }

//        if (aboutPresent == null) {
//            aboutPresent = new AboutPresent(AboutNetModel.getNewInstance(), this);
//        }

        mPresenter.feedback(result);
    }


    @Override
    public void hideProgress() {
        if (commonDialogFragment != null) {
            commonDialogFragment.dismiss(BaseFragmentActivity.activity == this);
        }
    }

    @Override
    public void showProgress() {

        if (commonDialogFragment != null) {
            commonDialogFragment.show(getSupportFragmentManager(), TAG, BaseFragmentActivity.activity == this);
        } else {
            commonDialogFragment = DialogFragmentHelper.showProgress(getSupportFragmentManager(), "请稍等", true);
        }

    }

    @Override
    public void error() {
        showToast("error");
    }

    @Override
    public void setFeedbackSuccess(String info) {
        showToast(info);
    }
}
