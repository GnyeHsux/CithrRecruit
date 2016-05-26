package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.UserInfoFragment;

/**
 * Created by xusha on 2016/5/26.
 */
public class UserInfoActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return new UserInfoFragment();
    }
}
