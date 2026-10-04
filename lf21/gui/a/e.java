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
import lf21.settings.impl.BooleanSetting;
import lf21.utils.render.RenderUtil;

public class e
extends d {
    private final BooleanSetting d;
    private float e = 0.0f;
    private final float f = 0.2f;

    public e(Setting<?> setting, c c2, int n2) {
        super(setting, c2, n2);
        this.d = (BooleanSetting)setting;
        this.e = (Boolean)this.d.getValue() != false ? 1.0f : 0.0f;
    }

    @Override
    public void a(PoseStack poseStack, int n2, int n3, float f2, float f3) {
        float f4 = (Boolean)this.d.getValue() != false ? 1.0f : 0.0f;
        float f5 = f4;
        this.e = Math.abs(this.e - f4) > 0.01f ? (this.e = this.e + (f4 - this.e) * 0.2f) : f4;
        int n4 = this.b.b.b + this.b.c + this.b.b.f + this.c;
        int n5 = 4;
        int n6 = this.b.b.f - n5 * 2;
        int n7 = this.b.b.a;
        int n8 = this.b.b.e;
        int n9 = 8;
        String string = this.d.getName();
        float f6 = (float)(n4 + n5) + ((float)n6 - v_0.a.c()) / 2.0f;
        int n10 = new Color(255, 255, 255, (int)(255.0f * f3)).getRGB();
        v_0.a.c(poseStack, string, n7 + n9, f6, n10);
        int n11 = 18;
        int n12 = 8;
        int n13 = n7 + n8 - n9 - n11;
        int n14 = n4 + n5 + (n6 - n12) / 2 + 2;
        Color color = (Boolean)this.d.getValue() != false ? new Color(138, 180, 248, (int)(200.0f * f3)) : new Color(100, 100, 100, (int)(180.0f * f3));
        RenderUtil.a(poseStack, (float)n13, (float)n14, (float)n11, (float)n12, color.getRGB());
        float f7 = 7.0f;
        float f8 = n12 + 4;
        float f9 = (float)(n13 + 1) + ((float)n11 - f7 - 2.0f) * this.e;
        float f10 = n14 - 2;
        RenderUtil.a(poseStack, f9, f10, f7, f8, new Color(160, 195, 255, (int)(255.0f * f3)).getRGB());
    }

    @Override
    public void a(double d2, double d3, int n2) {
        if (this.a(d2, d3) && n2 == 0) {
            boolean bl = (Boolean)this.d.getValue();
            this.d.setValue(!bl);
            if (bl != (Boolean)this.d.getValue()) {
                this.b.b.c();
            }
        }
        super.a(d2, d3, n2);
    }

    @Override
    public void b(double d2, double d3, int n2) {
        super.b(d2, d3, n2);
    }
}

