package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.DividerItemDecoration;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.UserInfoAdapter;
import cn.cithr.jackdraw.cithrrecruit.ui.widget.RecyclerViewClickListener;

/**
 * Created by xusha on 2016/5/25.
 */
public class UserInfoFragment extends BaseFragment {
    @BindView(R.id.rv_user_info)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    RecyclerView.LayoutManager mLayoutManager;
    UserInfoAdapter mAdapter;

    private String[] mDatas;
    private TextView mTextView;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);


        //设置toolbar
        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_user_info);

        mLayoutManager = new LinearLayoutManager(getHoldingActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new UserInfoAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //添加Item点击事件
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getHoldingActivity(), mRecyclerView,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, int position) {
                        showDialog(view, position);
                    }
                }));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.user_info_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }

    private void showDialog(final View view, int position) {
        switch (position) {
            //头像
            case 0:
                break;
            //姓名
            case 1:
                break;
            //性别
            case 2:
                mDatas = new String[]{"男", "女"};
                initDialog(view);
                break;
            //婚姻状况
            case 3:
                mDatas = new String[]{"已婚", "未婚"};
                initDialog(view);
                break;
            //出生日期
            case 4:
                break;
            //最高学历
            case 5:
                mDatas = new String[]{"小学", "初中", "高中", "本科", "硕士", "博士"};
                initDialog(view);
                break;
            //工作年限
            case 6:
                break;
            //现居地
            case 8:
                break;
        }
    }

    private void initDialog(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getHoldingActivity());
        builder.setItems(mDatas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTextView = (TextView) view.findViewById(R.id.tv_normal_item_value);
                mTextView.setText(mDatas[which]);
            }
        });
        builder.create().show();
    }
}
