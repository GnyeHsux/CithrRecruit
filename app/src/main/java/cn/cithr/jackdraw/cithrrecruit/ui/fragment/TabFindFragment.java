package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.view.View;

import cn.cithr.jackdraw.cithrrecruit.R;


/**
 * Created by xusha on 2016/5/20.
 */
public class TabFindFragment extends BaseFragment {

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        //取消侧滑返回
        setSwipeBackEnable(false);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_find;
    }
}
