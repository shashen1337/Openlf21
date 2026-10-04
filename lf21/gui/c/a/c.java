/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c.a;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf21.ClientBase;
import lf21.gui.c.a.e;
import lf21.gui.n;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.Setting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class c
extends ClientBase
implements e {
    private static final int a;
    private static final int b;
    private final Map<String, Float> c = new HashMap<String, Float>();
    private final Map<String, Float> d = new HashMap<String, Float>();
    private static final String[] e;

    @Override
    public int a(GuiGraphics guiGraphics, Setting<?> setting, int n2, int n3, int n4, int n5, int n6, float f2, float f3) {
        if (!(setting instanceof MultiSelectSetting)) {
            return 0;
        }
        MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
        u_0 u_02 = t_0.l(14.0f * f3);
        float f4 = (float)n3 + 20.0f * f3 / 2.0f - u_02.d().f() / 2.0f;
        T.a(multiSelectSetting.getName(), (float)n2, f4, u_02, this.a(-1, f2), this.a(new Color(255, 255, 255, 120).getRGB(), f2), 8.0f * f3);
        int n7 = n3 + Math.round(20.0f * f3);
        int n8 = Math.round(20.0f * f3);
        int n9 = Math.round(10.0f * f3);
        int n10 = Math.round(5.0f * f3);
        for (String string : multiSelectSetting.getOptions()) {
            int n11;
            boolean bl = multiSelectSetting.isSelected(string);
            boolean bl2 = n5 >= n2 && n5 <= n2 + n4 && n6 >= n7 && n6 <= n7 + n8;
            this.a(this.c, string, bl2, 0.28f);
            this.a(this.d, string, bl, 0.25f);
            float f5 = this.c.getOrDefault(string, Float.valueOf(0.0f)).floatValue();
            float f6 = this.d.getOrDefault(string, Float.valueOf(0.0f)).floatValue();
            int n12 = n2 + n4 - n9 - n10;
            int n13 = n7 + (n8 - n9) / 2;
            if (f6 > 0.01f) {
                n11 = this.a(b, f2 * f6);
                RenderUtil.b(guiGraphics.pose(), (float)n12 - f3, (float)n13 - f3, (float)n9 + 2.0f * f3, (float)n9 + 2.0f * f3, 2.0f * f3, n11);
            }
            RenderUtil.b(guiGraphics.pose(), (float)n12, (float)n13, (float)n9, (float)n9, 2.0f * f3, this.a(-5592406, f2));
            if ((double)f6 > 0.01) {
                float f7 = (1.0f - f6) * ((float)n9 / 2.0f);
                RenderUtil.b(guiGraphics.pose(), (float)n12 + f7, (float)n13 + f7, (float)n9 - f7 * 2.0f, (float)n9 - f7 * 2.0f, f3, this.a(a, f2));
            }
            n11 = bl ? -1 : -5592406;
            int n14 = this.a(n11, -1, f5);
            u_0 u_03 = t_0.l(13.0f * f3);
            float f8 = (float)n7 + (float)n8 / 2.0f - u_03.d().f() / 2.0f;
            if (f6 > 0.01f) {
                int n15 = this.a(b, f2 * f6);
                T.a(string, (float)(n2 + n10), f8, u_03, this.a(n14, f2), n15, 8.0f * f3 * f6);
            } else {
                y_0.a(string, (float)(n2 + n10), f8, u_03, this.a(n14, f2));
            }
            n7 += n8;
        }
        return (multiSelectSetting.getOptions().size() + 1) * n8;
    }

    private void a(Map<String, Float> map, String string, boolean bl, float f2) {
        float f3 = map.getOrDefault(string, Float.valueOf(0.0f)).floatValue();
        float f4 = bl ? 1.0f : 0.0f;
        f3 = Math.abs(f4 - f3) > 0.01f ? g.b(f3, f4, f2) : f4;
        map.put(string, Float.valueOf(f3));
    }

    @Override
    public boolean a(Setting<?> setting, int n2, int n3, int n4, int n5, int n6, int n7, float f2) {
        MultiSelectSetting multiSelectSetting;
        block10: {
            block9: {
                if (!(setting instanceof MultiSelectSetting)) break block9;
                multiSelectSetting = (MultiSelectSetting)setting;
                if (n7 == 0) break block10;
            }
            return false;
        }
        int n8 = n3 + Math.round(20.0f * f2);
        int n9 = Math.round(20.0f * f2);
        for (String string : multiSelectSetting.getOptions()) {
            if (n5 >= n2 && n5 <= n2 + n4 && n6 >= n8 && n6 <= n8 + n9) {
                String string2;
                if (multiSelectSetting.isSelected(string)) {
                    ((List)multiSelectSetting.getValue()).remove(string);
                } else {
                    ((List)multiSelectSetting.getValue()).add(string);
                }
                boolean bl = multiSelectSetting.isSelected(string);
                if (bl) {
                    String[] stringArray = e;
                    string2 = " enabled";
                } else {
                    string2 = " disabled";
                }
                n.a.a(string + string2);
                return true;
            }
            n8 += n9;
        }
        return false;
    }

    @Override
    public boolean a(Setting<?> setting) {
        return setting instanceof MultiSelectSetting;
    }

    @Override
    public int a(Setting<?> setting, float f2) {
        if (!(setting instanceof MultiSelectSetting)) {
            return 0;
        }
        MultiSelectSetting multiSelectSetting = (MultiSelectSetting)setting;
        return Math.round((float)((multiSelectSetting.getOptions().size() + 1) * 20) * f2);
    }

    @Override
    public void a(double d2, double d3, int n2) {
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private int a(int n2, int n3, float f2) {
        float f3 = 1.0f - f2;
        int n4 = n2 >> 24 & 0xFF;
        int n5 = n2 >> 16 & 0xFF;
        int n6 = n2 >> 8 & 0xFF;
        int n7 = n2 & 0xFF;
        int n8 = n3 >> 24 & 0xFF;
        int n9 = n3 >> 16 & 0xFF;
        int n10 = n3 >> 8 & 0xFF;
        int n11 = n3 & 0xFF;
        int n12 = (int)((float)n4 * f3 + (float)n8 * f2);
        int n13 = (int)((float)n5 * f3 + (float)n9 * f2);
        int n14 = (int)((float)n6 * f3 + (float)n10 * f2);
        int n15 = (int)((float)n7 * f3 + (float)n11 * f2);
        return n12 << 24 | n13 << 16 | n14 << 8 | n15;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "\u0016\u00174we>*S\u0017\b\u0016\u00163ef0#R";
        int n3 = "\u0016\u00174we>*S\u0017\b\u0016\u00163ef0#R".length();
        int n4 = 9;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.c.a.c.b(lf21.gui.c.a.c.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                e = stringArray;
                a = new Color(76, 175, 80).getRGB();
                b = new Color(76, 175, 80, 100).getRGB();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x46);
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
                    case 0 -> 54;
                    case 1 -> 115;
                    case 2 -> 93;
                    case 3 -> 4;
                    case 4 -> 4;
                    case 5 -> 92;
                    default -> 70;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

