/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui;

public final class e
extends Enum<e> {
    public static final /* enum */ e IDLE;
    public static final /* enum */ e FADING_OUT;
    public static final /* enum */ e WAITING;
    public static final /* enum */ e FADING_IN;
    private static final e[] e;

    public static e[] a() {
        return (e[])e.clone();
    }

    public static e a(String string) {
        return Enum.valueOf(e.class, string);
    }

    private static e[] b() {
        return new e[]{IDLE, FADING_OUT, WAITING, FADING_IN};
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "X._e\tW+Wi\u0011\u0012(X$";
                var5_3 = "X._e\tW+Wi\u0011\u0012(X$".length();
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
                    var0[var4_1++] = lf21.gui.e.a(lf21.gui.e.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "F+Zt\u0016\u001b0\nW+Wi\u0011\u0012(^?G";
                    var5_3 = "F+Zt\u0016\u001b0\nW+Wi\u0011\u0012(^?G".length();
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
                    var0[var4_1++] = lf21.gui.e.a(lf21.gui.e.b(v1));
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
        lf21.gui.e.IDLE = new e();
        lf21.gui.e.FADING_OUT = new e();
        lf21.gui.e.WAITING = new e();
        lf21.gui.e.FADING_IN = new e();
        lf21.gui.e.e = lf21.gui.e.b();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x77);
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
                    case 0 -> 17;
                    case 1 -> 106;
                    case 2 -> 19;
                    case 3 -> 32;
                    case 4 -> 95;
                    case 5 -> 85;
                    default -> 119;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

