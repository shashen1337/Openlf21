/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

final class c
extends Enum<c> {
    public static final /* enum */ c AUTHENTIC;
    public static final /* enum */ c SUSPICIOUS;
    public static final /* enum */ c SCANNING;
    private static final c[] d;

    public static c[] a() {
        return (c[])d.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "&'7Do6\"2\n&1%Zh<%:1%\t41\"Bd18<'";
        int n3 = "&'7Do6\"2\n&1%Zh<%:1%\t41\"Bd18<'".length();
        int n4 = 8;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = c.a(c.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                AUTHENTIC = new c();
                SUSPICIOUS = new c();
                SCANNING = new c();
                d = c.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static c a(String string) {
        return Enum.valueOf(c.class, string);
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
                    case 0 -> 117;
                    case 1 -> 100;
                    case 2 -> 118;
                    case 3 -> 10;
                    case 4 -> 33;
                    case 5 -> 127;
                    default -> 108;
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
            cArray[0] = (char)(cArray[0] ^ 0x6C);
        }
        return cArray;
    }

    private static c[] b() {
        return new c[]{AUTHENTIC, SUSPICIOUS, SCANNING};
    }
}

