/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.b;

public final class g {
    private static long a = 0L;
    private static float b = 1.0f;

    private g() {
    }

    public static void a() {
        a = 0L;
        b = 1.0f;
    }

    public static void b() {
        long l2;
        a = l2 = System.nanoTime();
        b = 1.0f;
    }

    public static float a(float f2, float f3, float f4) {
        float f5 = f4 * b;
        if (f2 < f3) {
            return Math.min(f3, f2 + f5);
        }
        return Math.max(f3, f2 - f5);
    }

    public static float b(float f2, float f3, float f4) {
        return f2 + (f3 - f2) * g.a(f4);
    }

    public static float a(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        return 1.0f - (float)Math.pow(1.0f - f2, b);
    }
}

