package com.paopao.chatproject.Entity;

/**
 * 作者：paopao on 2019/4/4 14:52
 * <p>
 * 作用:
 */
public class ChatMessage {

    private String account;//账号
    private String messageType;//信息类型 文本 图片 文件
    private String text;//文本内容
    private String image;//图片字节流数组

    public ChatMessage(String account, String messageType, String text, String image) {
        this.account = account;
        this.messageType = messageType;
        this.text = text;
        this.image = image;
    }



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
