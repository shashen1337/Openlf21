/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c.a;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import lf21.ClientBase;
import lf21.gui.c.a.e;
import lf21.gui.n;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.settings.Setting;
import lf21.settings.impl.BooleanSetting;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class a
extends ClientBase
implements e {
    private static final int a;
    private static final int b;
    private final Map<BooleanSetting, Boolean> c = new HashMap<BooleanSetting, Boolean>();
    private final Map<BooleanSetting, Float> d = new HashMap<BooleanSetting, Float>();
    private final Map<BooleanSetting, Float> e = new HashMap<BooleanSetting, Float>();
    private static final String[] f;

    @Override
    public int a(GuiGraphics guiGraphics, Setting<?> setting, int n2, int n3, int n4, int n5, int n6, float f2, float f3) {
        int n7;
        if (!(setting instanceof BooleanSetting)) {
            return 0;
        }
        BooleanSetting booleanSetting = (BooleanSetting)setting;
        int n8 = Math.round(24.0f * f3);
        int n9 = n7 = Math.round(10.0f * f3);
        int n10 = Math.round(5.0f * f3);
        float f4 = this.d.getOrDefault(booleanSetting, Float.valueOf((Boolean)booleanSetting.getValue() != false ? 1.0f : 0.0f)).floatValue();
        float f5 = (Boolean)booleanSetting.getValue() != false ? 1.0f : 0.0f;
        f4 = Math.abs(f5 - f4) > 0.01f ? g.b(f4, f5, 0.25f) : f5;
        this.d.put(booleanSetting, Float.valueOf(f4));
        int n11 = n7 * 2;
        int n12 = n2 + n4 - n11 - n10;
        int n13 = n3 + (n8 - n9) / 2;
        this.a(booleanSetting, n12, n13, n5, n6, n7);
        float f6 = this.e.getOrDefault(booleanSetting, Float.valueOf(0.0f)).floatValue();
        float f7 = this.c.getOrDefault(booleanSetting, false) != false ? 1.0f : 0.0f;
        f6 = Math.abs(f7 - f6) > 0.01f ? g.b(f6, f7, 0.3f) : f7;
        this.e.put(booleanSetting, Float.valueOf(f6));
        u_0 u_02 = t_0.l(14.0f * f3);
        float f8 = (float)n3 + (float)n8 / 2.0f - u_02.d().f() / 2.0f;
        T.a(booleanSetting.getName(), (float)n2, f8, u_02, this.a(-1, f2), this.a(new Color(255, 255, 255, 120).getRGB(), f2), 6.0f * f3);
        this.a(guiGraphics, n12, n13, f4, f6, f2, f3);
        return n8;
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3, float f4, float f5) {
        int n4 = Math.round(10.0f * f5);
        int n5 = n4 * 2;
        int n6 = n4;
        int n7 = this.a(b, a, f2);
        if (f3 > 0.0f) {
            float f6 = 1.0f + 0.3f * f3;
            n7 = this.b(n7, f6);
        }
        if (f2 > 0.01f) {
            int n8 = new Color(76, 175, 80, (int)(70.0f * f2)).getRGB();
            RenderUtil.b(guiGraphics.pose(), (float)n2 - f5, (float)n3 - f5, (float)n5 + 2.0f * f5, (float)n6 + 2.0f * f5, (float)n6 / 2.0f, this.a(n8, f4));
        }
        RenderUtil.b(guiGraphics.pose(), (float)n2, (float)n3, (float)n5, (float)n6, (float)n6 / 2.0f, this.a(n7, f4));
        int n9 = Math.round(2.0f * f5);
        int n10 = n6 - n9 * 2;
        int n11 = n2 + n9 + Math.round((float)(n5 - n10 - n9 * 2) * f2);
        int n12 = n3 + n9;
        int n13 = -1;
        if (f3 > 0.0f) {
            int n14 = (int)(50.0f * f3);
            int n15 = n14 << 24 | 0xFFFFFF;
            RenderUtil.b(guiGraphics.pose(), (float)(n11 - 1), (float)(n12 - 1), (float)(n10 + 2), (float)(n10 + 2), (float)(n10 + 2) / 2.0f, this.a(n15, f4));
        }
        RenderUtil.b(guiGraphics.pose(), (float)n11, (float)n12, (float)n10, (float)n10, (float)n10 / 2.0f, this.a(n13, f4));
    }

    private void a(BooleanSetting booleanSetting, int n2, int n3, int n4, int n5, int n6) {
        boolean bl = n4 >= n2 && n4 <= n2 + n6 * 2 && n5 >= n3 && n5 <= n3 + n6;
        this.c.put(booleanSetting, bl);
    }

    private int b(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = n2 >> 16 & 0xFF;
        int n5 = n2 >> 8 & 0xFF;
        int n6 = n2 & 0xFF;
        n4 = Math.min(255, (int)((float)n4 * f2));
        n5 = Math.min(255, (int)((float)n5 * f2));
        n6 = Math.min(255, (int)((float)n6 * f2));
        return n3 << 24 | n4 << 16 | n5 << 8 | n6;
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

    @Override
    public boolean a(Setting<?> setting, int n2, int n3, int n4, int n5, int n6, int n7, float f2) {
        int n8;
        if (!(setting instanceof BooleanSetting)) {
            return false;
        }
        BooleanSetting booleanSetting = (BooleanSetting)setting;
        int n9 = Math.round(24.0f * f2);
        int n10 = n8 = Math.round(10.0f * f2);
        int n11 = Math.round(5.0f * f2);
        int n12 = n8 * 2;
        int n13 = n2 + n4 - n12 - n11;
        int n14 = n3 + (n9 - n10) / 2;
        if (n7 == 0 && n5 >= n13 && n5 <= n13 + n12 && n6 >= n14 && n6 <= n14 + n10) {
            String string;
            boolean bl = (Boolean)booleanSetting.getValue() == false;
            booleanSetting.setValue(bl);
            if (bl) {
                var19_18 = f;
                string = "On";
            } else {
                var19_18 = f;
                string = "Off";
            }
            String string2 = string;
            n.a.a(booleanSetting.getName() + " is " + string2);
            return true;
        }
        return false;
    }

    @Override
    public boolean a(Setting<?> setting) {
        return setting instanceof BooleanSetting;
    }

    @Override
    public int a(Setting<?> setting, float f2) {
        return Math.round(24.0f * f2);
    }

    @Override
    public void a(double d2, double d3, int n2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "\u0013-\u0004|*+\u0012\u0003\u0013%>";
        int n3 = "\u0013-\u0004|*+\u0012\u0003\u0013%>".length();
        int n4 = 2;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.c.a.a.b(lf21.gui.c.a.a.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                f = stringArray;
                a = new Color(76, 175, 80, 180).getRGB();
                b = new Color(158, 158, 158, 150).getRGB();
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x47);
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
                    case 0 -> 92;
                    case 1 -> 67;
                    case 2 -> 88;
                    case 3 -> 50;
                    case 4 -> 33;
                    case 5 -> 55;
                    default -> 71;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

