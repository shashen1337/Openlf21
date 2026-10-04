/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import net.minecraft.util.Mth;

public final class c {
    private static final float a;
    private static final float b;
    private float c;
    private long d;

    public float a(boolean bl) {
        long l2 = System.nanoTime();
        float f2 = this.d == 0L ? 0.016666668f : Math.min(0.05f, (float)(l2 - this.d) / 1.0E9f);
        this.d = l2;
        float f3 = bl ? 1.0f : 0.0f;
        float f4 = bl ? 15.0f : 12.0f;
        float f5 = 1.0f - (float)Math.exp(-f4 * f2);
        this.c += (f3 - this.c) * f5;
        if (Math.abs(f3 - this.c) < 0.002f) {
            this.c = f3;
        }
        return this.c;
    }

    public float a() {
        float f2 = Mth.clamp((float)this.c, (float)0.0f, (float)1.0f);
        return f2 * f2 * (3.0f - 2.0f * f2);
    }

    public float b() {
        return 0.82f + 0.18f * this.a();
    }

    public boolean c() {
        return this.c <= 0.0f;
    }

    public void a(float f2) {
        this.c = Mth.clamp((float)f2, (float)0.0f, (float)1.0f);
        this.d = 0L;
    }

    static {
        b = 12.0f;
        a = 15.0f;
    }
}

