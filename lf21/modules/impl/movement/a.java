/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.movement;

final class a
extends Enum<a> {
    public static final /* enum */ a RESTORE_ON_MOVE;
    private static final a[] h;
    public static final /* enum */ a WAIT_FLUSH;
    public static final /* enum */ a USING;
    public static final /* enum */ a IDLE;
    public static final /* enum */ a PREPARED;
    public static final /* enum */ a RELEASED;
    public static final /* enum */ a FLUSH;

    public static a[] a() {
        return (a[])h.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[7];
                var4_1 = 0;
                var3_2 = "K\u001fz\u0006o\b_\u0016c\u0010fO\u0010I\nZ\u0012f\u0001xZ\u0019X\u0000g\u0005X\u0000f\u001b`\b]\u0001j\u0005fN\u0010I";
                var5_3 = "K\u001fz\u0006o\b_\u0016c\u0010fO\u0010I\nZ\u0012f\u0001xZ\u0019X\u0000g\u0005X\u0000f\u001b`\b]\u0001j\u0005fN\u0010I".length();
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
                    var3_2 = "_\u0016|\u0001hN\u0010R\u001ca\njS\u0003H\u0004D\u0017c\u0010";
                    var5_3 = "_\u0016|\u0001hN\u0010R\u001ca\njS\u0003H\u0004D\u0017c\u0010".length();
                    var2_4 = 15;
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
        a.PREPARED = new a();
        a.USING = new a();
        a.RELEASED = new a();
        a.RESTORE_ON_MOVE = new a();
        a.WAIT_FLUSH = new a();
        a.FLUSH = new a();
        a.h = a.b();
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
                    case 0 -> 13;
                    case 1 -> 83;
                    case 2 -> 47;
                    case 3 -> 85;
                    case 4 -> 39;
                    case 5 -> 28;
                    default -> 85;
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
            cArray[0] = (char)(cArray[0] ^ 0x55);
        }
        return cArray;
    }

    private static a[] b() {
        return new a[]{IDLE, PREPARED, USING, RELEASED, RESTORE_ON_MOVE, WAIT_FLUSH, FLUSH};
    }
}

