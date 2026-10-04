/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

final class a
extends Enum<a> {
    public static final /* enum */ a NONE;
    public static final /* enum */ a SAVE;
    public static final /* enum */ a DELETE;
    private static final a[] d;

    public static a[] a() {
        return (a[])d.clone();
    }

    public static a a(String string) {
        return Enum.valueOf(a.class, string);
    }

    private static a[] b() {
        return new a[]{NONE, SAVE, DELETE};
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "{\u0006*\u00172\u001e\u0004l\u00020\u0017\u0004q\f(\u0017";
        int n3 = "{\u0006*\u00172\u001e\u0004l\u00020\u0017\u0004q\f(\u0017".length();
        int n4 = 6;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = a.a(a.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                NONE = new a();
                SAVE = new a();
                DELETE = new a();
                d = a.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x76);
        }
        return cArray;
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
                    case 1 -> 67;
                    case 2 -> 102;
                    case 3 -> 82;
                    case 4 -> 102;
                    case 5 -> 91;
                    default -> 118;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

