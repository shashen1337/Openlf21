/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import lf21.hud.n;
import lf21.render.y_0;
import lf21.utils.a.e;
import lf21.utils.b.f;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

public final class B {
    public String f;
    public final n a;
    public final e g = new e();
    public final e h = new e();
    public boolean k = true;
    public final e i = new e();
    public String e;
    public String d;
    public MobEffectInstance b;
    public boolean j = false;
    public long c;

    public float d() {
        float f2 = 22.0f;
        float f3 = 5.0f;
        float f4 = y_0.a(this.d, this.a.J);
        float f5 = y_0.a(this.e, this.a.L);
        return f2 + f4 + f5 + f3 * 3.0f;
    }

    public B(n n2, MobEffectInstance mobEffectInstance) {
        this.a = n2;
        this.b = mobEffectInstance;
        this.c = mobEffectInstance.getDuration();
        this.c();
        this.h.a(0.0);
        this.i.a(0.0);
    }

    public void a(float f2) {
        this.h.a(1.0, 0.3, lf21.utils.b.f.e);
        this.i.a((double)f2, 0.3, lf21.utils.b.f.e);
    }

    public void e() {
        if (this.j) {
            return;
        }
        this.j = true;
        this.h.a(0.0, 0.2, lf21.utils.b.f.d);
        this.i.a(0.0, 0.2, lf21.utils.b.f.d);
    }

    public boolean f() {
        return this.j && this.h.c() && this.i.c();
    }

    public void c() {
        this.d = this.b.getEffect().getDisplayName().getString();
        this.e = this.a.a(this.b);
        this.f = this.a.a(this.b.getAmplifier() + 1);
    }

    public void a(MobEffectInstance mobEffectInstance) {
        if (mobEffectInstance.getDuration() > this.b.getDuration()) {
            this.c = mobEffectInstance.getDuration();
        }
        this.b = mobEffectInstance;
        this.c();
    }

    public int a() {
        return this.b.getDuration();
    }

    public void g() {
        this.g.a();
        this.h.a();
        this.i.a();
        if (!this.b.getEffect().isInstantenous()) {
            this.c();
        }
    }

    public MobEffect b() {
        return this.b.getEffect();
    }
}

