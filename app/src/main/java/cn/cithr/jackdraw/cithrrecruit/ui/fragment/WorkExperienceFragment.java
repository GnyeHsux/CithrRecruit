package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/6/21.
 */
public class WorkExperienceFragment extends BaseFragment implements BaseFragment.MyOnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_start_date)
    TextView mTvStartDate;
    @BindView(R.id.ll_start_date)
    LinearLayout mLlStartDate;
    @BindView(R.id.tv_end_date)
    TextView mTvEndDate;
    @BindView(R.id.ll_end_date)
    LinearLayout mLlEndDate;
    @BindView(R.id.et_company_name)
    EditText mEtCompanyName;
    @BindView(R.id.tv_company_nature)
    TextView mTvCompanyNature;
    @BindView(R.id.ll_company_nature)
    LinearLayout mLlCompanyNature;
    @BindView(R.id.tv_company_industry)
    TextView mTvCompanyIndustry;
    @BindView(R.id.ll_company_industry)
    LinearLayout mLlCompanyIndustry;
    @BindView(R.id.et_sub_function)
    EditText mEtSubFunction;
    @BindView(R.id.et_responsibility)
    EditText mEtResponsibility;

    private String[] mData;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setMyOnClickListener(this);
        initClick();

        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_work_experience);
    }

    private void initClick() {
        mLlStartDate.setOnClickListener(this);
        mLlEndDate.setOnClickListener(this);
        mLlCompanyNature.setOnClickListener(this);
        mLlCompanyIndustry.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work_experience;
    }


    @Override
    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_start_date:
                showDateSelectDialog(mTvStartDate);
                break;

            case R.id.ll_end_date:
                showDateSelectDialog(mTvEndDate);
                break;

            case R.id.ll_company_nature:
                mData = new String[]{"国企", "民营", "集体", "个人"};
                showDialog(mData, mTvCompanyNature);
                break;

            case R.id.ll_company_industry:
                mData = new String[]{"计算机科学", "金融", "建筑业"};
                showDialog(mData, mTvCompanyIndustry);
                break;

        }
    }
}
