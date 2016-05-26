package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.RegisterFragment;

/**
 * Created by xusha on 2016/5/24.
 */
public class RegisterActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return new RegisterFragment();
    }
}
