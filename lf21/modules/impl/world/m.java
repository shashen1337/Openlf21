/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

final class m
extends Record {
    private final float c;
    private final double a;
    private final float b;

    public double a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{m.class, "a;b;c", "a", "b", "c"}, this);
    }

    private m(double d2, float f2, float f3) {
        this.a = d2;
        this.b = f2;
        this.c = f3;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{m.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{m.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public float c() {
        return this.c;
    }
}

