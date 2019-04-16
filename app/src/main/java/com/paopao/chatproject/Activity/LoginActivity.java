package com.paopao.chatproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paopao.chatproject.Application.App;
import com.paopao.chatproject.CallBack.OnResponseListener;
import com.paopao.chatproject.R;
import com.paopao.chatproject.Request.UserLoginRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.user_account)
    EditText userAccount;
    @BindView(R.id.user_password)
    EditText userPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.forgot_password)
    TextView forgotPassword;
    @BindView(R.id.user_register)
    TextView userRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.forgot_password, R.id.user_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.forgot_password:
                break;
            case R.id.user_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    //登录请求
    private void userLogin() {
        if (TextUtils.isEmpty(userAccount.getText().toString().trim()))
            Toast.makeText(this, "账号不能为空!", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(userPassword.getText().toString().trim()))
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
        else
            new UserLoginRequest().setParams(new Object[]{userAccount.getText().toString().trim(), userPassword.getText().toString().trim()}).sendRequest(new OnResponseListener() {
                @Override
                public void callBack(Object param) {
                    if (param.equals("F"))
                        Toast.makeText(LoginActivity.this, "账号或密码错误!", Toast.LENGTH_SHORT).show();
                    else {
                        App.set("UserAccount", userAccount.getText().toString().trim());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }
            });

    }

}
