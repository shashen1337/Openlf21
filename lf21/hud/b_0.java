/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

/*
 * Renamed from lf21.hud.b
 */
public final class b_0
extends Enum<b_0> {
    public static final /* enum */ b_0 TOP;
    public static final /* enum */ b_0 BOTTOM;
    public static final /* enum */ b_0 RIGHT;
    public static final /* enum */ b_0 LEFT;
    private static final b_0[] f;
    public static final /* enum */ b_0 CENTER;

    public static b_0[] a() {
        return (b_0[])f.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "u\u0015c\b\u0006z\u0015k\bY;\u0003m\u001fu";
                var5_3 = "u\u0015c\b\u0006z\u0015k\bY;\u0003m\u001fu".length();
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
                    var0[var4_1++] = b_0.a(b_0.b(v1));
                    if ((var1_5 += var2_4) < var5_3) {
                        var2_4 = var3_2.charAt(var1_5);
                        ** continue;
                    }
                    var3_2 = "k\u0019b\u0014H\u0006{\u001fq\bS$";
                    var5_3 = "k\u0019b\u0014H\u0006{\u001fq\bS$".length();
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
                    var0[var4_1++] = b_0.a(b_0.b(v1));
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
        b_0.TOP = new b_0();
        b_0.BOTTOM = new b_0();
        b_0.LEFT = new b_0();
        b_0.RIGHT = new b_0();
        b_0.CENTER = new b_0();
        b_0.f = b_0.b();
    }

    public static b_0 a(String string) {
        return Enum.valueOf(b_0.class, string);
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
                    case 0 -> 57;
                    case 1 -> 80;
                    case 2 -> 37;
                    case 3 -> 92;
                    case 4 -> 28;
                    case 5 -> 105;
                    default -> 27;
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
            cArray[0] = (char)(cArray[0] ^ 0x1B);
        }
        return cArray;
    }

    private static b_0[] b() {
        return new b_0[]{TOP, BOTTOM, LEFT, RIGHT, CENTER};
    }
}

