package com.eizzo.launchers.models;

public class LauncherType {
    private final String material;
    private double vertical;
    private double horizontal;

    public LauncherType(String material, double vertical, double horizontal) {
        this.material = material;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public String getMaterial() { return material; }
    public double getVertical() { return vertical; }
    public double getHorizontal() { return horizontal; }

    public void setVertical(double vertical) { this.vertical = vertical; }
    public void setHorizontal(double horizontal) { this.horizontal = horizontal; }
}
