package com.eizzo.launchers.models;

public class LauncherType {
    private final String material;
    private double vertical;
    private double horizontal;
    private boolean boat;
    private String particle1;
    private String particle2;
    private String sound;

    public LauncherType(String material, double vertical, double horizontal, boolean boat) {
        this(material, vertical, horizontal, boat, "FLAME", "CLOUD", "ENTITY_FIREWORK_ROCKET_LAUNCH");
    }

    public LauncherType(String material, double vertical, double horizontal, boolean boat, String particle1, String particle2, String sound) {
        this.material = material;
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.boat = boat;
        this.particle1 = particle1 != null ? particle1 : "FLAME";
        this.particle2 = particle2 != null ? particle2 : "CLOUD";
        this.sound = sound != null ? sound : "ENTITY_FIREWORK_ROCKET_LAUNCH";
    }

    public String getMaterial() { return material; }
    public double getVertical() { return vertical; }
    public double getHorizontal() { return horizontal; }
    public boolean isBoat() { return boat; }
    public String getParticle1() { return particle1; }
    public String getParticle2() { return particle2; }
    public String getSound() { return sound; }

    public void setVertical(double vertical) { this.vertical = vertical; }
    public void setHorizontal(double horizontal) { this.horizontal = horizontal; }
    public void setBoat(boolean boat) { this.boat = boat; }
    public void setParticle1(String particle1) { this.particle1 = particle1; }
    public void setParticle2(String particle2) { this.particle2 = particle2; }
    public void setSound(String sound) { this.sound = sound; }
}
