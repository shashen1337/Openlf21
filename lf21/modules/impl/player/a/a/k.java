/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.player.a.a;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public final class k
extends Record {
    private final Vec3 c;
    private final BlockPos a;
    private final Direction b;

    public BlockPos a() {
        return this.a;
    }

    public Direction b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{k.class, "a;b;c", "a", "b", "c"}, this);
    }

    public k(BlockPos blockPos, Direction direction, Vec3 vec3) {
        this.a = blockPos;
        this.b = direction;
        this.c = vec3;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{k.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{k.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public Vec3 c() {
        return this.c;
    }
}

