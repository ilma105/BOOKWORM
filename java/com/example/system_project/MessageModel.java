package com.example.system_project;

public class MessageModel {
    String uId, message,messageId;
    String  timestamp;

    public MessageModel(String uId, String message, String  timestamp) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
    }
    public MessageModel(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageModel(){};

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String  getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
