/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

final class d
extends Enum<d> {
    public static final /* enum */ d LAVA_RETRIEVE;
    private static final d[] e;
    public static final /* enum */ d GROUND_WEB_BREAK;
    public static final /* enum */ d PLACE;
    public static final /* enum */ d IDLE;

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
                var3_2 = "'E\u0013&\u0018\u0010T?V\f\"\u0011\u0007\u0004\"@\t\"";
                var5_3 = "'E\u0013&\u0018\u0010T?V\f\"\u0011\u0007\u0004\"@\t\"".length();
                var2_4 = 13;
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
                    var3_2 = ";H\u0004$\u0002\u0010,V\n2\t\u0006N<A\u00078\u0005\u0010T*O";
                    var5_3 = ";H\u0004$\u0002\u0010,V\n2\t\u0006N<A\u00078\u0005\u0010T*O".length();
                    var2_4 = 5;
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
        d.IDLE = new d();
        d.PLACE = new d();
        d.LAVA_RETRIEVE = new d();
        d.GROUND_WEB_BREAK = new d();
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
                    case 0 -> 107;
                    case 1 -> 4;
                    case 2 -> 69;
                    case 3 -> 103;
                    case 4 -> 71;
                    case 5 -> 66;
                    default -> 17;
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
            cArray[0] = (char)(cArray[0] ^ 0x11);
        }
        return cArray;
    }

    private static d[] b() {
        return new d[]{IDLE, PLACE, LAVA_RETRIEVE, GROUND_WEB_BREAK};
    }
}

