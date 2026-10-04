/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

final class o
extends Record {
    private final int c;
    private final int e;
    private final int d;
    private final int b;
    private final double a;

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{o.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this, object);
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{o.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public int b() {
        return this.b;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{o.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public double a() {
        return this.a;
    }

    public int e() {
        return this.e;
    }

    private o(double d2, int n2, int n3, int n4, int n5) {
        this.a = d2;
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
    }
}

