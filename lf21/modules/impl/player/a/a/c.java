/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

public final class c
extends Enum<c> {
    private static final c[] c;
    public static final /* enum */ c NONE;
    public static final /* enum */ c FIRE;

    public static c[] a() {
        return (c[])c.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "EnoI\u0004MhsI";
        int n3 = "EnoI\u0004MhsI".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.modules.impl.player.a.a.c.a(lf21.modules.impl.player.a.a.c.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                NONE = new c();
                FIRE = new c();
                c = lf21.modules.impl.player.a.a.c.b();
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
                    case 0 -> 3;
                    case 1 -> 39;
                    case 2 -> 61;
                    case 3 -> 12;
                    case 4 -> 88;
                    case 5 -> 21;
                    default -> 68;
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
            cArray[0] = (char)(cArray[0] ^ 0x44);
        }
        return cArray;
    }

    private static c[] b() {
        return new c[]{NONE, FIRE};
    }
}

