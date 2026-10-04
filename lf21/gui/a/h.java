/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.a;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.gui.a.c;
import lf21.gui.a.d;
import lf21.render.v_0;
import lf21.settings.Setting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.b.a;
import lf21.utils.render.RenderUtil;

public class h
extends d {
    private boolean d;
    private final NumberSetting e;
    private static final String[] f;

    public h(Setting<?> setting, c c2, int n2) {
        super(setting, c2, n2);
        this.e = (NumberSetting)setting;
        this.d = false;
    }

    @Override
    public void a(PoseStack poseStack, int n2, int n3, float f2, float f3) {
        String string;
        int n4 = this.b.b.b + this.b.c + this.b.b.f + this.c;
        int n5 = this.b.b.a;
        int n6 = this.b.b.e;
        int n7 = this.b.b.f;
        int n8 = new Color(255, 255, 255, (int)(255.0f * f3)).getRGB();
        int n9 = new Color(138, 180, 248, (int)(255.0f * f3)).getRGB();
        int n10 = 8;
        int n11 = n5 + n10;
        String[] stringArray = f;
        int n12 = (int)v_0.a.b("00.00");
        int n13 = n6 - n10 * 2 - n12 + 20;
        if (this.d) {
            this.a((double)n2, n11, n13);
        }
        String string2 = this.e.getName();
        if (this.e.getStep().doubleValue() % 1.0 == 0.0) {
            stringArray = f;
            string = String.format(stringArray[2], ((Number)this.e.getValue()).intValue());
        } else {
            string = String.format(f[3], Float.valueOf(((Number)this.e.getValue()).floatValue()));
        }
        String string3 = string;
        float f4 = (float)n4 + ((float)n7 - v_0.a.c()) / 2.0f;
        v_0.a.c(poseStack, string2, n11, f4, n8);
        v_0.a.c(poseStack, string3, (float)(n5 + n6 - n10) - v_0.a.b(string3), f4, n8);
        float f5 = this.e.getMin().floatValue();
        float f6 = this.e.getMax().floatValue();
        float f7 = ((Number)this.e.getValue()).floatValue();
        float f8 = (f7 - f5) / (f6 - f5);
        int n14 = n4 + n7 / 2 + 10;
        int n15 = 2;
        RenderUtil.a(poseStack, (float)n11, (float)n14, (float)n13, (float)n15, new Color(10, 10, 10, 200).getRGB());
        RenderUtil.a(poseStack, (float)n11, (float)n14, (float)((int)((float)n13 * f8)), (float)n15, n9);
    }

    private void a(double d2, int n2, int n3) {
        float f2 = this.e.getMin().floatValue();
        float f3 = this.e.getMax().floatValue();
        float f4 = f3 - f2;
        double d3 = Math.max((double)n2, Math.min(d2, (double)(n2 + n3)));
        float f5 = f2 + f4 * (float)((d3 - (double)n2) / (double)n3);
        this.e.setValue(lf21.utils.b.a.b((double)f5, 2));
    }

    @Override
    public void a(double d2, double d3, int n2) {
        int n3 = this.b.b.a;
        int n4 = this.b.b.e;
        int n5 = this.b.b.b + this.b.c + this.b.b.f + this.c;
        int n6 = this.b.b.f;
        int n7 = 8;
        int n8 = n3 + n7;
        int n9 = (int)v_0.a.b("00.00");
        int n10 = n4 - n7 * 2 - n9 + 20;
        boolean bl = d2 >= (double)n8 && d2 <= (double)(n8 + n10) && d3 >= (double)n5 && d3 <= (double)(n5 + n6);
        boolean bl2 = bl;
        if (n2 == 0 && bl) {
            this.d = true;
            this.a(d2, n8, n10);
        }
    }

    @Override
    public void b(double d2, double d3, int n2) {
        if (n2 == 0) {
            this.d = false;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[4];
                var3_1 = 0;
                var2_2 = "FAuQL\u0005FAuQL";
                var4_3 = "FAuQL\u0005FAuQL".length();
                var1_4 = 5;
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
                    var5[var3_1++] = h.a(h.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "S\u0015\u0004S_i\u0007";
                    var4_3 = "S\u0015\u0004S_i\u0007".length();
                    var1_4 = 2;
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
                    var5[var3_1++] = h.a(h.a(v1));
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
        h.f = var5;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x37);
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
                    case 0 -> 118;
                    case 1 -> 113;
                    case 2 -> 91;
                    case 3 -> 97;
                    case 4 -> 124;
                    case 5 -> 66;
                    default -> 55;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

