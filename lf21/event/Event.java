/*
 * Decompiled with CFR 0.152.
 */
package lf21.event;

import lf21.event.EventMarker;
import lf21.event.b;

public abstract class Event
implements b,
EventMarker {
    public boolean a;

    protected Event() {
    }

    @Override
    public boolean isCancelled() {
        return this.a;
    }

    @Override
    public void a(boolean bl) {
        this.a = bl;
    }
}

