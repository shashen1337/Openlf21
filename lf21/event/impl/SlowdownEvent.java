/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lombok.Generated;

public class SlowdownEvent
implements EventMarker {
    private boolean slowDown;

    public boolean isSlowDown() {
        return this.slowDown;
    }

    @Generated
    public SlowdownEvent(boolean bl) {
        this.slowDown = bl;
    }

    @Generated
    public void setSlowDown(boolean bl) {
        this.slowDown = bl;
    }
}

