/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

public final class d
extends Enum<d> {
    public static final /* enum */ d BREAKING;
    private static final d[] e;
    public static final /* enum */ d COMPLETE;
    public static final /* enum */ d PLACING;
    public static final /* enum */ d NONE;

    public static d[] a() {
        return (d[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "\u0013-qV\b\u001f0zRkuD\u001a";
                var5_3 = "\u0013-qV\b\u001f0zRkuD\u001a".length();
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
                    var0[var4_1++] = d.a(d.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "\u001e-rCly^\u0018\u0007\r.~PirM";
                    var5_3 = "\u001e-rCly^\u0018\u0007\r.~PirM".length();
                    var2_4 = 8;
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
        d.NONE = new d();
        d.PLACING = new d();
        d.BREAKING = new d();
        d.COMPLETE = new d();
        d.e = d.b();
    }

    public static d a(String string) {
        return Enum.valueOf(d.class, string);
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
                    case 0 -> 93;
                    case 1 -> 98;
                    case 2 -> 63;
                    case 3 -> 19;
                    case 4 -> 32;
                    case 5 -> 60;
                    default -> 10;
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
            cArray[0] = (char)(cArray[0] ^ 0xA);
        }
        return cArray;
    }

    private static d[] b() {
        return new d[]{NONE, PLACING, BREAKING, COMPLETE};
    }
}

