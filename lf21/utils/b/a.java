/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.b;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Random;
import lf21.ClientBase;
import lombok.Generated;

public final class a
extends ClientBase {
    public static final Random a;
    private static final String b;

    public static double a(double d2, double d3, double d4) {
        return Math.min(Math.max(d2, d3), d4);
    }

    public static double a(double d2, int n2) {
        if (n2 == 0) {
            return Math.floor(d2);
        }
        double d3 = Math.pow(10.0, n2);
        return (double)Math.round(d2 * d3) / d3;
    }

    public static double a(int n2, int n3) {
        double d2 = n2 >= n3 ? (double)n2 : (double)(a.nextInt() * (n3 - n2) + n2);
        return d2;
    }

    public static double a(double d2, double d3) {
        double d4 = d2 >= d3 ? d2 : a.nextDouble() * (d3 - d2) + d2;
        return d4;
    }

    public static double b(double d2, double d3) {
        double d4 = (double)Math.round(d2 / d3) * d3;
        d4 *= 1000.0;
        d4 = (int)d4;
        return d4 /= 1000.0;
    }

    public static double b(double d2, int n2) {
        if (n2 < 0) {
            return d2;
        }
        BigDecimal bigDecimal = new BigDecimal(d2);
        bigDecimal = bigDecimal.setScale(n2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static float a(float f2, float f3) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextFloat() * (f2 - f3) + f3;
    }

    public static float a(float f2) {
        if (f2 > 90.0f) {
            return 90.0f;
        }
        if (f2 < -90.0f) {
            return -90.0f;
        }
        return f2;
    }

    public static float a(float f2, float f3, float f4) {
        float f5 = f2 < f3 ? f3 : Math.min(f2, f4);
        return f5;
    }

    public static float b(float f2, float f3, float f4) {
        return f3 + f2 * (f4 - f3);
    }

    public static double a(double d2, double d3, float f2) {
        return d3 + d2 * ((double)f2 - d3);
    }

    public static double a(float f2, double d2, double d3) {
        return d2 + (double)f2 * (d3 - d2);
    }

    public static int a(int n2, int n3, float f2) {
        f2 = Math.max(0.0f, Math.min(1.0f, f2));
        int n4 = n2 >> 24 & 0xFF;
        int n5 = n2 >> 16 & 0xFF;
        int n6 = n2 >> 8 & 0xFF;
        int n7 = n2 & 0xFF;
        int n8 = n3 >> 24 & 0xFF;
        int n9 = n3 >> 16 & 0xFF;
        int n10 = n3 >> 8 & 0xFF;
        int n11 = n3 & 0xFF;
        int n12 = (int)((float)n4 * (1.0f - f2) + (float)n8 * f2);
        int n13 = (int)((float)n5 * (1.0f - f2) + (float)n9 * f2);
        int n14 = (int)((float)n6 * (1.0f - f2) + (float)n10 * f2);
        int n15 = (int)((float)n7 * (1.0f - f2) + (float)n11 * f2);
        return n12 << 24 | n13 << 16 | n14 << 8 | n15;
    }

    @Generated
    private a() {
        throw new UnsupportedOperationException(b);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String string = lf21.utils.b.a.b(lf21.utils.b.a.a("!5+\nG$1U<b\f\u0013$.\u001c);Y\u0004!#\u0006.b\u0018\t)b\u0016<,\u0017\b9b\u00178b\u0010\t>6\u001436\u0010\u00069'\u0011"));
        b = string;
        a = new Random();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x42);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
        char[] cArray2;
        block11: {
            int n2;
            int n3;
            block10: {
                cArray2 = cArray;
                n3 = cArray.length;
                n2 = 0;
                if (!true) break block10;
                n3 = n3;
                if (n3 <= n2) break block11;
            }
            do {
                cArray2 = cArray2;
                int n4 = n2;
                char c2 = cArray2[n4];
                cArray2[n4] = (char)(c2 ^ (switch (n2 % 7) {
                    case 0 -> 117;
                    case 1 -> 93;
                    case 2 -> 66;
                    case 3 -> 121;
                    case 4 -> 103;
                    case 5 -> 77;
                    default -> 66;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

