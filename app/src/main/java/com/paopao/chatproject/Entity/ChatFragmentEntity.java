package com.paopao.chatproject.Entity;

/**
 * 作者：paopao on 2019/4/10 23:06
 * <p>
 * 作用:
 */
public class ChatFragmentEntity {

    private String name;
    private String message;
    private String time;
    private int photo;

    public ChatFragmentEntity(String name, String message, String time, int photo) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
