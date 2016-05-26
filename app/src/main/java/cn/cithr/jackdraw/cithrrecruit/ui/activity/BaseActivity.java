package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;

/**
 * Created by xusha on 2016/5/24.
 */
public abstract class BaseActivity extends AppCompatActivity {
    //布局文件ID
    protected abstract int getContentViewId();

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    //添加fragment
    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
//            if (fragment.getClass() != MainFragment.class) {
//                getSupportFragmentManager().beginTransaction()
//                        .setCustomAnimations(
//                                R.anim.fragment_slide_right_in,
//                                R.anim.fragment_slide_left_out,
//                                R.anim.fragment_slide_left_in,
//                                R.anim.fragment_slide_right_out
//                                );
//            }
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
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
