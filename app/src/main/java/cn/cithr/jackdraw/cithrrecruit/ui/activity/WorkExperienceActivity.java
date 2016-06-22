package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.WorkExperienceFragment;

/**
 * Created by xusha on 2016/6/21.
 */
public class WorkExperienceActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return new WorkExperienceFragment();
    }
}
