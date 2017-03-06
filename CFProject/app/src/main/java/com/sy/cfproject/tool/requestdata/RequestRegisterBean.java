package com.sy.cfproject.tool.requestdata;

/**
 * Created by Trust on 2017/3/6.
 */
public class RequestRegisterBean {
    private String phone,pwd,nickName,email;
    private int phoneType;

    public RequestRegisterBean(String phone, String pwd, String nickName, String email, int phoneType) {
        this.phone = phone;
        this.pwd = pwd;
        this.nickName = nickName;
        this.email = email;
        this.phoneType = phoneType;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }
}
