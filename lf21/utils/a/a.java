/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import lf21.utils.a.e;
import lf21.utils.b.d;
import lf21.utils.b.f;
import lombok.Generated;

public class a {
    private int a;
    private e b = new e();
    private e c = new e();
    private e d = new e();
    private e e = new e();

    public a(int n2) {
        this.a = n2;
    }

    public void a(int n2, float f2) {
        this.a(n2, f2, f.b);
    }

    public void a(int n2, float f2, d d2) {
        this.b.a((double)lf21.utils.render.a.b(n2), 0.2 / (double)f2, d2);
        this.c.a((double)lf21.utils.render.a.c(n2), 0.2 / (double)f2, d2);
        this.d.a((double)lf21.utils.render.a.d(n2), 0.2 / (double)f2, d2);
        this.e.a((double)lf21.utils.render.a.a(n2), 0.2 / (double)f2, d2);
        this.b.a();
        this.c.a();
        this.d.a();
        this.e.a();
        this.a = lf21.utils.render.a.a(this.b.f(), this.c.f(), this.d.f(), this.e.f());
    }

    @Generated
    public int a() {
        return this.a;
    }

    @Generated
    public void a(int n2) {
        this.a = n2;
    }

    @Generated
    public e b() {
        return this.b;
    }

    @Generated
    public void a(e e2) {
        this.b = e2;
    }

    @Generated
    public e c() {
        return this.c;
    }

    @Generated
    public void b(e e2) {
        this.c = e2;
    }

    @Generated
    public e d() {
        return this.d;
    }

    @Generated
    public void c(e e2) {
        this.d = e2;
    }

    @Generated
    public e e() {
        return this.e;
    }

    @Generated
    public void d(e e2) {
        this.e = e2;
    }
}

