package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.JobObjectiveFragment;

/**
 * Created by xusha on 2016/6/22.
 */
public class JobObjectiveActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return new JobObjectiveFragment();
    }
}
