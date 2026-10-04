/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.a;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.List;
import lf21.gui.a.c;
import lf21.gui.a.d;
import lf21.render.v_0;
import lf21.settings.Setting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.utils.render.RenderUtil;
import lombok.Generated;

public class g
extends d {
    private final MultiSelectSetting d;
    private boolean e;

    public g(Setting<?> setting, c c2, int n2) {
        super(setting, c2, n2);
        this.d = (MultiSelectSetting)setting;
    }

    @Override
    public void a(PoseStack poseStack, int n2, int n3, float f2, float f3) {
        int n4 = this.b.b.b + this.b.c + this.b.b.f + this.c;
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
        int n15 = new Color(138, 180, 248, (int)(255.0f * f3)).getRGB();
        int n16 = n9;
        if (this.e) {
            n16 += this.a();
        }
        boolean bl = n2 >= n12 && n2 <= n12 + n13 && n3 >= n4 + n8 && n3 <= n4 + n8 + n9;
        Color color = bl && !this.e ? new Color(40, 40, 40, (int)(180.0f * f3)) : new Color(15, 15, 15, (int)(220.0f * f3));
        RenderUtil.a(poseStack, (float)n12, (float)(n4 + n8), (float)n13, (float)n16, color.getRGB());
        Color color2 = new Color(100, 100, 100, (int)(180.0f * f3));
        RenderUtil.a(poseStack, (float)(n5 + n10 + 2), (float)(n4 + n8), (float)(n11 - 2), (float)n9, color2.getRGB());
        RenderUtil.a(poseStack, (float)(n5 + n6 - n10 - n11), (float)(n4 + n8), (float)(n11 - 2), (float)n9, color2.getRGB());
        String string = this.d.getName();
        float f4 = v_0.a.b(string);
        float f5 = (float)n12 + ((float)n13 - f4) / 2.0f;
        float f6 = (float)(n4 + n8) + ((float)n9 - v_0.a.c()) / 2.0f - 1.0f;
        v_0.a.c(poseStack, string, f5, f6, n14);
        if (this.e) {
            int n17 = n4 + n8 + n9;
            List<String> list = this.d.getOptions();
            for (int i2 = 0; i2 < list.size(); ++i2) {
                String string2 = list.get(i2);
                float f7 = n17 + i2 * n9;
                boolean bl2 = n2 >= n12 && n2 <= n12 + n13 && (float)n3 >= f7 && (float)n3 < f7 + (float)n9;
                boolean bl3 = bl2;
                if (bl2) {
                    RenderUtil.a(poseStack, (float)n12, f7, (float)n13, (float)n9, new Color(0, 0, 0, 100).getRGB());
                }
                float f8 = v_0.a.b(string2);
                float f9 = (float)n12 + ((float)n13 - f8) / 2.0f;
                float f10 = f7 + ((float)n9 - v_0.a.c()) / 2.0f;
                boolean bl4 = this.d.isSelected(string2);
                v_0.a.c(poseStack, string2, f9, f10, bl4 ? n15 : n14);
            }
        }
    }

    @Override
    public void a(double d2, double d3, int n2) {
        if (this.b(d2, d3) && n2 == 1) {
            this.e = !this.e;
            this.b.b.c();
            return;
        }
        if (this.e) {
            int n3 = this.b.b.b + this.b.c + this.b.b.f + this.c;
            int n4 = this.b.b.f;
            int n5 = n3 + n4;
            int n6 = 4;
            int n7 = n4 - n6 * 2;
            int n8 = this.b.b.a;
            int n9 = this.b.b.e;
            int n10 = 8;
            int n11 = 4;
            int n12 = n8 + n10 + n11;
            int n13 = n9 - (n10 + n11) * 2;
            if (d2 >= (double)n12 && d2 <= (double)(n12 + n13) && d3 >= (double)n5) {
                int n14 = (int)((d3 - (double)n5) / (double)n7);
                List<String> list = this.d.getOptions();
                if (n14 >= 0 && n14 < list.size()) {
                    String string = list.get(n14);
                    if (this.d.isSelected(string)) {
                        ((List)this.d.getValue()).remove(string);
                    } else {
                        ((List)this.d.getValue()).add(string);
                    }
                }
            }
        }
    }

    public int a() {
        if (!this.e) {
            return 0;
        }
        int n2 = 4;
        int n3 = this.b.b.f - n2 * 2;
        return n3 * this.d.getOptions().size();
    }

    private boolean b(double d2, double d3) {
        int n2 = this.b.b.b + this.b.c + this.b.b.f + this.c;
        return d2 >= (double)this.b.b.a && d2 <= (double)(this.b.b.a + this.b.b.e) && d3 >= (double)n2 && d3 <= (double)(n2 + this.b.b.f);
    }

    @Override
    public boolean a(double d2, double d3) {
        if (this.b(d2, d3)) {
            return true;
        }
        if (this.e) {
            int n2 = this.b.b.b + this.b.c + this.b.b.f + this.c;
            int n3 = n2 + this.b.b.f;
            int n4 = this.a();
            int n5 = this.b.b.a;
            int n6 = this.b.b.e;
            int n7 = 8;
            int n8 = 4;
            int n9 = n5 + n7 + n8;
            int n10 = n6 - (n7 + n8) * 2;
            return d2 >= (double)n9 && d2 <= (double)(n9 + n10) && d3 >= (double)n3 && d3 <= (double)(n3 + n4);
        }
        return false;
    }

    @Generated
    public boolean b() {
        return this.e;
    }
}

