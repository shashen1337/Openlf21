/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.c;

import java.awt.Color;
import java.util.List;
import lf21.ClientBase;
import lf21.gui.c.a.e;
import lf21.gui.c.a.f;
import lf21.gui.c.c;
import lf21.gui.n;
import lf21.modules.Module;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.render.S;
import lf21.render.T;
import lf21.render.ab;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.settings.Setting;
import lf21.utils.b.g;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.gui.GuiGraphics;

public class i
extends ClientBase {
    private static final int a;
    private static final int b;
    private static final int c;
    private Module d;
    private boolean e = false;
    private float f = 0.0f;
    private float g = 0.0f;
    private float h = 0.0f;
    private float i = 0.0f;
    private float j = 0.0f;
    private boolean k = false;
    private float l = 0.0f;
    private float m = 0.0f;
    private float n = 0.0f;
    private long o = 0L;
    private Module p;
    private c q = lf21.gui.c.c.NONE;
    private float r = 0.0f;
    private float s = 1.0f;
    private static final String[] t;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(GuiGraphics guiGraphics, int n2, int n3, int n4, int n5, Module module, float f2, float f3) {
        if (Math.abs(f2 - this.s) > 0.001f) {
            this.a(f2);
            this.s = f2;
        }
        if (this.d != module) {
            if (this.d != null) {
                this.p = this.d;
                this.q = lf21.gui.c.c.FADE_OUT;
                this.r = 0.0f;
            } else if (module != null) {
                this.q = lf21.gui.c.c.FADE_IN;
                this.r = 0.0f;
                this.p = null;
            }
            this.d = module;
            this.h = 0.0f;
            this.i = 0.0f;
        }
        if (this.q != lf21.gui.c.c.NONE) {
            this.r = lf21.utils.b.g.b(this.r, 1.0f, 0.22f);
            if (this.r > 0.99f) {
                this.r = 1.0f;
                if (this.q == lf21.gui.c.c.FADE_OUT) {
                    this.p = null;
                    if (this.d != null) {
                        this.q = lf21.gui.c.c.FADE_IN;
                        this.r = 0.0f;
                    } else {
                        this.q = lf21.gui.c.c.NONE;
                    }
                } else {
                    this.q = lf21.gui.c.c.NONE;
                }
            }
        }
        if (this.d == null && this.q == lf21.gui.c.c.NONE) {
            return;
        }
        this.h = Math.abs(this.h - this.i) > 0.01f ? lf21.utils.b.g.b(this.h, this.i, 0.35f) : this.i;
        float f4 = this.d != null && this.d.isEnabled() ? 1.0f : 0.0f;
        this.f = Math.abs(f4 - this.f) > 0.01f ? lf21.utils.b.g.b(this.f, f4, 0.28f) : f4;
        try {
            int n6 = (int)(405.0f * f2);
            int n7 = (int)(20.0f * f2);
            int n8 = (int)(20.0f * f2);
            int n9 = (int)(400.0f * f2);
            int n10 = (int)(30.0f * f2);
            int n11 = (int)(10.0f * f2);
            int n12 = n2 + (int)(600.0f * f2) - n6 - n7 + (int)(8.0f * f2);
            int n13 = n3 + n8 + (int)(23.0f * f2);
            int n14 = n9 - 2 * n7 - (int)(20.0f * f2);
            int n15 = (int)(12.0f * f2);
            int n16 = (int)(15.0f * f2);
            int n17 = n12 + n6 - n15 * 2 - n16;
            int n18 = n13 + (n10 - n15) / 2;
            this.a(n17, n18, n4, n5, n15);
            float f5 = this.e ? 1.0f : 0.0f;
            this.g = Math.abs(f5 - this.g) > 0.01f ? lf21.utils.b.g.b(this.g, f5, 0.35f) : f5;
            RenderUtil.b(guiGraphics.pose(), (float)n12, (float)n13, (float)n6, (float)n14, 4.0f * f2, this.a(a, f3));
            this.a(guiGraphics, n17, n18, this.f, this.g, this.d != null && this.d.isEnabled(), f2, f3);
            int n19 = n12;
            int n20 = n13 + n10;
            int n21 = n6;
            int n22 = n14 - n10;
            S.a(false);
            RenderUtil.b(guiGraphics.pose(), (float)n19, (float)n20, (float)n21, (float)n22, 4.0f * f2, Color.WHITE.getRGB());
            S.b(true);
            Renderer.a((DrawContext drawContext) -> {
                Module module;
                float f4;
                this.a(this.d, f2);
                drawContext.d();
                drawContext.a(ab.a(n12, n13, n6, n14));
                Module module2 = this.q == lf21.gui.c.c.FADE_OUT ? this.p : this.d;
                float f5 = f4 = this.q == lf21.gui.c.c.FADE_OUT ? (1.0f - this.r) * f3 : f3;
                if (module2 != null) {
                    u_0 u_02 = t_0.k(20.0f * f2);
                    String string = module2.c();
                    if (module2.isEnabled()) {
                        int n9 = this.a(new Color(255, 255, 255, 150).getRGB(), f4);
                        T.a(string, (float)n12 + 10.0f * f2, (float)n13 + 12.0f * f2, u_02, this.a(-1, f4), n9, 12.0f * f2);
                    } else {
                        y_0.a(string, (float)n12 + 10.0f * f2, (float)n13 + 12.0f * f2, u_02, this.a(-1, f4));
                    }
                }
                drawContext.e();
                drawContext.d();
                drawContext.a(ab.a(n12, n13 + n10, n6, n14 - n10));
                float f6 = 0.0f;
                float f7 = f3;
                if (this.q == lf21.gui.c.c.FADE_OUT && this.p != null) {
                    module = this.p;
                    f6 = this.r * 30.0f * f2;
                    f7 = (1.0f - this.r) * f3;
                } else if (this.q == lf21.gui.c.c.FADE_IN && this.d != null) {
                    module = this.d;
                    f6 = (1.0f - this.r) * -30.0f * f2;
                    f7 = this.r * f3;
                } else {
                    module = this.d;
                }
                if (module != null && f7 > 0.01f) {
                    drawContext.d();
                    drawContext.a(0.0f, f6);
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate(0.0f, f6, 0.0f);
                    int n10 = (int)(28.0f * f2);
                    int n11 = n13 + n10 - (int)this.h;
                    RenderUtil.b(guiGraphics.pose(), (float)n12 + 10.0f * f2, (float)n11 + 3.0f * f2, (float)n6 - 20.0f * f2, (float)n10 - 6.0f * f2, 3.0f * f2, this.a(new Color(255, 255, 255, 12).getRGB(), f7));
                    u_0 u_03 = t_0.l(13.0f * f2);
                    String string = "Keybind";
                    String string2 = module.g().a();
                    float f8 = (float)n11 + 8.0f * f2;
                    y_0.a(string, (float)n12 + 18.0f * f2, f8, u_03, this.a(new Color(190, 190, 190).getRGB(), f7));
                    float f9 = y_0.a(string2, u_03);
                    y_0.a(string2, (float)(n12 + n6) - 18.0f * f2 - f9, f8, u_03, this.a(-1, f7));
                    List<Setting<?>> list = module.getSettings();
                    if (list != null && !list.isEmpty()) {
                        int n12 = n11 + n10;
                        for (Setting<?> setting : list) {
                            if (setting.getVisibility() != null && !setting.getVisibility().displayable()) continue;
                            int n13 = lf21.gui.c.a.f.a().a(guiGraphics, setting, n12 + (int)(10.0f * f2), n12, n6 - (int)(20.0f * f2), n4, n5, f7, f2);
                            n12 += n13;
                        }
                    } else {
                        String string3 = this.a(module);
                        if (string3 != null && !string3.isEmpty()) {
                            u_0 u_04 = t_0.l(12.0f * f2);
                            this.a(string3, n12 + (int)(10.0f * f2), n11 + n10 + (int)(10.0f * f2), n6 - (int)(20.0f * f2), u_04, -5592406, f7, f2);
                        }
                    }
                    guiGraphics.pose().popPose();
                    drawContext.e();
                }
                drawContext.e();
            });
            S.a();
            int n23 = n9 - 2 * n7 - (int)(20.0f * f2);
            float f6 = n23 - n10 - n11;
            if (this.j > f6) {
                float f7 = this.j - f6;
                if (this.h > f7) {
                    this.h = f7;
                    this.i = f7;
                }
                if (this.i > f7) {
                    this.i = f7;
                }
            } else {
                this.h = 0.0f;
                this.i = 0.0f;
            }
            this.a(guiGraphics, n12, n13, n14, f2, f3);
            return;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, int n4, float f2, float f3) {
        float f4;
        int n5 = (int)(30.0f * f2);
        int n6 = (int)(10.0f * f2);
        float f5 = n4 - n5 - n6;
        if (this.j <= f5) {
            f4 = 0.0f;
        } else {
            long l2 = System.currentTimeMillis() - this.o;
            if (this.k || l2 < 500L) {
                f4 = 1.0f;
            } else if (l2 < 1000L) {
                long l3 = l2 - 500L;
                f4 = 1.0f - (float)l3 / 500.0f;
            } else {
                f4 = 0.0f;
            }
        }
        float f6 = this.n = Math.abs(this.n - f4) > 0.01f ? lf21.utils.b.g.b(this.n, f4, 0.35f) : f4;
        if (this.n <= 0.01f) {
            return;
        }
        float f7 = this.j - f5;
        if (f7 <= 0.0f) {
            return;
        }
        float f8 = Math.max(20.0f * f2, f5 / this.j * f5);
        float f9 = (float)(n3 + n5) + this.h / f7 * (f5 - f8);
        int n7 = n2 + (int)(405.0f * f2) - (int)(4.0f * f2) - 2;
        float f10 = 4.0f * f2;
        int n8 = new Color(1.0f, 1.0f, 1.0f, this.n * f3).getRGB();
        RenderUtil.b(guiGraphics.pose(), (float)n7, f9, f10, f8, f10 / 2.0f, n8);
    }

    private void a(int n2, int n3, int n4, int n5, int n6) {
        this.e = n4 >= n2 && n4 <= n2 + n6 * 2 && n5 >= n3 && n5 <= n3 + n6;
    }

    private void a(GuiGraphics guiGraphics, int n2, int n3, float f2, float f3, boolean bl, float f4, float f5) {
        int n4;
        int n5 = (int)(12.0f * f4);
        int n6 = n5 * 2;
        int n7 = n5;
        int n8 = this.a(c, b, f2);
        if (f3 > 0.0f) {
            float f6 = 1.0f + 0.3f * f3;
            n8 = this.b(n8, f6);
        }
        int n9 = this.a(n8, f5);
        if (bl) {
            n4 = this.a(new Color(76, 175, 80, 70).getRGB(), f5);
            RenderUtil.b(guiGraphics.pose(), (float)n2 - f4, (float)n3 - f4, (float)n6 + 2.0f * f4, (float)n7 + 2.0f * f4, (float)n7 / 2.0f, n4);
        }
        RenderUtil.b(guiGraphics.pose(), (float)n2, (float)n3, (float)n6, (float)n7, (float)n7 / 2.0f, n9);
        n4 = n5 - (int)(4.0f * f4);
        int n10 = (n7 - n4) / 2;
        int n11 = n2 + n10;
        int n12 = n2 + n6 - n4 - n10;
        int n13 = n11 + (int)((float)(n12 - n11) * f2);
        int n14 = n3 + n10;
        if (f3 > 0.0f) {
            int n15 = (int)(50.0f * f3);
            int n16 = n15 << 24 | 0xFFFFFF;
            float f7 = (float)n4 + 2.0f * f4;
            RenderUtil.b(guiGraphics.pose(), (float)(n13 - 1), (float)(n14 - 1), f7, f7, f7 / 2.0f, this.a(n16, f5));
        }
        RenderUtil.b(guiGraphics.pose(), (float)n13, (float)n14, (float)n4, (float)n4, (float)n4 / 2.0f, this.a(-1, f5));
    }

    private int a(String string, int n2, int n3, int n4, u_0 u_02, int n5, float f2, float f3) {
        String[] stringArray = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int n6 = n3;
        int n7 = (int)(16.0f * f3);
        for (String string2 : stringArray) {
            Object object = stringBuilder.length() == 0 ? string2 : String.valueOf(stringBuilder) + " " + string2;
            float f4 = y_0.a((String)object, u_02);
            if (f4 > (float)n4 && stringBuilder.length() > 0) {
                y_0.a(stringBuilder.toString(), (float)n2, (float)n6, u_02, this.a(n5, f2));
                stringBuilder = new StringBuilder(string2);
                n6 += n7;
                continue;
            }
            stringBuilder = new StringBuilder((String)object);
        }
        if (stringBuilder.length() > 0) {
            y_0.a(stringBuilder.toString(), (float)n2, (float)n6, u_02, this.a(n5, f2));
        }
        return n6 - n3 + n7;
    }

    private String a(Module module) {
        try {
            String[] stringArray = t;
            return "This module provides " + module.getName().toLowerCase() + " functionality.";
        }
        catch (Exception exception) {
            return "No description available.";
        }
    }

    private void a(Module module, float f2) {
        if (module == null) {
            this.j = 0.0f;
            return;
        }
        float f3 = 28.0f * f2;
        List<Setting<?>> list = module.getSettings();
        if (list != null && !list.isEmpty()) {
            int n2 = 0;
            for (Setting<?> setting : list) {
                if (setting.getVisibility() != null && !setting.getVisibility().displayable()) continue;
                n2 += lf21.gui.c.a.f.a().b(setting, f2);
            }
            this.j = f3 + (float)n2;
        } else {
            String string = this.a(module);
            if (string != null && !string.isEmpty()) {
                u_0 u_02 = t_0.l(12.0f * f2);
                this.j = f3 + (float)this.a(string, (int)(385.0f * f2), u_02, f2);
            } else {
                this.j = f3;
            }
        }
    }

    private int a(String string, int n2, u_0 u_02, float f2) {
        if (string == null || string.isEmpty()) {
            return 0;
        }
        String[] stringArray = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int n3 = 1;
        int n4 = (int)(16.0f * f2);
        for (String string2 : stringArray) {
            String string3 = stringBuilder.length() == 0 ? string2 : String.valueOf(stringBuilder) + " " + string2;
            String string4 = string3;
            if (y_0.a(string3, u_02) > (float)n2 && stringBuilder.length() > 0) {
                ++n3;
                stringBuilder = new StringBuilder(string2);
                continue;
            }
            stringBuilder = new StringBuilder(string3);
        }
        return n3 * n4;
    }

    private int a(int n2, float f2) {
        int n3 = n2 >> 24 & 0xFF;
        int n4 = (int)((float)n3 * f2);
        return n4 << 24 | n2 & 0xFFFFFF;
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

    public boolean a(int n2, int n3, int n4, int n5, int n6, float f2) {
        int n7;
        if (this.q != lf21.gui.c.c.NONE) {
            return true;
        }
        if (this.d == null) {
            return false;
        }
        int n8 = (int)(405.0f * f2);
        int n9 = (int)(20.0f * f2);
        int n10 = (int)(20.0f * f2);
        int n11 = (int)(400.0f * f2);
        int n12 = (int)(30.0f * f2);
        int n13 = (int)(10.0f * f2);
        int n14 = n2 + (int)(600.0f * f2) - n8 - n9 + (int)(8.0f * f2);
        int n15 = n3 + n10 + (int)(23.0f * f2);
        int n16 = n11 - 2 * n9 - (int)(20.0f * f2);
        float f3 = n16 - n12 - n13;
        if (this.j > f3) {
            float f4 = this.j - f3;
            float f5 = Math.max(20.0f * f2, f3 / this.j * f3);
            float f6 = (float)(n15 + n12) + this.h / f4 * (f3 - f5);
            float f7 = 4.0f * f2;
            n7 = n14 + n8 - (int)f7 - 2;
            if (n4 >= n7 && (float)n4 <= (float)n7 + f7 && (float)n5 >= f6 && (float)n5 <= f6 + f5) {
                this.k = true;
                this.l = n5;
                this.m = this.h;
                this.o = System.currentTimeMillis();
                return true;
            }
        }
        int n17 = (int)(12.0f * f2);
        int n18 = (int)(15.0f * f2);
        int n19 = n14 + n8 - n17 * 2 - n18;
        int n20 = n15 + (n12 - n17) / 2;
        if (n6 == 0 && n4 >= n19 && n4 <= n19 + n17 * 2 && n5 >= n20 && n5 <= n20 + n17) {
            String string;
            this.d.toggle();
            if (this.d.isEnabled()) {
                var29_27 = t;
                string = "On";
            } else {
                var29_27 = t;
                string = "Off";
            }
            String string2 = string;
            lf21.gui.n.a.a(this.d.c() + " Module " + string2);
            return true;
        }
        n7 = (int)(28.0f * f2);
        int n21 = n15 + n12 - (int)this.h;
        if (n6 == 0 && n4 >= n14 + (int)(10.0f * f2) && n4 <= n14 + n8 - (int)(10.0f * f2) && n5 >= n21 && n5 <= n21 + n7) {
            lf21.gui.n.a.a(this.d);
            return true;
        }
        List<Setting<?>> list = this.d.getSettings();
        if (list != null && !list.isEmpty()) {
            int n22 = n15 + n12 + n7;
            int n23 = n5 + (int)this.h;
            for (Setting<?> setting : list) {
                if (setting.getVisibility() != null && !setting.getVisibility().displayable()) continue;
                int n24 = lf21.gui.c.a.f.a().b(setting, f2);
                if (n23 >= n22 && n23 <= n22 + n24 && lf21.gui.c.a.f.a().a(setting, n14 + (int)(10.0f * f2), n22 - (int)this.h, n8 - (int)(20.0f * f2), n4, n5, n6, f2)) {
                    return true;
                }
                n22 += n24;
            }
        }
        return false;
    }

    public boolean a(int n2, int n3, int n4, int n5, float f2) {
        if (this.d == null) {
            return false;
        }
        int n6 = (int)(405.0f * f2);
        int n7 = (int)(20.0f * f2);
        int n8 = (int)(20.0f * f2);
        int n9 = (int)(400.0f * f2);
        int n10 = n2 + (int)(600.0f * f2) - n6 - n7 + (int)(8.0f * f2);
        int n11 = n3 + n8 + (int)(23.0f * f2);
        int n12 = n9 - 2 * n7 - (int)(20.0f * f2);
        return n4 >= n10 && n4 <= n10 + n6 && n5 >= n11 && n5 <= n11 + n12;
    }

    public void a(double d2, float f2) {
        int n2 = (int)(400.0f * f2) - (int)(40.0f * f2) - (int)(20.0f * f2);
        float f3 = (float)n2 - 30.0f * f2 - 10.0f * f2;
        if (this.j > f3) {
            float f4 = this.j - f3;
            this.i -= (float)d2 * 18.0f * f2;
            this.i = Math.max(0.0f, Math.min(this.i, f4));
            this.o = System.currentTimeMillis();
        }
    }

    public void a(double d2, double d3, float f2) {
        if (this.k) {
            int n2 = (int)(400.0f * f2) - (int)(40.0f * f2) - (int)(20.0f * f2);
            float f3 = (float)n2 - 30.0f * f2 - 10.0f * f2;
            float f4 = this.j - f3;
            float f5 = Math.max(20.0f * f2, f3 / this.j * f3);
            float f6 = f3 - f5;
            if (f6 > 0.0f) {
                float f7 = (float)d3 - this.l;
                float f8 = f7 / f6 * f4;
                this.h = this.m + f8;
                this.i = this.h = Math.max(0.0f, Math.min(this.h, f4));
            }
            this.o = System.currentTimeMillis();
        }
        if (this.d != null) {
            for (Setting<?> setting : this.d.getSettings()) {
                e e2;
                if (setting.getVisibility() != null && !setting.getVisibility().displayable() || (e2 = lf21.gui.c.a.f.a().a(setting)) == null) continue;
                e2.a(d2, d3);
            }
        }
    }

    public void a(double d2, double d3, int n2) {
        this.k = false;
        this.o = System.currentTimeMillis();
        if (this.d != null) {
            for (Setting<?> setting : this.d.getSettings()) {
                e e2;
                if (setting.getVisibility() != null && !setting.getVisibility().displayable() || (e2 = lf21.gui.c.a.f.a().a(setting)) == null) continue;
                e2.a(d2, d3, n2);
            }
        }
    }

    public Module a() {
        return this.d;
    }

    public void b(Module module) {
        this.d = module;
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

    private void a(float f2) {
        float f3;
        if (this.s <= 0.0f) {
            return;
        }
        float f4 = f2 / this.s;
        this.h *= f4;
        this.i *= f4;
        if (this.d != null) {
            this.a(this.d, f2);
        }
        int n2 = (int)(400.0f * f2) - (int)(40.0f * f2) - (int)(20.0f * f2);
        float f5 = 30.0f * f2;
        float f6 = (float)n2 - f5;
        if (this.j > f3) {
            float f7 = this.j - f6;
            this.h = Math.max(0.0f, Math.min(this.h, f7));
            this.i = Math.max(0.0f, Math.min(this.i, f7));
        } else {
            this.h = 0.0f;
            this.i = 0.0f;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block10: {
            block9: {
                var5 = new String[7];
                var3_1 = 0;
                var2_2 = "\fcN0=2\"0e\u001e 1./bm\u001851-  `\u000bz\u0003\rj\b\u0007\ti\u001761/%\u000fbj\u001b:;5(-b\u000f8158l\u0015\u0016d\u0007'x,.&y\u00021x13-z\u00070=2a";
                var4_3 = "\fcN0=2\"0e\u001e 1./bm\u001851-  `\u000bz\u0003\rj\b\u0007\ti\u001761/%\u000fbj\u001b:;5(-b\u000f8158l\u0015\u0016d\u0007'x,.&y\u00021x13-z\u00070=2a".length();
                var1_4 = 25;
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
                    var5[var3_1++] = lf21.gui.c.i.b(lf21.gui.c.i.a(v1));
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        ** continue;
                    }
                    var2_2 = "bA\u00010--$b\u0002\rb";
                    var4_3 = "bA\u00010--$b\u0002\rb".length();
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
                    var5[var3_1++] = lf21.gui.c.i.b(lf21.gui.c.i.a(v1));
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
        lf21.gui.c.i.t = var5;
        lf21.gui.c.i.a = new Color(255, 255, 255, 20).getRGB();
        lf21.gui.c.i.b = new Color(76, 175, 80).getRGB();
        lf21.gui.c.i.c = new Color(158, 158, 158).getRGB();
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x41);
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
                    case 0 -> 66;
                    case 1 -> 12;
                    case 2 -> 110;
                    case 3 -> 84;
                    case 4 -> 88;
                    case 5 -> 65;
                    default -> 65;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

