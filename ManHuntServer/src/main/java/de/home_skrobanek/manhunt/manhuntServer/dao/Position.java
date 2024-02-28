package de.home_skrobanek.manhunt.manhuntServer.dao;

public class Position {

    private Player player;
    private double latitude;
    private double longitude;
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Position(Player player, double latitude, double longitude) {
        this.player = player;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
