package it.itzsamirr.afra.utils;

import org.bukkit.Location;

public final class Distance {
    private double xFrom, yFrom, zFrom, xTo, yTo, zTo;
    private float yawFrom, yawTo, pitchFrom, pitchTo;

    public Distance(double xFrom, double yFrom, double zFrom, double xTo, double yTo, double zTo, float yawFrom, float yawTo, float pitchFrom, float pitchTo) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.zFrom = zFrom;
        this.xTo = xTo;
        this.yTo = yTo;
        this.zTo = zTo;
        this.yawFrom = yawFrom;
        this.yawTo = yawTo;
        this.pitchFrom = pitchFrom;
        this.pitchTo = pitchTo;
    }

    public Distance(Location from, Location to){
        this(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ(), from.getYaw(), to.getYaw(), from.getPitch(), to.getPitch());
    }

    public float getDYaw(){
        return yawTo - yawFrom;
    }

    public float getDPitch(){
        return pitchTo - pitchFrom;
    }

    public float getYawFrom() {
        return yawFrom;
    }

    public void setYawFrom(float yawFrom) {
        this.yawFrom = yawFrom;
    }

    public float getYawTo() {
        return yawTo;
    }

    public void setYawTo(float yawTo) {
        this.yawTo = yawTo;
    }

    public float getPitchFrom() {
        return pitchFrom;
    }

    public void setPitchFrom(float pitchFrom) {
        this.pitchFrom = pitchFrom;
    }

    public float getPitchTo() {
        return pitchTo;
    }

    public void setPitchTo(float pitchTo) {
        this.pitchTo = pitchTo;
    }

    public double getDX(){
        return xTo - xFrom;
    }

    public double getDY(){
        return yTo - yFrom;
    }

    public double getDZ(){
        return zTo - zFrom;
    }

    public double getHorizontal(){
        return Math.max(xTo, zTo) - Math.max(xFrom, zFrom);
    }

    public double getDXZ(){
        return (getDX() * getDX()) + (getDZ() * getDZ());
    }

    public double getxFrom() {
        return xFrom;
    }

    public void setxFrom(double xFrom) {
        this.xFrom = xFrom;
    }

    public double getyFrom() {
        return yFrom;
    }

    public void setyFrom(double yFrom) {
        this.yFrom = yFrom;
    }

    public double getzFrom() {
        return zFrom;
    }

    public void setzFrom(double zFrom) {
        this.zFrom = zFrom;
    }

    public double getxTo() {
        return xTo;
    }

    public void setxTo(double xTo) {
        this.xTo = xTo;
    }

    public double getyTo() {
        return yTo;
    }

    public void setyTo(double yTo) {
        this.yTo = yTo;
    }

    public double getzTo() {
        return zTo;
    }

    public void setzTo(double zTo) {
        this.zTo = zTo;
    }
}
