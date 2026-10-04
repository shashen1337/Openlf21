/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.h
 */
public final class h_0
extends Enum<h_0> {
    public static final /* enum */ h_0 FILL_AND_STROKE;
    private static final h_0[] e;
    public static final /* enum */ h_0 STROKE_AND_FILL;
    public static final /* enum */ h_0 STROKE;
    public static final /* enum */ h_0 FILL;

    public static h_0[] a() {
        return (h_0[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "'\u0017I\u001bcd%5\r_\u000bnh68\u00042\nW\u0018";
                var5_3 = "'\u0017I\u001bcd%5\r_\u000bnh68\u00042\nW\u0018".length();
                var2_4 = 15;
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
                    var0[var4_1++] = h_0.a(h_0.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "'\u0017I\u001bcd\u000f2\nW\u0018w`40\u001cH\u0000zn11";
                    var5_3 = "'\u0017I\u001bcd\u000f2\nW\u0018w`40\u001cH\u0000zn11".length();
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
                    var0[var4_1++] = h_0.a(h_0.b(v1));
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
        h_0.FILL = new h_0();
        h_0.STROKE = new h_0();
        h_0.FILL_AND_STROKE = new h_0();
        h_0.STROKE_AND_FILL = new h_0();
        h_0.e = h_0.b();
    }

    public static h_0 a(String string) {
        return Enum.valueOf(h_0.class, string);
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
                    case 0 -> 116;
                    case 1 -> 67;
                    case 2 -> 27;
                    case 3 -> 84;
                    case 4 -> 40;
                    case 5 -> 33;
                    default -> 122;
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
            cArray[0] = (char)(cArray[0] ^ 0x7A);
        }
        return cArray;
    }

    private static h_0[] b() {
        return new h_0[]{FILL, STROKE, FILL_AND_STROKE, STROKE_AND_FILL};
    }
}

