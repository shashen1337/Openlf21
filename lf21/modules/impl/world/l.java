/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.world;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

final class l
extends Record {
    private final double a;
    private final float b;
    private final BlockPos d;
    private final float c;
    private final BlockPos f;
    private final Direction e;

    public float b() {
        return this.b;
    }

    private l(double d2, float f2, float f3, BlockPos blockPos, Direction direction, BlockPos blockPos2) {
        this.a = d2;
        this.b = f2;
        this.c = f3;
        this.d = blockPos;
        this.e = direction;
        this.f = blockPos2;
    }

    public float c() {
        return this.c;
    }

    public BlockPos d() {
        return this.d;
    }

    public Direction e() {
        return this.e;
    }

    public double a() {
        return this.a;
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{l.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this, object);
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{l.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }

    public BlockPos f() {
        return this.f;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{l.class, "a;b;c;d;e;f", "a", "b", "c", "d", "e", "f"}, this);
    }
}

