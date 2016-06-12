package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/6/12.
 */
public class CompanyInfoFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView_CompanyIntroduce)
    WebView mWebView;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        setToolbar(mToolbar, R.string.title_company_info);

        String companyIntroduce = "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无锡九九鼎盛营销策划有限公司成立于2014年，是一家资金实力雄厚，服务功能齐全，行业信誉卓越的房地产代理、企业营销策划公司。九九鼎盛的创始人经历于行业数十载，经过综合经验积累，愿意带领每一位愿意努力、不怕辛苦、满腔热忱、无限学习潜力的您一起成功，致力打造全国优质团队连锁第一品牌。<br/>&nbsp;企业精神：品质为先，以人为本，诚信经营。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        mWebView.loadDataWithBaseURL(null, companyIntroduce, "text/html", "utf-8", null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company_info;
    }

}
