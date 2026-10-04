/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class RotationEvent
implements EventMarker {
    private float a;
    private float b;

    @Generated
    public RotationEvent(float f2, float f3) {
        this.a = f2;
        this.b = f3;
    }

    @Generated
    public float getYaw() {
        return this.a;
    }

    @Generated
    public void a(float f2) {
        this.a = f2;
    }

    @Generated
    public float a() {
        return this.b;
    }

    @Generated
    public void b(float f2) {
        this.b = f2;
    }
}

