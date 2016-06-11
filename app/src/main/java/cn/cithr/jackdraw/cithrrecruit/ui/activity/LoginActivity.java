package cn.cithr.jackdraw.cithrrecruit.ui.activity;


import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.LoginFragment;


/**
 * Created by xusha on 2016/5/22.
 */
public class LoginActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return LoginFragment.newIntance();
    }

    
}
