/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lf21.ClientBase;
import lf21.LF21Client;
import lf21.gui.c.b;
import lf21.gui.n;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.render.T;
import lf21.render.ab;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class f
extends ClientBase {
    private static final int a;
    private final Map<Module, Float> b = new HashMap<Module, Float>();
    private Module c;
    private b d = lf21.gui.c.b.NONE;
    private float e = 0.0f;
    private Category f;
    private Category g;
    private List<Module> h;
    private List<Module> i;
    private float j = 1.0f;
    private float k = 0.0f;
    private float l = 0.0f;
    private float m = 0.0f;
    private boolean n = false;
    private float o = 0.0f;
    private float p = 0.0f;
    private float q = 0.0f;
    private long r = 0L;
    private String s = "";
    private List<Module> t;
    private static final String[] u;

    public void a(String string) {
        if (string == null) {
            this.s = "";
            this.t = null;
            return;
        }
        this.s = string;
        this.t = !string.isEmpty() ? LF21Client.instance.getModuleManager().getModules().stream().filter(module -> module.getName().toLowerCase().contains(string.toLowerCase())).sorted(Comparator.comparing(Module::getName)).collect(Collectors.toList()) : LF21Client.instance.getModuleManager().getModules().stream().sorted(Comparator.comparing(Module::getName)).collect(Collectors.toList());
        this.f = null;
        this.k = 0.0f;
        this.l = 0.0f;
        this.d = lf21.gui.c.b.NONE;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, Category category, float f2, float f3) {
        if (Math.abs(f2 - this.j) > 0.001f) {
            this.a(f2);
            this.j = f2;
        }
        float f4 = this.k = Math.abs(this.k - this.l) > 0.01f ? lf21.utils.b.g.b(this.k, this.l, 0.35f) : this.l;
        if (this.t != null) {
            this.a(guiGraphics, n2, n3, n4, n5, f2, f3);
            return;
        }
        if (this.f != category) {
            this.k = 0.0f;
            this.l = 0.0f;
            if (this.f != null) {
                this.g = this.f;
                this.i = this.h;
                this.d = lf21.gui.c.b.FADE_OUT;
                this.e = 0.0f;
            } else if (category != null) {
                this.d = lf21.gui.c.b.FADE_IN;
                this.e = 0.0f;
            }
            this.f = category;
            this.h = category == null ? null : LF21Client.instance.getModuleManager().getModules().stream().filter(module -> module.f() == category).sorted(Comparator.comparing(Module::getName)).collect(Collectors.toList());
            List list2 = this.h;
        }
        if (this.d != lf21.gui.c.b.NONE) {
            this.e = lf21.utils.b.g.b(this.e, 1.0f, 0.18f);
            if (this.e > 0.99f) {
                this.e = 1.0f;
                if (this.d == lf21.gui.c.b.FADE_OUT) {
                    this.g = null;
                    this.i = null;
                    if (this.f != null) {
                        this.d = lf21.gui.c.b.FADE_IN;
                        this.e = 0.0f;
                    } else {
                        this.d = lf21.gui.c.b.NONE;
                    }
                } else {
                    this.d = lf21.gui.c.b.NONE;
                }
            }
        }
        try {
            int n6 = (int)(160.0f * f2);
            int n7 = (int)(20.0f * f2);
            int n8 = (int)(20.0f * f2);
            int n9 = (int)(400.0f * f2);
            int n10 = n2 + n7 - (int)(8.0f * f2);
            int n11 = n3 + n8 + (int)(23.0f * f2);
            int n12 = n9 - 2 * n7 - (int)(20.0f * f2);
            RenderUtil.b(guiGraphics.pose(), (float)n10, (float)n11, (float)n6, (float)n12, 4.0f * f2, this.a(a, f3));
            Renderer.render(guiGraphics, drawContext -> {
                Category category = this.f;
                List<Module> list = this.h;
                float f4 = 0.0f;
                float f5 = f3;
                if (this.d == lf21.gui.c.b.FADE_OUT) {
                    category = this.g;
                    list = this.i;
                    f4 = this.e * 20.0f * f2;
                    f5 = (1.0f - this.e) * f3;
                } else if (this.d == lf21.gui.c.b.FADE_IN) {
                    category = this.f;
                    list = this.h;
                    f4 = (1.0f - this.e) * 20.0f * f2;
                    f5 = this.e * f3;
                }
                drawContext.d();
                drawContext.a(ab.a(n10, n11, n6, n12));
                if (category != null) {
                    drawContext.d();
                    drawContext.a(0.0f, f4);
                    this.a(category, list, n10, n11, n12, n4, n5, f5, this.d != lf21.gui.c.b.NONE, f2);
                    drawContext.e();
                }
                drawContext.e();
            });
            if (this.d != lf21.gui.c.b.NONE) return;
            this.a(guiGraphics, n10, n11, n12, f2, f3);
            return;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, float f2, float f3) {
        try {
            int n6 = (int)(160.0f * f2);
            int n7 = (int)(20.0f * f2);
            int n8 = (int)(20.0f * f2);
            int n9 = (int)(400.0f * f2);
            int n10 = n2 + n7 - (int)(8.0f * f2);
            int n11 = n3 + n8 + (int)(23.0f * f2);
            int n12 = n9 - 2 * n7 - (int)(20.0f * f2);
            RenderUtil.b(guiGraphics.pose(), (float)n10, (float)n11, (float)n6, (float)n12, 4.0f * f2, this.a(a, f3));
            Renderer.render(guiGraphics, drawContext -> this.a(null, this.t, n10, n11, n12, n4, n5, f3, false, f2));
            this.a(guiGraphics, n10, n11, n12, f2, f3);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, int n4, float f2, float f3) {
        float f4;
        float f5 = 30.0f * f2;
        float f6 = (float)n4 - f5;
        if (this.m <= f6) {
            f4 = 0.0f;
        } else {
            long l2 = System.currentTimeMillis() - this.r;
            if (this.n || l2 < 500L) {
                f4 = 1.0f;
            } else if (l2 < 1000L) {
                long l3 = l2 - 500L;
                f4 = 1.0f - (float)l3 / 500.0f;
            } else {
                f4 = 0.0f;
            }
        }
        float f7 = this.q = Math.abs(this.q - f4) > 0.01f ? lf21.utils.b.g.b(this.q, f4, 0.35f) : f4;
        if (this.q <= 0.01f) {
            return;
        }
        float f8 = this.m - f6;
        if (f8 <= 0.0f) {
            return;
        }
        float f9 = Math.max(20.0f * f2, f6 / this.m * f6);
        float f10 = (float)n3 + f5 + this.k / f8 * (f6 - f9);
        int n5 = n2 + (int)(160.0f * f2) - (int)(4.0f * f2) - 2;
        float f11 = 4.0f * f2;
        int n6 = new Color(1.0f, 1.0f, 1.0f, this.q * f3).getRGB();
        RenderUtil.b(guiGraphics.pose(), (float)n5, f10, f11, f9, f11 / 2.0f, n6);
    }

    private void a(Module module, int n2, int n3, int n4, int n5, float f2) {
        if (module.isEnabled()) {
            this.b.put(module, Float.valueOf(0.0f));
            return;
        }
        float f3 = this.b.getOrDefault(module, Float.valueOf(0.0f)).floatValue();
        boolean bl = this.b(module, n2, n3, n4, n5, f2);
        this.b.put(module, Float.valueOf(lf21.utils.b.g.a(f3, bl ? 1.0f : 0.0f, 0.12f)));
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
    }

    private void a(Category category, List<Module> list, int n2, int n3, int n4, int n5, int n6, float f2, boolean bl, float f3) {
        Object object;
        Object object2;
        String[] stringArray;
        float f4 = 30.0f * f3;
        int n7 = (int)(160.0f * f3);
        u_0 u_02 = t_0.k(20.0f * f3);
        if (category == null) {
            stringArray = u;
            object2 = "Search";
        } else {
            object2 = category.name().substring(0, 1).toUpperCase() + category.name().substring(1).toLowerCase();
        }
        String string = object2;
        y_0.a(string, (float)n2 + 10.0f * f3, (float)n3 + 12.0f * f3, u_02, this.a(-1, f2));
        u_0 u_03 = t_0.l(12.0f * f3);
        if (category == null) {
            stringArray = u;
            object = list.size() + " Results";
        } else {
            stringArray = u;
            object = "Sorting: A-Z";
        }
        String string2 = object;
        float f5 = y_0.a(string2, u_03);
        float f6 = (float)(n2 + n7) - f5 - 10.0f * f3;
        y_0.a(string2, f6, (float)n3 + 14.0f * f3, u_03, this.a(-5592406, f2));
        DrawContext drawContext = y_0.a();
        drawContext.d();
        drawContext.a(ab.a(n2, (float)n3 + f4, n7, (float)n4 - f4));
        int n8 = n3 + (int)f4 + (int)(8.0f * f3);
        if (list != null) {
            this.m = list.size() * Math.round(18.0f * f3);
            for (Module module : list) {
                char c2;
                float f7 = (float)n8 - this.k;
                if (!bl) {
                    this.a(module, n2, (int)f7, n5, n6, f3);
                }
                u_0 u_04 = module.isEnabled() ? t_0.k(16.0f * f3) : t_0.l(16.0f * f3);
                Object object3 = module.c();
                if (this.t != null) {
                    String string3 = module.f().name().substring(1).toLowerCase();
                    c2 = module.f().name().charAt(0);
                    String string4 = string3;
                    char c3 = c2;
                    stringArray = u;
                    object3 = (String)object3 + " (" + c3 + string4 + ")";
                }
                if (module.isEnabled()) {
                    int n9 = this.a(-1, f2);
                    c2 = this.a(new Color(255, 255, 255, 150).getRGB(), f2);
                    T.a((String)object3, (float)n2 + 10.0f * f3, f7, u_04, n9, c2, 8.0f * f3);
                    String string5 = module.g().a();
                    stringArray = u;
                    if (!string5.equalsIgnoreCase("None")) {
                        u_0 u_05 = t_0.j(16.0f * f3);
                        String string6 = "\ue312";
                        u_0 u_06 = t_0.l(16.0f * f3);
                        float f8 = y_0.a(string5, u_06);
                        float f9 = y_0.a(string6, u_05);
                        float f10 = 2.0f * f3;
                        float f11 = f8 + f9 + f10;
                        float f12 = (float)(n2 + n7) - f11 - 10.0f * f3;
                        T.a(string6, f12, f7 - (float)Math.round(f3), u_05, n9, c2, 8.0f * f3);
                        T.a(string5, f12 + f9 + f10, f7 - 2.0f, u_04, n9, c2, 8.0f * f3);
                    }
                } else {
                    float f13 = this.b.getOrDefault(module, Float.valueOf(0.0f)).floatValue();
                    c2 = '\u00aa';
                    int n10 = 170;
                    int n11 = 170;
                    int n12 = 204;
                    int n13 = 204;
                    int n14 = 204;
                    int n15 = (int)((float)c2 + (float)(n12 - c2) * f13);
                    int n16 = (int)((float)n10 + (float)(n13 - n10) * f13);
                    int n17 = (int)((float)n11 + (float)(n14 - n11) * f13);
                    int n18 = 0xFF000000 | n15 << 16 | n16 << 8 | n17;
                    int n19 = this.a(n18, f2);
                    y_0.a((String)object3, (float)n2 + 10.0f * f3, f7, u_04, n19);
                    String string7 = module.g().a();
                    if (!string7.equalsIgnoreCase("None")) {
                        int n20 = (int)(f2 * 0.39215687f * f13);
                        int n21 = this.a(-3355444, (float)n20 / 255.0f);
                        u_0 u_07 = t_0.j(16.0f * f3);
                        String string8 = "\ue312";
                        u_0 u_08 = t_0.l(16.0f * f3);
                        float f14 = y_0.a(string7, u_08);
                        float f15 = y_0.a(string8, u_07);
                        float f16 = 2.0f * f3;
                        float f17 = f14 + f15 + f16;
                        float f18 = (float)(n2 + n7) - f17 - 10.0f * f3;
                        T.a(string8, f18, f7 - (float)Math.round(f3), u_07, n19, n21, 5.0f * f3);
                        T.a(string7, f18 + f15 + f16, f7 - 2.0f, u_04, n19, n21, 5.0f * f3);
                    }
                }
                n8 += Math.round(18.0f * f3);
            }
        } else {
            this.m = 0.0f;
        }
        drawContext.e();
    }

    public boolean a(int n2, int n3, int n4, int n5, Category category, int n6, float f2) {
        List<Module> list = !this.s.isEmpty() ? this.t : this.h;
        List<Module> list2 = list;
        if (list == null || this.s.isEmpty() && this.d != lf21.gui.c.b.NONE) {
            return false;
        }
        int n7 = (int)(160.0f * f2);
        int n8 = (int)(20.0f * f2);
        int n9 = (int)(20.0f * f2);
        int n10 = (int)(400.0f * f2);
        int n11 = (int)(30.0f * f2);
        int n12 = n2 + n8 - (int)(8.0f * f2);
        int n13 = n3 + n9 + (int)(23.0f * f2);
        int n14 = n10 - 2 * n8 - (int)(20.0f * f2);
        float f3 = n14 - n11;
        if (this.m > f3) {
            float f4 = this.m - f3;
            float f5 = Math.max(20.0f * f2, f3 / this.m * f3);
            float f6 = (float)(n13 + n11) + this.k / f4 * (f3 - f5);
            float f7 = 4.0f * f2;
            int n15 = n12 + n7 - (int)f7 - 2;
            if (n4 >= n15 && (float)n4 <= (float)n15 + f7 && (float)n5 >= f6 && (float)n5 <= f6 + f5) {
                this.n = true;
                this.o = n5;
                this.p = this.k;
                this.r = System.currentTimeMillis();
                return true;
            }
        }
        if (!this.a(n2, n3, n4, n5, f2)) {
            return false;
        }
        int n16 = n13 + n11 + Math.round(8.0f * f2);
        for (Module module : list) {
            int n17;
            if (this.b(module, n12, n17 = (int)((float)n16 - this.k), n4, n5, f2)) {
                if (n6 == 0) {
                    String string;
                    module.toggle();
                    if (module.isEnabled()) {
                        var24_29 = u;
                        string = "On";
                    } else {
                        var24_29 = u;
                        string = "Off";
                    }
                    String string2 = string;
                    lf21.gui.n.a.a(module.c() + " Module " + string2);
                } else if (n6 == 1) {
                    this.c = module;
                } else if (n6 == 2) {
                    lf21.gui.n.a.a(module);
                }
                return true;
            }
            n16 += Math.round(18.0f * f2);
        }
        return false;
    }

    private boolean b(Module module, int n2, int n3, int n4, int n5, float f2) {
        int n6 = Math.round(18.0f * f2);
        int n7 = (int)(160.0f * f2);
        boolean bl = n4 >= n2 && n4 <= n2 + n7;
        boolean bl2 = n5 >= n3 - n6 / 2 && n5 <= n3 + n6 / 2;
        return bl && bl2;
    }

    public void a(double d2, float f2) {
        int n2 = (int)(400.0f * f2) - (int)(40.0f * f2) - (int)(20.0f * f2);
        float f3 = 30.0f * f2;
        float f4 = (float)n2 - f3;
        if (this.m > f4) {
            float f5 = this.m - f4;
            this.l -= (float)d2 * 18.0f * f2;
            this.l = Math.max(0.0f, Math.min(this.l, f5));
            this.r = System.currentTimeMillis();
        }
    }

    public void a(double d2, double d3, float f2) {
        if (this.n) {
            int n2 = (int)(400.0f * f2) - (int)(40.0f * f2) - (int)(20.0f * f2);
            float f3 = (float)n2 - 30.0f * f2;
            float f4 = this.m - f3;
            float f5 = Math.max(20.0f * f2, f3 / this.m * f3);
            float f6 = f3 - f5;
            if (f6 > 0.0f) {
                float f7 = (float)d3 - this.o;
                float f8 = f7 / f6 * f4;
                this.k = this.p + f8;
                this.l = this.k = Math.max(0.0f, Math.min(this.k, f4));
            }
            this.r = System.currentTimeMillis();
        }
    }

    public void a() {
        this.n = false;
        this.r = System.currentTimeMillis();
    }

    public boolean a(int n2, int n3, int n4, int n5, float f2) {
        int n6 = (int)(160.0f * f2);
        int n7 = (int)(20.0f * f2);
        int n8 = (int)(20.0f * f2);
        int n9 = (int)(400.0f * f2);
        int n10 = (int)(30.0f * f2);
        int n11 = n2 + n7 - (int)(8.0f * f2);
        int n12 = n3 + n8 + (int)(23.0f * f2);
        int n13 = n9 - 2 * n7 - (int)(20.0f * f2);
        return n4 >= n11 && n4 <= n11 + n6 && n5 >= n12 + n10 && n5 <= n12 + n13;
    }

    public Module b() {
        return this.c;
    }

    public void a(Module module) {
        this.c = module;
    }

    private void a(float f2) {
        if (this.j <= 0.0f) {
            return;
        }
        float f3 = f2 / this.j;
        this.k *= f3;
        this.l *= f3;
        int n2 = (int)(400.0f * f2) - (int)(40.0f * f2) - (int)(20.0f * f2);
        float f4 = 30.0f * f2;
        float f5 = (float)n2 - f4;
        List<Module> list = !this.s.isEmpty() ? this.t : this.h;
        List<Module> list2 = list;
        if (list != null) {
            this.m = list.size() * Math.round(18.0f * f2);
        }
        if (this.m > f5) {
            float f6 = this.m - f5;
            this.k = Math.max(0.0f, Math.min(this.k, f6));
            this.l = Math.max(0.0f, Math.min(this.l, f6));
        } else {
            this.k = 0.0f;
            this.l = 0.0f;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[9];
                var3_1 = 0;
                var2_2 = "\n\u0017?>Nn\u0018Y\u0002e+\u0004d*4(\u0004d*4(\u0003e#<\fy*(9Rl\u000b\u0010e\u001b`a\u0002\nm";
                var4_3 = "\n\u0017?>Nn\u0018Y\u0002e+\u0004d*4(\u0004d*4(\u0003e#<\fy*(9Rl\u000b\u0010e\u001b`a\u0002\nm".length();
                var1_4 = 8;
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
                    var5[var3_1++] = lf21.gui.c.f.b(lf21.gui.c.f.b(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "\n\b5)Nn\t\n\u0006y ;?Xj";
                    var4_3 = "\n\b5)Nn\t\n\u0006y ;?Xj".length();
                    var1_4 = 8;
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
                    var5[var3_1++] = lf21.gui.c.f.b(lf21.gui.c.f.b(v1));
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
        lf21.gui.c.f.u = var5;
        lf21.gui.c.f.a = new Color(255, 255, 255, 20).getRGB();
    }

    private static char[] b(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x6C);
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
                    case 0 -> 42;
                    case 1 -> 69;
                    case 2 -> 90;
                    case 3 -> 77;
                    case 4 -> 59;
                    case 5 -> 2;
                    default -> 108;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

