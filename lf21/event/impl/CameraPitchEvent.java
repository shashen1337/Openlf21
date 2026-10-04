/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class CameraPitchEvent
implements EventMarker {
    private float pitch;

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float f2) {
        this.pitch = f2;
    }

    @Generated
    public CameraPitchEvent(float f2) {
        this.pitch = f2;
    }
}

