package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.SimpleFragmentPagerAdapter;
import cn.cithr.jackdraw.cithrrecruit.ui.common.HintSearchBox;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;


/**
 * Created by xusha on 2016/5/20.
 */
public class MainFragment extends BaseFragment {
    private static final int SEARCH_HISTORY_SIZE = 5;   //搜索数量

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.searchbox)
    HintSearchBox mSearchBox;

    private ArrayList<String> mSearchHistory;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.home_name);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                //搜索按钮监听事件
                if (id == R.id.action_search) {
                    mSearchBox.clearSearchable();
                    for (String his : mSearchHistory) {
                        mSearchBox.addSearchable(
                                new SearchResult(his, null));
                    }
                    mSearchBox.revealFromMenuItem(R.id.action_search, getHoldingActivity());

                }

                return false;
            }
        });
        ((MainActivity) getActivity()).setToolBar(toolbar);

        setupViewPager();

        mSearchHistory = new ArrayList<>();
        mSearchBox.setLogoText(getString(R.string.search_hint));
        //searchBox的搜索事件
        mSearchBox.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {
                mSearchBox.hideCircularly(getHoldingActivity());
            }

            //s为搜索关键字
            @Override
            public void onSearchTermChanged(String s) {
                if (mSearchHistory.size() == SEARCH_HISTORY_SIZE) {
                    mSearchHistory.remove(SEARCH_HISTORY_SIZE - 1);
                }
                if (!mSearchHistory.contains(s)) {
                    mSearchHistory.add(0, s);
                }

            }

            @Override
            public void onSearch(String s) {
                //启动搜索页面，传入搜索关键字
                ToastUtils.showShort(getHoldingActivity(), s);
            }

            @Override
            public void onResultClick(SearchResult searchResult) {

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mian;
    }


    private void setupViewPager() {
        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), getActivity());
        mViewPager.setAdapter(pagerAdapter);
        //建立ViewPager与Tab的联系
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

}
