/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import lf21.utils.a.b;
import lf21.utils.a.c;

public class d
extends c {
    private final String h;
    private final b i;
    private static final String[] j;

    public d(String string, b b2) {
        this.h = string;
        this.i = b2;
    }

    @Override
    public void a(double d2) {
        if (d2 == (double)this.e()) {
            return;
        }
        this.a(this.m().e());
        if (this.b() && (d2 == this.i() || d2 == this.j() || d2 == (double)this.e())) {
            if (this.l()) {
                String[] stringArray = j;
                System.out.println("Animating " + this.h + " cancelled due to valueTo equals fromValue");
            }
            return;
        }
        this.a(this.m().d());
        this.b(this.m().b() * 1000.0);
        this.a(System.currentTimeMillis());
        this.c(this.e());
        this.d(d2);
        if (this.l()) {
            double d3 = this.h();
            float f2 = this.e();
            double d4 = this.j();
            double d5 = d3;
            float f3 = f2;
            double d6 = d4;
            String[] stringArray = j;
            System.out.println(this.h + "#animate {\n    to value: " + d6 + "\n    from value: " + f3 + "\n    duration: " + d5 + "\n}");
        }
    }

    public b m() {
        return this.i;
    }

    public String n() {
        return this.h;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = ")LVqA,}eJS?F<t)[X?T(}|Jcp\u0002,`|N[l\u0002/cfBa~N<t\u0011\u0003\u000f\u0017?\u0002/cfB\u0017iC%dl\u0015\u0017\u000f\u0003\u000f\u0017?\u0002-d{NCvM'+)\u0002\u0003R";
                var4_3 = ")LVqA,}eJS?F<t)[X?T(}|Jcp\u0002,`|N[l\u0002/cfBa~N<t\u0011\u0003\u000f\u0017?\u0002/cfB\u0017iC%dl\u0015\u0017\u000f\u0003\u000f\u0017?\u0002-d{NCvM'+)\u0002\u0003R".length();
                var1_4 = 42;
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
                    var5[var3_1++] = d.a(d.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "HA^rC=xgH\u0017\u0019*NYvO(el\u000fL\u0015\u0002i1)[X?T(}|J\r?";
                    var4_3 = "HA^rC=xgH\u0017\u0019*NYvO(el\u000fL\u0015\u0002i1)[X?T(}|J\r?".length();
                    var1_4 = 10;
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
                    var5[var3_1++] = d.a(d.a(v1));
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
        d.j = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x11);
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
                    case 0 -> 9;
                    case 1 -> 47;
                    case 2 -> 55;
                    case 3 -> 31;
                    case 4 -> 34;
                    case 5 -> 73;
                    default -> 17;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

