/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

/*
 * Renamed from lf21.modules.impl.player.b
 */
public final class b_0
extends Enum<b_0> {
    public static final /* enum */ b_0 IDLE;
    public static final /* enum */ b_0 PLACING;
    public static final /* enum */ b_0 RECYCLING;
    private static final b_0[] d;

    public static b_0[] a() {
        return (b_0[])d.clone();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "4\u000b,N\u0012K8(\t\u00076\u0002.T\u0018I6\u0004/\n#R";
        int n3 = "4\u000b,N\u0012K8(\t\u00076\u0002.T\u0018I6\u0004/\n#R".length();
        int n4 = 9;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = b_0.a(b_0.b(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                IDLE = new b_0();
                PLACING = new b_0();
                RECYCLING = new b_0();
                d = b_0.b();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    public static b_0 a(String string) {
        return Enum.valueOf(b_0.class, string);
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
                    case 0 -> 102;
                    case 1 -> 78;
                    case 2 -> 111;
                    case 3 -> 23;
                    case 4 -> 81;
                    case 5 -> 7;
                    default -> 113;
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
            cArray[0] = (char)(cArray[0] ^ 0x71);
        }
        return cArray;
    }

    private static b_0[] b() {
        return new b_0[]{IDLE, PLACING, RECYCLING};
    }
}

