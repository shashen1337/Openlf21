/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

record Y(float a, float b, float d, float c, float f, float e) {
    private Y(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
        this.e = f6;
        this.f = f7;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{Y.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this, object);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{Y.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{Y.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }
}

