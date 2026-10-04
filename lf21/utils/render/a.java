/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.render;

import java.awt.Color;
import lf21.ClientBase;
import lombok.Generated;
import net.minecraft.world.entity.player.Player;

public final class a
extends ClientBase {
    private static final String a;
    private static final String[] b;

    public static Color a(Player player) {
        int n2 = player.getName().getString().hashCode();
        int n3 = (n2 & 0xFF0000) >> 16;
        int n4 = (n2 & 0xFF00) >> 8;
        int n5 = n2 & 0xFF;
        return new Color(n3, n4, n5);
    }

    public static int a(int n2, int n3, double d2) {
        if (d2 > 1.0) {
            d2 = 1.0 - d2 % 1.0;
        }
        return lf21.utils.render.a.b(n2, n3, d2);
    }

    public static int a(int n2, int n3, long l2) {
        return lf21.utils.render.a.a(n2, n3, (double)((System.currentTimeMillis() + l2) % 4000L) / 2000.0);
    }

    public static int b(int n2, int n3, double d2) {
        double d3 = 1.0 - d2;
        int n4 = (int)((double)(n2 >> 16 & 0xFF) * d3 + (double)(n3 >> 16 & 0xFF) * d2);
        int n5 = (int)((double)(n2 >> 8 & 0xFF) * d3 + (double)(n3 >> 8 & 0xFF) * d2);
        int n6 = (int)((double)(n2 & 0xFF) * d3 + (double)(n3 & 0xFF) * d2);
        int n7 = (int)((double)(n2 >> 24 & 0xFF) * d3 + (double)(n3 >> 24 & 0xFF) * d2);
        return (n7 & 0xFF) << 24 | (n4 & 0xFF) << 16 | (n5 & 0xFF) << 8 | n6 & 0xFF;
    }

    public static Color a(int n2, int n3) {
        int n4 = (int)((System.currentTimeMillis() / (long)n2 + (long)n3) % 360L);
        float f2 = (float)n4 / 360.0f;
        return new Color(Color.HSBtoRGB(f2, 0.5f, 1.0f));
    }

    public static int a(int n2, int n3, int n4) {
        return lf21.utils.render.a.a(n2, n3, n4, 255);
    }

    public static int a(int n2, int n3, int n4, int n5) {
        return (n5 & 0xFF) << 24 | (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | n4 & 0xFF;
    }

    public static int a(int n2, float f2) {
        Color color = new Color(n2);
        return lf21.utils.render.a.a(color, f2).getRGB();
    }

    public static Color a(Color color, float f2) {
        f2 = Math.min(1.0f, Math.max(0.0f, f2));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * f2));
    }

    public static int a(int n2) {
        return n2 >> 24 & 0xFF;
    }

    public static int b(int n2) {
        return n2 >> 16 & 0xFF;
    }

    public static int c(int n2) {
        return n2 >> 8 & 0xFF;
    }

    public static int d(int n2) {
        return n2 & 0xFF;
    }

    @Generated
    private a() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0000\u000eo{\rV\u0012t\u0007&}YV\r=\u0012\u007f(NS\u0000'\u0015&iC[A7\u0007hfBKA6\u0003&aCL\u00155\braLK\u000402\u0000\u000eo{\rV\u0012t\u0007&}YV\r=\u0012\u007f(NS\u0000'\u0015&iC[A7\u0007hfBKA6\u0003&aCL\u00155\braLK\u00040";
        int n3 = "\u0000\u000eo{\rV\u0012t\u0007&}YV\r=\u0012\u007f(NS\u0000'\u0015&iC[A7\u0007hfBKA6\u0003&aCL\u00155\braLK\u000402\u0000\u000eo{\rV\u0012t\u0007&}YV\r=\u0012\u007f(NS\u0000'\u0015&iC[A7\u0007hfBKA6\u0003&aCL\u00155\braLK\u00040".length();
        int n4 = 50;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.utils.render.a.b(lf21.utils.render.a.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                b = stringArray;
                a = b[1];
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x61);
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
                    case 0 -> 84;
                    case 1 -> 102;
                    case 2 -> 6;
                    case 3 -> 8;
                    case 4 -> 45;
                    case 5 -> 63;
                    default -> 97;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

