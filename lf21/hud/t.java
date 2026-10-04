/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import lf21.hud.HudElement;
import lf21.hud.HudElement$InternalPartType;
import lf21.hud.s;

public final class t {
    private final HudElement a;
    private final float b;

    public float b() {
        return this.a.m;
    }

    public float f() {
        return this.a.q;
    }

    public float e() {
        return this.a.p;
    }

    public boolean a() {
        s s2 = this.j();
        float f2 = s2 == null ? this.a.r : s2.b;
        float f3 = s2 == null ? this.a.s : s2.c;
        float f4 = s2 == null ? this.a.t : s2.d;
        return Math.abs(this.a.q - 1.0f) >= 0.001f || Math.abs(f2 - 1.0f) >= 0.001f || Math.abs(f3) >= 0.001f || Math.abs(f4) >= 0.001f;
    }

    private t(HudElement hudElement, float f2) {
        this.a = hudElement;
        this.b = f2;
    }

    public float g() {
        s s2 = this.j();
        return s2 == null ? this.a.r : s2.b;
    }

    public float d() {
        return this.a.o;
    }

    public float h() {
        s s2 = this.j();
        return s2 == null ? this.a.s : s2.c;
    }

    public float c() {
        return this.a.n;
    }

    public float i() {
        s s2 = this.j();
        return s2 == null ? this.a.t : s2.d;
    }

    private s j() {
        String string = HudElement.k.get();
        s s2 = string == null ? null : this.a.l.get(string);
        return s2 != null && s2.a == HudElement$InternalPartType.TEXT ? s2 : null;
    }
}

