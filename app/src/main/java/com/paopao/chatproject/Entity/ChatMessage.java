package com.paopao.chatproject.Entity;

/**
 * 作者：paopao on 2019/4/4 14:52
 * <p>
 * 作用:
 */
public class ChatMessage {

    private String account;//账号
    private String messageType;//信息类型 文本(Text) 图片(Image) 文件 表情
    private String message;//内容

    public ChatMessage(String account, String messageType, String message) {
        this.account = account;
        this.messageType = messageType;
        this.message = message;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
