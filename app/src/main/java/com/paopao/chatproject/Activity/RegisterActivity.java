package com.paopao.chatproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paopao.chatproject.CallBack.OnResponseListener;
import com.paopao.chatproject.R;
import com.paopao.chatproject.Request.UserLoginRequest;
import com.paopao.chatproject.Request.UserRegisterRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.user_account)
    EditText userAccount;
    @BindView(R.id.user_password)
    EditText userPassword;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.back, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_register:
                userRegister();
                break;
        }
    }

    //用户登录
    private void userRegister() {
        if (TextUtils.isEmpty(userAccount.getText().toString().trim()))
            Toast.makeText(this, "账号不能为空!", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(userPassword.getText().toString().trim()))
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(confirmPassword.getText().toString().trim()))
            Toast.makeText(this, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
        else if (!userPassword.getText().toString().trim().equals(confirmPassword.getText().toString().trim()))
            Toast.makeText(this, "密码不一致!", Toast.LENGTH_SHORT).show();
        else
            new UserRegisterRequest().setParams(new Object[]{userAccount.getText().toString().trim(), userPassword.getText().toString().trim()}).sendRequest(new OnResponseListener() {
                @Override
                public void callBack(Object param) {
                    if (param.equals("F"))
                        Toast.makeText(RegisterActivity.this, "注册失败!", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(RegisterActivity.this, "注册成功!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
    }

}
