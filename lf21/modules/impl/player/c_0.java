/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

/*
 * Renamed from lf21.modules.impl.player.c
 */
final class c_0
extends Enum<c_0> {
    public static final /* enum */ c_0 IDLE;
    public static final /* enum */ c_0 FOOT_PLACED;
    public static final /* enum */ c_0 HEAD_PLACED;
    private static final c_0[] d;

    public static c_0[] a() {
        return (c_0[])d.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\u0017uWz>Fe\u0010y]j\u0004\u0018~Tk\u000b\u0019\u007fYj>Fe\u0010y]j";
        int n3 = "\u0017uWz>Fe\u0010y]j\u0004\u0018~Tk\u000b\u0019\u007fYj>Fe\u0010y]j".length();
        int n4 = 11;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = c_0.a(c_0.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                IDLE = new c_0();
                FOOT_PLACED = new c_0();
                HEAD_PLACED = new c_0();
                d = c_0.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static c_0 a(String string) {
        return Enum.valueOf(c_0.class, string);
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
                    case 0 -> 81;
                    case 1 -> 58;
                    case 2 -> 24;
                    case 3 -> 46;
                    case 4 -> 97;
                    case 5 -> 22;
                    default -> 41;
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
            cArray[0] = (char)(cArray[0] ^ 0x29);
        }
        return cArray;
    }

    private static c_0[] b() {
        return new c_0[]{IDLE, FOOT_PLACED, HEAD_PLACED};
    }
}

