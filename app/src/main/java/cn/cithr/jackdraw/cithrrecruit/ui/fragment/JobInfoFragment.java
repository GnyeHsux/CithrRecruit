package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;

/**
 * Created by xusha on 2016/6/11.
 */
public class JobInfoFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        MainActivity.isLockDrawer(true);    //禁止主页面抽屉侧滑

        setToolbar(mToolbar, R.string.title_job_info);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobinfo;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.isLockDrawer(false);   //打开主页面抽屉侧滑
    }
}
