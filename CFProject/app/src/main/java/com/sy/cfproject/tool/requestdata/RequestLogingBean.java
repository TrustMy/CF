package com.sy.cfproject.tool.requestdata;

/**
 * Created by Trust on 2017/3/7.
 */
public class RequestLogingBean {
    private String phone,pwd;
    private int type;

    public RequestLogingBean(String phone, String pwd, int type) {
        this.phone = phone;
        this.pwd = pwd;
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
