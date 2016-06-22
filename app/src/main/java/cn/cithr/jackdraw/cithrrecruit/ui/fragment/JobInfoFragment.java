package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;

/**
 * Created by xusha on 2016/6/11.
 */
public class JobInfoFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView_jobRequirements)
    WebView mWebview;
    @BindView(R.id.include)
    View mView;


    public static JobInfoFragment newIntance() {
        return new JobInfoFragment();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        String htmlData = "岗位职责:负责接听客户电话，了解客户信息。<br/><br/>任职资格:<br/>1.普通话标准，有流畅的语言沟通能力，认真的工作态度。<br/>2.有上进心，有目标，能完善自我，体现自我价值，完成上级领导所安排的事宜。<br/>3.有无经验亦可，可带薪培训。<br/>工作时间:8.30-5.30，不加班，朝九晚五。&nbsp;<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        mWebview.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);

//        RelativeLayout rl = (RelativeLayout) mView.findViewById(R.id.rl_company);
//        rl.setId(Integer.parseInt("1"));
//        rl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addFragment(new CompanyInfoFragment());
//            }
//        });

        setHasOptionsMenu(true);
        setToolbar(mToolbar, R.string.title_job_info);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.job_info_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.job_info_save) {
            ToastUtils.showShort(getHoldingActivity(), "收藏");
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jobinfo;
    }

}
