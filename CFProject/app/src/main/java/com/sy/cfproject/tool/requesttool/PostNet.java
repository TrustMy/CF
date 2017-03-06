package com.sy.cfproject.tool.requesttool;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.sy.cfproject.tool.responsebean.ResponseRegisterBean;
import com.sy.cfproject.tool.url.Constant;
import com.sy.cfproject.tool.url.HttpStatus;

import java.net.ResponseCache;

/**
 * Created by Trust on 2017/3/6.
 */
public class PostNet extends Handler {
    private Gson gson;

    public PostNet() {
        this.gson = new Gson();
    }

    @Override
    public void handleMessage(Message msg) {

        switch (msg.what)
        {
            case Constant.REGISTER:
                if(msg.arg1 == HttpStatus.HTTP_SUCCESS)
                {
                    toRegister((String)msg.obj, Constant.REGISTER);
                }else
                {

                }
                break;
        }
    }

    private void toRegister(String obj ,int type) {
        ResponseRegisterBean registerBean = gson.fromJson(obj,ResponseRegisterBean.class);
        if(registerBean.getStatus())
        {
            
        }else
        {

        }
    }
}
