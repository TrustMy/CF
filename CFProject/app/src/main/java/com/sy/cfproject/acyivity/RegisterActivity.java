package com.sy.cfproject.acyivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sy.cfproject.R;
import com.sy.cfproject.tool.MD5Utils;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEt,pwdEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        userEt = (EditText) findViewById(R.id.register_user_phone);
        pwdEt = (EditText) findViewById(R.id.register_pwd);
    }

    public void toRegister(View v)
    {
        String phone = userEt.getText().toString().trim();
        String oldPwd = pwdEt.getText().toString().trim();
        String pwd = MD5Utils.encrypt(oldPwd);
        if(!phone .equals("") && !pwd.equals(""))
        {

        }else
        {

        }
    }
}
