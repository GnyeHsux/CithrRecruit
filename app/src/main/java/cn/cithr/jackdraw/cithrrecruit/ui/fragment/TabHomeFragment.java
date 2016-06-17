package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.JobInfoActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.EndLessOnScrollListener;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.JobListAdapter;
import cn.cithr.jackdraw.cithrrecruit.ui.widget.RecyclerViewClickListener;


/**
 * Created by xusha on 2016/5/20.
 */
public class TabHomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout mRefreshLayout;

    private JobListAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private List<String> mData;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initData();
        ButterKnife.bind(this, view);

        mAdapter = new JobListAdapter(getHoldingActivity(), mData);

        //刷新旋转图标颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.red,
                R.color.orange,
                R.color.blue
        );

        mLinearLayoutManager = new LinearLayoutManager(getHoldingActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);

        //recyclerView中Item的点击监听事件
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getHoldingActivity(), mRecyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position != -1) {       //莫名其妙的bug
                    //传入职位id
                    Intent intent = new Intent(getHoldingActivity(), JobInfoActivity.class);
                    startActivity(intent);
                }
            }
        }));

        /**
         * 监听addOnScrollListener这个方法，新建我们的EndLessOnScrollListener
         * 在onLoadMore方法中去完成上拉加载的操作
         * */
        mRecyclerView.addOnScrollListener(new EndLessOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });
    }

    //初始化一开始加载的数据
    private void initData() {
        mData = new ArrayList<String>();
        mData.add("");
    }

    //每次上拉加载的时候，就加载数据到RecyclerView中
    private void loadMoreData() {
        mData.add("");
        mAdapter.notifyDataSetChanged();

    }

    //加载布局
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        updateData();
        mAdapter.notifyDataSetChanged();
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mRefreshLayout.setRefreshing(false);
    }

    //更新数据
    private void updateData() {
        //我在List最前面加入一条数据
        mData.add(0, "");
    }
}
