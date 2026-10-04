/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class StrafeEvent
implements EventMarker {
    private float a;
    private float b;
    private boolean c;

    @Generated
    public StrafeEvent(float f2, float f3, boolean bl) {
        this.a = f2;
        this.b = f3;
        this.c = bl;
    }

    @Generated
    public float getForward() {
        return this.a;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    @Generated
    public float getStrafe() {
        return this.b;
    }

    @Generated
    public void b(float f2) {
        this.b = f2;
    }

    @Generated
    public boolean isSprinting() {
        return this.c;
    }

    @Generated
    public void a(boolean bl) {
        this.c = bl;
    }
}

