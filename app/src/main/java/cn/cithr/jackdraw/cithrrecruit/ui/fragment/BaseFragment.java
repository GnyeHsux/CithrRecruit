package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.cithr.jackdraw.cithrrecruit.ui.activity.BaseActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;


/**
 * Created by xusha on 2016/5/24.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);

        return view;
    }

    public void setToolbar(Toolbar toolbar, int title){
        toolbar.setTitle(title);
        getHoldingActivity().setSupportActionBar(toolbar);
        getHoldingActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getHoldingActivity().getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    getHoldingActivity().finish();
                }else {
                    removeFragment();
                }
            }
        });
    }
}
