package com.inbm.inbmstarter.inbm.firebase;

import java.io.Serializable;

public class _notification_ implements Serializable {
    private int send_time;
    private String user_id;
    private String image_url;
    private String name;
    private String chat_room_id;
    private String message;
    private String type;

    public int getSend_time() {
        return send_time;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getChat_room_id() {
        return chat_room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}