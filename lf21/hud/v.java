/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.hud.F;
import lf21.hud.b_0;
import lf21.render.DrawContext;
import net.minecraft.client.gui.GuiGraphics;

public interface v {
    default public F d() {
        return new F(240.0f, 25.0f);
    }

    default public int a(int n2, float f2) {
        float f3 = Math.max(0.0f, Math.min(1.0f, f2));
        return n2 & 0xFFFFFF | (int)(255.0f * f3) << 24;
    }

    public void a(GuiGraphics var1, PoseStack var2, float var3, float var4, float var5, float var6, float var7);

    default public b_0 a() {
        return b_0.TOP;
    }

    default public boolean b() {
        return false;
    }

    default public boolean c() {
        return true;
    }

    default public void a(DrawContext drawContext, float f2, float f3, float f4, float f5, float f6) {
    }
}

