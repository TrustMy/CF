package com.sy.cfproject.tool.requesttool;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sy.cfproject.tool.L;
import com.sy.cfproject.tool.responsebean.ErrorBean;
import com.sy.cfproject.tool.responsebean.ResponseCarTrajectoryBean;
import com.sy.cfproject.tool.responsebean.ResponseLogingBean;
import com.sy.cfproject.tool.responsebean.ResponseRegisterBean;
import com.sy.cfproject.tool.url.Constant;
import com.sy.cfproject.tool.url.HttpStatus;

import java.net.ResponseCache;

/**
 * Created by Trust on 2017/3/6.
 */
public class PostNet extends Handler {
    private Gson gson;
    private Handler handler;
    private Context context;
    public PostNet(Context context,Handler handler) {
        this.gson = new Gson();
        this.handler = handler;
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {

        switch (msg.what)
        {
            case Constant.REGISTER:
                if(msg.arg1 == HttpStatus.HTTP_SUCCESS)
                {
                    toRegister((String)msg.obj, Constant.REGISTER,HttpStatus.HTTP_SUCCESS);
                }else
                {

                }
                break;

            case Constant.LOGING:
                if(msg.arg1 == HttpStatus.HTTP_SUCCESS)
                {
                    toLoging((String) msg.obj,Constant.LOGING,HttpStatus.HTTP_SUCCESS);
                }else
                {
                    toHandler(Constant.LOGING, msg.obj ,HttpStatus.HTTP_ERROR);
                }
                break;

            case Constant.CAR_TRAJECTORY_LINE:
                if(msg.arg1 == HttpStatus.HTTP_SUCCESS)
                {
                    toHirstoryLine((String)msg.obj,Constant.CAR_TRAJECTORY_LINE,
                            HttpStatus.HTTP_SUCCESS);
                }else
                {
                    toHandler(Constant.CAR_TRAJECTORY_LINE,msg.obj,HttpStatus.HTTP_ERROR);
                }
                break;
        }
    }

    private void toHirstoryLine(String obj, int type, int httpStatus) {
        ResponseCarTrajectoryBean carTrajectoryBean = gson.fromJson(obj,
                ResponseCarTrajectoryBean.class);
        if(carTrajectoryBean.getStatus())
        {
            toHandler(type,carTrajectoryBean,httpStatus);
        }else
        {
            doError(obj, type, httpStatus);
        }
    }


    private void toRegister(String obj ,int type , int httpStatus) {
        ResponseRegisterBean registerBean = gson.fromJson(obj,ResponseRegisterBean.class);
        if(registerBean.getStatus())
        {

        }else
        {
            doError(obj, type, httpStatus);
        }
    }

    private void toLoging(String obj,int type,int httpStatus)
    {
        ResponseLogingBean logingBean = gson.fromJson(obj,ResponseLogingBean.class);
        if(logingBean.getStatus())
        {
            toHandler(type, logingBean ,httpStatus);
        }else
        {
            doError(obj, type, httpStatus);
        }
    }





    private void doError(String obj, int type, int httpStatus) {
        ErrorBean errorBean = gson.fromJson(obj,ErrorBean.class);
        toHandler(type,errorBean,httpStatus);
    }

    private void toHandler(int type, Object obj , int httpstatus) {
        Message message = new Message();
        message.what = type;
        message.arg1 = httpstatus;
        message.obj = obj;
        handler.sendMessage(message);
    }


}
