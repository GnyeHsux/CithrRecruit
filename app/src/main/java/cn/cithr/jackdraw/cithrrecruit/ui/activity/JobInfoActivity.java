package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.JobInfoFragment;

/**
 * Created by xusha on 2016/6/16.
 */
public class JobInfoActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return JobInfoFragment.newIntance();
    }
}
