package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.model.entities.JobFavoriteList;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.JobInfoActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.JobFavoriteListAdapter;

/**
 * Created by xusha on 2016/6/25.
 */
public class JobFavoriteFragment extends BaseFragment implements RecyclerTouchListener.RecyclerTouchListenerHelper {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_job_favorite)
    RecyclerView mRvJobFavorite;

    JobFavoriteListAdapter mJobFavoriteListAdapter;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mToolbar.setTitle(R.string.title_job_favorite);
        ((MainActivity) getActivity()).setToolBar(mToolbar);

        mJobFavoriteListAdapter = new JobFavoriteListAdapter(getHoldingActivity(), getData());
        mRvJobFavorite.setAdapter(mJobFavoriteListAdapter);
        mRvJobFavorite.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));

        onTouchListener = new RecyclerTouchListener(getHoldingActivity(), mRvJobFavorite);
        onTouchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //根据简历收藏中，职位id打开职位详情
                        //以下为测试界面
                        startActivity(new Intent(getHoldingActivity(), JobInfoActivity.class));
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                })
                .setSwipeOptionViews(R.id.send_resume, R.id.favorite)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        if (viewID == R.id.send_resume) {
                            //发送简历
                            Snackbar.make(mRvJobFavorite, "已发送", Snackbar.LENGTH_SHORT).show();
                        } else if (viewID == R.id.favorite) {
                            mJobFavoriteListAdapter.deleteData(position);
                            Snackbar.make(mRvJobFavorite, "已取消收藏", Snackbar.LENGTH_LONG)
                                    .setAction("算了", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //再次收藏刚刚取消收藏的职位，并恢复在原位置
                                            mJobFavoriteListAdapter.addData(position);
                                        }
                                    }).show();
                        }
                    }
                });

        mRvJobFavorite.addOnItemTouchListener(onTouchListener);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_favorite;
    }


    //获取职位收藏列表
    private List<JobFavoriteList> getData() {
        List<JobFavoriteList> list = new ArrayList<>(15);
        for (int i = 0; i < 15; ++i) {
            list.add(new JobFavoriteList("XXX职位 " + (i + 1), "九九鼎盛营销策划有限公司"));
        }
        return list;
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }
}
