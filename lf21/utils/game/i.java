/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.game;

import lf21.utils.b.a;
import lombok.Generated;

public final class i {
    public static long a(String string, double d2) {
        if (string.equals("DBC")) {
            return (long)(500.0 / d2 + a.a(-50.0, 50.0));
        }
        return (long)(1000.0 / d2 + a.a(-25.0, 25.0));
    }

    public static long a(double d2) {
        return (long)(1000.0 / d2);
    }

    public static long a(double d2, double d3) {
        double d4 = 1000.0 / d2;
        return (long)(d4 + a.a(-d3, d3));
    }

    @Generated
    private i() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

