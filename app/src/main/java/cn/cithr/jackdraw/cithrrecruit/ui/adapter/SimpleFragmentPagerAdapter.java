package cn.cithr.jackdraw.cithrrecruit.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.TabCVFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.TabFindFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.TabHomeFragment;


/**
 * Created by xusha on 2016/5/20.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private Context mContext;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new TabHomeFragment();
                break;
            case 1:
                fragment = new TabCVFragment();
                break;
            case 2:
                fragment = new TabFindFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = mContext.getString(R.string.no_title);
        switch (position) {
            case 0:
                title = mContext.getString(R.string.tab_name);
                break;
            case 1:
                title = mContext.getString(R.string.tab_cv);
                break;
            case 2:
                title = mContext.getString(R.string.tab_find);
                break;
        }
        return title;
    }

}
