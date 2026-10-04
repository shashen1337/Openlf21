/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.a;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lf21.gui.a.a;
import lf21.gui.a.d;
import lf21.gui.a.e;
import lf21.gui.a.f;
import lf21.gui.a.g;
import lf21.gui.a.h;
import lf21.gui.m;
import lf21.modules.Module;
import lf21.render.v_0;
import lf21.settings.Setting;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.render.RenderUtil;

public class c {
    public Module a;
    public a b;
    public int c;
    public List<d> d;
    public boolean e;
    private final lf21.utils.a.e f;
    private float g = 0.0f;
    private final float h = 4.0f;
    private long i = System.currentTimeMillis();
    private boolean j;
    private static final String[] k;

    public c(Module module, a a2, int n2) {
        this.a = module;
        this.b = a2;
        this.c = n2;
        this.e = false;
        this.d = new ArrayList<d>();
        for (Setting<?> setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                this.d.add(new e(setting, this, 0));
                continue;
            }
            if (setting instanceof ModeSetting) {
                this.d.add(new f(setting, this, 0));
                continue;
            }
            if (setting instanceof NumberSetting) {
                this.d.add(new h(setting, this, 0));
                continue;
            }
            if (!(setting instanceof MultiSelectSetting)) continue;
            this.d.add(new g(setting, this, 0));
        }
        this.f = new lf21.utils.a.e();
    }

    public int a() {
        if (!this.e) {
            return this.b.f;
        }
        int n2 = this.b.f * 2;
        List list = this.d.stream().filter(d2 -> d2.a.getVisibility().displayable()).collect(Collectors.toList());
        for (d d3 : list) {
            d d4;
            n2 += this.b.f;
            if (d3 instanceof f && ((f)(d4 = (f)d3)).c()) {
                n2 += ((f)d4).a();
            }
            if (!(d3 instanceof g) || !((g)(d4 = (g)d3)).b()) continue;
            n2 += ((g)d4).a();
        }
        return n2;
    }

    public int b() {
        int n2 = this.b.f;
        List list = this.d.stream().filter(d2 -> d2.a.getVisibility().displayable()).collect(Collectors.toList());
        for (d d3 : list) {
            d d4;
            n2 += this.b.f;
            if (d3 instanceof f && ((f)(d4 = (f)d3)).c()) {
                n2 += ((f)d4).a();
            }
            if (!(d3 instanceof g) || !((g)(d4 = (g)d3)).b()) continue;
            n2 += ((g)d4).a();
        }
        return n2;
    }

    public void a(PoseStack poseStack, int n2, int n3, float f2) {
        float f3;
        this.f.a();
        long l2 = System.currentTimeMillis();
        float f4 = (float)(l2 - this.i) / 1000.0f;
        this.i = l2;
        if (this.a(n2, n3)) {
            if (this.g < 1.0f) {
                this.g += f4 * 4.0f;
            }
            if (this.g > 1.0f) {
                this.g = 1.0f;
            }
        } else {
            if (this.g > 0.0f) {
                this.g -= f4 * 4.0f;
            }
            if (this.g < 0.0f) {
                this.g = 0.0f;
            }
        }
        int n4 = (int)(160.0f + 40.0f * this.g);
        int n5 = this.b.b + this.c - (this.c == 0 ? 0 : 1);
        int n6 = this.b.f + (this.c == 0 ? 0 : 1);
        RenderUtil.a(poseStack, this.b.a, (float)n5, (float)this.b.e, (float)n6, 4.0f, 6.0f, 0.9f, 0);
        RenderUtil.a(poseStack, (float)this.b.a, (float)n5, (float)this.b.e, (float)n6, new Color(21, 21, 21, n4).getRGB());
        String string = this.a.c();
        float f5 = v_0.a.b(string);
        float f6 = (float)this.b.a + (float)this.b.e / 2.0f - f5 / 2.0f;
        float f7 = (float)(this.b.b + this.c) + (float)this.b.f / 2.0f - v_0.a.c() / 2.0f;
        v_0.a.c(poseStack, string, f6, f7, this.a.isEnabled() ? lf21.utils.render.a.a(138, 180, 248, 255) : -1);
        poseStack.pushPose();
        float f8 = this.b.a + this.b.e - 15;
        float f9 = (float)(this.b.b + this.c) + (float)this.b.f / 2.0f;
        float f10 = 180.0f * this.f.e();
        poseStack.translate(f8, f9, 0.0f);
        poseStack.mulPose(Axis.ZP.rotationDegrees(f10));
        poseStack.translate(-f8, -f9, 0.0f);
        v_0.g.c(poseStack, "", f8 - v_0.g.b("") / 2.0f, f9 - v_0.g.c() / 2.0f, -1);
        poseStack.popPose();
        f8 = (float)this.b() * this.f.e();
        if (f3 > 0.0f) {
            int n7 = this.b.b + this.c + this.b.f - 1;
            RenderUtil.a(poseStack, this.b.a, (float)n7, (float)this.b.e, (float)((int)f8 + 1), 4.0f, 6.0f, 0.9f, 0);
            RenderUtil.a(this.b.a, n7, this.b.e, (int)f8 + 1);
            RenderUtil.a(poseStack, (float)this.b.a, (float)n7, (float)this.b.e, (float)((int)f8 + 1), new Color(11, 11, 11, 150).getRGB());
            RenderUtil.a(poseStack, (float)(this.b.a + 5), (float)(n7 + 2), (float)(this.b.e - 10), (float)(this.b.f - 4), new Color(255, 255, 255, 12).getRGB());
            String[] stringArray = k;
            String string2 = "Keybind";
            String string3 = this.j ? "Press a key..." : this.a.g().a();
            float f11 = (float)n7 + ((float)this.b.f - v_0.a.c()) / 2.0f;
            v_0.a.c(poseStack, string2, (float)this.b.a + 8.0f, f11, new Color(190, 190, 190).getRGB());
            float f12 = v_0.a.b(string3);
            v_0.a.c(poseStack, string3, (float)(this.b.a + this.b.e) - 8.0f - f12, f11, this.j ? lf21.utils.render.a.a(138, 180, 248, 255) : -1);
            List list = this.d.stream().filter(d2 -> d2.a.getVisibility().displayable()).collect(Collectors.toList());
            int n8 = this.b.f;
            for (d d3 : list) {
                d d4;
                d3.c = n8;
                d3.a(poseStack, n2, n3, f2, 1.0f);
                n8 += this.b.f;
                if (d3 instanceof f && ((f)(d4 = d3)).c()) {
                    n8 += ((f)d4).a();
                }
                if (!(d3 instanceof g) || !((g)(d4 = d3)).b()) continue;
                n8 += ((g)d4).a();
            }
            RenderUtil.d();
        }
    }

    public void a(double d3, double d4, int n2) {
        if (this.a(d3, d4)) {
            if (n2 == 0) {
                this.a.toggle();
            } else if (n2 == 1) {
                this.e = !this.e;
                this.f.a(this.e ? 1.0 : 0.0, 0.2, lf21.utils.b.f.b);
                this.b.c();
            }
        }
        if (this.e) {
            int n3 = this.b.b + this.c + this.b.f - 1;
            if (n2 == 0 && d3 >= (double)this.b.a && d3 <= (double)(this.b.a + this.b.e) && d4 >= (double)n3 && d4 <= (double)(n3 + this.b.f)) {
                m.a(this);
                return;
            }
            List list = this.d.stream().filter(d2 -> d2.a.getVisibility().displayable()).collect(Collectors.toList());
            int n4 = this.b.f;
            for (d d5 : list) {
                d d6;
                d5.c = n4;
                if (d5.a(d3, d4)) {
                    d5.a(d3, d4, n2);
                }
                n4 += this.b.f;
                if (d5 instanceof f && ((f)(d6 = d5)).c()) {
                    n4 += ((f)d6).a();
                }
                if (!(d5 instanceof g) || !((g)(d6 = d5)).b()) continue;
                n4 += ((g)d6).a();
            }
        }
    }

    public void b(double d2, double d3, int n2) {
        if (this.e) {
            for (d d4 : this.d) {
                d4.b(d2, d3, n2);
            }
        }
    }

    public void a(double d2, double d3, double d4) {
    }

    public void c() {
    }

    public boolean a(double d2, double d3) {
        return d2 >= (double)this.b.a && d2 <= (double)(this.b.a + this.b.e) && d3 >= (double)(this.b.b + this.c) && d3 <= (double)(this.b.b + this.c + this.b.f);
    }

    public boolean d() {
        return !this.f.c();
    }

    public void a(boolean bl) {
        this.j = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[2];
        int n2 = 0;
        String string = "-D<\u0016\r\u001c\u0005\u000e6S \u0007\u0017R\u0000FJ \rJ\\O";
        int n3 = "-D<\u0016\r\u001c\u0005\u000e6S \u0007\u0017R\u0000FJ \rJ\\O".length();
        int n4 = 7;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.a.c.a(lf21.gui.a.c.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                k = stringArray;
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x61);
        }
        return cArray;
    }

    /*
     * Handled impossible loop by duplicating code
     * Enabled aggressive block sorting
     */
    private static String a(char[] cArray) {
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
                    case 0 -> 102;
                    case 1 -> 33;
                    case 2 -> 69;
                    case 3 -> 116;
                    case 4 -> 100;
                    case 5 -> 114;
                    default -> 97;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

