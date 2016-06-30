package cn.cithr.jackdraw.cithrrecruit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.model.entities.JobApplyList;

/**
 * Created by xusha on 2016/6/29.
 */
public class JobApplyListAdapter extends RecyclerView.Adapter<JobApplyListAdapter.JobApplyViewHolder> {

    LayoutInflater mInflater;
    List<JobApplyList> mJobApplyList;

    public JobApplyListAdapter(Context context, List<JobApplyList> list) {
        mInflater = LayoutInflater.from(context);
        mJobApplyList = list;
    }

    @Override
    public JobApplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_item_job_apply, parent, false);
        return new JobApplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobApplyViewHolder holder, int position) {
        holder.bindData(mJobApplyList.get(position));
    }

    @Override
    public int getItemCount() {
        return mJobApplyList.size();
    }

    class JobApplyViewHolder extends RecyclerView.ViewHolder {
        TextView companyName, jobName;

        public JobApplyViewHolder(View itemView) {
            super(itemView);
            companyName = (TextView) itemView.findViewById(R.id.tv_company_name);
            jobName = (TextView) itemView.findViewById(R.id.tv_job_name);
        }

        public void bindData(JobApplyList jobApplyList) {
            companyName.setText(jobApplyList.getCompanyName());
            jobName.setText(jobApplyList.getJobName());
        }
    }
}
