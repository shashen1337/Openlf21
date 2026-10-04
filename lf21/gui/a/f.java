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
import lf21.settings.impl.ModeSetting;
import lf21.utils.render.RenderUtil;
import lombok.Generated;

public class f
extends d {
    private final ModeSetting d;
    private boolean e = false;

    public f(Setting<?> setting, c c2, int n2) {
        super(setting, c2, n2);
        this.d = (ModeSetting)setting;
    }

    @Override
    public void a(PoseStack poseStack, int n2, int n3, float f2, float f3) {
        int n4 = this.b.b.b + this.b.c + this.b.b.f + this.c + 3;
        int n5 = this.b.b.a;
        int n6 = this.b.b.e;
        int n7 = this.b.b.f;
        int n8 = 4;
        int n9 = n7 - n8 * 2;
        int n10 = 8;
        int n11 = 4;
        int n12 = n5 + n10 + n11;
        int n13 = n6 - (n10 + n11) * 2;
        int n14 = new Color(255, 255, 255, (int)(255.0f * f3)).getRGB();
        boolean bl = n2 >= n12 && n2 <= n12 + n13 && n3 >= n4 + n8 && n3 <= n4 + n8 + n9;
        Color color = bl && !this.e ? new Color(45, 45, 45, (int)(180.0f * f3)) : new Color(25, 25, 25, (int)(150.0f * f3));
        RenderUtil.a(poseStack, (float)n12, (float)(n4 + n8), (float)n13, (float)n9, color.getRGB());
        Color color2 = new Color(100, 100, 100, (int)(180.0f * f3));
        RenderUtil.a(poseStack, (float)(n5 + n10 + 2), (float)(n4 + n8), (float)(n11 - 2), (float)n9, color2.getRGB());
        RenderUtil.a(poseStack, (float)(n5 + n6 - n10 - n11), (float)(n4 + n8), (float)(n11 - 2), (float)n9, color2.getRGB());
        String string = this.d.getName();
        float f4 = (float)(n4 + n8) + ((float)n9 - v_0.a.c()) / 2.0f - 1.5f;
        v_0.a.c(poseStack, string, n12 + n11, f4, n14);
        String string2 = (String)this.d.getValue();
        float f5 = (float)(n12 + n13) - v_0.a.b(string2) - (float)n11;
        v_0.a.c(poseStack, string2, f5, f4, new Color(138, 180, 248).getRGB());
        if (this.e) {
            int n15 = n4 + n8 + n9;
            int n16 = this.a();
            RenderUtil.a(poseStack, (float)n12, (float)n15, (float)n13, (float)n16, new Color(33, 33, 33, 150).getRGB());
            String[] stringArray = this.b();
            for (int i2 = 0; i2 < stringArray.length; ++i2) {
                String string3 = stringArray[i2];
                float f6 = n15 + i2 * n9;
                boolean bl2 = n2 >= n12 && n2 <= n12 + n13 && (float)n3 >= f6 && (float)n3 < f6 + (float)n9;
                boolean bl3 = bl2;
                if (bl2) {
                    RenderUtil.a(poseStack, (float)n12, f6, (float)n13, (float)n9, new Color(0, 0, 0, 100).getRGB());
                }
                float f7 = v_0.a.b(string3);
                float f8 = (float)n12 + ((float)n13 - f7) / 2.0f;
                float f9 = f6 + ((float)n9 - v_0.a.c()) / 2.0f;
                boolean bl4 = string3.equals(this.d.getValue());
                v_0.a.c(poseStack, string3, f8, f9, bl4 ? new Color(138, 180, 248).getRGB() : n14);
            }
        }
    }

    @Override
    public void a(double d2, double d3, int n2) {
        int n3;
        int n4;
        int n5 = this.b.b.b + this.b.c + this.b.b.f + this.c + 3;
        int n6 = this.b.b.f;
        int n7 = 4;
        int n8 = n6 - n7 * 2;
        int n9 = this.b.b.a;
        int n10 = this.b.b.e;
        int n11 = 8;
        int n12 = 4;
        int n13 = n9 + n11 + n12;
        int n14 = n10 - (n11 + n12) * 2;
        String[] stringArray = this.d.getModes();
        if (this.e) {
            n4 = n5 + n7 + n8;
            n3 = this.a();
            if (d2 >= (double)n13 && d2 <= (double)(n13 + n14) && d3 >= (double)n4 && d3 < (double)(n4 + n3)) {
                int n15 = (int)((d3 - (double)n4) / (double)n8);
                if (n15 >= 0 && n15 < stringArray.length) {
                    this.d.setValue(stringArray[n15]);
                    this.e = false;
                    this.b.b.c();
                }
                return;
            }
        }
        n4 = d2 >= (double)n13 && d2 <= (double)(n13 + n14) && d3 >= (double)(n5 + n7) && d3 <= (double)(n5 + n7 + n8) ? 1 : 0;
        n3 = n4;
        if (n4 != 0 && (n2 == 0 || n2 == 1)) {
            this.e = !this.e;
            this.b.b.c();
        }
    }

    @Override
    public boolean a(double d2, double d3) {
        int n2 = this.b.b.b + this.b.c + this.b.b.f + this.c;
        int n3 = this.b.b.a;
        int n4 = this.b.b.e;
        int n5 = this.b.b.f;
        boolean bl = d2 >= (double)n3 && d2 <= (double)(n3 + n4) && d3 >= (double)n2 && d3 <= (double)(n2 + n5);
        boolean bl2 = bl;
        if (bl) {
            return true;
        }
        if (this.e) {
            int n6 = n2 + n5;
            int n7 = this.a();
            return d2 >= (double)n3 && d2 <= (double)(n3 + n4) && d3 >= (double)n6 && d3 <= (double)(n6 + n7);
        }
        return false;
    }

    @Override
    public void b(double d2, double d3, int n2) {
        super.b(d2, d3, n2);
    }

    public int a() {
        int n2 = 4;
        int n3 = this.b.b.f - n2 * 2;
        return n3 * this.d.getModes().length;
    }

    public String[] b() {
        return this.d.getModes();
    }

    @Generated
    public boolean c() {
        return this.e;
    }
}

