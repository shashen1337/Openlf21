/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c.a;

import java.awt.Color;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lf21.ClientBase;
import lf21.gui.c.a.e;
import lf21.gui.n;
import lf21.render.T;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.Setting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class d
extends ClientBase
implements e {
    private static NumberSetting a;
    private static String b;
    private static long c;
    private final Map<NumberSetting, Long> d = new HashMap<NumberSetting, Long>();
    private final Map<NumberSetting, Boolean> e = new HashMap<NumberSetting, Boolean>();
    private final Map<NumberSetting, Boolean> f = new HashMap<NumberSetting, Boolean>();
    private final Map<NumberSetting, Boolean> g = new HashMap<NumberSetting, Boolean>();
    private static final String[] h;

    @Override
    public int a(GuiGraphics guiGraphics, Setting<?> setting, int n2, int n3, int n4, int n5, int n6, float f2, float f3) {
        if (!(setting instanceof NumberSetting)) {
            return 0;
        }
        NumberSetting numberSetting = (NumberSetting)setting;
        boolean bl = numberSetting.equals(a);
        int n7 = Math.round(24.0f * f3);
        int n8 = Math.round(12.0f * f3);
        int n9 = Math.round(8.0f * f3);
        u_0 u_02 = t_0.k(14.0f * f3);
        String string = bl ? b : this.a(((Number)numberSetting.getValue()).doubleValue());
        String string2 = string;
        float f4 = y_0.a(string2, u_02);
        int n10 = n8 * 2 + (int)f4 + n9 * 2 - Math.round(2.0f * f3);
        int n11 = n2 + n4 - n10;
        int n12 = n7 - Math.round(14.0f * f3);
        float f5 = (float)n3 + (float)n7 / 2.0f;
        int n13 = Math.round(f5 - (float)n12 / 2.0f);
        int n14 = Math.round(16.0f * f3);
        int n15 = Math.round(4.0f * f3);
        int n16 = n11 - n14 - n15;
        int n17 = Math.round(f5 - (float)n14 / 2.0f);
        this.a(numberSetting, n5, n6, n11, n13, n10, n8, n12, n16, n17, n14);
        this.a(guiGraphics, numberSetting, n2, n3, n4, n11, n13, n10, n12, n16, n17, bl, f2, f3);
        return n7;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean a(Setting<?> setting, int n2, int n3, int n4, int n5, int n6, int n7, float f2) {
        int n8;
        int n9;
        int n10;
        NumberSetting numberSetting;
        block24: {
            int n11;
            int n12;
            block23: {
                if (!(setting instanceof NumberSetting)) return false;
                numberSetting = (NumberSetting)setting;
                if (n7 != 0) {
                    return false;
                }
                int n13 = Math.round(24.0f * f2);
                n10 = Math.round(12.0f * f2);
                int n14 = Math.round(8.0f * f2);
                u_0 u_02 = t_0.k(14.0f * f2);
                String string = this.a(((Number)numberSetting.getValue()).doubleValue());
                float f3 = y_0.a(string, u_02);
                n9 = n10 * 2 + (int)f3 + n14 * 2 - Math.round(2.0f * f2);
                n8 = n2 + n4 - n9;
                n12 = n13 - Math.round(14.0f * f2);
                float f4 = (float)n3 + (float)n13 / 2.0f;
                n11 = Math.round(f4 - (float)n12 / 2.0f);
                int n15 = Math.round(16.0f * f2);
                int n16 = Math.round(4.0f * f2);
                int n17 = n8 - n15 - n16;
                int n18 = Math.round(f4 - (float)n15 / 2.0f);
                if (n5 < n17 || n5 > n17 + n15) break block23;
                if (n6 < n18) break block23;
                if (n6 > n18 + n15) break block23;
                this.a(numberSetting);
                return true;
            }
            if (n5 < n8 || n5 > n8 + n9) return false;
            if (n6 < n11) return false;
            if (n6 > n11 + n12) return false;
            if (n5 >= n8 + n10) break block24;
            this.c(numberSetting);
            return true;
        }
        if (n5 <= n8 + n9 - n10) return true;
        this.b(numberSetting);
        return true;
    }

    public static void a() {
        lf21.gui.c.a.d.c();
    }

    public static boolean a(int n2, int n3, int n4) {
        block15: {
            block14: {
                if (a == null) {
                    return false;
                }
                if (n2 != 257 && n2 != 335) break block14;
                lf21.gui.c.a.d.b();
                return true;
            }
            if (n2 == 256) {
                lf21.gui.c.a.d.c();
                return true;
            }
            if (n2 != 259 || b.isEmpty()) break block15;
            b = b.substring(0, b.length() - 1);
            c = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public static boolean a(char c2) {
        block16: {
            block17: {
                block15: {
                    if (a == null) {
                        return false;
                    }
                    if (Character.isDigit(c2) || c2 == '.') break block15;
                    if (c2 != '-') break block16;
                    if (!b.isEmpty()) break block16;
                }
                if (c2 != '.') break block17;
                if (!b.contains(".")) break block17;
                return true;
            }
            b = b + c2;
            c = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    public void a(double d2, double d3) {
    }

    @Override
    public void a(double d2, double d3, int n2) {
    }

    private void a(NumberSetting numberSetting) {
        a = numberSetting;
        b = "";
        c = System.currentTimeMillis();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void b() {
        if (a == null || b.isEmpty()) {
            lf21.gui.c.a.d.c();
            return;
        }
        try {
            double d2 = Double.parseDouble(b);
            double d3 = a.getMin().doubleValue();
            double d4 = a.getMax().doubleValue();
            d2 = Math.max(d3, Math.min(d4, d2));
            lf21.gui.c.a.d.b(a, d2);
            String[] stringArray = h;
            n.a.a(a.getName() + " set to " + String.format(Locale.US, "%.1f", d2));
        }
        catch (NumberFormatException numberFormatException) {
            n.a.a("Invalid input, edit cancelled");
        }
        lf21.gui.c.a.d.c();
    }

    private static void c() {
        a = null;
        b = "";
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private void a(GuiGraphics guiGraphics, NumberSetting numberSetting, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, boolean bl, float f2, float f3) {
        String string;
        float f4;
        u_0 u_02;
        block10: {
            float f5 = 24.0f * f3;
            float f6 = (float)n3 + f5 / 2.0f;
            u_0 u_03 = t_0.l(14.0f * f3);
            u_02 = t_0.k(14.0f * f3);
            u_0 u_04 = t_0.k(12.0f * f3);
            float f7 = f6 - u_03.d().f() / 2.0f;
            T.a(numberSetting.getName(), (float)n2, f7, u_03, this.a(-1, f2), this.a(new Color(255, 255, 255, 120).getRGB(), f2), 8.0f * f3);
            this.a(guiGraphics, n9, n10, numberSetting, f2, f3);
            int n11 = Math.round(12.0f * f3);
            RenderUtil.b(guiGraphics.pose(), (float)n5, (float)n6, (float)n7, (float)n8, 5.0f * f3, this.a(0x50F5F5F5, f2));
            int n12 = n5 + n11;
            int n13 = n7 - n11 * 2;
            RenderUtil.b(guiGraphics.pose(), (float)n12, (float)n6, (float)n13, (float)n8, 0.0f, this.a(1086900424, f2));
            f4 = (float)n6 + (float)n8 / 2.0f;
            float f8 = f4 - u_04.d().f() / 2.0f;
            int n14 = this.f.getOrDefault(numberSetting, false) != false ? new Color(255, 255, 255).getRGB() : -1;
            int n15 = n14;
            y_0.a("-", (float)n5 + (float)n11 / 2.0f - y_0.a("-", u_04) / 2.0f, f8, u_04, this.a(n15, f2));
            int n16 = this.e.getOrDefault(numberSetting, false) != false ? new Color(255, 255, 255).getRGB() : -1;
            int n17 = n16;
            y_0.a("+", (float)(n5 + n7 - n11) + (float)n11 / 2.0f - y_0.a("+", u_04) / 2.0f, f8, u_04, this.a(n17, f2));
            String string2 = bl ? b : this.a(((Number)numberSetting.getValue()).doubleValue());
            string = string2;
            if (!bl || !string.isEmpty()) break block10;
            string = "0";
        }
        float f9 = y_0.a(string, u_02);
        float f10 = (float)n5 + (float)n7 / 2.0f - f9 / 2.0f;
        float f11 = f4 - u_02.d().f() / 2.0f;
        if (bl) {
            long l2 = System.currentTimeMillis();
            float f12 = (float)(l2 % 1000L) / 1000.0f;
            float f13 = (float)(Math.sin((double)f12 * Math.PI * 2.0) * 0.5 + 0.5);
            int n18 = (int)(255.0f * (0.6f + f13 * 0.4f) * f2);
            int n19 = n18 << 24 | 0xFFFFFF;
            y_0.a(string, f10, f11, u_02, n19);
            float f14 = f10 + f9 + 2.0f * f3;
            int n20 = (int)(255.0f * f13 * f2);
            int n21 = n20 << 24 | 0xFFFFFF;
            float f15 = u_02.d().f();
            RenderUtil.a(guiGraphics.pose(), f14, f11, Math.max(1.0f, (float)Math.round(f3)), f15, n21);
        } else {
            int n22 = new Color(255, 255, 255, 120).getRGB();
            T.a(string, f10, f11, u_02, this.a(-1, f2), this.a(n22, f2), 6.0f * f3);
        }
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, NumberSetting numberSetting, float f2, float f3) {
        boolean bl = this.g.getOrDefault(numberSetting, false);
        long l2 = this.d.getOrDefault(numberSetting, 0L);
        long l3 = System.currentTimeMillis() - l2;
        int n4 = Math.round(16.0f * f3);
        float f4 = Math.min(1.0f, (float)l3 / 200.0f);
        if (!bl) {
            f4 = 1.0f - f4;
        }
        int n5 = -5197648;
        int n6 = -1;
        int n7 = RenderUtil.a(n5, n6, f4);
        u_0 u_02 = t_0.j(n4);
        String string = "\ue3c9";
        float f5 = y_0.a(string, u_02);
        float f6 = (float)n2 + ((float)n4 - f5) / 2.0f;
        float f7 = (float)n3 + (float)n4 / 2.0f - u_02.d().f() / 2.0f;
        y_0.a(string, f6, f7, u_02, this.a(n7, f2));
    }

    private void b(NumberSetting numberSetting) {
        double d2 = ((Number)numberSetting.getValue()).doubleValue();
        double d3 = numberSetting.getStep().doubleValue();
        double d4 = numberSetting.getMax().doubleValue();
        double d5 = Math.min(d4, d2 + d3);
        this.a(numberSetting, d5);
        n.a.a(numberSetting.getName() + " set to " + this.a(d5));
    }

    private void c(NumberSetting numberSetting) {
        double d2 = ((Number)numberSetting.getValue()).doubleValue();
        double d3 = numberSetting.getStep().doubleValue();
        double d4 = numberSetting.getMin().doubleValue();
        double d5 = Math.max(d4, d2 - d3);
        this.a(numberSetting, d5);
        n.a.a(numberSetting.getName() + " set to " + this.a(d5));
    }

    private void a(NumberSetting numberSetting, double d2) {
        block7: {
            block9: {
                block8: {
                    block6: {
                        if (!(numberSetting.getValue() instanceof Integer)) break block6;
                        numberSetting.setValue((int)Math.round(d2));
                        break block7;
                    }
                    if (!(numberSetting.getValue() instanceof Long)) break block8;
                    numberSetting.setValue(Math.round(d2));
                    break block7;
                }
                if (!(numberSetting.getValue() instanceof Float)) break block9;
                numberSetting.setValue(Float.valueOf((float)d2));
                break block7;
            }
            numberSetting.setValue(d2);
        }
    }

    private static void b(NumberSetting numberSetting, double d2) {
        block7: {
            block9: {
                block8: {
                    block6: {
                        if (!(numberSetting.getValue() instanceof Integer)) break block6;
                        numberSetting.setValue((int)Math.round(d2));
                        break block7;
                    }
                    if (!(numberSetting.getValue() instanceof Long)) break block8;
                    numberSetting.setValue(Math.round(d2));
                    break block7;
                }
                if (!(numberSetting.getValue() instanceof Float)) break block9;
                numberSetting.setValue(Float.valueOf((float)d2));
                break block7;
            }
            numberSetting.setValue(d2);
        }
    }

    private void a(NumberSetting numberSetting, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
        boolean bl;
        block34: {
            block33: {
                boolean bl2;
                NumberSetting numberSetting2;
                Map<NumberSetting, Boolean> map;
                block32: {
                    block31: {
                        boolean bl3;
                        NumberSetting numberSetting3;
                        Map<NumberSetting, Boolean> map2;
                        boolean bl4;
                        block30: {
                            block29: {
                                boolean bl5;
                                block28: {
                                    block27: {
                                        if (n2 < n4 || n2 > n4 + n6) break block27;
                                        if (n3 < n5) break block27;
                                        if (n3 > n5 + n8) break block27;
                                        bl5 = true;
                                        break block28;
                                    }
                                    bl5 = false;
                                }
                                bl4 = bl5;
                                map2 = this.f;
                                numberSetting3 = numberSetting;
                                if (!bl4 || n2 >= n4 + n7) break block29;
                                bl3 = true;
                                break block30;
                            }
                            bl3 = false;
                        }
                        map2.put(numberSetting3, bl3);
                        map = this.e;
                        numberSetting2 = numberSetting;
                        if (!bl4 || n2 <= n4 + n6 - n7) break block31;
                        bl2 = true;
                        break block32;
                    }
                    bl2 = false;
                }
                map.put(numberSetting2, bl2);
                if (n2 < n9 || n2 > n9 + n11) break block33;
                if (n3 < n10) break block33;
                if (n3 > n10 + n11) break block33;
                bl = true;
                break block34;
            }
            bl = false;
        }
        boolean bl6 = bl;
        boolean bl7 = bl;
        if (bl6 != this.g.getOrDefault(numberSetting, false)) {
            this.g.put(numberSetting, bl6);
            this.d.put(numberSetting, System.currentTimeMillis());
        }
    }

    private String a(double d2) {
        return String.format(Locale.US, "%.1f", d2);
    }

    @Override
    public boolean a(Setting<?> setting) {
        return setting instanceof NumberSetting;
    }

    @Override
    public int a(Setting<?> setting, float f2) {
        return Math.round(24.0f * f2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[5];
                var3_1 = 0;
                var2_2 = "\u007fN\u001d|\bz\u0013InR-\tz\bz\u0013InR-\tz";
                var4_3 = "\u007fN\u001d|\bz\u0013InR-\tz\bz\u0013InR-\tz".length();
                var1_4 = 4;
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
                    var5[var3_1++] = lf21.gui.c.a.d.b(lf21.gui.c.a.d.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\u007fN\u001d|\u001d\u0013\u000eZ{\u001e0\u0002z\tBj\u0007-Jz\u0005Hs\u0006y\u0005;\u000eO\u007f\u001e5\u0003>";
                    var4_3 = "\u007fN\u001d|\u001d\u0013\u000eZ{\u001e0\u0002z\tBj\u0007-Jz\u0005Hs\u0006y\u0005;\u000eO\u007f\u001e5\u0003>".length();
                    var1_4 = 4;
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
                    var5[var3_1++] = lf21.gui.c.a.d.b(lf21.gui.c.a.d.a(v1));
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
        lf21.gui.c.a.d.h = var5;
        lf21.gui.c.a.d.b = "";
        lf21.gui.c.a.d.c = 0L;
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x66);
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
                    case 0 -> 90;
                    case 1 -> 96;
                    case 2 -> 44;
                    case 3 -> 26;
                    case 4 -> 114;
                    case 5 -> 89;
                    default -> 102;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

