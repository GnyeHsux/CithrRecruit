package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import android.view.KeyEvent;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by xusha on 2016/5/24.
 */
public abstract class BaseActivity extends SwipeBackActivity {
    //布局文件ID
    protected abstract int getContentViewId();

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    //添加fragment
    public void addFragment(BaseFragment fromFragment, BaseFragment toFragment) {
        if (fromFragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), toFragment, toFragment.getClass().getSimpleName())
                    .addToBackStack(toFragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        } else if (toFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    //.replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .add(getFragmentContentId(), toFragment, toFragment.getClass().getSimpleName())
                    .hide(fromFragment)
                    .addToBackStack(toFragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public void removeFragment() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
