/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

final class a
extends Enum<a> {
    public static final /* enum */ a IDLE;
    public static final /* enum */ a READY;
    public static final /* enum */ a TUNING;
    public static final /* enum */ a SETUP;
    private static final a[] g;
    public static final /* enum */ a PLAYING;
    public static final /* enum */ a VERIFY;

    public static a[] a() {
        return (a[])g.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[6];
                var4_1 = 0;
                var3_2 = "\u0019y@ii\u0005\u0018yUx`\u0006\u001fiOd~\u001c\u0007\u001bp@ty\u0015<";
                var5_3 = "\u0019y@ii\u0005\u0018yUx`\u0006\u001fiOd~\u001c\u0007\u001bp@ty\u0015<".length();
                var2_4 = 5;
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
                    var0[var4_1++] = a.a(a.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "\u0002xMh\u0006\u001dySdv\u0002";
                    var5_3 = "\u0002xMh\u0006\u001dySdv\u0002".length();
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
                    var0[var4_1++] = a.a(a.b(v1));
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
        a.IDLE = new a();
        a.SETUP = new a();
        a.TUNING = new a();
        a.VERIFY = new a();
        a.READY = new a();
        a.PLAYING = new a();
        a.g = a.b();
    }

    public static a a(String string) {
        return Enum.valueOf(a.class, string);
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
                    case 0 -> 75;
                    case 1 -> 60;
                    case 2 -> 1;
                    case 3 -> 45;
                    case 4 -> 48;
                    case 5 -> 91;
                    default -> 123;
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
            cArray[0] = (char)(cArray[0] ^ 0x7B);
        }
        return cArray;
    }

    private static a[] b() {
        return new a[]{IDLE, SETUP, TUNING, VERIFY, READY, PLAYING};
    }
}

