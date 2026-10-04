/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import lf21.hud.q;
import lf21.hud.v;

public final class r {
    private final q a;

    public r(q q2) {
        this.a = q2;
    }

    public v a() {
        for (v v2 : this.a.a) {
            if (!v2.c()) continue;
            return v2;
        }
        return null;
    }
}

