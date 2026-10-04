/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

public final class d
extends Enum<d> {
    public static final /* enum */ d CLOSED;
    public static final /* enum */ d OPENING;
    public static final /* enum */ d OPEN;
    public static final /* enum */ d CLOSING;
    private static final d[] e;

    public static d[] a() {
        return (d[])e.clone();
    }

    public static d a(String string) {
        return Enum.valueOf(d.class, string);
    }

    private static d[] b() {
        return new d[]{CLOSED, OPENING, OPEN, CLOSING};
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "p?~?JD_\u0004|#t\"";
                var5_3 = "p?~?JD_\u0004|#t\"".length();
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
                    var0[var4_1++] = d.a(d.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "|#t\"JD_\u0006p?~?FN";
                    var5_3 = "|#t\"JD_\u0006p?~?FN".length();
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
                    var0[var4_1++] = d.a(d.b(v1));
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
        d.CLOSED = new d();
        d.OPENING = new d();
        d.OPEN = new d();
        d.CLOSING = new d();
        d.e = d.b();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x18);
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
                    case 0 -> 51;
                    case 1 -> 115;
                    case 2 -> 49;
                    case 3 -> 108;
                    case 4 -> 3;
                    case 5 -> 10;
                    default -> 24;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

