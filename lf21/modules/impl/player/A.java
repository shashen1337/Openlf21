/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.core.BlockPos;

final class A
extends Record {
    private final BlockPos c;
    private final int a;
    private final BlockPos b;

    public int a() {
        return this.a;
    }

    public BlockPos b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{A.class, "a;b;c", "a", "b", "c"}, this);
    }

    private A(int n2, BlockPos blockPos, BlockPos blockPos2) {
        this.a = n2;
        this.b = blockPos;
        this.c = blockPos2;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{A.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{A.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public BlockPos c() {
        return this.c;
    }
}

