/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import lf21.utils.b.d;
import lombok.Generated;

public abstract class c {
    private double a;
    private long b;
    private double c;
    private double d;
    private double e;
    private d f;
    private boolean g;

    public boolean a() {
        boolean bl = this.b();
        if (bl) {
            this.a = this.a(this.i(), this.j(), this.k().a(this.d()));
        } else {
            this.a(0L);
            this.a = this.j();
        }
        return bl;
    }

    public boolean b() {
        return !this.c();
    }

    public boolean c() {
        return this.d() >= 1.0;
    }

    public double d() {
        return (double)(System.currentTimeMillis() - this.g()) / this.h();
    }

    public double a(double d2, double d3, double d4) {
        return d2 + (d3 - d2) * d4;
    }

    public float e() {
        return (float)this.a;
    }

    public int f() {
        return (int)this.a;
    }

    public long g() {
        return this.b;
    }

    public double h() {
        return this.c;
    }

    public double i() {
        return this.d;
    }

    public double j() {
        return this.e;
    }

    public d k() {
        return this.f;
    }

    public boolean l() {
        return this.g;
    }

    @Generated
    public void a(double d2) {
        this.a = d2;
    }

    @Generated
    public void a(long l2) {
        this.b = l2;
    }

    @Generated
    public void b(double d2) {
        this.c = d2;
    }

    @Generated
    public void c(double d2) {
        this.d = d2;
    }

    @Generated
    public void d(double d2) {
        this.e = d2;
    }

    @Generated
    public void a(d d2) {
        this.f = d2;
    }

    @Generated
    public void a(boolean bl) {
        this.g = bl;
    }
}

