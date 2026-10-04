/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.i
 */
public final class i_0
extends Enum<i_0> {
    public static final /* enum */ i_0 BUTT;
    public static final /* enum */ i_0 ROUND;
    public static final /* enum */ i_0 MITER;
    private static final i_0[] d;

    public static i_0[] a() {
        return (i_0[])d.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "}\u00199N\u0005r\u00059_S\u0005m\u00038TE";
        int n3 = "}\u00199N\u0005r\u00059_S\u0005m\u00038TE".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = i_0.a(i_0.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                BUTT = new i_0();
                ROUND = new i_0();
                MITER = new i_0();
                d = i_0.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static i_0 a(String string) {
        return Enum.valueOf(i_0.class, string);
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
                    case 0 -> 63;
                    case 1 -> 76;
                    case 2 -> 109;
                    case 3 -> 26;
                    case 4 -> 1;
                    case 5 -> 86;
                    default -> 53;
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
            cArray[0] = (char)(cArray[0] ^ 0x35);
        }
        return cArray;
    }

    private static i_0[] b() {
        return new i_0[]{BUTT, ROUND, MITER};
    }
}

