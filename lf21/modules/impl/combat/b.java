/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

public final class b
extends Enum<b> {
    public static final /* enum */ b BLOCKS;
    private static final b[] e;
    public static final /* enum */ b NONE;
    public static final /* enum */ b GAPPLE;
    public static final /* enum */ b SNOWBALL;

    public static b[] a() {
        return (b[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "]Y\b\u0005! 8B\u0006IV\u0017\u0002/$";
                var5_3 = "]Y\b\u0005! 8B\u0006IV\u0017\u0002/$".length();
                var2_4 = 8;
                var1_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var1_5;
                    v1 = var3_2.substring(v0, v0 + var2_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var0[var4_1++] = b.a(b.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "L[\b\u0011(2\u0004@X\t\u0017";
                    var5_3 = "L[\b\u0011(2\u0004@X\t\u0017".length();
                    var2_4 = 6;
                    var1_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var1_5;
                        v1 = var3_2.substring(v3, v3 + var2_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var0[var4_1++] = b.a(b.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    break block10;
                    break;
                }
            }
            switch (v2) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl37:
                // 1 sources

                ** continue;
            }
        }
        b.SNOWBALL = new b();
        b.GAPPLE = new b();
        b.BLOCKS = new b();
        b.NONE = new b();
        b.e = b.b();
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
                    case 0 -> 14;
                    case 1 -> 23;
                    case 2 -> 71;
                    case 3 -> 82;
                    case 4 -> 99;
                    case 5 -> 97;
                    default -> 116;
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
            cArray[0] = (char)(cArray[0] ^ 0x74);
        }
        return cArray;
    }

    private static b[] b() {
        return new b[]{SNOWBALL, GAPPLE, BLOCKS, NONE};
    }
}

