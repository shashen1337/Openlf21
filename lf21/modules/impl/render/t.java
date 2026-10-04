/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

final class t
extends Record {
    private final int c;
    private final BlockPos a;
    private final Block b;

    public BlockPos a() {
        return this.a;
    }

    public Block b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{t.class, "a;b;c", "a", "b", "c"}, this);
    }

    private t(BlockPos blockPos, Block block, int n2) {
        this.a = blockPos;
        this.b = block;
        this.c = n2;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{t.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{t.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public int c() {
        return this.c;
    }
}

