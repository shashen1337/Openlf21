/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.l
 */
public final class l_0
extends Enum<l_0> {
    public static final /* enum */ l_0 HARD;
    private static final /* synthetic */ l_0[] e;
    public static final /* enum */ l_0 OUTLINE;
    public static final /* enum */ l_0 SOFT;
    public static final /* enum */ l_0 NONE;

    public static l_0[] a() {
        return (l_0[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "\u0010\u0000(0\u0007\f\u001a:(Pe\u0012";
                var5_3 = "\u0010\u0000(0\u0007\f\u001a:(Pe\u0012".length();
                var2_4 = 4;
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
                    var0[var4_1++] = l_0.a(l_0.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "\u000b\u000e< \u0004\r\u0000 !";
                    var5_3 = "\u000b\u000e< \u0004\r\u0000 !".length();
                    var2_4 = 4;
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
                    var0[var4_1++] = l_0.a(l_0.b(v1));
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
        l_0.NONE = new l_0();
        l_0.SOFT = new l_0();
        l_0.HARD = new l_0();
        l_0.OUTLINE = new l_0();
        l_0.e = l_0.b();
    }

    public static l_0 a(String string) {
        return Enum.valueOf(l_0.class, string);
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
                    case 0 -> 67;
                    case 1 -> 79;
                    case 2 -> 110;
                    case 3 -> 100;
                    case 4 -> 25;
                    case 5 -> 43;
                    default -> 87;
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
            cArray[0] = (char)(cArray[0] ^ 0x57);
        }
        return cArray;
    }

    private static /* synthetic */ l_0[] b() {
        return new l_0[]{NONE, SOFT, HARD, OUTLINE};
    }
}

