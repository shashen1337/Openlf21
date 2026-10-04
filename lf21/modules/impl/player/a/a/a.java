/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

public final class a
extends Enum<a> {
    public static final /* enum */ a NONE;
    public static final /* enum */ a LAVA;
    public static final /* enum */ a LAVA_SUPPORT;
    private static final a[] d;

    public static a[] a() {
        return (a[])d.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "P\u001f2y\u0014JQL\u000e+j\u001f\u0004P\u001f2y\u0004R\u0011*}";
        int n3 = "P\u001f2y\u0014JQL\u000e+j\u001f\u0004P\u001f2y\u0004R\u0011*}".length();
        int n4 = 12;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = a.a(a.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                NONE = new a();
                LAVA = new a();
                LAVA_SUPPORT = new a();
                d = a.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static a a(String string) {
        return Enum.valueOf(a.class, string);
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
                    case 0 -> 28;
                    case 1 -> 94;
                    case 2 -> 100;
                    case 3 -> 56;
                    case 4 -> 75;
                    case 5 -> 25;
                    default -> 4;
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
            cArray[0] = (char)(cArray[0] ^ 4);
        }
        return cArray;
    }

    private static a[] b() {
        return new a[]{NONE, LAVA, LAVA_SUPPORT};
    }
}

