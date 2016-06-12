package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.MainActivity;

/**
 * Created by xusha on 2016/6/11.
 */
public class JobInfoFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView_jobRequirements)
    WebView mWebview;
    @BindView(R.id.cardView_company)
    CardView mCardViewCompany;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        MainActivity.isLockDrawer(true);    //禁止主页面抽屉侧滑

        mCardViewCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传入公司id
                addFragment(new CompanyInfoFragment());
            }
        });

        String htmlData = "岗位职责:负责接听客户电话，了解客户信息。<br/><br/>任职资格:<br/>1.普通话标准，有流畅的语言沟通能力，认真的工作态度。<br/>2.有上进心，有目标，能完善自我，体现自我价值，完成上级领导所安排的事宜。<br/>3.有无经验亦可，可带薪培训。<br/>工作时间:8.30-5.30，不加班，朝九晚五。&nbsp;<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        mWebview.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);

        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_job_info);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.job_info_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobinfo;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.isLockDrawer(false);   //打开主页面抽屉侧滑
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
