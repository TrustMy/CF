package com.sy.cfproject.tool.responsebean;

/**
 * Created by Trust on 2017/3/7.
 */
public class ErrorBean {


    /**
     * status : false
     * err : 设备未回复
     */

    private boolean status;
    private String err;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public boolean getStatus() {
        return status;
    }

    public String getErr() {
        return err;
    }
}
