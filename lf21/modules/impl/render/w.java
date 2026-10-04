/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

public record w<A, B>(A a, B b) {
    public static <A, B> w<A, B> a(A a2, B b2) {
        return new w<A, B>(a2, b2);
    }
}

