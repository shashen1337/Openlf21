/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;

public abstract class d
implements lf21.event.d,
EventMarker {
    private final byte a;

    protected d(byte by) {
        this.a = by;
    }

    @Override
    public byte a() {
        return this.a;
    }
}

