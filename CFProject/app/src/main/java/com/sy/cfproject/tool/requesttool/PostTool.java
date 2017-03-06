package com.sy.cfproject.tool.requesttool;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.sy.cfproject.tool.L;
import com.sy.cfproject.tool.User;
import com.sy.cfproject.tool.requestdata.RequestRegisterBean;
import com.sy.cfproject.tool.url.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private Handler handler;
    private OkHttpClient okHttpClient;
    private Request.Builder builder;
    public PostTool(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }


    /**
     * Loging
     * @param url
     * @param userPhone
     * @param pwd
     * @param type
     */
    public void toLoging(String url,String userPhone,String pwd,int type)
    {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("cp", userPhone);
            jsonObj.put("pw",pwd);
            String json = jsonObj.toString();
            requestTitle(url, type, json);
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
            jsonObj.put("termId", 862180034558954L);
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
        if(type != Constant.REGISTER && type != Constant.LOGING)
        {
            request =  builder.url(url).addHeader("Token", "Bearer IpjCW+OtGCaIP08Xk7y6gYbThCo+NX8dKU6NXyUVew1lcwby4Z4U/6R5GljEVtHxIyL5/T8EyZPI1c20jkjw5Q==").post(body).build();

        }else
        {
            request =  builder.url(url).post(body).build();
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
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("lhh","Post json :"+json);
                if(type == Constant.LOGING)
                {
                    User.token = response.header("Token");
                }
            }
        });
    }
}
