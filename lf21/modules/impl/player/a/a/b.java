/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

public final class b
extends Enum<b> {
    public static final /* enum */ b NONE;
    public static final /* enum */ b WATER;
    public static final /* enum */ b WATER_SUPPORT;
    private static final b[] d;

    public static b[] a() {
        return (b[])d.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "Q:\u0019G\u0005H4\u0003G5\rH4\u0003G5mOJ%\u0007M5f";
        int n3 = "Q:\u0019G\u0005H4\u0003G5\rH4\u0003G5mOJ%\u0007M5f".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = b.a(b.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                NONE = new b();
                WATER = new b();
                WATER_SUPPORT = new b();
                d = b.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static b a(String string) {
        return Enum.valueOf(b.class, string);
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 31;
                    case 1 -> 117;
                    case 2 -> 87;
                    case 3 -> 2;
                    case 4 -> 103;
                    case 5 -> 50;
                    default -> 28;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x1C);
        }
        return cArray;
    }

    private static b[] b() {
        return new b[]{NONE, WATER, WATER_SUPPORT};
    }
}

