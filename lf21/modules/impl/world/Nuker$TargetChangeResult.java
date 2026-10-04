/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;

public final class Nuker$TargetChangeResult
extends Record {
    private final boolean a;
    private final String b;

    public Nuker$TargetChangeResult(boolean bl, String string) {
        this.a = bl;
        this.b = string;
    }

    public boolean success() {
        return this.a;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{Nuker$TargetChangeResult.class, "a;b", "a", "b"}, this);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{Nuker$TargetChangeResult.class, "a;b", "a", "b"}, this);
    }

    public String message() {
        return this.b;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{Nuker$TargetChangeResult.class, "a;b", "a", "b"}, this, object);
    }
}

