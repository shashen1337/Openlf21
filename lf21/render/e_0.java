/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.e
 */
public final class e_0
extends Enum<e_0> {
    public static final /* enum */ e_0 RIGHT;
    private static final /* synthetic */ e_0[] e;
    public static final /* enum */ e_0 JUSTIFY;
    public static final /* enum */ e_0 CENTER;
    public static final /* enum */ e_0 LEFT;

    public static e_0[] a() {
        return (e_0[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "+S\fP\f\u0003N\u0004-C\u0019P";
                var5_3 = "+S\fP\f\u0003N\u0004-C\u0019P".length();
                var2_4 = 7;
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
                    var0[var4_1++] = e_0.a(e_0.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "\"C\u0011P\u0000\u0017\u00053O\u0018L\u0011";
                    var5_3 = "\"C\u0011P\u0000\u0017\u00053O\u0018L\u0011".length();
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
                    var0[var4_1++] = e_0.a(e_0.b(v1));
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
        e_0.LEFT = new e_0();
        e_0.CENTER = new e_0();
        e_0.RIGHT = new e_0();
        e_0.JUSTIFY = new e_0();
        e_0.e = e_0.b();
    }

    public static e_0 a(String string) {
        return Enum.valueOf(e_0.class, string);
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
                    case 0 -> 97;
                    case 1 -> 6;
                    case 2 -> 95;
                    case 3 -> 4;
                    case 4 -> 69;
                    case 5 -> 69;
                    default -> 23;
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
            cArray[0] = (char)(cArray[0] ^ 0x17);
        }
        return cArray;
    }

    private static /* synthetic */ e_0[] b() {
        return new e_0[]{LEFT, CENTER, RIGHT, JUSTIFY};
    }
}

