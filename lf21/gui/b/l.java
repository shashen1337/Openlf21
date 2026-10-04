/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.gui.b.d;
import lf21.gui.b.h;
import lf21.gui.f;
import lf21.render.v_0;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.e;
import lf21.utils.b.a;
import lf21.utils.misc.c;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class l
extends h<NumberSetting> {
    private final e i = new e();
    private boolean j;
    private boolean k;
    private boolean l;
    private static final String[] m;

    public l(d d2, NumberSetting numberSetting) {
        super(d2, numberSetting);
    }

    @Override
    public float f() {
        return 30.0f;
    }

    @Override
    public void a(lf21.gui.l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        float f4;
        this.k = lf21.utils.misc.c.a(n2, n3, this.a, this.b, 120.0f, this.f());
        float f5 = 108.0f;
        float f6 = 5.0f;
        float f7 = this.b + this.f() / 2.0f + (this.f() / 2.0f - f6) / 2.0f;
        this.h.a(((NumberSetting)this.g).getVisibility().displayable() ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.h.a();
        if (Mth.equal((float)(f2 *= this.h.e()), (float)0.0f)) {
            return;
        }
        float f8 = this.b + (this.f() / 2.0f - v_0.j.c()) / 2.0f + 1.0f;
        Object object = ((NumberSetting)this.g).getName();
        if (v_0.j.b((String)object) > 78.0f) {
            object = ((String)object).substring(0, 10);
            String[] stringArray = m;
            object = (String)object + "...";
            this.j = true;
        }
        v_0.j.a(poseStack, (String)object, (double)(this.a + 6.0f), (double)f8, lf21.utils.render.a.a(-1, f2 * 0.8f));
        String string = String.format("%.2f", Float.valueOf(((Number)((NumberSetting)this.g).getValue()).floatValue()));
        v_0.m.a(poseStack, string, (double)(this.a + 120.0f - v_0.m.b(string) - 6.0f), (double)f8, lf21.utils.render.a.a(-1, f2 * 0.92f));
        float f9 = ((NumberSetting)this.g).getMin().floatValue();
        float f10 = ((NumberSetting)this.g).getMax().floatValue();
        float f11 = f10 - f9;
        float f12 = f11 == 0.0f ? 0.0f : (((Number)((NumberSetting)this.g).getValue()).floatValue() - f9) / f11;
        f12 = Mth.clamp((float)f12, (float)0.0f, (float)1.0f);
        this.i.a((double)f12, 0.2, lf21.utils.b.f.c);
        this.i.a();
        if (this.l) {
            NumberSetting numberSetting = (NumberSetting)this.g;
            f4 = ((float)n2 - (this.a + 6.0f)) / f5;
            double d2 = numberSetting.getMin().floatValue() + (numberSetting.getMax().floatValue() - numberSetting.getMin().floatValue()) * f4;
            double d3 = numberSetting.getStep().floatValue();
            double d4 = numberSetting.getMin().doubleValue();
            double d5 = numberSetting.getMax().doubleValue();
            double d6 = lf21.utils.b.a.a(d2, d4, d5);
            double d7 = d4 + (double)Math.round((d6 - d4) / d3) * d3;
            d7 = lf21.utils.b.a.a(d7, d4, d5);
            numberSetting.setValue((double)Math.round(d7 * 1000.0) / 1000.0);
        }
        RenderUtil.b(poseStack, this.a + 6.0f, f7, f5, 5.0f, 2.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(60, 60, 60), f2));
        float f13 = this.i.e();
        f4 = 10.0f;
        float f14 = 5.0f + f4 * 2.0f;
        RenderUtil.a(poseStack, this.a + 6.0f - f4, f7 - f4, Math.max(f5 * f13 + f4 * 2.0f, f14), f14, 2.0f + f4 / 2.0f + 1.0f, f4, lf21.utils.render.a.a(-13768502, 0.26f * f2));
        RenderUtil.b(poseStack, this.a + 6.0f, f7, f5 * f13, 5.0f, 2.0f, lf21.utils.render.a.a(lf21.gui.b.d.h, f2));
        float f15 = Math.max(this.a + 6.0f + this.i.e() * f5 - 5.0f - 0.5f, this.a + 6.0f - 0.5f);
        RenderUtil.a(poseStack, f15 - f4, f7 - 0.5f - f4, 6.0f + f4 * 2.0f, 6.0f + f4 * 2.0f, 2.0f + f4 / 2.0f + 1.0f, f4, lf21.utils.render.a.a(-1, 0.36f * f2));
        RenderUtil.b(poseStack, f15, f7 - 0.5f, 6.0f, 6.0f, 2.9f, lf21.utils.render.a.a(-1, f2));
        if (this.k) {
            this.f.a(lf21.gui.f.a(((NumberSetting)this.g).getName()));
        }
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (!((NumberSetting)this.g).getVisibility().displayable()) {
            return false;
        }
        float f2 = 108.0f;
        float f3 = 5.0f;
        float f4 = this.b + this.f() / 2.0f + (this.f() / 2.0f - f3) / 2.0f;
        if (this.k && lf21.utils.misc.c.a((float)d2, (float)d3, this.a + 6.0f, f4, f2, 5.0f)) {
            this.l = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean b(double d2, double d3, int n2) {
        float f2 = 108.0f;
        float f3 = 5.0f;
        float f4 = this.b + this.f() / 2.0f + (this.f() / 2.0f - f3) / 2.0f;
        this.l = false;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "j m\u0017\u0003a q";
        int n3 = "j m\u0017\u0003a q".length();
        int n4 = 4;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.b.l.a(lf21.gui.b.l.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                m = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6D);
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
                    case 0 -> 79;
                    case 1 -> 14;
                    case 2 -> 95;
                    case 3 -> 113;
                    case 4 -> 103;
                    case 5 -> 63;
                    default -> 109;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

