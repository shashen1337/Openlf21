/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import lombok.Generated;

public class f {
    private final float a;
    private final float b;
    private final float c;
    private float d;
    private float e;
    private float f;

    public f(float f2, float f3, float f4, float f5) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.e = f5;
        this.d = f5;
    }

    public void a(float f2) {
        this.e = f2;
        this.d = f2;
        this.f = 0.0f;
    }

    public void b(float f2) {
        if (f2 <= 0.0f) {
            return;
        }
        float f3 = -this.a * (this.e - this.d) - this.c * this.f;
        float f4 = f3 / this.b;
        this.f += f4 * f2;
        this.e += this.f * f2;
    }

    public float a() {
        return this.e;
    }

    public void c(float f2) {
        this.e = f2;
    }

    @Generated
    public float b() {
        return this.d;
    }

    @Generated
    public void d(float f2) {
        this.d = f2;
    }
}

