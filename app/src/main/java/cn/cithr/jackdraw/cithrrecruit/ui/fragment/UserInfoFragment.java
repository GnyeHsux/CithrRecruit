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
 * Created by xusha on 2016/5/25.
 */
public class UserInfoFragment extends BaseFragment implements BaseFragment.MyOnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_user_name)
    EditText mEtUserName;
    @BindView(R.id.tv_gender)
    TextView mTvGender;
    @BindView(R.id.tv_marriage)
    TextView mTvMarriage;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.tv_degree)
    TextView mTvDegree;
    @BindView(R.id.tv_polictical_status)
    TextView mTvPolicticalStatus;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.et_workyear)
    EditText mEtWorkyear;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.ll_gender)
    LinearLayout mLlGender;
    @BindView(R.id.ll_marriage)
    LinearLayout mLlMarriage;
    @BindView(R.id.ll_birthday)
    LinearLayout mLlBirthday;
    @BindView(R.id.ll_degree)
    LinearLayout mLlDegree;
    @BindView(R.id.ll_polictical_status)
    LinearLayout mLlPolicticalStatus;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;

    private String[] mDatas;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initClick();
        setMyOnClickListener(this);
        //设置toolbar
        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_user_info);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }

    private void initClick() {
        mLlBirthday.setOnClickListener(this);
        mLlDegree.setOnClickListener(this);
        mLlGender.setOnClickListener(this);
        mLlLocation.setOnClickListener(this);
        mLlMarriage.setOnClickListener(this);
        mLlPolicticalStatus.setOnClickListener(this);
    }

    @Override
    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_gender:
                mDatas = new String[]{"男", "女"};
                showDialog(mDatas, mTvGender);
                break;

            case R.id.ll_marriage:
                mDatas = new String[]{"已婚", "未婚"};
                showDialog(mDatas, mTvMarriage);
                break;

            case R.id.ll_birthday:
                showDateSelectDialog(mTvBirthday);
                break;

            case R.id.ll_degree:
                mDatas = new String[]{"小学", "初中", "高中", "本科", "硕士", "博士"};
                showDialog(mDatas, mTvDegree);
                break;

            case R.id.ll_polictical_status:
                mDatas = new String[]{"群众", "团员", "党员"};
                showDialog(mDatas, mTvPolicticalStatus);
                break;

            case R.id.ll_location:
                mDatas = new String[]{"广州", "深圳", "东莞", "北京", "上海", "天津"};
                showDialog(mDatas, mTvLocation);
                break;

            default:
                break;
        }
    }

}
