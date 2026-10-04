/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat.a;

final class a
extends Enum<a> {
    public static final /* enum */ a IDLE;
    public static final /* enum */ a AIR;
    public static final /* enum */ a GROUND;
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
        String string = "RG;\u0005\u0006\\Q8\u0015bX\u0003ZJ%";
        int n3 = "RG;\u0005\u0006\\Q8\u0015bX\u0003ZJ%".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = a.a(a.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                IDLE = new a();
                AIR = new a();
                GROUND = new a();
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
                    case 0 -> 27;
                    case 1 -> 3;
                    case 2 -> 119;
                    case 3 -> 64;
                    case 4 -> 44;
                    case 5 -> 28;
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

    private static a[] b() {
        return new a[]{IDLE, AIR, GROUND};
    }
}

