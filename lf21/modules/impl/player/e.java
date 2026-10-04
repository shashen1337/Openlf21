/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

final class e
extends Enum<e> {
    public static final /* enum */ e GROUND_WEB;
    private static final e[] e;
    public static final /* enum */ e LAVA;
    public static final /* enum */ e WEB_EXIT_PREDICT;
    public static final /* enum */ e COBWEB;

    public static e[] a() {
        return (e[])e.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[4];
                var4_1 = 0;
                var3_2 = "\t$#\u0011y-=\n>1\u001cy1=\u001d5\u0006\u001d.#\u0019y7";
                var5_3 = "\t$#\u0011y-=\n>1\u001cy1=\u001d5\u0006\u001d.#\u0019y7".length();
                var2_4 = 16;
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
                    var0[var4_1++] = lf21.modules.impl.player.e.a(lf21.modules.impl.player.e.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "\u00193.\u001br1+\t$#\u0004\u0012 7\u000f";
                    var5_3 = "\u00193.\u001br1+\t$#\u0004\u0012 7\u000f".length();
                    var2_4 = 10;
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
                    var0[var4_1++] = lf21.modules.impl.player.e.a(lf21.modules.impl.player.e.b(v1));
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
        lf21.modules.impl.player.e.COBWEB = new e();
        lf21.modules.impl.player.e.WEB_EXIT_PREDICT = new e();
        lf21.modules.impl.player.e.GROUND_WEB = new e();
        lf21.modules.impl.player.e.LAVA = new e();
        lf21.modules.impl.player.e.e = lf21.modules.impl.player.e.b();
    }

    public static e a(String string) {
        return Enum.valueOf(e.class, string);
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
                    case 0 -> 94;
                    case 1 -> 97;
                    case 2 -> 97;
                    case 3 -> 78;
                    case 4 -> 60;
                    case 5 -> 117;
                    default -> 116;
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
            cArray[0] = (char)(cArray[0] ^ 0x74);
        }
        return cArray;
    }

    private static e[] b() {
        return new e[]{COBWEB, WEB_EXIT_PREDICT, GROUND_WEB, LAVA};
    }
}

