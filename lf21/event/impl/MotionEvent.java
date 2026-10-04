/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.Event;
import lombok.Generated;

public class MotionEvent
extends Event {
    public boolean pre;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;
    public boolean onGround;

    public boolean isPost() {
        return !this.isPre();
    }

    @Generated
    public MotionEvent(boolean bl, double d2, double d3, double d4, float f2, float f3, boolean bl2) {
        this.pre = bl;
        this.x = d2;
        this.y = d3;
        this.z = d4;
        this.yaw = f2;
        this.pitch = f3;
        this.onGround = bl2;
    }

    @Generated
    public boolean isPre() {
        return this.pre;
    }

    @Generated
    public void setPre(boolean bl) {
        this.pre = bl;
    }

    @Generated
    public double getX() {
        return this.x;
    }

    @Generated
    public void setX(double d2) {
        this.x = d2;
    }

    @Generated
    public double getY() {
        return this.y;
    }

    @Generated
    public void setY(double d2) {
        this.y = d2;
    }

    @Generated
    public double getZ() {
        return this.z;
    }

    @Generated
    public void setZ(double d2) {
        this.z = d2;
    }

    @Generated
    public float getYaw() {
        return this.yaw;
    }

    @Generated
    public void setYaw(float f2) {
        this.yaw = f2;
    }

    @Generated
    public float getPitch() {
        return this.pitch;
    }

    @Generated
    public void setPitch(float f2) {
        this.pitch = f2;
    }

    @Generated
    public boolean isOnGround() {
        return this.onGround;
    }

    @Generated
    public void setOnGround(boolean bl) {
        this.onGround = bl;
    }
}

