package com.sy.cfproject.acyivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sy.cfproject.BaseActivity;
import com.sy.cfproject.MainActivity;
import com.sy.cfproject.R;
import com.sy.cfproject.tool.L;
import com.sy.cfproject.tool.MD5Utils;
import com.sy.cfproject.tool.User;
import com.sy.cfproject.tool.requestdata.RequestLogingBean;
import com.sy.cfproject.tool.requesttool.PostTool;
import com.sy.cfproject.tool.responsebean.ResponseLogingBean;
import com.sy.cfproject.tool.url.Constant;
import com.sy.cfproject.tool.url.HttpStatus;
import com.sy.cfproject.tool.url.ServerUrl;

public class LogingActivity extends BaseActivity implements View.OnClickListener {
    private Context context = LogingActivity.this;
    private EditText userEt,pwdEt;
    private Button changePwdBtn,registerBtn,logingBtn;

    private PostTool postTool;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case Constant.LOGING:
                    if(msg.arg1 == HttpStatus.HTTP_SUCCESS)
                    {
                        ResponseLogingBean logingBean = (ResponseLogingBean) msg.obj;
                        User.termId = logingBean.getContent().getTermId();
                        User.name = logingBean.getContent().getNickName();
                        User.pushId = logingBean.getContent().getPushId();

                        startActivity(new Intent(context,MainActivity.class));
                    }else
                    {
                        Toast.makeText(context, (String)msg.obj, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEt = (EditText) findViewById(R.id.login_user_phone);
        pwdEt = (EditText) findViewById(R.id.login_user_pwd);
        changePwdBtn = (Button) findViewById(R.id.login_change_pwd);
        registerBtn = (Button) findViewById(R.id.login_register);
        logingBtn = (Button) findViewById(R.id.login_loging);

        changePwdBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        logingBtn.setOnClickListener(this);

        postTool = new PostTool(context,handler);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.login_change_pwd:
                intent.setClass(context,ChangePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.login_register:
                intent.setClass(context,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_loging:
                initMessage(intent);

                break;

        }

    }

    private void initMessage(Intent intent) {
        String phone = userEt.getText().toString().trim();
        String oldPwd = pwdEt.getText().toString().trim();
        String pwd = MD5Utils.encrypt(oldPwd);
        if(!phone.equals("") && !pwd.equals(""))
        {
            postTool.toLoging(ServerUrl.server_url+ServerUrl.login_url,new RequestLogingBean(phone,pwd,Constant.LOGING));
        }else
        {

        }
    }
}
