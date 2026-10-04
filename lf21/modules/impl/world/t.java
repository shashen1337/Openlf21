/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import lf21.modules.impl.world.i;

final class t
extends Record {
    private final double c;
    private final File a;
    private final i b;

    public File a() {
        return this.a;
    }

    public i b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{t.class, "a;b;c", "a", "b", "c"}, this);
    }

    private t(File file, i i2, double d2) {
        this.a = file;
        this.b = i2;
        this.c = d2;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{t.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{t.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public double c() {
        return this.c;
    }
}

