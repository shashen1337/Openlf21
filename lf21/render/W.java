/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import lf21.render.z_0;

final class W
extends Record {
    private final int a;
    private final int b;
    private final int d;
    private final int c;
    private final z_0 f;
    private final char e;

    public int b() {
        return this.b;
    }

    W(int n2, int n3, int n4, int n5, char c2, z_0 z_02) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
        this.d = n5;
        this.e = c2;
        this.f = z_02;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public char e() {
        return this.e;
    }

    public int a() {
        return this.a;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{W.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this, object);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{W.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }

    public z_0 f() {
        return this.f;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{W.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }
}

