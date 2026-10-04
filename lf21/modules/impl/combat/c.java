/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

final class c
extends Enum<c> {
    public static final /* enum */ c IDLE;
    public static final /* enum */ c DISPLACING;
    public static final /* enum */ c RETURNING;
    public static final /* enum */ c OUTWARD_SENT_WAIT;
    private static final c[] f;
    public static final /* enum */ c RESTORE_SENT_WAIT;

    public static c[] a() {
        return (c[])f.clone();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var0 = new String[5];
                var4_1 = 0;
                var3_2 = "#\fv\tMW\u0013?\u000e\u0011#\fq\bPK\u001f.\u001ag\u0012KF\r0\u0000v\u00048\rn\u0019";
                var5_3 = "#\fv\tMW\u0013?\u000e\u0011#\fq\bPK\u001f.\u001ag\u0012KF\r0\u0000v\u00048\rn\u0019".length();
                var2_4 = 9;
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
                    var3_2 = "5\u0000q\fSX\u00198\u0007e\u0011>\u001cv\u000b^K\u001e.\u001ag\u0012KF\r0\u0000v";
                    var5_3 = "5\u0000q\fSX\u00198\u0007e\u0011>\u001cv\u000b^K\u001e.\u001ag\u0012KF\r0\u0000v".length();
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
        c.IDLE = new c();
        c.DISPLACING = new c();
        c.OUTWARD_SENT_WAIT = new c();
        c.RETURNING = new c();
        c.RESTORE_SENT_WAIT = new c();
        c.f = c.b();
    }

    public static c a(String string) {
        return Enum.valueOf(c.class, string);
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
                    case 0 -> 113;
                    case 1 -> 73;
                    case 2 -> 34;
                    case 3 -> 92;
                    case 4 -> 31;
                    case 5 -> 25;
                    default -> 90;
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
            cArray[0] = (char)(cArray[0] ^ 0x5A);
        }
        return cArray;
    }

    private static c[] b() {
        return new c[]{IDLE, DISPLACING, OUTWARD_SENT_WAIT, RETURNING, RESTORE_SENT_WAIT};
    }
}

