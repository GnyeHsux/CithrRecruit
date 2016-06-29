package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.CreateResumeActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.DividerItemDecoration;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.ResumeListAdapter;
import cn.cithr.jackdraw.cithrrecruit.ui.widget.RecyclerViewClickListener;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;

/**
 * Created by xusha on 2016/5/20.
 */
public class TabResumeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private ResumeListAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private List<String> mData;     //简历名称集合

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initData();

        mAdapter = new ResumeListAdapter(getHoldingActivity(), mData);
        mLinearLayoutManager = new LinearLayoutManager(getHoldingActivity());

        mRecyclerview.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mRecyclerview.setAdapter(mAdapter);

        //刷新旋转图标颜色
        mRefreshLayout.setColorSchemeResources(
                R.color.red,
                R.color.orange,
                R.color.blue
        );
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerview.addOnItemTouchListener(new RecyclerViewClickListener(getHoldingActivity(), mRecyclerview, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //传入简历id，打开简历页面
                if (position != -1) {
                    TextView textView = (TextView) view.findViewById(R.id.rv_item_resumName);
                    ToastUtils.showShort(getActivity(), textView.getText().toString());
                }
            }
        }));

        //悬浮按钮监听事件，新建简历
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showShort(getHoldingActivity(),"新建简历");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(getHoldingActivity(), mFab, mFab.getTransitionName());
                    startActivity(new Intent(getHoldingActivity(), CreateResumeActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(getHoldingActivity(), CreateResumeActivity.class));
                }
            }
        });

        //取消侧滑返回
        setSwipeBackEnable(false);
    }

    private void initData() {
        mData = new ArrayList<String>();
        mData.add("我的简历1");
        mData.add("我的简历2");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_resume;
    }

    @Override
    public void onRefresh() {
        updateData();

        mAdapter.notifyDataSetChanged();
        //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
        mRefreshLayout.setRefreshing(false);
    }

    private void updateData() {
        //在List最前面加入最新数据
        mData.add(0, "我的简历3");
        mData.add(0, "我的简历4");
    }
}
