/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import lf21.utils.a.c;
import lf21.utils.b.d;
import lf21.utils.b.f;

public class e
extends c {
    private static final String[] h;

    public void a(double d2, double d3) {
        this.a(d2, d3, f.b);
    }

    public void a(double d2, double d3, d d4) {
        this.a(d2, d3, d4, false);
    }

    public void a(double d2, double d3, d d4, boolean bl) {
        this.a(bl);
        if (this.b() && (d2 == this.i() || d2 == this.j() || d2 == (double)this.e())) {
            if (this.l()) {
                String[] stringArray = h;
                System.out.println("Animate cancelled due to valueTo equals fromValue");
            }
            return;
        }
        this.a(d4);
        this.b(d3 * 1000.0);
        this.a(System.currentTimeMillis());
        this.c(this.e());
        this.d(d2);
        if (this.l()) {
            double d5 = this.h();
            float f2 = this.e();
            double d6 = this.j();
            double d7 = d5;
            float f3 = f2;
            double d8 = d6;
            String[] stringArray = h;
            System.out.println("#animate {\n    to value: " + d8 + "\n    from value: " + f3 + "\n    duration: " + d7 + "\n}");
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "7+R\\C:\u0001RfR\n\u00020\u0006X1R1|e\u001b\u0011\u0002(\u0016\u001dh\u0013\u0012\u00009\u001fQn\u0016\\\u0007)\u0016\u001d\u007f\u001d\\\u0015=\u001fHn&\u0013C9\u0002Hj\u001e\u000fC:\u0001Rf$\u001d\u000f)\u0016\u0019\u001ej\u001c\u0015\u000e=\u0007X+\tvC|S\u001d\u007f\u001d\\\u0015=\u001fHnH\\";
                var4_3 = "7+R\\C:\u0001RfR\n\u00020\u0006X1R1|e\u001b\u0011\u0002(\u0016\u001dh\u0013\u0012\u00009\u001fQn\u0016\\\u0007)\u0016\u001d\u007f\u001d\\\u0015=\u001fHn&\u0013C9\u0002Hj\u001e\u000fC:\u0001Rf$\u001d\u000f)\u0016\u0019\u001ej\u001c\u0015\u000e=\u0007X+\tvC|S\u001d\u007f\u001d\\\u0015=\u001fHnH\\".length();
                var1_4 = 17;
                var0_5 = -1;
lbl7:
                // 2 sources

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block9;
                    break;
                }
lbl12:
                // 1 sources

                while (true) {
                    var5[var3_1++] = e.a(e.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "7+R\\C8\u0006Oj\u0006\u0015\f2I\u001d\u00027v";
                    var4_3 = "7+R\\C8\u0006Oj\u0006\u0015\f2I\u001d\u00027v".length();
                    var1_4 = 15;
                    var0_5 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var0_5;
                        v1 = var2_2.substring(v3, v3 + var1_4);
                        v2 = 0;
                        break block9;
                        break;
                    }
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var5[var3_1++] = e.a(e.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
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
        e.h = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x73);
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
                    case 0 -> 61;
                    case 1 -> 11;
                    case 2 -> 114;
                    case 3 -> 124;
                    case 4 -> 99;
                    case 5 -> 92;
                    default -> 115;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

