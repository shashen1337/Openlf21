/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

public final class r
extends Record {
    private final int c;
    private final boolean e;
    private final boolean d;
    private final int b;
    private final String a;

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{r.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this, object);
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{r.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public int b() {
        return this.b;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{r.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public String a() {
        return this.a;
    }

    public boolean e() {
        return this.e;
    }

    public r(String string, int n2, int n3, boolean bl, boolean bl2) {
        this.a = string;
        this.b = n2;
        this.c = n3;
        this.d = bl;
        this.e = bl2;
    }
}

