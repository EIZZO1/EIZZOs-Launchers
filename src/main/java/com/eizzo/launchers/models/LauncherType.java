package com.eizzo.launchers.models;

public class LauncherType {
    private final String material;
    private double vertical;
    private double horizontal;
    private boolean boat;
    private String particle;
    private String sound;

    public LauncherType(String material, double vertical, double horizontal, boolean boat) {
        this(material, vertical, horizontal, boat, "FLAME", "ENTITY_FIREWORK_ROCKET_LAUNCH");
    }

    public LauncherType(String material, double vertical, double horizontal, boolean boat, String particle, String sound) {
        this.material = material;
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.boat = boat;
        this.particle = particle != null ? particle : "FLAME";
        this.sound = sound != null ? sound : "ENTITY_FIREWORK_ROCKET_LAUNCH";
    }

    public String getMaterial() { return material; }
    public double getVertical() { return vertical; }
    public double getHorizontal() { return horizontal; }
    public boolean isBoat() { return boat; }
    public String getParticle() { return particle; }
    public String getSound() { return sound; }

    public void setVertical(double vertical) { this.vertical = vertical; }
    public void setHorizontal(double horizontal) { this.horizontal = horizontal; }
    public void setBoat(boolean boat) { this.boat = boat; }
    public void setParticle(String particle) { this.particle = particle; }
    public void setSound(String sound) { this.sound = sound; }
}