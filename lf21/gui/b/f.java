/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.List;
import lf21.gui.b.c;
import lf21.gui.b.d;
import lf21.gui.b.h;
import lf21.gui.b.i;
import lf21.gui.b.j;
import lf21.gui.b.k;
import lf21.gui.b.l;
import lf21.modules.Module;
import lf21.render.v_0;
import lf21.settings.Setting;
import lf21.settings.impl.BooleanSetting;
import lf21.settings.impl.ModeSetting;
import lf21.settings.impl.MultiSelectSetting;
import lf21.settings.impl.NumberSetting;
import lf21.utils.a.e;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;

public class f
extends c {
    public static final int f;
    private final List<h<?>> g = new ArrayList();
    private final d h;
    private final Module i;
    private final e j = new e();
    private final e k = new e();
    private final e l = new e();
    private final e m = new e();
    private float n;
    private float o;
    private float p = 20.0f;
    private float q;
    private boolean r;
    private boolean s;
    private boolean t;
    private static final String u;
    private static final String[] v;

    public f(d d2, Module module) {
        this.h = d2;
        this.i = module;
        for (Setting<?> setting : module.getSettings()) {
            Setting setting2;
            if (setting instanceof BooleanSetting) {
                setting2 = (BooleanSetting)setting;
                this.g.add(new i(d2, (BooleanSetting)setting2));
                continue;
            }
            if (setting instanceof ModeSetting) {
                setting2 = (ModeSetting)setting;
                this.g.add(new j(d2, (ModeSetting)setting2));
                continue;
            }
            if (setting instanceof MultiSelectSetting) {
                setting2 = (MultiSelectSetting)setting;
                this.g.add(new k(d2, (MultiSelectSetting)setting2));
                continue;
            }
            if (!(setting instanceof NumberSetting)) continue;
            setting2 = (NumberSetting)setting;
            this.g.add(new l(d2, (NumberSetting)setting2));
        }
    }

    @Override
    public void a(lf21.gui.l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        float f7;
        float f8 = 20.0f;
        for (h<?> h2 : this.g) {
            if (!((Setting)h2.i()).getVisibility().displayable()) continue;
            f8 += h2.f();
        }
        this.m.a((double)f8, 0.2, lf21.utils.b.f.c);
        this.m.a();
        this.h.c(!this.m.c());
        this.k.a(this.r ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.k.a();
        this.j.a(this.i.isEnabled() ? 1.0 : 0.0, 0.3, lf21.utils.b.f.c);
        this.j.a();
        this.l.a(this.s ? 1.0 : 0.0, 0.2, lf21.utils.b.f.e);
        this.l.a();
        float f9 = this.l.e();
        this.p = 20.0f + f9 * this.m.e();
        this.r = this.h.equals(lf21.gui.l.d) && lf21.utils.misc.c.a(n2, n3, this.n, this.o, 120.0f, this.p);
        float f10 = this.p - 20.0f;
        if (f10 > 0.01f && f9 > 0.01f) {
            RenderUtil.a(poseStack, this.n + 1.0f, this.o + 20.0f, 118.0f, f10, 4.0f, 18.0f, 0.75f * f9 * f2, 0);
            RenderUtil.b(poseStack, this.n + 1.0f, this.o + 20.0f, 118.0f, f10, 4.0f, lf21.utils.render.a.a(f, 0.5f * f9 * f2));
        }
        if ((f7 = this.k.e()) > 0.0f) {
            RenderUtil.a(poseStack, this.n + 0.5f, this.o, 119.0f, 20.0f, lf21.utils.render.a.a(-1, 0.1f * f2 * f7));
        }
        float f11 = this.j.e();
        if (1.0f - f6 > 0.0f) {
            v_0.k.d(poseStack, this.i.c(), this.n + 60.0f, this.o + (20.0f - v_0.k.c()) / 2.0f, lf21.utils.render.a.a(-1, f2 * (1.0f - f11) * 0.6f));
        }
        if (f11 > 0.0f) {
            f5 = v_0.q.b(this.i.c());
            f4 = this.o + (20.0f - v_0.q.c()) / 2.0f;
            RenderUtil.c(poseStack, this.n + (120.0f - f5) / 2.0f, f4 + v_0.q.c() / 4.0f, f5, v_0.q.c() / 2.0f, 12, lf21.utils.render.a.a(-13768502, f2 * f11 * 0.36f));
            v_0.q.d(poseStack, this.i.c(), this.n + 60.0f, f4, lf21.utils.render.a.a(-13768502, f2 * f11));
        }
        String string = String.valueOf('\ueb4e');
        f4 = v_0.g.b(string);
        float f12 = this.n + 120.0f - f4 - 6.0f;
        float f13 = this.o + (20.0f - v_0.g.c()) / 2.0f + 1.0f;
        lf21.utils.render.d.b(poseStack, f12 + f4 / 2.0f, f13 + v_0.g.c() / 2.0f - 1.0f, 180.0f * f9);
        v_0.g.a(poseStack, string, (double)f12, (double)f13, lf21.utils.render.a.a(-1, (0.8f - 0.3f * f9) * f2));
        lf21.utils.render.d.a(poseStack);
        if (this.s) {
            float f14 = this.o + 20.0f;
            RenderUtil.a(poseStack, this.n + 5.0f, f14 + 2.0f, 110.0f, 16.0f, lf21.utils.render.a.a(-1, 0.045f * f2 * f9));
            String[] stringArray = v;
            String string2 = "Keybind";
            String string3 = this.t ? "Press a key..." : this.i.g().a();
            float f15 = f14 + (20.0f - v_0.j.c()) / 2.0f + 1.0f;
            v_0.j.a(poseStack, string2, (double)(this.n + 8.0f), (double)f15, lf21.utils.render.a.a(-1, 0.65f * f2 * f9));
            float f16 = v_0.m.b(string3);
            v_0.m.a(poseStack, string3, (double)(this.n + 112.0f - f16), (double)f15, lf21.utils.render.a.a(this.t ? -13768502 : -1, (this.t ? 1.0f : 0.8f) * f2 * f9));
            f5 = this.o + 40.0f;
            for (h<?> h3 : this.g) {
                h3.a(this.n);
                h3.b(f5);
                h3.a(l2, guiGraphics, poseStack, n2, n3, f2 * f9, f3);
                f5 += h3.b() * h3.j().e();
            }
        }
        if (this.h.equals(lf21.gui.l.d) && lf21.utils.misc.c.a(n2, n3, this.n, this.o, 120.0f, 20.0f)) {
            this.h.a(lf21.gui.f.a(this.i));
        }
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (!this.r) {
            return false;
        }
        if (lf21.utils.misc.c.a((float)d2, (float)d3, this.n, this.o, 120.0f, 20.0f)) {
            if (n2 == 0) {
                this.i.toggle();
            } else if (n2 == 1) {
                this.s = !this.s;
            }
            return true;
        }
        if (lf21.utils.misc.c.a((float)d2, (float)d3, this.n, this.o + 20.0f, 120.0f, this.p - 20.0f)) {
            if (this.s && n2 == 0 && lf21.utils.misc.c.a((float)d2, (float)d3, this.n, this.o + 20.0f, 120.0f, 20.0f)) {
                lf21.gui.l.a(this);
                return true;
            }
            for (h<?> h2 : this.g) {
                if (!((Setting)h2.i()).getVisibility().displayable() || !h2.a(d2, d3, n2)) continue;
                return true;
            }
        }
        return this.r;
    }

    @Override
    public boolean b(double d2, double d3, int n2) {
        for (h<?> h2 : this.g) {
            if (!h2.b(d2, d3, n2)) continue;
            return true;
        }
        return false;
    }

    @Override
    @Generated
    public e g() {
        return this.k;
    }

    @Generated
    public e h() {
        return this.l;
    }

    @Generated
    public e i() {
        return this.m;
    }

    @Override
    @Generated
    public float c() {
        return this.n;
    }

    @Override
    @Generated
    public float d() {
        return this.o;
    }

    @Override
    @Generated
    public float f() {
        return this.p;
    }

    @Override
    @Generated
    public void a(float f2) {
        this.n = f2;
    }

    @Override
    @Generated
    public void b(float f2) {
        this.o = f2;
    }

    @Override
    @Generated
    public void d(float f2) {
        this.p = f2;
    }

    @Generated
    public List<h<?>> j() {
        return this.g;
    }

    @Generated
    public d k() {
        return this.h;
    }

    @Generated
    public Module l() {
        return this.i;
    }

    @Generated
    public e m() {
        return this.j;
    }

    @Generated
    public float n() {
        return this.q;
    }

    @Generated
    public void e(float f2) {
        this.q = f2;
    }

    @Generated
    public boolean o() {
        return this.r;
    }

    @Generated
    public void a(boolean bl) {
        this.r = bl;
    }

    @Generated
    public boolean p() {
        return this.s;
    }

    @Generated
    public void b(boolean bl) {
        this.s = bl;
    }

    @Generated
    public boolean q() {
        return this.t;
    }

    @Generated
    public void c(boolean bl) {
        this.t = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        String[] stringArray = new String[3];
        int n2 = 0;
        String string = "f\u000eK\u0018\\!X\u000e}\u0019W\tFo]\r\u0000W\u0003\u001ba\u0012\u0002\u001c\\";
        int n3 = "f\u000eK\u0018\\!X\u000e}\u0019W\tFo]\r\u0000W\u0003\u001ba\u0012\u0002\u001c\\".length();
        int n4 = 7;
        int n5 = -1;
        while (true) {
            int n6 = ++n5;
            stringArray[n2++] = lf21.gui.b.f.a(lf21.gui.b.f.a(string.substring(n6, n6 + n4)));
            if ((n5 += n4) >= n3) {
                v = stringArray;
                u = "17";
                f = lf21.utils.render.a.a(32, 32, 32);
                return;
            }
            n4 = string.charAt(n5);
        }
    }

    private static char[] a(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x3C);
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
                    case 0 -> 45;
                    case 1 -> 107;
                    case 2 -> 50;
                    case 3 -> 122;
                    case 4 -> 53;
                    case 5 -> 79;
                    default -> 60;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

