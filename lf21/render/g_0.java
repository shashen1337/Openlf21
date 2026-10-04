/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

/*
 * Renamed from lf21.render.g
 */
public final class g_0
extends Enum<g_0> {
    public static final /* enum */ g_0 UNKNOWN;
    private static final String[] e;
    private static final /* synthetic */ g_0[] d;
    public static final /* enum */ g_0 OTF;
    public static final /* enum */ g_0 a;

    private static /* synthetic */ g_0[] b() {
        return new g_0[]{a, OTF, UNKNOWN};
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
                    case 0 -> 86;
                    case 1 -> 83;
                    case 2 -> 87;
                    case 3 -> 116;
                    case 4 -> 120;
                    case 5 -> 126;
                    default -> 12;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\u0019\u0007\u0011\u0007\u0003\u001d\u001c:7)B\u0003\"'1";
                var4_3 = "\u0019\u0007\u0011\u0007\u0003\u001d\u001c:7)B\u0003\"'1".length();
                var1_4 = 3;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = g_0.a(g_0.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "9'1\u0003\u0002\u0007\u0011";
                    var4_3 = "9'1\u0003\u0002\u0007\u0011".length();
                    var1_4 = 3;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = g_0.a(g_0.c(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
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
        g_0.e = var5;
        var0_6 = g_0.e;
        g_0.a = new g_0();
        g_0.OTF = new g_0();
        g_0.UNKNOWN = new g_0();
        g_0.d = g_0.b();
    }

    public static g_0[] a() {
        return (g_0[])d.clone();
    }

    public static g_0 a(String string) {
        return Enum.valueOf(g_0.class, string);
    }

    public static g_0 b(String string) {
        if (string == null) {
            return UNKNOWN;
        }
        String string2 = string.toLowerCase();
        int n2 = -1;
        switch (string2.hashCode()) {
            case 115174: {
                String[] stringArray = e;
                if (!string2.equals("ttf")) break;
                n2 = 0;
                break;
            }
            case 110369: {
                if (!string2.equals(e[3])) break;
                n2 = 1;
            }
        }
        switch (n2) {
            case 0: {
                return a;
            }
            case 1: {
                return OTF;
            }
        }
        return UNKNOWN;
    }

    private static char[] c(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xC);
        }
        return cArray;
    }
}

