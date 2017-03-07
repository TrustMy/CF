package com.sy.cfproject.tool.requesttool;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.sy.cfproject.tool.L;
import com.sy.cfproject.tool.User;
import com.sy.cfproject.tool.requestdata.RequestCarTrajectoryBean;
import com.sy.cfproject.tool.requestdata.RequestLogingBean;
import com.sy.cfproject.tool.requestdata.RequestRegisterBean;
import com.sy.cfproject.tool.url.Constant;
import com.sy.cfproject.tool.url.HttpStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Trust on 2017/3/6.
 */
public class PostTool {
    private Context context ;
    private OkHttpClient okHttpClient;
    private Request.Builder builder;

    private PostNet postNet;

    public PostTool(Context context, Handler handler) {
        this.context = context;
        this.postNet = new PostNet(context,handler);
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }


    /**
     * Loging
     * @param url
     * @param logingBean
     */
    public void toLoging(String url, RequestLogingBean logingBean)
    {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("cp", logingBean.getPhone());
            jsonObj.put("pw",logingBean.getPwd());
            String json = jsonObj.toString();
            requestTitle(url, logingBean.getType(), json);
        } catch (JSONException e) {
            e.printStackTrace();
            L.e("error:"+e.toString());
        }
    }
    /*
    public void test(Object obj)
    {
        if(obj instanceof User)
        {
            L.i("是User");
        }else
        {
            L.i("不是");
        }
    }
    */

    public void toRegister(String url, RequestRegisterBean registerBean , int type)
    {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("phone", registerBean.getPhone());
            jsonObj.put("pwd", registerBean.getPwd());
            jsonObj.put("phoneType", registerBean.getPhoneType());
            jsonObj.put("nickName", registerBean.getNickName());
            jsonObj.put("email", registerBean.getEmail());
            String json = jsonObj.toString();
            requestTitle(url, type, json);
        } catch (JSONException e) {
            e.printStackTrace();
            L.e("error:"+e.toString());
        }
    }

    /**
     * Check the vehicle location
     * @param url
     * @param type
     */
    public void toCheckCarLocation(String url, int type)
    {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("termId", User.termId);
            String json = jsonObj.toString();
            requestTitle(url, type, json);
        } catch (JSONException e) {
            e.printStackTrace();
            L.e("error:"+e.toString());
        }
    }
    public void toDrawHirstoryLine(String url, RequestCarTrajectoryBean carTrajectoryBean , int type)
    {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("termId", carTrajectoryBean.getTermId());
            jsonObj.put("startTime", carTrajectoryBean.getStartTime());
            jsonObj.put("endTime", carTrajectoryBean.getEndTime());
            String json = jsonObj.toString();
            requestTitle(url, type, json);
        } catch (JSONException e) {
            e.printStackTrace();
            L.e("error:"+e.toString());
        }
    }


    /**
     * Request header
     * @param url
     * @param type
     * @param json
     */
    private void requestTitle(String url, int type, String json) {
        builder = new Request.Builder();
        MediaType JSON = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, json);
        Request request ;
        if(type == Constant.REGISTER || type == Constant.LOGING)
        {
            request =  builder.url(url).post(body).build();
        }else
        {
            request =  builder.url(url).addHeader("Token", User.token).post(body).build();
        }
        executeResponse(request, type);
    }

    /**
     * Normal or exception handling
     * @param request
     * @param type
     */
    public void executeResponse(Request request , final int type) {

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("lhh:", "onFailure: "+e.toString());
                toPostNet(e.toString(),type, HttpStatus.HTTP_ERROR);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                if(type == Constant.LOGING)
                {
                    User.token = response.header("Token");
                }
                Log.i("lhh","Post json :"+json+"|token:"+User.token);
                toPostNet(json,type, HttpStatus.HTTP_SUCCESS);
            }
        });
    }


    public void toPostNet(String json,int type,int httpsStatus)
    {
        Message message = new Message();
        message.what = type;
        message.arg1 = httpsStatus;
        message.obj = json;
        postNet.sendMessage(message);
    }
}
