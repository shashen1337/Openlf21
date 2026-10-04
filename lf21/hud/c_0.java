/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.ClientBase;
import lf21.hud.F;
import lf21.hud.b_0;
import lf21.hud.v;
import lf21.modules.impl.world.AutoPlay;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.G;
import lf21.render.h_0;
import lf21.render.i_0;
import lf21.render.t_0;
import lf21.render.u_0;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

/*
 * Renamed from lf21.hud.c
 */
public class c_0
extends ClientBase
implements v {
    private long c = -1L;
    private static final String[] e;
    private long d = -1L;
    private float b = 0.0f;
    private static final u_0 a;

    @Override
    public b_0 a() {
        return b_0.CENTER;
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
                    case 0 -> 32;
                    case 1 -> 60;
                    case 2 -> 89;
                    case 3 -> 112;
                    case 4 -> 90;
                    case 5 -> 26;
                    default -> 21;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(DrawContext drawContext, float f2, float f3, float f4, float f5, float f6) {
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        long l2 = 0L;
        if (mc == null) return;
        if (c_0.mc.player == null) return;
        if (f6 <= 0.01f) return;
        if (AutoPlay.j == null) {
            return;
        }
        long l3 = System.currentTimeMillis();
        if (!AutoPlay.j.m) {
            if (this.d == -1L) {
                this.d = l3;
            }
        } else {
            this.d = -1L;
        }
        if (this.c == -1L) {
            this.c = l3;
        }
        long l4 = l3 - this.c;
        this.c = l3;
        long l5 = AutoPlay.j.l;
        double d2 = ((Number)AutoPlay.j.a().getValue()).doubleValue();
        double d3 = d2 * 1000.0;
        long l6 = System.currentTimeMillis() - l5;
        float f14 = d3 > 0.0 ? (float)((double)l6 / d3) : 1.0f;
        f14 = Mth.clamp((float)f14, (float)0.0f, (float)1.0f);
        float f15 = Mth.clamp((float)((float)l4 / 200.0f), (float)0.0f, (float)1.0f);
        this.b = Mth.lerp((float)f15, (float)this.b, (float)f14);
        if (Math.abs(this.b - f14) < 0.01f) {
            this.b = f14;
        }
        boolean bl = false;
        if (l5 > 0L) {
            long l7 = System.currentTimeMillis() - l5;
            if (d3 <= 0.0 || (double)l7 >= d3) {
                bl = true;
                l2 = d3 > 0.0 ? l7 - (long)d3 : l7;
            }
        }
        float f16 = f3 + f5 / 2.0f;
        float f17 = f5 - 12.0f;
        float f18 = f2 + 18.0f;
        float f19 = f3 + 6.0f;
        float f20 = f18 + f17 / 2.0f;
        float f21 = f19 + f17 / 2.0f;
        float f22 = f17 / 2.0f - 2.0f;
        try (Object object = new D();
             G g2 = new G();){
            float f23;
            ((D)object).b(2.0f);
            ((D)object).a(h_0.STROKE);
            ((D)object).a(i_0.ROUND);
            ((D)object).a(this.a(Color.WHITE.getRGB(), f6));
            if (this.b > 0.001f) {
                f12 = 360.0f * this.b;
                f23 = f20 - f22;
                f11 = f21 - f22;
                f10 = f20 + f22;
                f9 = f21 + f22;
                drawContext.a(f23, f11, f10, f9, -90.0f, f12, false, (D)object);
            }
            if (bl) {
                g2.c();
                f12 = Mth.clamp((float)((float)l2 / 400.0f), (float)0.0f, (float)1.0f);
                f23 = f20 - f22 * 0.4f;
                f11 = f21;
                f10 = f20 - f22 * 0.15f;
                f9 = f21 + f22 * 0.3f;
                f8 = f20 + f22 * 0.4f;
                f7 = f21 - f22 * 0.3f;
                float f24 = (float)Math.hypot(f10 - f23, f9 - f11);
                float f25 = (float)Math.hypot(f8 - f10, f7 - f9);
                float f26 = f24 + f25;
                float f27 = f26 * f12;
                g2.a(f23, f11);
                if (f27 <= f24) {
                    float f28 = f27 / f24;
                    g2.b(Mth.lerp((float)f28, (float)f23, (float)f10), Mth.lerp((float)f28, (float)f11, (float)f9));
                } else {
                    g2.b(f10, f9);
                    float f29 = (f27 - f24) / f25;
                    g2.b(Mth.lerp((float)f29, (float)f10, (float)f8), Mth.lerp((float)f29, (float)f9, (float)f7));
                }
                drawContext.a(g2, (D)object);
            }
        }
        object = bl ? "Done!" : "Sending you to next game...";
        float f30 = f17;
        f12 = f18 + f30 + 8.0f;
        try (D d4 = new D();){
            d4.a(this.a(Color.WHITE.getRGB(), f6));
            f11 = f16 - a.d().f() / 2.0f + 8.0f;
            drawContext.a((String)object, f12, f11, a, d4);
            f10 = 2.5f;
            f9 = f11 + a.d().d() + 8.0f;
            f8 = f2 + f4 - f12 - 18.0f;
            d4.a(h_0.FILL);
            d4.a(this.a(new Color(0, 0, 0, 40).getRGB(), f6));
            if (!(this.b > 0.0f)) return;
            d4.a(this.a(Color.WHITE.getRGB(), f6));
            f7 = f8 * this.b;
            return;
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
                var2_2 = "sY7\u00143tr\u0000E6\u0005znz\u0000R<\b.:rAQ<^t4\u0005dS7\u0015{";
                var4_3 = "sY7\u00143tr\u0000E6\u0005znz\u0000R<\b.:rAQ<^t4\u0005dS7\u0015{".length();
                var1_4 = 27;
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
                    var5[var3_1++] = c_0.b(c_0.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "dS7\u0015{\u001bsY7\u00143tr\u0000E6\u0005znz\u0000R<\b.:rAQ<^t4";
                    var4_3 = "dS7\u0015{\u001bsY7\u00143tr\u0000E6\u0005znz\u0000R<\b.:rAQ<^t4".length();
                    var1_4 = 5;
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
                    var5[var3_1++] = c_0.b(c_0.a(v1));
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
        c_0.e = var5;
        c_0.a = t_0.e(24.0f);
    }

    @Override
    public F d() {
        String string;
        long l2;
        if (!this.c()) {
            return new F(0.0f, 40.0f);
        }
        long l3 = AutoPlay.j.l;
        double d2 = ((Number)AutoPlay.j.a().getValue()).doubleValue() * 1000.0;
        long l4 = System.currentTimeMillis() - l3;
        boolean bl = d2 <= 0.0 || (double)l4 >= d2;
        long l5 = l2 = bl ? l4 - (long)d2 : -1L;
        if (bl) {
            String[] stringArray = e;
            string = "Done!";
        } else {
            string = "Sending you to next game...";
        }
        String string2 = string;
        float f2 = a.a(string2).a();
        float f3 = 28.0f;
        float f4 = 18.0f + f3 + 8.0f + f2 + 18.0f;
        float f5 = 30.0f;
        float f6 = f4 - 30.0f;
        float f7 = 60.0f;
        f6 = Math.max(f6, f7);
        float f8 = 40.0f;
        if (bl) {
            float f9 = Mth.clamp((float)((float)l2 / 400.0f), (float)0.0f, (float)1.0f);
            f8 = Mth.lerp((float)f9, (float)40.0f, (float)25.0f);
        }
        return new F(f6, f8);
    }

    @Override
    public void a(GuiGraphics guiGraphics, PoseStack poseStack, float f2, float f3, float f4, float f5, float f6) {
    }

    @Override
    public boolean c() {
        if (AutoPlay.j == null || !AutoPlay.j.isEnabled()) {
            return false;
        }
        if (AutoPlay.j.m) {
            return true;
        }
        long l2 = AutoPlay.j.l;
        if (l2 <= 0L) {
            return false;
        }
        double d2 = ((Number)AutoPlay.j.a().getValue()).doubleValue() * 1000.0;
        long l3 = System.currentTimeMillis() - l2;
        if ((double)l3 >= d2) {
            long l4 = l3 - (long)d2;
            return l4 < 500L;
        }
        return false;
    }

    @Override
    public boolean b() {
        return true;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x15);
        }
        return cArray;
    }
}

