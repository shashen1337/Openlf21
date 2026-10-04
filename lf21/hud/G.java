/*
 * Decompiled with CFR 0.152.
 */
package lf21.hud;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import net.minecraft.world.entity.LivingEntity;

final class G
extends Record {
    private final float c;
    private final float e;
    private final float d;
    private final float b;
    private final LivingEntity a;

    @Override
    public final boolean equals(Object object) {
        return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{G.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this, object);
    }

    public float c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }

    @Override
    public final int hashCode() {
        return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{G.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public float b() {
        return this.b;
    }

    @Override
    public final String toString() {
        return ObjectMethods.bootstrap("toString", new MethodHandle[]{G.class, "a;b;c;d;e", "a", "b", "c", "d", "e"}, this);
    }

    public LivingEntity a() {
        return this.a;
    }

    public float e() {
        return this.e;
    }

    private G(LivingEntity livingEntity, float f2, float f3, float f4, float f5) {
        this.a = livingEntity;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
    }
}

