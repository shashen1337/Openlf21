/*
 * Decompiled with CFR 0.152.
 */
package lf21.event;

import lf21.event.EventMarker;

public abstract class a
implements EventMarker {
    private boolean a;

    protected a() {
    }

    public void a() {
        this.a = true;
    }

    public boolean b() {
        return this.a;
    }
}

