package cn.cithr.jackdraw.cithrrecruit.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/5/25.
 */
public class UserInfoAdapter extends RecyclerView.Adapter {

    private final int ITEMCOUNT = 9;

    private static final int NORMAL_TYPE = 0;       //普通布局
    private static final int EDIT_TYPE = 1;         //可编辑布局
    private static final int IMAGE_TYPE = 2;        //可选择图片布局

    private String[] itemNames = {"头像", "姓名", "性别", "婚姻情况", "出生日期", "最高学历", "工作年限", "手机号码", "现居地"};

    //根据itemview的位置，选择需要的布局
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IMAGE_TYPE;
        } else if (position == 1 || position == 7) {
            return EDIT_TYPE;
        } else {
            return NORMAL_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_TYPE) {
            return new NormalHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, parent, false));
        } else if (viewType == IMAGE_TYPE) {
            return new ImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head_img, parent, false));
        } else {
            return new EditHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).mItemName.setText(itemNames[position]);
        } else if (holder instanceof ImageHolder) {
            ((ImageHolder) holder).mItemName.setText(itemNames[position]);
        } else if (holder instanceof EditHolder) {
            ((EditHolder) holder).mItemName.setText(itemNames[position]);
        }
    }

    @Override
    public int getItemCount() {
        return ITEMCOUNT;
    }


    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_normal_item_name)
        TextView mItemName;
        @BindView(R.id.root_view)
        LinearLayout mRootView;
        @BindView(R.id.tv_normal_item_value)
        TextView mItemValue;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_img_item_name)
        TextView mItemName;
        @BindView(R.id.img_head)
        ImageView mHeadImg;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EditHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_edit_item_name)
        TextView mItemName;
        @BindView(R.id.et_item)
        EditText mEditText;

        public EditHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
