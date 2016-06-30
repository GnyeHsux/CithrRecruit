package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.model.entities.JobApplyList;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.JobInfoActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.DividerItemDecoration;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.JobApplyListAdapter;
import cn.cithr.jackdraw.cithrrecruit.ui.widget.RecyclerViewClickListener;

/**
 * Created by xusha on 2016/6/29.
 */
public class JobApplayFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_job_apply)
    RecyclerView mRvJobApply;

    JobApplyListAdapter mJobApplyListAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mToolbar.setTitle(R.string.title_job_apply);
        ((MainActivity) getActivity()).setToolBar(mToolbar);
        setSwipeBackEnable(false);

        mJobApplyListAdapter = new JobApplyListAdapter(getHoldingActivity(), getData());
        mRvJobApply.setAdapter(mJobApplyListAdapter);
        mRvJobApply.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        mRvJobApply.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), DividerItemDecoration.VERTICAL_LIST));

        mRvJobApply.addOnItemTouchListener(new RecyclerViewClickListener(getHoldingActivity(), mRvJobApply, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getHoldingActivity(), JobInfoActivity.class));
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_apply;
    }

    //获取职位收藏列表
    private List<JobApplyList> getData() {
        List<JobApplyList> list = new ArrayList<>(15);
        for (int i = 0; i < 15; ++i) {
            list.add(new JobApplyList("XXX职位 " + (i + 1), "九九鼎盛营销策划有限公司"));
        }
        return list;
    }

}
