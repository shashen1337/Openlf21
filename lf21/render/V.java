/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import lf21.render.W;

public final class V
extends Record {
    private final float a;
    private final float b;
    private final float d;
    private final float c;
    private final W f;
    private final float e;

    public float b() {
        return this.b;
    }

    public V(float f2, float f3, float f4, float f5, float f6, W w2) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
        this.e = f6;
        this.f = w2;
    }

    public float c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }

    public float e() {
        return this.e;
    }

    public float a() {
        return this.a;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{V.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this, object);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{V.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }

    public W f() {
        return this.f;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{V.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }
}

