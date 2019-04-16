package com.paopao.chatproject.Request;

import android.os.Handler;
import android.os.Looper;

import com.paopao.chatproject.CallBack.OnResponseListener;
import com.paopao.chatproject.Util.OkHttpUtil;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class BaseRequest {
    public abstract String getType();

    public abstract BaseRequest setParams(Object[] params);

    public abstract String getRequestBody();

    public abstract Object onRequestParse(String parse);

    private Handler handler = new Handler(Looper.getMainLooper());

    public void sendRequest(final OnResponseListener onResponseListener) {
        String url = "http://192.168.0.6:8080/" + getType() + ".do";
        OkHttpUtil.sendOkHttpRequest(url, getRequestBody(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onResponseListener.callBack(onRequestParse(s));
                    }
                });
            }
        });
    }
}
