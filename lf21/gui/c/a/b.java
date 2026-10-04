/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c.a;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lf21.ClientBase;
import lf21.gui.c.a.e;
import lf21.gui.n;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.Setting;
import lf21.settings.impl.ModeSetting;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class b
extends ClientBase
implements e {
    private static final int a = new Color(255, 255, 255, 15).getRGB();
    private final Map<ModeSetting, Boolean> b = new HashMap<ModeSetting, Boolean>();
    private final Map<ModeSetting, Float> c = new HashMap<ModeSetting, Float>();
    private final Map<ModeSetting, Map<String, Float>> d = new HashMap<ModeSetting, Map<String, Float>>();

    @Override
    public int a(GuiGraphics guiGraphics, Setting<?> setting, int n2, int n3, int n4, int n5, int n6, float f2, float f3) {
        int n7;
        int n8;
        block9: {
            if (!(setting instanceof ModeSetting)) {
                return 0;
            }
            ModeSetting modeSetting = (ModeSetting)setting;
            this.a(modeSetting);
            String[] stringArray = (String[])Arrays.stream((Object[])modeSetting.getModes()).filter(object -> !Objects.equals(object, modeSetting.getValue())).toArray(String[]::new);
            float f4 = this.c.getOrDefault(modeSetting, Float.valueOf(0.0f)).floatValue();
            int n9 = Math.round(16.0f * f3);
            n8 = Math.round((float)(stringArray.length * n9) * f4);
            n7 = Math.round(24.0f * f3);
            try {
                int n10 = Math.round(75.0f * f3);
                int n11 = n2 + n4 - Math.round(80.0f * f3);
                int n12 = n3 + 1;
                RenderUtil.b(guiGraphics.pose(), (float)n11, (float)n12, (float)n10, (float)(n9 + n8), 4.0f * f3, this.a(a, f2));
                u_0 u_02 = t_0.l(14.0f * f3);
                float f5 = (float)n3 + (float)n7 / 2.0f - u_02.d().f() / 2.0f;
                int n13 = new Color(255, 255, 255, 100).getRGB();
                T.a(modeSetting.getName(), (float)n2, f5, u_02, this.a(-1, f2), this.a(n13, f2), 8.0f * f3);
                u_0 u_03 = t_0.l(14.0f * f3);
                String string = modeSetting.getValue() != null ? (String)modeSetting.getValue() : "None";
                String string2 = string;
                float f6 = y_0.a(string2, u_03);
                float f7 = (float)n11 + ((float)n10 - f6) / 2.0f;
                float f8 = (float)n12 + (float)n9 / 2.0f - u_03.d().f() / 2.0f + 2.0f * f3;
                T.a(string2, f7, f8, u_03, this.a(-3355444, f2), this.a(new Color(255, 255, 255, 150).getRGB(), f2), 10.0f * f3);
                float f9 = this.c.getOrDefault(modeSetting, Float.valueOf(0.0f)).floatValue();
                if (!((double)f9 > 0.01)) break block9;
                this.d.putIfAbsent(modeSetting, new HashMap());
                Map<String, Float> map = this.d.get(modeSetting);
                for (int i2 = 0; i2 < stringArray.length; ++i2) {
                    String string3 = stringArray[i2];
                    float f10 = y_0.a(string3, u_03);
                    float f11 = (float)n11 + ((float)n10 - f10) / 2.0f;
                    int n14 = n12 + n9 + i2 * n9;
                    float f12 = (float)n14 + (float)n9 / 2.0f - u_03.d().f() / 2.0f + 2.0f * f3;
                    boolean bl = this.a(n5, n6, n11, n14, n10, n9);
                    this.a(map, string3, bl);
                    float f13 = map.getOrDefault(string3, Float.valueOf(0.0f)).floatValue();
                    float f14 = Math.min(1.0f, f9);
                    if (!(f14 > 0.01f)) {
                        continue;
                    }
                    int n15 = (int)(255.0f * f14 * f2);
                    int n16 = this.a(-3355444, -1, f13);
                    int n17 = n15 << 24 | n16 & 0xFFFFFF;
                    int n18 = (int)this.a(80.0f, 150.0f, f13);
                    int n19 = new Color(255, 255, 255, (int)((float)n18 * f14 * f2)).getRGB();
                    float f15 = this.a(6.0f, 10.0f, f13);
                    T.a(string3, f11, f12, u_03, n17, n19, f15 * f3);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return n7 + n8;
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private void a(Map<String, Float> map, String string, boolean bl) {
        float f2 = map.getOrDefault(string, Float.valueOf(0.0f)).floatValue();
        float f3 = bl ? 1.0f : 0.0f;
        f2 = Math.abs(f2 - f3) > 0.01f ? g.b(f2, f3, 0.28f) : f3;
        map.put(string, Float.valueOf(f2));
    }

    private float a(float f2, float f3, float f4) {
        return f2 + (f3 - f2) * f4;
    }

    private int a(int n2, int n3, float f2) {
        int n4 = n2 >> 24 & 0xFF;
        int n5 = n2 >> 16 & 0xFF;
        int n6 = n2 >> 8 & 0xFF;
        int n7 = n2 & 0xFF;
        int n8 = n3 >> 24 & 0xFF;
        int n9 = n3 >> 16 & 0xFF;
        int n10 = n3 >> 8 & 0xFF;
        int n11 = n3 & 0xFF;
        int n12 = (int)((float)n4 + (float)(n8 - n4) * f2);
        int n13 = (int)((float)n5 + (float)(n9 - n5) * f2);
        int n14 = (int)((float)n6 + (float)(n10 - n6) * f2);
        int n15 = (int)((float)n7 + (float)(n11 - n7) * f2);
        return n12 << 24 | n13 << 16 | n14 << 8 | n15;
    }

    private void a(ModeSetting modeSetting) {
        boolean bl = this.b.getOrDefault(modeSetting, false);
        float f2 = this.c.getOrDefault(modeSetting, Float.valueOf(0.0f)).floatValue();
        if (bl) {
            this.c.put(modeSetting, Float.valueOf(g.a(f2, 1.0f, 0.08f)));
        } else {
            this.c.put(modeSetting, Float.valueOf(g.a(f2, 0.0f, 0.16f)));
        }
    }

    @Override
    public boolean a(Setting<?> setting, int n2, int n3, int n4, int n5, int n6, int n7, float f2) {
        if (!(setting instanceof ModeSetting)) {
            return false;
        }
        ModeSetting modeSetting = (ModeSetting)setting;
        int n8 = Math.round(16.0f * f2);
        int n9 = Math.round(75.0f * f2);
        int n10 = n2 + n4 - Math.round(80.0f * f2);
        int n11 = n3 - 2;
        boolean bl = this.b.getOrDefault(modeSetting, false);
        String[] stringArray = (String[])Arrays.stream((Object[])modeSetting.getModes()).filter(object -> !Objects.equals(object, modeSetting.getValue())).toArray(String[]::new);
        boolean bl2 = this.a(n5, n6, n10, n11, n9, n8);
        if (bl2 && n7 == 1) {
            this.b.put(modeSetting, !bl);
            return true;
        }
        if (bl) {
            int n12 = n11 + n8;
            for (int i2 = 0; i2 < stringArray.length; ++i2) {
                if (!this.a(n5, n6, n10, n12 + i2 * n8, n9, n8) || n7 != 0) continue;
                String string = stringArray[i2];
                modeSetting.setValue(string);
                this.b.put(modeSetting, false);
                n.a.a(modeSetting.getName() + " set to " + string);
                return true;
            }
            if (!bl2) {
                this.b.put(modeSetting, false);
            }
        }
        return false;
    }

    private boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
        return n2 >= n4 && n2 <= n4 + n6 && n3 >= n5 && n3 <= n5 + n7;
    }

    @Override
    public boolean a(Setting<?> setting) {
        return setting instanceof ModeSetting;
    }

    @Override
    public int a(Setting<?> setting, float f2) {
        if (!(setting instanceof ModeSetting)) {
            return Math.round(24.0f * f2);
        }
        ModeSetting modeSetting = (ModeSetting)setting;
        String[] stringArray = (String[])Arrays.stream((Object[])modeSetting.getModes()).filter(object -> !Objects.equals(object, modeSetting.getValue())).toArray(String[]::new);
        float f3 = this.c.getOrDefault(modeSetting, Float.valueOf(0.0f)).floatValue();
        int n2 = Math.round(16.0f * f2);
        int n3 = Math.round((float)(stringArray.length * n2) * f3);
        return Math.round(24.0f * f2) + n3;
    }

    @Override
    public void a(double d2, double d3, int n2) {
    }
}

