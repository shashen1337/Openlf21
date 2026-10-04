/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import lf21.render.F;

/*
 * Renamed from lf21.render.s
 */
public final class s_0 {
    private boolean c;
    private final F a;
    private float b;

    void a(float f2) {
        if (this.a == null || this.a.a == null || this.a.a.length == 0) {
            return;
        }
        this.b += f2;
        while (this.b >= this.b()) {
            this.b -= this.b();
            this.c = !this.c;
        }
    }

    private float b() {
        int n2 = this.c ? 0 : (this.a.a.length > 1 ? 1 : 0);
        return Math.max(0.001f, this.a.a[n2]);
    }

    boolean a() {
        return this.a == null || this.c;
    }

    s_0(F f2) {
        this.a = f2;
        this.c = true;
        this.b = 0.0f;
        if (f2 != null) {
            this.b = f2.b;
        }
    }
}

