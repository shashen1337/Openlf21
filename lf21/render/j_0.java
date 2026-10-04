/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.j
 */
public final class j_0
extends Enum<j_0> {
    public static final /* enum */ j_0 CLOSE;
    private static final j_0[] h;
    public static final /* enum */ j_0 RRECT;
    public static final /* enum */ j_0 QUAD_TO;
    public static final /* enum */ j_0 MOVE_TO;
    public static final /* enum */ j_0 LINE_TO;
    public static final /* enum */ j_0 CUBIC_TO;
    public static final /* enum */ j_0 RECT;

    public static j_0[] a() {
        return (j_0[])h.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[7];
                var4_1 = 0;
                var3_2 = "aN\u001aZGK\u001d\u0007}T\r[GK\u001d\u0004b^\u0018J\u0005bI\u001e]L\u0005sW\u0014M]";
                var5_3 = "aN\u001aZGK\u001d\u0007}T\r[GK\u001d\u0004b^\u0018J\u0005bI\u001e]L\u0005sW\u0014M]".length();
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
                    var0[var4_1++] = j_0.a(j_0.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "|R\u0015[GK\u001d\bsN\u0019W[@\u0006\u007f";
                    var5_3 = "|R\u0015[GK\u001d\bsN\u0019W[@\u0006\u007f".length();
                    var2_4 = 7;
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
                    var0[var4_1++] = j_0.a(j_0.b(v1));
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
        j_0.MOVE_TO = new j_0();
        j_0.LINE_TO = new j_0();
        j_0.QUAD_TO = new j_0();
        j_0.CUBIC_TO = new j_0();
        j_0.CLOSE = new j_0();
        j_0.RRECT = new j_0();
        j_0.RECT = new j_0();
        j_0.h = j_0.b();
    }

    public static j_0 a(String string) {
        return Enum.valueOf(j_0.class, string);
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
                    case 0 -> 48;
                    case 1 -> 27;
                    case 2 -> 91;
                    case 3 -> 30;
                    case 4 -> 24;
                    case 5 -> 31;
                    default -> 82;
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
            cArray[0] = (char)(cArray[0] ^ 0x52);
        }
        return cArray;
    }

    private static j_0[] b() {
        return new j_0[]{MOVE_TO, LINE_TO, QUAD_TO, CUBIC_TO, CLOSE, RRECT, RECT};
    }
}

