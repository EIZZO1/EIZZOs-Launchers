package com.eizzo.launchers.models;
public class LauncherType {
    private final String material;
    private double vertical;
    private double horizontal;
    private boolean boat;
    private String particle1;
    private String particle2;
    private String trailParticle1;
    private String trailParticle2;
    private String sound;
    public LauncherType(String material, double vertical, double horizontal, boolean boat) {
        this(material, vertical, horizontal, boat, "FLAME", "CLOUD", "SOUL_FIRE_FLAME", "WHITE_ASH", "ENTITY_FIREWORK_ROCKET_LAUNCH");
    }

    public LauncherType(String material, double vertical, double horizontal, boolean boat, String p1, String p2, String tp1, String tp2, String sound) {
        this.material = material;
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.boat = boat;
        this.particle1 = p1 != null ? p1 : "FLAME";
        this.particle2 = p2 != null ? p2 : "CLOUD";
        this.trailParticle1 = tp1 != null ? tp1 : "SOUL_FIRE_FLAME";
        this.trailParticle2 = tp2 != null ? tp2 : "WHITE_ASH";
        this.sound = sound != null ? sound : "ENTITY_FIREWORK_ROCKET_LAUNCH";
    }

    public String getMaterial() { return material; }
    public double getVertical() { return vertical; }
    public double getHorizontal() { return horizontal; }
    public boolean isBoat() { return boat; }
    public String getParticle1() { return particle1; }
    public String getParticle2() { return particle2; }
    public String getTrailParticle1() { return trailParticle1; }
    public String getTrailParticle2() { return trailParticle2; }
    public String getSound() { return sound; }
    public void setVertical(double vertical) { this.vertical = vertical; }
    public void setHorizontal(double horizontal) { this.horizontal = horizontal; }
    public void setBoat(boolean boat) { this.boat = boat; }
    public void setParticle1(String p) { this.particle1 = p; }
    public void setParticle2(String p) { this.particle2 = p; }
    public void setTrailParticle1(String p) { this.trailParticle1 = p; }
    public void setTrailParticle2(String p) { this.trailParticle2 = p; }
    public void setSound(String s) { this.sound = s; }
}
