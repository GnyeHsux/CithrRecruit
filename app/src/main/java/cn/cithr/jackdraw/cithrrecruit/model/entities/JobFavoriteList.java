package cn.cithr.jackdraw.cithrrecruit.model.entities;

/**
 * Created by xusha on 2016/6/25.
 */
public class JobFavoriteList {
    String jobName, companyName;

    public JobFavoriteList(String company, String job) {
        this.jobName = company;
        this.companyName = job;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
