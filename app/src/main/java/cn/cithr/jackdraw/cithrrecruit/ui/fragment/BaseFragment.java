package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import cn.cithr.jackdraw.cithrrecruit.app.MyApplication;
import cn.cithr.jackdraw.cithrrecruit.ui.activity.BaseActivity;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


/**
 * Created by xusha on 2016/5/24.
 */
public abstract class BaseFragment extends SwipeBackFragment implements View.OnClickListener {
    protected BaseActivity mActivity;
    private MyApplication app;
    MyOnClickListener myOnClickListener;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fromFragment, BaseFragment toFragment) {
        if (null != toFragment) {
            getHoldingActivity().addFragment(fromFragment, toFragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        // 设置滑动方向
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_RIGHT); // EDGE_LEFT(默认),EDGE_ALL
        return attachToSwipeBack(view);
    }

    public void setToolbar(Toolbar toolbar, int title) {
        toolbar.setTitle(title);
        getHoldingActivity().setSupportActionBar(toolbar);
        getHoldingActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getHoldingActivity().getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    getHoldingActivity().finish();
                } else {
                    removeFragment();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        app = (MyApplication) getContext().getApplicationContext();
        if (app.getIsNetwork()) {
            myOnClickListener.myOnClick(view);
        } else {
            ToastUtils.makeShortText("网络连接失败,请检查你的网络设置", getHoldingActivity());
        }
    }

    public interface MyOnClickListener {
        void myOnClick(View view);
    }

    public void setMyOnClickListener(MyOnClickListener mOnClickListener) {
        this.myOnClickListener = mOnClickListener;
    }

    /**
     * 根据datas显示选择对话框
     *
     * @param datas
     * @param textView
     */
    public void showDialog(String[] datas, TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getHoldingActivity());
        builder.setItems(datas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(datas[which]);
            }
        });
        builder.create().show();
    }

    /**
     * 显示选择日期对话框
     *
     * @param textView 显示选择的日期
     */
    public void showDateSelectDialog(TextView textView) {
        Calendar c = Calendar.getInstance();
        //日期对话框，选择日期
        new DatePickerDialog(getHoldingActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textView.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }
                , c.get(Calendar.YEAR)
                , c.get(Calendar.MONTH)
                , c.get(Calendar.DAY_OF_MONTH)
        ).show();
    }
}