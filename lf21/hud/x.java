/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.util.Objects;
import lf21.hud.i;
import lf21.hud.w;
import lf21.modules.Module;
import lf21.render.u_0;
import lf21.render.y_0;
import lf21.utils.a.e;
import lf21.utils.b.f;

public final class x {
    public final Module b;
    public float o;
    private String r;
    private u_0 q;
    private float p;
    public float l;
    public float n;
    public final e x;
    public final i a;
    public String c;
    public boolean h = false;
    public final e w;
    public String d;
    private float s;
    public final boolean g;
    public final e v;
    public float m;
    public boolean e;
    private float t;
    public boolean j = false;
    public boolean i = false;
    public boolean f = true;
    public final e u = new e();
    public float k;

    public void b() {
        if (!this.j) {
            return;
        }
        this.j = false;
        this.h = false;
        this.i = true;
    }

    public boolean e() {
        return this.j && this.w.c() && this.x.c();
    }

    public u_0 a(u_0 u_02, float f2) {
        if (this.d == null) {
            return u_02;
        }
        if (this.q != null && this.d.equals(this.r) && this.s == f2) {
            return this.q;
        }
        float f3 = y_0.a(this.d, u_02);
        u_0 u_03 = u_02;
        if (f3 > f2) {
            float f4 = f2 / f3;
            u_03 = new u_0(u_02.e(), u_02.f() * f4);
            this.t = y_0.a(this.d, u_03);
        } else {
            this.t = f3;
        }
        this.q = u_03;
        this.r = this.d;
        this.s = f2;
        return u_03;
    }

    public void a() {
        if (this.j) {
            return;
        }
        this.j = true;
        this.h = true;
        this.i = false;
        float f2 = 40.0f;
        this.u.a(this.g ? (double)f2 : (double)(-f2), 0.2, lf21.utils.b.f.d);
        this.w.a(0.0, 0.2, lf21.utils.b.f.e);
        this.x.a(0.0, 0.2, lf21.utils.b.f.e);
    }

    public float d() {
        return this.p;
    }

    public void a(w w2) {
        this.c = w2.a;
        String string = w2.b;
        if (!Objects.equals(this.d, string)) {
            this.q = null;
        }
        this.d = string;
        this.e = w2.c;
        this.p = y_0.a(this.c, this.a.K);
    }

    public void c() {
        this.u.a();
        this.v.a();
        this.w.a();
        this.x.a();
    }

    public float f() {
        return this.t;
    }

    public x(i i2, Module module, w w2) {
        this.v = new e();
        this.w = new e();
        this.x = new e();
        this.a = i2;
        this.b = module;
        this.a(w2);
        this.g = i2.af;
        this.u.a(this.g ? 20.0 : -20.0);
        this.w.a(0.0);
        this.x.a(0.0);
    }
}

