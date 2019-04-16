package com.paopao.chatproject.Request;


import org.json.JSONObject;

public class UserRegisterRequest extends BaseRequest {

    private String UserAccount;
    private String UserPassword;

    @Override
    public String getType() {
        return "UserRegister";
    }

    @Override
    public BaseRequest setParams(Object[] params) {
        this.UserAccount = params[0].toString();
        this.UserPassword = params[1].toString();
        return this;
    }

    @Override
    public String getRequestBody() {
        JSONObject object = new JSONObject();
        try {
            object.put("UserAccount", UserAccount);
            object.put("UserPassword", UserPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public Object onRequestParse(String parse) {
        try {
            if (new JSONObject(parse).getString("RESULT").equals("S")) {
                return new JSONObject(parse).getString("MESSAGE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "F";
    }

}
