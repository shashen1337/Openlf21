/*
 * Decompiled with CFR 0.152.
 */
package lf21.modules.impl.render;

import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

public final class n {
    private final String a;
    private final double b;
    private final long d;
    private final double c;
    private final Vec3 f;
    private final Item e;

    public long d() {
        return this.d;
    }

    public Item e() {
        return this.e;
    }

    public double b() {
        return this.b;
    }

    public n(String string, double d2, double d3, long l2, Item item, Vec3 vec3) {
        this.a = string;
        this.b = d2;
        this.c = d3;
        this.d = l2;
        this.e = item;
        this.f = vec3;
    }

    public String a() {
        return this.a;
    }

    public double c() {
        return this.c;
    }

    public Vec3 f() {
        return this.f;
    }
}

