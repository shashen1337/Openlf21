/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.a;

import java.util.LinkedList;
import java.util.Optional;
import lf21.utils.a.c;
import lf21.utils.a.d;
import lf21.utils.b.f;

public class b {
    private final LinkedList<d> a = new LinkedList();
    private double b = 1.0;
    private lf21.utils.b.d c = f.b;
    private boolean d = false;

    public d a(String string) {
        d d3;
        Optional<d> optional = this.a.stream().filter(d2 -> d2.n().equalsIgnoreCase(string)).findFirst();
        if (!optional.isPresent()) {
            d3 = new d(string, this);
            this.a.add(d3);
        } else {
            d3 = optional.get();
        }
        return d3;
    }

    public b a(String string, double d2, double d3) {
        return this.a(string, d2, d3, f.b);
    }

    public b a(String string, double d2, double d3, lf21.utils.b.d d4) {
        return this.a(string, d2, d3, d4, false);
    }

    private b a(String string, double d2, double d3, lf21.utils.b.d d4, boolean bl) {
        this.a(d3);
        this.a(d4);
        this.a(bl);
        this.a(string).a(d2);
        return this;
    }

    public b a(double d2, Runnable runnable) {
        return this.a(d2, this.c, runnable);
    }

    public b a(double d2, lf21.utils.b.d d3, Runnable runnable) {
        return this.a(d2, d3, false, runnable);
    }

    private b a(double d2, lf21.utils.b.d d3, boolean bl, Runnable runnable) {
        this.a(d2);
        this.a(d3);
        this.a(bl);
        runnable.run();
        return this;
    }

    public boolean a() {
        this.c().forEach(c::a);
        return this.c().stream().anyMatch(c::b);
    }

    public void a(double d2) {
        this.b = d2;
    }

    public void a(boolean bl) {
        this.d = bl;
    }

    public void a(lf21.utils.b.d d2) {
        this.c = d2;
    }

    public double b() {
        return this.b;
    }

    public LinkedList<d> c() {
        return this.a;
    }

    public lf21.utils.b.d d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }
}

