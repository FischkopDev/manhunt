package de.home_skrobanek.manhunt.backend.manager.http.dao;

public class Message {

    private String message;
    private boolean event;

    public Message(String message, boolean event) {
        this.message = message;
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }
}
