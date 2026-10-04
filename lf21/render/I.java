/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import lf21.render.G;
import lf21.render.H;
import lf21.render.J;

public final class I
implements AutoCloseable {
    private final float a;

    public I(G g2) {
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        if (g2 != null) {
            block7: for (H h2 : g2.b()) {
                switch (J.a[h2.a.ordinal()]) {
                    case 1: {
                        f3 = h2.b[0];
                        f4 = h2.b[1];
                        f5 = f3;
                        f6 = f4;
                        continue block7;
                    }
                    case 2: {
                        float f7 = h2.b[0];
                        float f8 = h2.b[1];
                        f2 += (float)Math.hypot(f7 - f3, f8 - f4);
                        f3 = f7;
                        f4 = f8;
                        continue block7;
                    }
                    case 3: {
                        float f7 = h2.b[0];
                        float f8 = h2.b[1];
                        float f9 = h2.b[2];
                        float f10 = h2.b[3];
                        f2 += I.a(f3, f4, f7, f8, f9, f10);
                        f3 = f9;
                        f4 = f10;
                        continue block7;
                    }
                    case 4: {
                        float f7 = h2.b[0];
                        float f8 = h2.b[1];
                        float f9 = h2.b[2];
                        float f10 = h2.b[3];
                        float f11 = h2.b[4];
                        float f12 = h2.b[5];
                        f2 += I.a(f3, f4, f7, f8, f9, f10, f11, f12);
                        f3 = f11;
                        f4 = f12;
                        continue block7;
                    }
                    case 5: {
                        f2 += (float)Math.hypot(f5 - f3, f6 - f4);
                        f3 = f5;
                        f4 = f6;
                        continue block7;
                    }
                }
            }
        }
        this.a = f2;
    }

    public float a() {
        return this.a;
    }

    private static float a(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = 0.0f;
        float f11 = f2;
        float f12 = f3;
        int n2 = 24;
        for (int i2 = 1; i2 <= n2; ++i2) {
            float f13 = (float)i2 / (float)n2;
            float f14 = 1.0f - f13;
            float f15 = f14 * f14 * f14 * f2 + 3.0f * f14 * f14 * f13 * f4 + 3.0f * f14 * f13 * f13 * f6 + f13 * f13 * f13 * f8;
            float f16 = f14 * f14 * f14 * f3 + 3.0f * f14 * f14 * f13 * f5 + 3.0f * f14 * f13 * f13 * f7 + f13 * f13 * f13 * f9;
            f10 += (float)Math.hypot(f15 - f11, f16 - f12);
            f11 = f15;
            f12 = f16;
        }
        return f10;
    }

    private static float a(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = 0.0f;
        float f9 = f2;
        float f10 = f3;
        int n2 = 16;
        for (int i2 = 1; i2 <= n2; ++i2) {
            float f11 = (float)i2 / (float)n2;
            float f12 = 1.0f - f11;
            float f13 = f12 * f12 * f2 + 2.0f * f12 * f11 * f4 + f11 * f11 * f6;
            float f14 = f12 * f12 * f3 + 2.0f * f12 * f11 * f5 + f11 * f11 * f7;
            f8 += (float)Math.hypot(f13 - f9, f14 - f10);
            f9 = f13;
            f10 = f14;
        }
        return f8;
    }

    @Override
    public void close() {
    }
}

