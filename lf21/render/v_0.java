/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import lf21.render.q_0;
import lf21.utils.misc.a;
import org.jetbrains.annotations.NotNull;

/*
 * Renamed from lf21.render.v
 */
public class v_0 {
    public static q_0 o;
    public static q_0 q;
    public static q_0 n;
    public static q_0 b;
    public static q_0 p;
    public static q_0 c;
    public static q_0 a;
    public static q_0 m;
    private static final String[] r;
    public static q_0 h;
    public static q_0 g;
    public static q_0 e;
    public static q_0 j;
    public static q_0 l;
    public static q_0 f;
    public static q_0 i;
    public static q_0 d;
    public static q_0 k;

    @NotNull
    public static q_0 a(float f2, String string) {
        q_0 q_02;
        String[] stringArray = r;
        InputStream inputStream = lf21.utils.misc.a.a("/assets/lf21/fonts/" + string);
        try {
            if (inputStream == null) {
                throw new IOException("Font not found: " + string);
            }
            q_02 = new q_0(Font.createFont(0, inputStream).deriveFont(0, f2 / 2.0f), f2 / 2.0f);
        }
        catch (Throwable throwable) {
            try {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                }
                throw throwable;
            }
            catch (Exception exception) {
                return new q_0(new Font("SansSerif", 0, (int)(f2 / 2.0f)), f2 / 2.0f);
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return q_02;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[21];
                var3_1 = 0;
                var2_2 = "\u007f,05k}g\u007f\u000b;<hk$j ?\u0017n=74bndy\u000b*0[}oy!52v!~j2\u000fX50?ak*j;y?knn>\u0014\u007f,05k}g\u007f\u000b+6czf\u007f&w'pi\u0017n=74bndy\u000b*0[}oy!52v!~j2\u0011\u007f,05k}g\u007f\u000b;<hk$j ?\tM57 Wjxw2\fq$<=wndmz-'b\bw76=*{~x\fs5-6vfkrz-'b\tM57 Wjxw2\u00162t, mam>28?hmk}?y5ka~0\u0014\u007f,05k}g\u007f\u000b+6czf\u007f&w'pi\u0016\u007f,05k}g\u007f\u000b<+p}k|;57*{~x\u001315* a{y18?a5 lq:- +\bw76=*{~x\u0014r2kbmlepy\u000b6czf\u007f&w'pi\u0016\u007f,05k}g\u007f\u000b<+p}k|;57*{~x\u0010X;7'$aejt?<qan$t";
                var4_3 = "\u007f,05k}g\u007f\u000b;<hk$j ?\u0017n=74bndy\u000b*0[}oy!52v!~j2\u000fX50?ak*j;y?knn>\u0014\u007f,05k}g\u007f\u000b+6czf\u007f&w'pi\u0017n=74bndy\u000b*0[}oy!52v!~j2\u0011\u007f,05k}g\u007f\u000b;<hk$j ?\tM57 Wjxw2\fq$<=wndmz-'b\bw76=*{~x\fs5-6vfkrz-'b\tM57 Wjxw2\u00162t, mam>28?hmk}?y5ka~0\u0014\u007f,05k}g\u007f\u000b+6czf\u007f&w'pi\u0016\u007f,05k}g\u007f\u000b<+p}k|;57*{~x\u001315* a{y18?a5 lq:- +\bw76=*{~x\u0014r2kbmlepy\u000b6czf\u007f&w'pi\u0016\u007f,05k}g\u007f\u000b<+p}k|;57*{~x\u0010X;7'$aejt?<qan$t".length();
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
                    var5[var3_1++] = v_0.a(v_0.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "s5-6vfkrz-'b\fq$<=wndmz-'b";
                    var4_3 = "s5-6vfkrz-'b\fq$<=wndmz-'b".length();
                    var1_4 = 12;
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
                    var5[var3_1++] = v_0.a(v_0.a(v1));
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
        v_0.r = var5;
        var0_6 = v_0.r;
        v_0.a = v_0.a(16.0f, "opensans.ttf");
        v_0.b = v_0.a(18.0f, "opensans.ttf");
        v_0.c = v_0.a(16.0f, "pingfang_sc_regular.ttf");
        v_0.d = v_0.a(18.0f, "pingfang_sc_regular.ttf");
        v_0.e = v_0.a(18.0f, "icon.ttf");
        v_0.f = v_0.a(30.0f, "icon.ttf");
        v_0.g = v_0.b(20.0f, "material.ttf");
        v_0.h = v_0.b(14.0f, "material.ttf");
        v_0.i = v_0.a(28.0f, "lf21icon-Regular.ttf");
        v_0.j = v_0.a(14.0f, "axiforma_regular.ttf");
        v_0.k = v_0.a(16.0f, "axiforma_regular.ttf");
        v_0.l = v_0.a(18.0f, "axiforma_regular.ttf");
        v_0.m = v_0.a(13.0f, "axiforma_bold.ttf");
        v_0.n = v_0.a(18.0f, "axiforma_bold.ttf");
        v_0.o = v_0.a(16.0f, "axiforma_extrabold.ttf");
        v_0.p = v_0.a(18.0f, "axiforma_extrabold.ttf");
        v_0.q = v_0.a(16.0f, "axiforma_bold.ttf");
    }

    @NotNull
    private static q_0 b(float f2, String string) {
        try {
            return v_0.a(f2, string);
        }
        catch (Exception exception) {
            String[] stringArray = r;
            System.err.println("Failed to load " + string + ", using fallback font.");
            return new q_0(new Font("SansSerif", 0, (int)(f2 / 2.0f)), f2 / 2.0f);
        }
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
                    case 0 -> 30;
                    case 1 -> 84;
                    case 2 -> 89;
                    case 3 -> 83;
                    case 4 -> 4;
                    case 5 -> 15;
                    default -> 10;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0xA);
        }
        return cArray;
    }
}

