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
 * Created by xusha on 2016/6/22.
 */
public class JobObjectiveFragment extends BaseFragment implements BaseFragment.MyOnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_expect_industry_type)
    TextView mTvExpectIndustryType;
    @BindView(R.id.ll_expct_industry_type)
    LinearLayout mLlExpctIndustryType;
    @BindView(R.id.tv_expect_job)
    TextView mTvExpectJob;
    @BindView(R.id.ll_expect_job)
    LinearLayout mLlExpectJob;
    @BindView(R.id.tv_expect_location)
    TextView mTvExpectLocation;
    @BindView(R.id.ll_expect_location)
    LinearLayout mLlExpectLocation;
    @BindView(R.id.tv_job_nature)
    TextView mTvJobNature;
    @BindView(R.id.ll_job_nature)
    LinearLayout mLlJobNature;
    @BindView(R.id.tv_expect_salary)
    TextView mTvExpectSalary;
    @BindView(R.id.ll_expect_salary)
    LinearLayout mLlExpectSalary;
    @BindView(R.id.et_seleveluation)
    EditText mEtSeleveluation;

    private String[] mData;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setMyOnClickListener(this);
        initClick();

        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_job_objective);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_objective;
    }

    //初始化点击事件
    private void initClick() {
        mLlExpctIndustryType.setOnClickListener(this);
        mLlExpectJob.setOnClickListener(this);
        mLlExpectLocation.setOnClickListener(this);
        mLlJobNature.setOnClickListener(this);
        mLlExpectSalary.setOnClickListener(this);
    }

    @Override
    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_expct_industry_type:
                mData = new String[]{"计算机", "金融", "建筑", "演员"};
                showDialog(mData, mTvExpectIndustryType);
                break;

            case R.id.ll_expect_job:
                mData = new String[]{"Android开发", "IOS开发", "PHP开发", "Node.js"};
                showDialog(mData, mTvExpectJob);
                break;

            case R.id.ll_expect_location:
                mData = new String[]{"广州", "深圳", "上海", "北京"};
                showDialog(mData, mTvExpectLocation);
                break;

            case R.id.ll_job_nature:
                mData = new String[]{"全职", "兼职", "临时"};
                showDialog(mData, mTvJobNature);
                break;

            case R.id.ll_expect_salary:
                mData = new String[]{"2k", "5k", "10k"};
                showDialog(mData, mTvExpectSalary);
                break;

            default:
                break;
        }
    }
}
