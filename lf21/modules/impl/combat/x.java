/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

final class x
extends Record {
    private final float g;
    private final double c;
    private final int i;
    private final float h;
    private final float e;
    private final int f;
    private final int a;
    private final double d;
    private final String b;

    public int a() {
        return this.a;
    }

    private x(int n2, String string, double d2, double d3, float f2, int n3, float f3, float f4, int n4) {
        this.a = n2;
        this.b = string;
        this.c = d2;
        this.d = d3;
        this.e = f2;
        this.f = n3;
        this.g = f3;
        this.h = f4;
        this.i = n4;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{x.class, "a;b;c;d;e;f;g;h;i", "a", "b", "c", "d", "e", "f", "g", "h", "i"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{x.class, "a;b;c;d;e;f;g;h;i", "a", "b", "c", "d", "e", "f", "g", "h", "i"}, this, object);
    }

    public int i() {
        return this.i;
    }

    public float e() {
        return this.e;
    }

    public double c() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public int f() {
        return this.f;
    }

    public float h() {
        return this.h;
    }

    public double d() {
        return this.d;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{x.class, "a;b;c;d;e;f;g;h;i", "a", "b", "c", "d", "e", "f", "g", "h", "i"}, this);
    }

    public float g() {
        return this.g;
    }
}

