/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.world.item.ItemStack;

final class x
extends Record {
    private final boolean c;
    private final ItemStack a;
    private final int b;

    public ItemStack a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{x.class, "a;b;c", "a", "b", "c"}, this);
    }

    private x(ItemStack itemStack, int n2, boolean bl) {
        this.a = itemStack;
        this.b = n2;
        this.c = bl;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{x.class, "a;b;c", "a", "b", "c"}, this);
    }

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{x.class, "a;b;c", "a", "b", "c"}, this, object);
    }

    public boolean c() {
        return this.c;
    }
}

