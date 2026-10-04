/*
 * Decompiled with CFR 0.152.
 */
package lf21.gui.b;

import com.mojang.blaze3d.vertex.PoseStack;
import lf21.gui.b.d;
import lf21.gui.b.h;
import lf21.gui.f;
import lf21.gui.l;
import lf21.render.v_0;
import lf21.settings.impl.ModeSetting;
import lf21.utils.a.e;
import lf21.utils.misc.c;
import lf21.utils.render.RenderUtil;
import lf21.utils.render.a;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class j
extends h<ModeSetting> {
    private final e i = new e();
    private final e j = new e();
    private final e k = new e();
    private final e l = new e();
    private boolean m;
    private boolean n;
    private String o;

    public j(d d2, ModeSetting modeSetting) {
        super(d2, modeSetting);
    }

    @Override
    public void a(l l2, GuiGraphics guiGraphics, PoseStack poseStack, int n2, int n3, float f2, float f3) {
        float f4;
        float f5;
        float f6 = 36.0f;
        float f7 = this.b + f6 / 2.0f + 2.0f - 2.0f;
        float f8 = 108.0f;
        float f9 = 14.0f;
        this.o = null;
        this.m = lf21.utils.misc.c.a(n2, n3, this.a + 6.0f, f7, f8, f9);
        this.i.a(this.m ? 1.0 : 0.0, 0.22, lf21.utils.b.f.c);
        this.i.a();
        this.h.a(((ModeSetting)this.g).getVisibility().displayable() ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.h.a();
        this.j.a(this.n ? 1.0 : 0.0, 0.2, lf21.utils.b.f.c);
        this.j.a();
        if (Mth.equal((float)(f2 *= this.h.e()), (float)0.0f)) {
            return;
        }
        float f10 = this.b + (f6 / 2.0f - v_0.j.c()) / 2.0f + 1.0f;
        v_0.j.a(poseStack, ((ModeSetting)this.g).getName(), (double)(this.a + 6.0f), (double)f10, lf21.utils.render.a.a(-1, f2 * 0.8f));
        float f11 = this.j.e();
        if (f11 > 0.0f) {
            f5 = f9 + (float)((ModeSetting)this.g).getModes().length * f9 * f11;
            RenderUtil.b(poseStack, this.a + 6.0f, f7, f8, f5, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(60, 60, 60), f2 * f11));
            f4 = (f9 - v_0.m.c()) / 2.0f;
            float f12 = f7 + f4 + f9;
            for (String string : ((ModeSetting)this.g).getModes()) {
                if (lf21.utils.misc.c.a(n2, n3, this.a + 6.0f, f12 - f4, f8, f9)) {
                    this.o = string;
                    this.l.a((double)(f12 - f4), 0.2, lf21.utils.b.f.c);
                }
                if (f7 + f5 > f12 + v_0.m.c()) {
                    v_0.m.d(poseStack, string, this.a + 60.0f, f12, lf21.utils.render.a.a(-1, f2 * 0.8f * f11));
                }
                f12 += f9;
            }
        }
        this.k.a(this.o == null || !this.n ? 0.0 : 1.0, 0.18, lf21.utils.b.f.c);
        this.k.a();
        this.l.a();
        f5 = this.k.e();
        if (f5 > 0.0f) {
            RenderUtil.b(poseStack, this.a + 6.0f, this.l.e(), f8, f9, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a(255, 255, 255), f2 * f5 * 1.0f * 0.1f));
        }
        f4 = this.i.e();
        RenderUtil.b(poseStack, this.a + 6.0f, f7, f8, f9, 3.0f, lf21.utils.render.a.a(lf21.utils.render.a.a((int)(60.0f + 30.0f * f4), (int)(60.0f + 30.0f * f4), (int)(60.0f + 30.0f * f4)), f2));
        v_0.m.d(poseStack, (String)((ModeSetting)this.g).getValue(), this.a + 60.0f, f7 + (f9 - v_0.m.c()) / 2.0f, lf21.utils.render.a.a(-1, f2 * 0.8f));
        String string = String.valueOf('\ueb5d');
        v_0.g.d(poseStack, string, this.a + 6.0f + f8 - v_0.g.b(string) + 2.0f, f7 + (f9 - v_0.g.c()) / 2.0f + 0.5f, lf21.utils.render.a.a(-1, f2 * 0.8f));
        if (this.o != null && this.n) {
            this.f.a(lf21.gui.f.b(this.o));
        } else if (lf21.utils.misc.c.a(n2, n3, this.a, this.b, 120.0f, this.f())) {
            this.f.a(lf21.gui.f.a(((ModeSetting)this.g).getName()));
        }
    }

    @Override
    public float f() {
        return 36 + (this.n ? 14 * ((ModeSetting)this.g).getModes().length : 0);
    }

    @Override
    public float b() {
        return 36.0f + (float)(14 * ((ModeSetting)this.g).getModes().length) * this.j.e();
    }

    @Override
    public boolean a(double d2, double d3, int n2) {
        if (!((ModeSetting)this.g).getVisibility().displayable()) {
            return false;
        }
        if (this.m) {
            this.n = !this.n;
            return true;
        }
        if (this.o != null && this.n) {
            ((ModeSetting)this.g).setValue(this.o);
            this.n = false;
            this.o = null;
            return true;
        }
        return false;
    }
}

