package cn.cithr.jackdraw.cithrrecruit.model.entities;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Login {

    public String result;
    public String msg;
    public int userId;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
