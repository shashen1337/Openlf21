/*
 * Decompiled with CFR 0.152.
 */
package lf21.utils.misc;

public record m<A, B, C>(A a, B b, C c) {
    private final A a;
    private final B b;
    private final C c;

    public boolean a() {
        C c2 = this.c;
        if (c2 instanceof Boolean) {
            Boolean bl = (Boolean)c2;
            return bl;
        }
        return false;
    }

    public A b() {
        return this.a;
    }

    public B c() {
        return this.b;
    }

    public C d() {
        return this.c;
    }
}

