/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.f
 */
public final class f_0
extends Enum<f_0> {
    public static final /* enum */ f_0 OUTER;
    private static final f_0[] e;
    public static final /* enum */ f_0 SOLID;
    public static final /* enum */ f_0 INNER;
    public static final /* enum */ f_0 NORMAL;

    public static f_0[] a() {
        return (f_0[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "A]5tc&\u0005\\]+pf";
                var5_3 = "A]5tc&\u0005\\]+pf".length();
                var2_4 = 6;
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
                    var0[var4_1++] = f_0.a(f_0.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "@G3|p\u0005F\\)|p";
                    var5_3 = "@G3|p\u0005F\\)|p".length();
                    var2_4 = 5;
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
                    var0[var4_1++] = f_0.a(f_0.b(v1));
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
        f_0.NORMAL = new f_0();
        f_0.INNER = new f_0();
        f_0.OUTER = new f_0();
        f_0.SOLID = new f_0();
        f_0.e = f_0.b();
    }

    public static f_0 a(String string) {
        return Enum.valueOf(f_0.class, string);
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
                    case 0 -> 15;
                    case 1 -> 18;
                    case 2 -> 103;
                    case 3 -> 57;
                    case 4 -> 34;
                    case 5 -> 106;
                    default -> 17;
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
            cArray[0] = (char)(cArray[0] ^ 0x11);
        }
        return cArray;
    }

    private static f_0[] b() {
        return new f_0[]{NORMAL, INNER, OUTER, SOLID};
    }
}

