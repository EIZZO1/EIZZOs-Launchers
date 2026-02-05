package com.eizzo.launchers.models;

public class LauncherType {
    private final String material;
    private double vertical;
    private double horizontal;
    private boolean boat;

    public LauncherType(String material, double vertical, double horizontal, boolean boat) {
        this.material = material;
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.boat = boat;
    }

    public String getMaterial() { return material; }
    public double getVertical() { return vertical; }
    public double getHorizontal() { return horizontal; }
    public boolean isBoat() { return boat; }

    public void setVertical(double vertical) { this.vertical = vertical; }
    public void setHorizontal(double horizontal) { this.horizontal = horizontal; }
    public void setBoat(boolean boat) { this.boat = boat; }
}
