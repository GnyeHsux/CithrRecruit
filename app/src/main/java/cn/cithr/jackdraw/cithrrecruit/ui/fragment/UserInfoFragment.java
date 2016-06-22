package cn.cithr.jackdraw.cithrrecruit.ui.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.DividerItemDecoration;
import cn.cithr.jackdraw.cithrrecruit.ui.adapter.UserInfoAdapter;
import cn.cithr.jackdraw.cithrrecruit.ui.widget.RecyclerViewClickListener;
import cn.cithr.jackdraw.cithrrecruit.utils.RegexUtils;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;

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
        inflater.inflate(R.menu.save_menu, menu);
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
                initEditDialog(view, "请输入姓名");
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
                Calendar c = Calendar.getInstance();
                //日期对话框，选择日期
                new DatePickerDialog(getHoldingActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mTextView.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                            }
                        }
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)
                ).show();
                break;
            //最高学历
            case 5:
                mDatas = new String[]{"小学", "初中", "高中", "本科", "硕士", "博士"};
                initDialog(view);
                break;
            //工作年限
            case 6:
                initEditDialog(view, "请输入工作年限");
                break;
            //手机号码
            case 7:
                initEditDialog(view, "请输入手机号码");
                break;
            //现居地
            case 8:
                mDatas = new String[]{"广州", "深圳", "东莞", "北京", "上海", "天津"};
                initDialog(view);
                break;
            //政治面貌：
            case 9:
                mDatas = new String[]{"群众", "团员", "党员"};
                initDialog(view);
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

    private void initEditDialog(final View view, String title) {
        AppCompatEditText editText = new AppCompatEditText(getHoldingActivity());
        if (!title.equals("请输入姓名")) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getHoldingActivity());
        builder.setTitle(title)
                .setIcon(R.mipmap.ic_launcher)
                .setView(editText)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (title.equals("请输入手机号码")) {
                            Boolean isTrue = RegexUtils.checkMobile(editText.getText().toString().trim());
                            if (!isTrue) {
                                ToastUtils.showShort(getHoldingActivity(), "手机格式不正确");
                            }
                        }
                        mTextView = (TextView) view.findViewById(R.id.tv_normal_item_value);
                        mTextView.setText(editText.getText().toString().trim());
                        if (title.equals("请输入工作年限")) {
                            mTextView.append("年");
                        }
                    }
                }).create().show();
    }
}
