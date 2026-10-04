/*
 * Decompiled with CFR 0.152.
 */
package lf21.render;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

record Z(float c, float e, float d, float b, float a) {
    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{Z.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this, object);
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{Z.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{Z.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    private Z(float f2, float f3, float f4, float f5, float f6) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
        this.e = f6;
    }
}

