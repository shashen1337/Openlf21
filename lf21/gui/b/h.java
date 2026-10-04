/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import lf21.gui.b.c;
import lf21.gui.b.d;
import lf21.settings.Setting;
import lf21.utils.a.e;
import lombok.Generated;

public abstract class h<T extends Setting<?>>
extends c {
    protected final d f;
    protected final T g;
    protected final e h = new e();

    @Generated
    public h(d d2, T t2) {
        this.f = d2;
        this.g = t2;
    }

    @Generated
    public d h() {
        return this.f;
    }

    @Generated
    public T i() {
        return this.g;
    }

    @Generated
    public e j() {
        return this.h;
    }
}

