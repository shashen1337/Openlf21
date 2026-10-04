/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import lf21.ClientBase;
import lf21.event.impl.Render2DEvent;
import lf21.hud.F;
import lf21.hud.b_0;
import lf21.hud.c_0;
import lf21.hud.d_0;
import lf21.hud.e_0;
import lf21.hud.f_0;
import lf21.hud.r;
import lf21.hud.v;
import lf21.render.D;
import lf21.render.DrawContext;
import lf21.render.Q;
import lf21.render.Renderer;
import lf21.utils.a.f;
import lf21.utils.render.RenderUtil;
import net.minecraft.util.Mth;

public class q {
    private v f = null;
    final List<v> a = Arrays.asList(new e_0(), new d_0(), new c_0(), new f_0());
    private v g = null;
    private final long h = 0L;
    private float k = 28.0f;
    private long i = 0L;
    private final f e;
    private final f d;
    private final r b = new r(this);
    private float j = 200.0f;
    private final f c = new f(300.0f, 1.2f, 20.0f, 170.0f);

    public q() {
        this.d = new f(300.0f, 1.2f, 20.0f, 18.0f);
        this.e = new f(250.0f, 1.0f, 22.0f, 1.0f);
    }

    public void a(Render2DEvent render2DEvent, float f2, float f3) {
        float f4;
        float f5;
        float f6;
        F f7;
        if (ClientBase.mc.player == null) {
            return;
        }
        long l2 = System.currentTimeMillis();
        if (this.i == 0L) {
            this.i = l2;
        }
        float f8 = (float)(l2 - this.i) / 1000.0f;
        this.i = l2;
        f8 = Math.min(f8, 0.033333335f);
        v v2 = this.b.a();
        if (this.f != v2) {
            this.g = this.f;
            this.f = v2;
            this.e.a(0.0f);
            this.e.d(1.0f);
            if (this.g == null) {
                f7 = this.f.d();
                this.c.a(f7.a());
                this.d.a(f7.b());
                this.e.a(1.0f);
            }
        }
        f7 = this.f.d();
        float f9 = f7.a();
        float f10 = f7.b();
        float f11 = this.e.a();
        if (this.g != null && f11 < 1.0f) {
            F f12 = this.g.d();
            f9 = Mth.lerp((float)f11, (float)f12.a(), (float)f7.a());
            f10 = Mth.lerp((float)f11, (float)f12.b(), (float)f7.b());
        }
        this.c.d(f9);
        this.d.d(f10);
        this.c.b(f8);
        this.d.b(f8);
        this.e.b(f8);
        float f13 = Math.max(0.0f, this.c.a() + 30.0f);
        float f14 = Math.max(0.0f, this.d.a() + 3.0f);
        float f15 = f2;
        this.j = f13;
        this.k = f14;
        float f16 = 25.0f;
        float f17 = f3;
        float f18 = f17 + f16 / 2.0f;
        float f19 = f6 = this.f.a() == b_0.CENTER ? f18 - f14 / 2.0f : f17;
        if (this.g != null && f11 < 1.0f) {
            f5 = this.g.a() == b_0.CENTER ? f18 - f14 / 2.0f : f17;
            f4 = Mth.lerp((float)f11, (float)f5, (float)f6);
        } else {
            f4 = f6;
        }
        f5 = 12.0f;
        float f20 = f4;
        float f21 = f5;
        if (this.f.b()) {
            Renderer.a(drawContext -> {
                try (D d2 = new D();){
                    d2.a(new Color(0, 0, 0, 40).getRGB());
                    drawContext.a(Q.a(f15, f20, f13, f14, f21), d2);
                }
                drawContext.d();
                drawContext.a(Q.a(f15, f20, f13, f14, f21), true);
                this.f.a((DrawContext)drawContext, f15, f20, f13, f14, f11);
                drawContext.e();
            });
        }
        RenderUtil.a((int)f15, (int)f4, (int)f13, (int)f14);
        this.f.a(render2DEvent.b(), render2DEvent.a(), f15, f4, f13, f14, f11);
        RenderUtil.d();
        if (f11 >= 1.0f) {
            this.g = null;
        }
    }

    public float b() {
        return this.k;
    }

    public float a() {
        return this.j;
    }
}

