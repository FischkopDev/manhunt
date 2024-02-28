package de.home_skrobanek.manhunt.manhuntServer.dao;

public class PositionRequest {

    private String device_id;
    private String timestamp;

    public PositionRequest(String device_id, String timestamp) {
        this.device_id = device_id;
        this.timestamp = timestamp;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
