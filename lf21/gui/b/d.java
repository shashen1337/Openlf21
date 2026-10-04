/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lf21.LF21Client;
import lf21.gui.b.c;
import lf21.gui.b.f;
import lf21.gui.b.h;
import lf21.gui.l;
import lf21.modules.Category;
import lf21.modules.Module;
import lf21.render.S;
import lf21.render.v_0;
import lf21.utils.a.e;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;
import lombok.Generated;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class d
extends c {
    public static final int f;
    public static final int g;
    public static final int h;
    private final List<f> i = new ArrayList<f>();
    private final Category j;
    private float k;
    private float l;
    private float m;
    private boolean n;
    private boolean o;
    private float p;
    private float q;
    private float r;
    private float s;
    private final e t = new e();
    private final e u = new e();
    private final e v = new e();
    private final e w = new e();
    private h<?> x;
    private String y = "";
    private String z = "";
    private boolean A;
    private boolean B;
    private float C;
    private float D;
    private float E;
    private float F;
    private static final String G;

    public d(Category category) {
        this(category, true);
    }

    public d(Category category, boolean bl) {
        this.j = category;
        if (bl) {
            for (Module module : LF21Client.getInstance().getModuleManager().a(category)) {
                if (module.getName().equals(G)) continue;
                this.i.add(new f(this, module));
            }
        }
        this.m = 20.0f + Math.min(240.0f, 20.0f * (float)this.i.size());
    }

    @Override
    public void a(l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        this.n = lf21.utils.misc.c.a(n2, n3, this.k, this.l, 120.0f, this.m);
        if (this.n) {
            lf21.gui.l.d = this;
        }
        this.t.a(l2.d() ? 0.0 : 1.0, l2.d() ? 0.22 : 0.32, lf21.utils.b.f.a);
        this.t.a();
        float f4 = 0.0f;
        for (f f5 : this.i) {
            f4 += f5.f();
        }
        if (this.i.size() < 10) {
            this.m = Math.min(f4, 240.0f) + 20.0f;
        }
        this.r = Mth.clamp((float)this.r, (float)0.0f, (float)(f4 - this.m + 20.0f));
        this.u.a((double)this.r, 0.22, lf21.utils.b.f.c);
        this.u.a();
        this.B = false;
        if (this.o) {
            this.k = (float)n2 + this.p;
            this.l = (float)n3 + this.q;
        }
        this.w.a(this.A ? 0.0 : 1.0, 0.2, lf21.utils.b.f.c);
        this.w.a();
        float f6 = this.w.e();
        if (!this.A) {
            this.s = this.m;
        }
        float f7 = this.t.e();
        lf21.utils.render.d.a(poseStack, this.k + 60.0f, this.l + this.m / 2.0f, 0.4f + 0.6f * f7);
        float f8 = 12.0f;
        RenderUtil.a(poseStack, this.k - f8, this.l - f8, 120.0f + f8 * 2.0f, this.m + f8 * 2.0f, 6.0f + f8 / 2.0f, f8, lf21.utils.render.a.a(0, 0, 0, (int)(80.0f * f2 * 1.0f)));
        RenderUtil.a(poseStack, this.k, this.l, 120.0f, this.m, 6.0f, 18.0f, 0.75f * f2, 0);
        RenderUtil.b(poseStack, this.k, this.l, 120.0f, this.m, 6.0f, lf21.utils.render.a.a(f, f2 * 0.5f));
        S.a(false);
        RenderUtil.b(poseStack, this.k + 0.5f, this.l, 118.0f, 20.0f, 6.0f, -1);
        S.b(true);
        RenderUtil.b(poseStack, this.k, this.l, 120.0f, 1.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(-13768502, g, 100L), f2), lf21.utils.render.a.a(lf21.utils.render.a.a(-13768502, g, 2000L), f2));
        S.a();
        v_0.p.a(poseStack, this.j.g, (double)(this.k + 8.0f), (double)(this.l + (20.0f - v_0.p.c()) / 2.0f + 3.0f), lf21.utils.render.a.a(-1, f2));
        float f9 = this.u.e();
        float f10 = this.l + 20.0f - f9;
        S.a(false);
        RenderUtil.a(poseStack, this.k + 0.5f, this.l + 20.0f, 119.0f, 6.0f, -1);
        RenderUtil.b(poseStack, this.k, this.l + 20.0f, 120.0f, this.m - 20.0f - 0.5f, 6.0f, -1);
        S.b(true);
        for (f f11 : this.i) {
            f11.a(this.k);
            f11.b(f10);
            f11.a(l2, guiGraphics, poseStack, n2, n3, f2, f3);
            f10 += f11.f();
        }
        RenderUtil.a(poseStack, this.k + 0.5f, this.l + 20.0f - 0.5f, 119.0f, 6.0f, lf21.utils.render.a.a(-16777216, 0.36f * f2), lf21.utils.render.a.a(-16777216, 0.0f));
        S.a();
        if (lf21.utils.misc.c.a(n2, n3, this.k, this.l, 120.0f, 20.0f)) {
            this.a(lf21.gui.f.a(this.j));
        }
        this.v.a(this.B ? 1.0 : 0.0, 0.18, lf21.utils.b.f.c);
        this.v.a();
        lf21.utils.render.d.a(poseStack);
    }

    public void a(PoseStack poseStack, int n2, int n3, int n4, int n5, float f2) {
        float f3 = this.v.e();
        if (f3 > 0.0f) {
            float f4 = v_0.c.b(this.y);
            float f5 = v_0.c.c() + 8.0f;
            float f6 = f4 + 20.0f;
            float f7 = (float)n2 + 12.0f;
            float f8 = (float)n3 + 10.0f;
            if (f7 + f6 > (float)n4 - 4.0f) {
                f7 = (float)n2 - f6 - 12.0f;
            }
            if (f8 + f5 > (float)n5 - 4.0f) {
                f8 = (float)n3 - f5 - 10.0f;
            }
            RenderUtil.c(poseStack, f7, f8, f6, f5, 12, lf21.utils.render.a.a(f, f2 * f3 * 0.72f));
            RenderUtil.b(poseStack, f7, f8, f6, f5, 3.5f, lf21.utils.render.a.a(-13768502, f2 * f3 * 0.9f));
            RenderUtil.b(poseStack, f7 + 1.0f, f8 + 1.0f, f6 - 2.0f, f5 - 2.0f, 2.7f, lf21.utils.render.a.a(lf21.utils.render.a.a(12, 12, 12), f2 * f3 * 0.98f));
            v_0.c.a(poseStack, this.y, (double)(f7 + 10.0f), (double)(f8 + 3.0f), lf21.utils.render.a.a(lf21.utils.render.a.a(188, 188, 188), f2 * f3));
        }
    }

    @Override
    public void a() {
        this.t.c(0.0);
        this.t.a(0.0);
    }

    public void a(String string) {
        if (string == null || string.isBlank()) {
            return;
        }
        this.y = string;
        this.B = true;
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (this.n) {
            if (lf21.utils.misc.c.a((float)d2, (float)d3, this.k, this.l, 120.0f, 20.0f)) {
                this.o = true;
                this.p = this.k - (float)d2;
                this.q = this.l - (float)d3;
            } else if (lf21.utils.misc.c.a((float)d2, (float)d3, this.k, this.l + 20.0f, 120.0f, this.m - 20.0f)) {
                for (f f2 : this.i) {
                    if (!f2.a(d2, d3, n2)) continue;
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean b(double d2, double d3, int n2) {
        for (f f2 : this.i) {
            if (!f2.b(d2, d3, n2)) continue;
            return true;
        }
        this.o = false;
        return true;
    }

    @Override
    public boolean a(double d2, double d3, double d4) {
        if (this.n) {
            this.r -= (float)d4 * 50.0f;
            return true;
        }
        return false;
    }

    @Override
    @Generated
    public float c() {
        return this.k;
    }

    @Override
    @Generated
    public float d() {
        return this.l;
    }

    @Override
    @Generated
    public float f() {
        return this.m;
    }

    @Override
    @Generated
    public void a(float f2) {
        this.k = f2;
    }

    @Override
    @Generated
    public void b(float f2) {
        this.l = f2;
    }

    @Override
    @Generated
    public void d(float f2) {
        this.m = f2;
    }

    @Generated
    public List<f> h() {
        return this.i;
    }

    @Generated
    public Category i() {
        return this.j;
    }

    @Generated
    public boolean j() {
        return this.n;
    }

    @Generated
    public void a(boolean bl) {
        this.n = bl;
    }

    @Generated
    public boolean k() {
        return this.o;
    }

    @Generated
    public void b(boolean bl) {
        this.o = bl;
    }

    @Generated
    public float l() {
        return this.p;
    }

    @Generated
    public void e(float f2) {
        this.p = f2;
    }

    @Generated
    public float m() {
        return this.q;
    }

    @Generated
    public void f(float f2) {
        this.q = f2;
    }

    @Generated
    public float n() {
        return this.r;
    }

    @Generated
    public void g(float f2) {
        this.r = f2;
    }

    @Generated
    public float o() {
        return this.s;
    }

    @Generated
    public void h(float f2) {
        this.s = f2;
    }

    @Generated
    public e p() {
        return this.t;
    }

    @Generated
    public e q() {
        return this.u;
    }

    @Generated
    public e r() {
        return this.v;
    }

    @Generated
    public e s() {
        return this.w;
    }

    @Generated
    public h<?> t() {
        return this.x;
    }

    @Generated
    public void a(h<?> h2) {
        this.x = h2;
    }

    @Generated
    public String u() {
        return this.y;
    }

    @Generated
    public void b(String string) {
        this.y = string;
    }

    @Generated
    public String v() {
        return this.z;
    }

    @Generated
    public void c(String string) {
        this.z = string;
    }

    @Generated
    public boolean w() {
        return this.A;
    }

    @Generated
    public void c(boolean bl) {
        this.A = bl;
    }

    @Generated
    public boolean x() {
        return this.B;
    }

    @Generated
    public void d(boolean bl) {
        this.B = bl;
    }

    @Generated
    public float y() {
        return this.C;
    }

    @Generated
    public void i(float f2) {
        this.C = f2;
    }

    @Generated
    public float z() {
        return this.D;
    }

    @Generated
    public void j(float f2) {
        this.D = f2;
    }

    @Generated
    public float A() {
        return this.E;
    }

    @Generated
    public void k(float f2) {
        this.E = f2;
    }

    @Generated
    public float B() {
        return this.F;
    }

    @Generated
    public void l(float f2) {
        this.F = f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        G = lf21.gui.b.d.a(lf21.gui.b.d.d(":q\u001f#V\u000e^\u0003"));
        f = lf21.utils.render.a.a(23, 23, 23);
        g = new Color(-13768502).darker().darker().getRGB();
        h = new Color(-13768502).darker().getRGB();
    }

    private static char[] d(String string) {
        char[] cArray = string.toCharArray();
        if (cArray.length < 2) {
            cArray = cArray;
            cArray[0] = (char)(cArray[0] ^ 0x31);
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
                    case 0 -> 119;
                    case 1 -> 4;
                    case 2 -> 108;
                    case 3 -> 74;
                    case 4 -> 53;
                    case 5 -> 76;
                    default -> 49;
                }));
                ++n2;
                n3 = n3;
            } while (n3 > n2);
        }
        return new String(cArray2).intern();
    }
}

