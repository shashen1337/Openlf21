/*
 * Decompiled with CFR 0.152.
 */
package lf21.event.impl;

import lf21.event.EventMarker;
import lf21.event.impl.SprintEvent$Source;

public class SprintEvent
implements EventMarker {
    private boolean a;
    private SprintEvent$Source b;

    public SprintEvent() {
        this(true, SprintEvent$Source.INPUT);
    }

    public SprintEvent(boolean bl, SprintEvent$Source sprintEvent$Source) {
        this.a = bl;
        this.b = sprintEvent$Source;
    }

    public boolean isSprint() {
        return this.a;
    }

    public void a(boolean bl) {
        this.a = bl;
    }

    public SprintEvent$Source a() {
        return this.b;
    }

    public void a(SprintEvent$Source sprintEvent$Source) {
        this.b = sprintEvent$Source;
    }
}

