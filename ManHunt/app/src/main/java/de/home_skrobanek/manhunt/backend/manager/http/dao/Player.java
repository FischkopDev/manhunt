package de.home_skrobanek.manhunt.backend.manager.http.dao;

public class Player {

    private String username;
    private String role;
    private String device_id;

    public Player(String username, String role, String device_id) {
        this.username = username;
        this.role = role;
        this.device_id = device_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
