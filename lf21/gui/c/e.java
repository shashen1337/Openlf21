/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.gui.n;
import lf21.modules.Module;
import lf21.modules.a;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class e
extends ClientBase {
    private static final Color a;
    private boolean b = false;
    private Module c = null;
    private float d = 0.0f;
    private long e = 0L;
    private static final String[] f;

    public void a(Module module) {
        this.c = module;
        this.b = true;
        this.e = System.currentTimeMillis();
    }

    public void a() {
        this.b = false;
        this.c = null;
    }

    public boolean b() {
        return this.b && this.d > 0.01f;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(GuiGraphics guiGraphics, int n2, int n3, float f2) {
        if (!this.b && this.d <= 0.005f) {
            return;
        }
        this.c();
        if (this.d <= 0.005f) {
            return;
        }
        try {
            this.a(guiGraphics, n2, n3);
            float f3 = 400.0f * f2;
            float f4 = 180.0f * f2;
            int n4 = (int)(((float)n2 - f3) / 2.0f);
            int n5 = (int)(((float)n3 - f4) / 2.0f);
            this.a(guiGraphics, n4, n5, f3, f4, f2);
            this.a(guiGraphics, n4, n5, f3, f2);
            return;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public boolean a(int n2, int n3, int n4) {
        if (!this.b) {
            return false;
        }
        if (n2 == 256) {
            if (this.c != null) {
                this.c.setKey(0);
                if (LF21Client.isReady()) {
                    LF21Client.instance.getConfigManager().saveAll();
                }
                String[] stringArray = f;
                n.a.a(this.c.c() + " keybind cleared");
            }
            this.a();
            return true;
        }
        if (this.c != null && n2 != -1) {
            this.c.setKey(n2);
            if (LF21Client.isReady()) {
                LF21Client.instance.getConfigManager().saveAll();
            }
            a a2 = new a(n2);
            String string = a2.a();
            n.a.a(this.c.c() + " bound to " + string.toUpperCase());
            this.a();
            return true;
        }
        return false;
    }

    private void c() {
        this.d = this.b ? g.a(this.d, 1.0f, 0.08f) : g.a(this.d, 0.0f, 0.08f);
    }

    private void d() {
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3) {
        Color color = new Color(a.getRed(), a.getGreen(), a.getBlue(), (int)((float)a.getAlpha() * this.d));
        RenderUtil.b(guiGraphics.pose(), 0.0f, 0.0f, (float)n2, (float)n3, 0.0f, color.getRGB());
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3, float f4) {
        T.a(guiGraphics.pose(), (float)n2, (float)n3, f2, f3, 12.0f * f4, this.d);
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3) {
        Renderer.a((DrawContext drawContext) -> {
            int n4;
            float f4;
            float f5;
            float f6;
            Object object;
            u_0 u_02;
            int n5 = (int)(255.0f * this.d);
            u_0 u_03 = t_0.k(24.0f * f3);
            String[] stringArray = f;
            String string = "KEYBIND";
            float f7 = y_0.a(string, u_03);
            float f8 = (float)n2 + (f2 - f7) / 2.0f;
            float f9 = (float)n3 + 45.0f * f3;
            int n6 = n5 << 24 | 0xFFFFFF;
            int n7 = n5 << 24 | 0xFFFFFF;
            T.a(string, f8, f9, u_03, n6, n7, 10.0f * f3);
            if (this.c != null) {
                u_02 = t_0.l(18.0f * f3);
                object = "Module: " + this.c.c();
                f6 = y_0.a((String)object, u_02);
                f5 = (float)n2 + (f2 - f6) / 2.0f;
                f4 = (float)n3 + 75.0f * f3;
                n4 = n5 << 24 | 0xFFFFFF;
                y_0.a((String)object, f5, f4, u_02, n4);
            }
            u_02 = t_0.l(16.0f * f3);
            stringArray = f;
            object = "Press any key to bind";
            f6 = y_0.a((String)object, u_02);
            f5 = (float)n2 + (f2 - f6) / 2.0f;
            f4 = (float)n3 + 105.0f * f3;
            n4 = n5 << 24 | 0xCCCCCC;
            y_0.a((String)object, f5, f4, u_02, n4);
            this.a(n2, (int)((float)n3 + 125.0f * f3), (int)f2, n5, f3);
            u_0 u_04 = t_0.l(14.0f * f3);
            String string2 = "Press ESC to cancel";
            float f10 = y_0.a(string2, u_04);
            float f11 = (float)n2 + (f2 - f10) / 2.0f;
            float f12 = (float)n3 + 155.0f * f3;
            int n8 = n5 << 24 | 0xCCCCCC;
            y_0.a(string2, f11, f12, u_04, n8);
        });
    }

    private void a(int n2, int n3, int n4, int n5, float f2) {
        u_0 u_02 = t_0.k(20.0f * f2);
        String string = "\u2022";
        float f3 = y_0.a(string, u_02);
        float f4 = f3 * 3.0f + 20.0f * f2;
        float f5 = (float)n2 + ((float)n4 - f4) / 2.0f;
        int n6 = n5 << 24 | 0xFFFFFF;
        long l2 = System.currentTimeMillis();
        long l3 = l2 - this.e;
        long l4 = l3 % 1400L;
        for (int i2 = 0; i2 < 3; ++i2) {
            float f6;
            float f7 = f5 + (float)i2 * (f3 + 10.0f * f2);
            long l5 = (long)i2 * 150L;
            long l6 = l5 + 300L;
            float f8 = 0.0f;
            if (l4 >= l5 && l4 <= l6) {
                f6 = (float)(l4 - l5) / 300.0f;
                float f9 = f6 * (float)Math.PI;
                f8 = (float)(Math.sin(f9) * 6.0 * (double)f2);
            }
            f6 = (float)n3 - f8;
            y_0.a(string, f7, f6, u_02, n6);
        }
    }

    public Module e() {
        return this.c;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[6];
                var3_1 = 0;
                var2_2 = "|ApDskt\bzkMsV@\n\u0017\n\u0017fFsTA\u0010Ck\t\u0013gvLuI\u0005udG\trU\u0005SVjJcV";
                var4_3 = "|ApDskt\bzkMsV@\n\u0017\n\u0017fFsTA\u0010Ck\t\u0013gvLuI\u0005udG\trU\u0005SVjJcV".length();
                var1_4 = 7;
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
                    var5[var3_1++] = lf21.gui.c.e.b(lf21.gui.c.e.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u0017oL\u007fXL^S$Jj_DBR`\u0015gvLuI\u0005QY}\tm_\\\u0010Ck\tdSKT";
                    var4_3 = "\u0017oL\u007fXL^S$Jj_DBR`\u0015gvLuI\u0005QY}\tm_\\\u0010Ck\tdSKT".length();
                    var1_4 = 16;
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
                    var5[var3_1++] = lf21.gui.c.e.b(lf21.gui.c.e.a(v1));
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
        lf21.gui.c.e.f = var5;
        lf21.gui.c.e.a = new Color(124, 124, 124, 13);
        new Color(255, 255, 255, 40);
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x30);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String b(char[] cArray) {
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
                    case 0 -> 55;
                    case 1 -> 4;
                    case 2 -> 41;
                    case 3 -> 6;
                    case 4 -> 58;
                    case 5 -> 37;
                    default -> 48;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

