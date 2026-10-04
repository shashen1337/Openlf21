/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import lf21.render.E;
import lf21.render.F;
import lf21.render.aa;
import lf21.render.h_0;
import lf21.render.i_0;

public class D
implements AutoCloseable {
    private F g;
    private i_0 c;
    private boolean i = true;
    private Object h;
    private float e = 0.0f;
    private E f;
    private int a = -1;
    private float d = 1.0f;
    private h_0 b = h_0.FILL;

    public D a(F f2) {
        this.g = f2;
        return this;
    }

    public D b(float f2) {
        this.d = f2;
        return this;
    }

    public D a(float[] fArray) {
        int n2 = (int)Math.max(0.0f, Math.min(255.0f, fArray[3] * 255.0f));
        int n3 = (int)Math.max(0.0f, Math.min(255.0f, fArray[0] * 255.0f));
        int n4 = (int)Math.max(0.0f, Math.min(255.0f, fArray[1] * 255.0f));
        int n5 = (int)Math.max(0.0f, Math.min(255.0f, fArray[2] * 255.0f));
        this.a = n2 << 24 | n3 << 16 | n4 << 8 | n5;
        return this;
    }

    public D a(E e2) {
        this.f = e2;
        return this;
    }

    public Object h() {
        return this.h;
    }

    public E f() {
        return this.f;
    }

    public i_0 c() {
        return this.c;
    }

    public D a(boolean bl) {
        this.i = bl;
        return this;
    }

    public h_0 b() {
        return this.b;
    }

    public D a(int n2) {
        this.a = n2;
        return this;
    }

    @Override
    public void close() {
    }

    public int a() {
        return this.a;
    }

    public F g() {
        return this.g;
    }

    public D a(h_0 h_02) {
        this.b = h_02;
        return this;
    }

    public float d() {
        return this.d;
    }

    public D a(int n2, int n3, int n4, int n5) {
        this.a = (n2 & 0xFF) << 24 | (n3 & 0xFF) << 16 | (n4 & 0xFF) << 8 | n5 & 0xFF;
        return this;
    }

    public boolean i() {
        return this.i;
    }

    public D a(Object object) {
        if (object instanceof aa) {
            this.e = ((aa)object).a;
        } else if (object == null) {
            this.e = 0.0f;
        }
        return this;
    }

    public float e() {
        return this.e;
    }

    public D j() {
        D d2 = new D();
        d2.a = this.a;
        d2.b = h_0.FILL;
        d2.c = this.c;
        d2.d = this.d;
        d2.e = this.e;
        d2.f = this.f;
        d2.g = this.g;
        d2.h = this.h;
        d2.i = this.i;
        return d2;
    }

    public D a(i_0 i_02) {
        this.c = i_02;
        return this;
    }

    public D a(float f2) {
        int n2 = (int)Math.max(0.0f, Math.min(255.0f, f2 * 255.0f));
        this.a = this.a & 0xFFFFFF | n2 << 24;
        return this;
    }

    public D b(Object object) {
        this.h = object;
        return this;
    }

    public D() {
        this.c = i_0.BUTT;
    }
}

