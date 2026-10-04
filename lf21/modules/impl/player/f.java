/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

final class f
extends Enum<f> {
    public static final /* enum */ f WAITING_FOR_CORRECTION;
    private static final f[] e;
    public static final /* enum */ f REPLACING_CONFIRMATION;
    public static final /* enum */ f FALLING;
    public static final /* enum */ f IDLE;

    public static f[] a() {
        return (f[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "wb=3`96\u007fe;5v4>rq1$}>>n\u0004ig8\"";
                var5_3 = "wb=3`96\u007fe;5v4>rq1$}>>n\u0004ig8\"".length();
                var2_4 = 22;
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
                    var0[var4_1++] = f.a(f.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "rf$+h48nd+$f97iq9&}>>n\u0007fb8+`96";
                    var5_3 = "rf$+h48nd+$f97iq9&}>>n\u0007fb8+`96".length();
                    var2_4 = 22;
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
                    var0[var4_1++] = f.a(f.b(v1));
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
        f.IDLE = new f();
        f.FALLING = new f();
        f.WAITING_FOR_CORRECTION = new f();
        f.REPLACING_CONFIRMATION = new f();
        f.e = f.b();
    }

    public static f a(String string) {
        return Enum.valueOf(f.class, string);
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
                    case 0 -> 32;
                    case 1 -> 35;
                    case 2 -> 116;
                    case 3 -> 103;
                    case 4 -> 41;
                    case 5 -> 119;
                    default -> 113;
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
            cArray[0] = (char)(cArray[0] ^ 0x71);
        }
        return cArray;
    }

    private static f[] b() {
        return new f[]{IDLE, FALLING, WAITING_FOR_CORRECTION, REPLACING_CONFIRMATION};
    }
}

