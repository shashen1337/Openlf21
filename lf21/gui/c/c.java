/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

public final class c
extends Enum<c> {
    public static final /* enum */ c NONE;
    public static final /* enum */ c FADE_IN;
    public static final /* enum */ c FADE_OUT;
    public static final /* enum */ c SWITCHING;
    private static final c[] e;

    public static c[] a() {
        return (c[])e.clone();
    }

    public static c a(String string) {
        return Enum.valueOf(c.class, string);
    }

    private static c[] b() {
        return new c[]{NONE, FADE_IN, FADE_OUT, SWITCHING};
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "%\t/+\u000fw{\t0\u001f\":\u0013v|-\u000f";
                var5_3 = "%\t/+\u000fw{\t0\u001f\":\u0013v|-\u000f".length();
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
                    var0[var4_1++] = c.a(c.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "-\u0007%+\b%\t/+\u000fq`7";
                    var5_3 = "-\u0007%+\b%\t/+\u000fq`7".length();
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
                    var0[var4_1++] = c.a(c.b(v1));
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
        c.NONE = new c();
        c.FADE_IN = new c();
        c.FADE_OUT = new c();
        c.SWITCHING = new c();
        c.e = c.b();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x35);
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
                    case 0 -> 99;
                    case 1 -> 72;
                    case 2 -> 107;
                    case 3 -> 110;
                    case 4 -> 80;
                    case 5 -> 62;
                    default -> 53;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

