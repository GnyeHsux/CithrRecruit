package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/6/22.
 */
public class LanguageAbilityFragment extends BaseFragment implements BaseFragment.MyOnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_language_type)
    TextView mTvLanguageType;
    @BindView(R.id.ll_language_type)
    LinearLayout mLlLanguageType;
    @BindView(R.id.tv_language_master)
    TextView mTvLanguageMaster;
    @BindView(R.id.ll_language_master)
    LinearLayout mLlLanguageMaster;
    @BindView(R.id.tv_rw_ability)
    TextView mTvRwAbility;
    @BindView(R.id.ll_rw_ability)
    LinearLayout mLlRwAbility;
    @BindView(R.id.tv_ls_ability)
    TextView mTvLsAbility;
    @BindView(R.id.ll_ls_ability)
    LinearLayout mLlLsAbility;

    private String[] mData;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setMyOnClickListener(this);

        initClick();
        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_language_ability);
    }

    private void initClick() {
        mLlLanguageType.setOnClickListener(this);
        mLlLanguageMaster.setOnClickListener(this);
        mLlRwAbility.setOnClickListener(this);
        mLlLsAbility.setOnClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_language_ability;
    }

    @Override
    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_language_type:
                mData = new String[]{"英语", "日语", "韩语", "西班牙语", "法语"};
                showDialog(mData, mTvLanguageType);
                break;

            case R.id.ll_language_master:
                mData = new String[]{"略懂", "一般", "熟练", "精通"};
                showDialog(mData, mTvLanguageMaster);
                break;

            case R.id.ll_rw_ability:
                mData = new String[]{"低", "中", "高", "优秀"};
                showDialog(mData, mTvRwAbility);
                break;

            case R.id.ll_ls_ability:
                mData = new String[]{"低", "中", "高", "优秀"};
                showDialog(mData, mTvLsAbility);
                break;

            default:
                break;
        }
    }
}
