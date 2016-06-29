package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Created by xusha on 2016/5/24.
 */
public abstract class BaseActivity extends SwipeBackActivity {
    BaseFragment currentFragment;
    BaseFragment lastFragment;

    //布局文件ID
    protected abstract int getContentViewId();

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    //添加fragment
    public void addFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        currentFragment = (BaseFragment) fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName());
        if (currentFragment == null) {
            currentFragment = fragment;
            ft.add(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            ft.addToBackStack(currentFragment.getClass().getSimpleName());
        }
        if (lastFragment != null) {
            ft.hide(lastFragment);
        }
        if (currentFragment.isDetached()) {
            ft.attach(currentFragment);
        }
        ft.show(currentFragment);
        lastFragment = currentFragment;
        ft.commit();

//        else if (toFragment != null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(getFragmentContentId(), toFragment, toFragment.getClass().getSimpleName())
//                    .hide(fromFragment)
//                    .addToBackStack(fromFragment.getClass().getSimpleName())
//                    .commitAllowingStateLoss();
//        }
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
