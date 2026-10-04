/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.combat.a;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

public final class f
extends Record {
    private final boolean c;
    private final float a;
    private final float b;

    public float a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{f.class, "a;b;c", "a", "b", "c"}, this);
    }

    public f(float f2, float f3, boolean bl) {
        this.a = f2;
        this.b = f3;
        this.c = bl;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{f.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{f.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public boolean c() {
        return this.c;
    }
}

