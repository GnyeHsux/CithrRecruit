package cn.cithr.jackdraw.cithrrecruit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.cithr.jackdraw.cithrrecruit.R;

/**
 * Created by xusha on 2016/6/11.
 */
public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义一个集合，接收从Activity中传递过来的数据和上下文
    private List<String> mList;
    private Context mContext;

    public JobListAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.rv_item_home, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof MyHolder){
//            final String itemText = mList.get(position);
//            ((MyHolder)holder).tv.setText(itemText);
//        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
//            tv = (TextView)itemView.findViewById(R.id.list_item);
        }
    }
}
