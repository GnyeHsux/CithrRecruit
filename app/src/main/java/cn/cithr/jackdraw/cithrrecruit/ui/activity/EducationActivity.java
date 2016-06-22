package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.EducationFragment;

/**
 * Created by xusha on 2016/6/21.
 */
public class EducationActivity extends AppActivity {
    @Override
    protected BaseFragment getFirstFragment() {
        return new EducationFragment();
    }
}
