/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import lf21.modules.impl.world.i;

final class s
extends Record {
    private final File a;
    private final int e;
    private final double c;
    private final i b;
    private final int g;
    private final int f;
    private final int d;

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{s.class, "a;b;c;d;e;f;g", "a", "b", "c", "d", "e", "f", "g"}, this);
    }

    public double c() {
        return this.c;
    }

    public i b() {
        return this.b;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{s.class, "a;b;c;d;e;f;g", "a", "b", "c", "d", "e", "f", "g"}, this);
    }

    private s(File file, i i2, double d2, int n2, int n3, int n4, int n5) {
        this.a = file;
        this.b = i2;
        this.c = d2;
        this.d = n2;
        this.e = n3;
        this.f = n4;
        this.g = n5;
    }

    public int d() {
        return this.d;
    }

    public File a() {
        return this.a;
    }

    public int e() {
        return this.e;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{s.class, "a;b;c;d;e;f;g", "a", "b", "c", "d", "e", "f", "g"}, this, object);
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }
}

