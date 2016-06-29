package cn.cithr.jackdraw.cithrrecruit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.model.entities.JobFavoriteList;

/**
 * Created by xusha on 2016/6/27.
 */
public class JobFavoriteListAdapter extends RecyclerView.Adapter<JobFavoriteListAdapter.JobFavoriteViewHolder> {

    LayoutInflater mInflater;
    List<JobFavoriteList> mJobFavoriteList;

    public JobFavoriteListAdapter(Context context, List<JobFavoriteList> list) {
        mInflater = LayoutInflater.from(context);
        mJobFavoriteList = list;
    }

    @Override
    public JobFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_item_job_favorite, parent, false);
        return new JobFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobFavoriteViewHolder holder, int position) {
        holder.bindData(mJobFavoriteList.get(position));
    }

    @Override
    public int getItemCount() {
        return mJobFavoriteList.size();
    }


    class JobFavoriteViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, jobName;

        public JobFavoriteViewHolder(View itemView) {
            super(itemView);
            companyName = (TextView) itemView.findViewById(R.id.tv_company_name);
            jobName = (TextView) itemView.findViewById(R.id.tv_job_name);
        }

        public void bindData(JobFavoriteList jobFavoriteList) {
            companyName.setText(jobFavoriteList.getCompanyName());
            jobName.setText(jobFavoriteList.getJobName());
        }
    }

    //添加职位
    public void addData(int position) {
        mJobFavoriteList.add(position, new JobFavoriteList("XXX职位 " + (position + 1), "九九鼎盛营销策划有限公司"));
        notifyItemInserted(position);
    }

    //删除职位
    public void deleteData(int position) {
        mJobFavoriteList.remove(position);
        notifyItemRemoved(position);
    }
}
