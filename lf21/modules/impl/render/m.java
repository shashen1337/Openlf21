/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import lf21.modules.impl.render.y;

final class m {
    private float a;
    private float e;
    private y c;
    private long b;
    private long g;
    private float f;
    private boolean d = true;

    private m(y y2, long l2) {
        this.c = y2;
        this.a = 1.0f;
        this.b = l2;
        this.g = l2;
    }

    private void a(boolean bl, long l2) {
        if (this.d == bl) {
            return;
        }
        this.e = this.a(l2);
        this.d = bl;
        this.f = this.e;
        this.g = l2;
    }

    private float a(long l2) {
        float f2 = Math.max(0.0f, Math.min(1.0f, (float)(l2 - this.g) / 2.6E8f));
        if (this.d) {
            float f3 = f2 - 1.0f;
            float f4 = 1.0f + 2.70158f * f3 * f3 * f3 + 1.70158f * f3 * f3;
            this.e = this.f + (1.0f - this.f) * f4;
        } else {
            this.e = Math.max(0.0f, this.f * (1.0f - f2 * f2 * f2));
        }
        return this.e;
    }
}

