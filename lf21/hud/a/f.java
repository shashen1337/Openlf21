/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud.a;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import lf21.event.impl.Render2DEvent;
import lf21.hud.E;
import lf21.hud.HudElement;
import lf21.hud.a.a;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Renderer;
import lf21.render.t_0;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.a.e;
import lf21.utils.render.RenderUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;

public class f
extends a {
    private static final Color g;
    private final u_0 h;
    private int l;
    private final D n;
    private long u = 0L;
    private boolean s = false;
    private static final Color c;
    private LivingEntity k;
    private static final Color d;
    private static final Color e;
    private static final Color f;
    private final e r;
    private final e p;
    private final e j = new e();
    private final D o;
    private final u_0 i;
    private LivingEntity t;
    private final e q;
    private static final String v;
    private final D m = new D();

    public f() {
        super(v);
        this.n = new D();
        this.o = new D();
        this.h = t_0.b(15.0f);
        this.i = t_0.d(14.0f);
        this.j.a(1.0);
        this.p = new e();
        this.p.a(0.0);
        this.q = new e();
        this.q.a(5.0);
        this.r = new e();
        this.r.a(0.0);
    }

    @Override
    public void a(Render2DEvent render2DEvent, LivingEntity livingEntity, e e2, e e3, float f2, float f3, float f4) {
        float f5;
        float f6 = 120.0f;
        float f7 = 38.0f;
        float f8 = 3.0f;
        float f9 = 5.0f;
        boolean bl = livingEntity != null;
        long l2 = System.currentTimeMillis();
        boolean bl2 = false;
        if (bl) {
            this.u = l2;
            if (this.t != livingEntity) {
                this.t = livingEntity;
                this.k = livingEntity;
                bl2 = true;
            }
        }
        boolean bl3 = bl || l2 - this.u < 300L;
        boolean bl4 = bl3;
        if (bl3 != this.s || bl3 && this.p.e() <= 0.01f) {
            this.s = bl3;
            if (this.s) {
                this.p.a(1.0, 0.35, lf21.utils.b.f.e);
                this.q.a(5.0);
                this.q.a(0L);
                this.r.a(0.0);
                this.r.a(0L);
                this.j.a(1.0);
                this.j.a(1.0, 0.0);
            } else {
                this.p.a(0.0, 0.15, lf21.utils.b.f.d);
                this.q.a(5.0, 0.15, lf21.utils.b.f.d);
                this.r.a(0.0, 0.15, lf21.utils.b.f.d);
            }
        } else if (bl2 && this.s) {
            this.p.a(1.0, 0.35, lf21.utils.b.f.e);
            this.q.a(5.0);
            this.q.a(0L);
            this.r.a(0.0);
            this.r.a(0L);
            this.j.a(1.0);
            this.j.a(1.0, 0.0);
        }
        this.p.a();
        if (this.p.b() && this.s) {
            if (this.p.d() >= 0.08 && this.q.g() == 0L) {
                this.q.a(0.0, 0.3, lf21.utils.b.f.e);
            }
            if (this.p.d() >= 0.15 && this.r.g() == 0L) {
                this.r.a(1.0, 0.4, lf21.utils.b.f.e);
            }
        }
        if (this.q.g() != 0L) {
            this.q.a();
        }
        if (this.r.g() != 0L) {
            this.r.a();
        }
        float f10 = this.p.e();
        if (f5 <= 0.01f) {
            return;
        }
        LivingEntity livingEntity2 = this.t;
        if (livingEntity2 == null) {
            return;
        }
        float f11 = 30.0f;
        float f12 = 4.0f;
        float f13 = f3 + 4.0f + 30.0f + 4.0f;
        float f14 = 120.0f - (f13 - f3) - 3.0f;
        float f15 = f4 + 3.0f + 2.0f;
        float f16 = y_0.a(this.h);
        float f17 = f15 + f16 + 4.0f;
        PoseStack poseStack = render2DEvent.b().pose();
        poseStack.pushPose();
        RenderUtil.a(poseStack, f3, f4, 120.0f, 38.0f, 5.0f, 15.0f, 0.95f * f10, 0);
        poseStack.popPose();
        Renderer.a((DrawContext drawContext) -> {
            this.m.a(new Color(0, 0, 0, (int)((float)c.getAlpha() * f10)).getRGB());
            y_0.a(f3, f4, 120.0f, 38.0f, 5.0f, this.m);
            if (bl && livingEntity.hurtTime > this.l) {
                this.j.a(0.7f);
                this.j.a(1.0, 1.5, lf21.utils.b.f.i);
            }
            if (bl) {
                this.l = livingEntity.hurtTime;
            }
            this.j.a();
            float f9 = this.j.e();
            float f10 = (float)Math.max(0.7, (double)f10);
            float f11 = f9 * f10;
            float f12 = 30.0f * f11;
            float f13 = f3 + 4.0f + (30.0f - f12) / 2.0f;
            float f14 = f4 + (38.0f - f12) / 2.0f;
            if (livingEntity2 instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)livingEntity2;
                y_0.a(abstractClientPlayer, f13, f14, f12, f12, f10, 5.0f * f11);
            }
            float f15 = this.q.e();
            String string = livingEntity2.getName().getString();
            String string2 = "HP: " + (int)Math.ceil(livingEntity2.getHealth());
            y_0.b(string, f13, f15 + 1.0f + f15, this.h, new Color(1.0f, 1.0f, 1.0f, f10).getRGB());
            y_0.b(string2, f13, f15 + 14.0f + f15, this.i, new Color(1.0f, 1.0f, 1.0f, Math.min(f10, 80.0f)).getRGB());
            float f16 = f17 + 17.0f;
            float f17 = 3.0f;
            float f18 = f14 - 2.0f;
            this.n.a(new Color(0, 0, 0, (int)((float)d.getAlpha() * f10)).getRGB());
            E e4 = HudElement.a(f13, f16, f18, f17);
            y_0.a(e4.a(), e4.b(), e4.c(), e4.d(), e4.d() * 0.5f, this.n);
            this.o.a(new Color(191, 64, 78, (int)((float)g.getAlpha() * f10)).getRGB());
            float f19 = e3.e() * e4.c();
            y_0.a(e4.a(), e4.b(), f19, e4.d(), e4.d() * 0.5f, this.o);
            float f20 = this.r.e();
            float f21 = e2.e() * e4.c() * f20;
            Color color = new Color(e.getRed(), e.getGreen(), e.getBlue(), (int)(255.0f * f10));
            Color color2 = new Color(f.getRed(), f.getGreen(), f.getBlue(), (int)(255.0f * f10));
            y_0.a(e4.a(), e4.b(), f21, e4.d(), e4.d() * 0.5f, color, color2);
        });
        float f18 = f13;
        float f19 = 0.8f;
        float f20 = 16.0f * f19;
        float f21 = 2.0f;
    }

    static {
        v = "Simple";
        c = new Color(0, 0, 0, 70);
        d = new Color(0, 0, 0, 90);
        e = new Color(255, 255, 255);
        f = new Color(255, 255, 255);
        g = new Color(150, 30, 30, 120);
    }
}

