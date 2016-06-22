package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/6/21.
 */
public class EducationFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.et_school_name)
    EditText mEtSchoolName;
    @BindView(R.id.et_major)
    EditText mEtMajor;
    @BindView(R.id.tv_degree)
    TextView mTvDegree;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_education);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_education;
    }

}
