/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

public final class Q {
    public final float e;
    public final float h;
    public final float f;
    public final float c;
    public final float a;
    public final float b;
    public final float d;
    public final float g;

    private Q(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
        this.e = f6;
        this.f = f7;
        this.g = f8;
        this.h = f9;
    }

    public static Q a(float f2, float f3, float f4, float f5, float f6) {
        return new Q(f2, f3, f2 + f4, f3 + f5, f6, f6, f6, f6);
    }

    public float a() {
        return this.c - this.a;
    }

    public static Q a(float f2, float f3, float f4, float f5, float[] fArray) {
        if (fArray.length >= 8) {
            return new Q(f2, f3, f2 + f4, f3 + f5, fArray[0], fArray[2], fArray[4], fArray[6]);
        }
        if (fArray.length >= 4) {
            return new Q(f2, f3, f2 + f4, f3 + f5, fArray[0], fArray[1], fArray[2], fArray[3]);
        }
        float f6 = fArray.length > 0 ? fArray[0] : 0.0f;
        return new Q(f2, f3, f2 + f4, f3 + f5, f6, f6, f6, f6);
    }

    public float b() {
        return this.d - this.b;
    }
}

