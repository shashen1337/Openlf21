/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c.a;

import lf21.settings.Setting;
import net.minecraft.client.gui.GuiGraphics;

public interface e {
    public int a(GuiGraphics var1, Setting<?> var2, int var3, int var4, int var5, int var6, int var7, float var8, float var9);

    public boolean a(Setting<?> var1, int var2, int var3, int var4, int var5, int var6, int var7, float var8);

    public boolean a(Setting<?> var1);

    default public int a(Setting<?> setting, float f2) {
        return Math.round(20.0f * f2);
    }

    default public void a(double d2, double d3) {
    }

    public void a(double var1, double var3, int var5);
}

